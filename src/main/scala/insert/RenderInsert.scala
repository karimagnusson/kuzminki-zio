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

import kuzminki.shape.ParamConv
import kuzminki.shape.RowConv
import kuzminki.run.{RunOperation, RunQuery}
import kuzminki.render._


class RenderInsert(
    coll: SectionCollector
  ) extends RunOperation {

  def render = RenderedOperation(
    coll.render,
    coll.args
  )

  def cache = new StoredOperation(
    coll.render,
    coll.args
  )
}


class RenderInsertReturning[R](
  coll: SectionCollector,
  rowConv: RowConv[R]
) extends RunQuery[R] {

  def render = RenderedQuery(
    coll.render,
    coll.args,
    rowConv
  )

  def cache = new StoredQuery(
    coll.render,
    coll.args,
    rowConv
  )
}


class RenderStoredInsert[P](
  coll: SectionCollector,
  paramConv: ParamConv[P]
) {

  def cache = {
    new StoredInsert(
      coll.render,
      coll.args,
      paramConv
    )
  }
}


class RenderStoredInsertReturning[P, R](
  coll: SectionCollector,
  paramConv: ParamConv[P],
  rowConv: RowConv[R]
) {

  def cache = {
    new StoredInsertReturning(
      coll.render,
      coll.args,
      paramConv,
      rowConv
    )
  }
}













