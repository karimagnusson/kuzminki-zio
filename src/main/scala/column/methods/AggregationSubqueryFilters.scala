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

package kuzminki.column

import kuzminki.filter.Filter
import kuzminki.filter.types._
import kuzminki.select.AggregationSubquery


trait AggregationSubqueryFilters[T] extends SelfRef[T] {

  def matches(sub: AggregationSubquery): Filter = FilterAggMatches(self, sub)
  def ===(sub: AggregationSubquery): Filter = matches(sub)

  def not(sub: AggregationSubquery): Filter = FilterAggNot(self, sub)
  def !==(sub: AggregationSubquery): Filter = not(sub)

  def gt(sub: AggregationSubquery): Filter = FilterAggGt(self, sub)
  def >(sub: AggregationSubquery): Filter = gt(sub)

  def gte(sub: AggregationSubquery): Filter = FilterAggGte(self, sub)
  def >=(sub: AggregationSubquery): Filter = gte(sub)

  def lt(sub: AggregationSubquery): Filter = FilterAggLt(self, sub)
  def <(sub: AggregationSubquery): Filter = lt(sub)

  def lte(sub: AggregationSubquery): Filter = FilterAggLte(self, sub)
  def <=(sub: AggregationSubquery): Filter = lte(sub)
}


