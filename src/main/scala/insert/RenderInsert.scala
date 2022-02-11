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

import zio._
import zio.blocking._
import kuzminki.api.{db, Kuzminki}
import kuzminki.shape.ParamConv
import kuzminki.shape.RowConv
import kuzminki.render.{
  RunQueryParams,
  RunOperation,
  RunOperationParams,
  RenderedQuery,
  RenderedOperation,
  SectionCollector
}


class RenderInsert[P](
    coll: SectionCollector,
    paramConv: ParamConv[P]
  ) extends RunOperationParams[P] {

  def cache = {
    new StoredInsert(
      coll.render,
      paramConv
    )
  }

  def render(params: P) = {
    RenderedOperation(
      coll.render,
      paramConv.fromShape(params)
    )
  }

  def debugSql(handler: String => Unit) = {
    handler(coll.render)
    this
  }
}


class RenderInsertReturning[P, R](
    coll: SectionCollector,
    paramConv: ParamConv[P],
    rowConv: RowConv[R]
  ) extends RunQueryParams[P, R] {

  def cache = {
    new StoredInsertReturning(
      coll.render,
      paramConv,
      rowConv
    )
  }

  def render(params: P) = {
    RenderedQuery(
      coll.render,
      paramConv.fromShape(params),
      rowConv
    )
  }

  def debugSql(handler: String => Unit) = {
    handler(coll.render)
    this
  }
}


class RenderInsertNoCache(
    coll: SectionCollector
  ) extends RunOperation {

  def render = {
    RenderedOperation(
      coll.render,
      coll.args
    )
  }

  def debugSql(handler: String => Unit) = {
    handler(coll.render)
    this
  }
}







