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

package kuzminki.filter

import kuzminki.column.TypeCol
import kuzminki.filter.types._
import kuzminki.select.AggregationSubquery
import kuzminki.api.Arg


trait ComparativeMethods[T] extends TypeMethods[T] {

  def >(value: T): Filter = FilterGt(col, value)
  def >=(value: T): Filter = FilterGte(col, value)
  def <(value: T): Filter = FilterLt(col, value)
  def <=(value: T): Filter = FilterLte(col, value)

  // optional

  def >(opt: Option[T]): Option[Filter] = opt.map(>)
  def >=(opt: Option[T]): Option[Filter] = opt.map(>=)
  def <(opt: Option[T]): Option[Filter] = opt.map(<)
  def <=(opt: Option[T]): Option[Filter] = opt.map(<=)

  // compare to col

  def >(col2: TypeCol[T]): Filter = FilterColGt(col, col2)
  def >=(col2: TypeCol[T]): Filter = FilterColGte(col, col2)
  def <(col2: TypeCol[T]): Filter = FilterColLt(col, col2)
  def <=(col2: TypeCol[T]): Filter = FilterColLte(col, col2)

  // subquery

  def matches(sub: AggregationSubquery): Filter = FilterAggMatches(col, sub)
  def not(sub: AggregationSubquery): Filter = FilterAggNot(col, sub)
  def gt(sub: AggregationSubquery): Filter = FilterAggGt(col, sub)
  def gte(sub: AggregationSubquery): Filter = FilterAggGte(col, sub)
  def lt(sub: AggregationSubquery): Filter = FilterAggLt(col, sub)
  def lte(sub: AggregationSubquery): Filter = FilterAggLte(col, sub)
}


trait ComparativeCache[T] extends TypeCache[T] {

  val col: TypeCol[T]

  def >(arg: Arg) = CacheGt(col, col.conv)
  def <(arg: Arg) = CacheLt(col, col.conv)
  def >=(arg: Arg) = CacheGte(col, col.conv)
  def <=(arg: Arg) = CacheLte(col, col.conv)
}












