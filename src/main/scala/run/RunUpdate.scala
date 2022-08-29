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
import zio.blocking._
import zio.stream.{ZSink, ZTransducer}
import kuzminki.api.db
import kuzminki.render.RenderedOperation


trait RunUpdate[P1, P2] {

  def render(args1: P1, args2: P2): RenderedOperation

  def run(args1: P1, args2: P2) = db.exec(render(args1, args2))

  def runNum(args1: P1, args2: P2) = db.execNum(render(args1, args2))

  def runList(args: Seq[Tuple2[P1, P2]]) =
    db.execList(args.map(arg => render(arg._1, arg._2)))

  def asSink = ZSink.foreach { (arg: Tuple2[P1, P2]) =>
    db.exec(render(arg._1, arg._2))
  }

  def collect(size: Int) = ZTransducer.collectAllN[Tuple2[P1, P2]](size)

  def asChunkSink = ZSink.foreach { (chunk: Chunk[Tuple2[P1, P2]]) =>
    db.execList(chunk.toList.map(arg => render(arg._1, arg._2)))
  }
}
