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

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import kuzminki.column.TypeCol
import kuzminki.assign._
import kuzminki.filter.types._
import kuzminki.select.AggregationSubquery


trait ComparativeMethods[T] extends TypeMethods[T] {

  def gt(value: T): Filter = FilterGt(col, value)
  def >(value: T): Filter = gt(value)

  def gte(value: T): Filter = FilterGte(col, value)
  def >=(value: T): Filter = gte(value)

  def lt(value: T): Filter = FilterLt(col, value)
  def <(value: T): Filter = lt(value)

  def lte(value: T): Filter = FilterLte(col, value)
  def <=(value: T): Filter = lte(value)

  // optional

  def gt(opt: Option[T]): Option[Filter] = opt.map(gt)
  def >(opt: Option[T]): Option[Filter] = opt.map(gt)

  def gte(opt: Option[T]): Option[Filter] = opt.map(gte)
  def >=(opt: Option[T]): Option[Filter] = opt.map(gte)

  def lt(opt: Option[T]): Option[Filter] = opt.map(lt)
  def <(opt: Option[T]): Option[Filter] = opt.map(lt)

  def lte(opt: Option[T]): Option[Filter] = opt.map(lte)
  def <=(opt: Option[T]): Option[Filter] = opt.map(lte)

  // subquery

  def matches(sub: AggregationSubquery): Filter = FilterAggMatches(col, sub)
  def not(sub: AggregationSubquery): Filter = FilterAggNot(col, sub)
  def gt(sub: AggregationSubquery): Filter = FilterAggGt(col, sub)
  def gte(sub: AggregationSubquery): Filter = FilterAggGte(col, sub)
  def lt(sub: AggregationSubquery): Filter = FilterAggLt(col, sub)
  def lte(sub: AggregationSubquery): Filter = FilterAggLte(col, sub)

  // cache

  def oprGt = CacheFilter.gt(col)
  def oprLt = CacheFilter.lt(col)
  def oprGte = CacheFilter.gte(col)
  def oprLte = CacheFilter.lte(col)

  // update

  def inc(value: T) = Increment(col, value)
  def +=(value: T) = inc(value)
  
  def dec(value: T) = Decrement(col, value)
  def -=(value: T) = dec(value)

  // update cache

  def modInc = CacheMod.inc(col)
  def decDec = CacheMod.dec(col)
}















