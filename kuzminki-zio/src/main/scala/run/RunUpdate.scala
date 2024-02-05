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

package kuzminki.run

import zio._
import zio.stream.{ZSink, ZTransducer}
import kuzminki.api.db
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation,
  JoinArgs
}


trait RunUpdate[P1, P2] extends JoinArgs {

  val statement: String

  def render(p1: P1, p2: P2): RenderedOperation

  def run(p1: P1, p2: P2) =
    db.exec(render(p1, p2))

  def runNum(p1: P1, p2: P2) =
    db.execNum(render(p1, p2))

  def runList(list: Seq[Tuple2[P1, P2]]) =
    db.execList(list.map(p => render(p._1, p._2)))

  def asSink = ZSink.foreach { (p: Tuple2[P1, P2]) =>
    db.exec(render(p._1, p._2))
  }

  def collect(size: Int) =
    ZTransducer.collectAllN[Tuple2[P1, P2]](size)

  def asChunkSink = ZSink.foreach { (chunk: Chunk[Tuple2[P1, P2]]) =>
    db.execList(chunk.toList.map(p => render(p._1, p._2)))
  }

  def printSql = {
    println(statement)
    this
  }
    
  def printSqlAndArgs(p1: P1, p2: P2) =
    render(p1, p2).printStatementAndArgs(this)
    
  def printSqlWithArgs(p1: P1, p2: P2) =
    render(p1, p2).printStatementWithArgs(this)
}


trait RunUpdateReturning[P1, P2, R] extends JoinArgs {

  val statement: String

  def render(p1: P1, p2: P2): RenderedQuery[R]

  def run(p1: P1, p2: P2) =
    db.query(render(p1, p2))

  def runAs[T](p1: P1, p2: P2)(implicit transform: R => T) =
    db.queryAs(render(p1, p2), transform)

  def runHead(p1: P1, p2: P2) =
    db.queryHead(render(p1, p2))

  def runHeadAs[T](p1: P1, p2: P2)(implicit transform: R => T) =
    db.queryHeadAs(render(p1, p2), transform)

  def runHeadOpt(p1: P1, p2: P2) =
    db.queryHeadOpt(render(p1, p2))

  def runHeadOptAs[T](p1: P1, p2: P2)(implicit transform: R => T) =
    db.queryHeadOptAs(render(p1, p2), transform)

  def printSql = {
    println(statement)
    this
  }
    
  def printSqlAndArgs(p1: P1, p2: P2) =
    render(p1, p2).printStatementAndArgs(this)
    
  def printSqlWithArgs(p1: P1, p2: P2) =
    render(p1, p2).printStatementWithArgs(this)
}










