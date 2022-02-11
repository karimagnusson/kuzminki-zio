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
import kuzminki.api.{db, Kuzminki}


trait RunOperation {

  def render: RenderedOperation

  def run: RIO[Has[Kuzminki] with Blocking, Unit] =
    db.exec(render)

  def runNum: RIO[Has[Kuzminki] with Blocking, Int] =
    db.execNum(render)
}


trait RunOperationParams[P] {

  def render(params: P): RenderedOperation

  def run(params: P): RIO[Has[Kuzminki] with Blocking, Unit] =
    db.exec(render(params))

  def runNum(params: P): RIO[Has[Kuzminki] with Blocking, Int] =
    db.execNum(render(params))
}

























