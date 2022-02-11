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

package kuzminki.select

import zio._
import zio.blocking._
import kuzminki.api.{db, Kuzminki}
import kuzminki.shape.ParamConv
import kuzminki.shape.RowConv
import kuzminki.render.RenderedQuery


class StoredSelectConditionAndOffset[P, R](
    statement: String,
    cacheArgs: Tuple3[Vector[Any], Vector[Any], Vector[Any]],
    paramConv: ParamConv[P],
    rowConv: RowConv[R]
  ) {

  private val (args1, args2, args3) = cacheArgs

  def render(params: P, offset: Int) = {
    RenderedQuery(
      statement,
      args1 ++ paramConv.fromShape(params) ++ args2 ++ Vector(offset) ++ args3,
      rowConv
    )
  }

  // run

  def run(params: P, offset: Int): RIO[Has[Kuzminki] with Blocking, List[R]] =
    db.query(render(params, offset))

  def runHead(params: P, offset: Int): RIO[Has[Kuzminki] with Blocking, R] =
    db.queryHead(render(params, offset))

  def runHeadOpt(params: P, offset: Int): RIO[Has[Kuzminki] with Blocking, Option[R]] =
    db.queryHeadOpt(render(params, offset))

  def debugSql(handler: String => Unit) = {
    handler(statement)
    this
  }
}





















