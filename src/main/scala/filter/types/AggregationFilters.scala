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

package kuzminki.filter.types

import kuzminki.column.AnyCol
import kuzminki.render.Renderable


case class FilterAggMatches(col: AnyCol, sub: Renderable) extends SubqueryFilter {
  val template = "%s = (%s)"
}

case class FilterAggNot(col: AnyCol, sub: Renderable) extends SubqueryFilter {
  val template = "%s != (%s)"
}

case class FilterAggGt(col: AnyCol, sub: Renderable) extends SubqueryFilter {
  val template = "%s > (%s)"
}

case class FilterAggGte(col: AnyCol, sub: Renderable) extends SubqueryFilter {
  val template = "%s >= (%s)"
}

case class FilterAggLt(col: AnyCol, sub: Renderable) extends SubqueryFilter {
  val template = "%s < (%s)"
}

case class FilterAggLte(col: AnyCol, sub: Renderable) extends SubqueryFilter {
  val template = "%s <= (%s)"
}