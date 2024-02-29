/*
* Copyright 2021 Kári Magnússon
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package kuzminki.insert

import java.sql.SQLException
import kuzminki.api.{db, Kuzminki}
import kuzminki.shape.{ParamConv, RowConv}
import kuzminki.run.RunQueryParams
import kuzminki.render.{
  RenderedOperation,
  RenderedQuery,
  JoinArgs
}
import zio._
import zio.blocking._
import zio.clock.Clock
import zio.stream.{ZSink, ZTransducer}


class StoredInsert[P](
  val statement: String,
  args: Vector[Any],
  paramConv: ParamConv[P]
) extends JoinArgs {

  def render(params: P) = RenderedOperation(
    statement,
    joinArgs(args, paramConv.fromShape(params))
  )

  def run(params: P): ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, Unit] =
    db.exec(render(params))

  def runNum(params: P): ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, Int] =
    db.execNum(render(params))

  def runList(paramList: Seq[P]): ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, Unit] =
    db.execList(paramList.map(render(_)))

  def collect(size: Int): ZTransducer[Any, Nothing, P, Chunk[P]] =
    ZTransducer.collectAllN[P](size)

  def asSink: ZSink[Has[Kuzminki] with Blocking with Clock, SQLException, P, P, Unit] =
    ZSink.foreach((params: P) => db.exec(render(params)))

  def asChunkSink: ZSink[Has[Kuzminki] with Blocking with Clock, SQLException, Chunk[P], Chunk[P], Unit] = {
    ZSink.foreach { (chunk: Chunk[P]) =>
      db.execList(chunk.toList.map(p => render(p)))
    }
  }

  def printSql: StoredInsert[P] = {
    println(statement)
    this
  }
  
  def printSqlAndArgs(params: P): StoredInsert[P] =
    render(params).printStatementAndArgs(this)
  
  def printSqlWithArgs(params: P): StoredInsert[P] =
    render(params).printStatementWithArgs(this)
}


class StoredInsertReturning[P, R](
  val statement: String,
  args: Vector[Any],
  paramConv: ParamConv[P],
  rowConv: RowConv[R]
) extends RunQueryParams[P, R] {

  def render(params: P) = RenderedQuery(
    statement,
    joinArgs(args, paramConv.fromShape(params)),
    rowConv
  )
}






