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

package kuzminki.delete

import zio._
import zio.stream.{ZSink, ZTransducer}
import kuzminki.api.db
import kuzminki.shape.{RowConv, ParamConv}
import kuzminki.run.RunQueryParams
import kuzminki.render.{
  RenderedOperation,
  RenderedQuery,
  JoinArgs
}


class StoredDelete[P](
  val statement: String,
  args: Vector[Any],
  paramConv: ParamConv[P]
) extends JoinArgs {

  def render(params: P) = RenderedOperation(
    statement,
    joinArgs(args, paramConv.fromShape(params))
  )

  def run(params: P) = db.exec(render(params))

  def runNum(params: P) = db.execNum(render(params))

  def runList(paramList: Seq[P]) = db.execList(paramList.map(render(_)))

  def asSink = ZSink.foreach((params: P) => db.exec(render(params)))

  def collect(size: Int) = ZTransducer.collectAllN[P](size)

  def asChunkSink = ZSink.foreach { (chunk: Chunk[P]) =>
    db.execList(chunk.toList.map(p => render(p)))
  }

  def printSql = {
    println(statement)
    this
  }
  
  def printSqlAndArgs(params: P) =
    render(params).printStatementAndArgs(this)
  
  def printSqlWithArgs(params: P) =
    render(params).printStatementWithArgs(this)
}


class StoredDeleteReturning[P, R](
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








