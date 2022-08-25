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

package kuzminki.render

import zio._
import zio.blocking._
import zio.stream.{ZSink, ZTransducer}
import kuzminki.shape.ParamConv
import kuzminki.api.{db, Kuzminki}


trait RunOperation {

  def render: RenderedOperation

  def run = db.exec(render)

  def runNum = db.execNum(render)
}


trait RunOperationParams[P] {

  def render(params: P): RenderedOperation

  def run(params: P) = db.exec(render(params))

  def runNum(params: P) = db.execNum(render(params))
}


trait RunOperationAsSink[P] {

  def render(params: P): RenderedOperation

  def runList(paramList: Seq[P]) = db.execList(paramList.map(render(_)))

  def asSink = ZSink.foreach((params: P) => db.exec(render(params)))

  def collect(size: Int) = ZTransducer.collectAllN[P](size)

  def asChunkSink = ZSink.foreach { (chunk: Chunk[P]) =>
    db.execList(chunk.toList.map(p => render(p)))
  }
}
























