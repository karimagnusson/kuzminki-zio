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

package kuzminki.update

import kuzminki.shape.ParamConv
import kuzminki.shape.RowConv
import kuzminki.run.{RunQuery, RunOperation}
import kuzminki.render._


class RenderUpdate[M](
  model: M,
  coll: SectionCollector
) extends PickUpdateReturning(model, coll)
     with RunOperation {
  
  def render = RenderedOperation(
    coll.render,
    coll.args
  )

  def cache = new StoredOperation(
    coll.render,
    coll.args
  )
}


class RenderUpdateReturning[R](
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


class RenderStoredUpdate[M, P1, P2](
  model: M,
  coll: SectionCollector,
  changes: ParamConv[P1],
  filters: ParamConv[P2]
) extends PickStoredUpdateReturning(model, coll, changes, filters) {

  def cache = {
    new StoredUpdate(
      coll.render,
      coll.args,
      changes,
      filters
    )
  }
}


class RenderStoredUpdateReturning[P1, P2, R](
  coll: SectionCollector,
  changes: ParamConv[P1],
  filters: ParamConv[P2],
  rowConv: RowConv[R]
) {

  def cache = {
    new StoredUpdateReturning(
      coll.render,
      coll.args,
      changes,
      filters,
      rowConv
    )
  }
}









