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


trait RunQuery[R] {

  def render: RenderedQuery[R]

  def run: RIO[Has[Kuzminki] with Blocking, List[R]] =
    db.query(render)

  def runAs[T](implicit transform: R => T): RIO[Has[Kuzminki] with Blocking, List[T]] =
    db.queryAs(render, transform)

  def runHead: RIO[Has[Kuzminki] with Blocking, R] =
    db.queryHead(render)

  def runHeadAs[T](implicit transform: R => T): RIO[Has[Kuzminki] with Blocking, T] =
    db.queryHeadAs(render, transform)

  def runHeadOpt: RIO[Has[Kuzminki] with Blocking, Option[R]] =
    db.queryHeadOpt(render)

  def runHeadOptAs[T](implicit transform: R => T): RIO[Has[Kuzminki] with Blocking, Option[T]] =
    db.queryHeadOptAs(render, transform)
}


trait RunQueryParams[P, R] {

  def render(params: P): RenderedQuery[R]

  def run(params: P): RIO[Has[Kuzminki] with Blocking, List[R]] =
    db.query(render(params))

  def runHead(params: P): RIO[Has[Kuzminki] with Blocking, R] =
    db.queryHead(render(params))

  def runHeadOpt(params: P): RIO[Has[Kuzminki] with Blocking, Option[R]] =
    db.queryHeadOpt(render(params))
}


















