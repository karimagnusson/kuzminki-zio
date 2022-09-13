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
import kuzminki.conv.ValConv
import kuzminki.select.SelectSubquery


trait TypeMethods[T] {
  
  val col: TypeCol[T]

  def matches(value: T): Filter = FilterMatches(col, value)
  def ===(value: T): Filter = matches(value)

  def not(value: T): Filter = FilterNot(col, value)
  def !==(value: T): Filter = not(value)
  
  // not optional

  def isNull: Filter = FilterIsNull(col)
  def isNotNull: Filter = FilterIsNotNull(col)

  def in(values: Seq[T]): Filter = FilterIn(col, values)
  def notIn(value: Seq[T]): Filter = FilterNotIn(col, value)

  def in(sub: SelectSubquery[T]): Filter = FilterInSubquery(col, sub)
  def notIn(sub: SelectSubquery[T]): Filter = FilterNotInSubquery(col, sub)

  // optional

  def matches(opt: Option[T]): Option[Filter] = opt.map(matches)
  def ===(opt: Option[T]): Option[Filter] = opt.map(matches)

  def not(opt: Option[T]): Option[Filter] = opt.map(not)
  def !==(opt: Option[T]): Option[Filter] = opt.map(not)

  def isNullSome: Option[Filter] = Some(isNull)
  def isNotNullSome: Option[Filter] = Some(isNotNull)

  def in(opt: Option[Seq[T]]): Option[Filter] = opt.map(in)
  def notIn(opt: Option[Seq[T]]): Option[Filter] = opt.map(notIn)

  // cache

  def oprEq = CacheFilter.matches(col)
  def oprNot = CacheFilter.not(col)
  def oprIn = CacheFilter.in(col)
  def oprNotIn = CacheFilter.notIn(col)

  // update

  def set(value: T) = SetValue(col, value)
  def ==>(value: T) = set(value)
  def setToNull = SetToNull(col)

  // update cache

  def modSet = CacheMod.set(col)
}




