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

import kuzminki.api.KuzminkiError
import kuzminki.fn.Agg.AggFn
import kuzminki.run.RunQuery
import kuzminki.shape.RowShapeSingle
import kuzminki.section._
import kuzminki.render._


class RenderSelect[M, R](
    model: M,
    coll: SelectCollector[R]
  ) extends SelectCacheMethods(model, coll)
       with RunQuery[R] {

  def render = RenderedQuery(
    coll.render,
    coll.args,
    coll.rowShape.conv
  )

  def cache = new StoredQuery(
    coll.render,
    coll.args,
    coll.rowShape.conv
  )

  // subquery

  def asSubquery = new Subquery(coll)

  def asColumn = {
    coll.rowShape match {
      case shape: RowShapeSingle[_] =>
        new SubqueryCol(coll, shape.col.conv)
      case _ =>
        throw KuzminkiError("Subquery is invalid")
    }
  }

  private def firstColumn = {
    coll.sections(0) match {
      case SelectSec(parts) =>
        parts(0)
      case _ =>
        throw KuzminkiError("Subquery is invalid")
    }
  }
  
  def asAggregation = {
    firstColumn match {
      case col: AggFn =>
      case _ =>
        throw KuzminkiError("Subquery column must be an aggregation function")
    }

    new AggregationSubquery(coll)
  }
}

























