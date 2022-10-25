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
import kuzminki.assign._
import kuzminki.filter.types._
import kuzminki.fn.types._
import kuzminki.select.AggregationSubquery
import kuzminki.api.Arg


trait NumericMethods[T] extends ComparativeMethods[T] with InMethods[T] {

  // fn

  def round(size: Int) = RoundFn(col, size)
  def roundStr(size: Int) = RoundStrFn(col, size)

  def asShort = CastShort(col)
  def asInt = CastInt(col)
  def asLong = CastLong(col)
  def asFloat = CastFloat(col)
  def asDouble = CastDouble(col)
  def asBigDecimal = CastBigDecimal(col)

  // update

  def +=(value: T) = Increment(col, value)
  def -=(value: T) = Decrement(col, value)

  // cache

  def use = NumericCache(col)
}


case class NumericCache[T](col: TypeCol[T]) extends ComparativeCache[T] with InCache[T] {
  def +=(arg: Arg) = CacheIncrement(col, col.conv)
  def -=(arg: Arg) = CacheDecrement(col, col.conv)
}












