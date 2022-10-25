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
import kuzminki.conv.NoArgConv
import kuzminki.fn.types._
import kuzminki.api.{Arg, NoArg}


trait TypeMethods[T] {
  
  val col: TypeCol[T]

  def default(value: T) = CoalesceFn(col, col.conv.put(value))

  // filters

  def ===(value: T): Filter = FilterMatches(col, col.conv.put(value))
  def !==(value: T): Filter = FilterNot(col, col.conv.put(value))
  def isNull: Filter = FilterIsNull(col)
  def isNotNull: Filter = FilterIsNotNull(col)

  // optional

  def ===(opt: Option[T]): Option[Filter] = opt.map(===)
  def !==(opt: Option[T]): Option[Filter] = opt.map(!==)
  def isNullSome: Option[Filter] = Some(isNull)
  def isNotNullSome: Option[Filter] = Some(isNotNull)

  // col filter

  def ===(col2: TypeCol[T]): Filter = FilterColMatches(col, col2)
  def !==(col2: TypeCol[T]): Filter = FilterNot(col, col2)
  def <=>(parCol: TypeCol[T]): Filter = FilterParentEqCol(col, parCol)

  // update

  def ==>(value: T) = SetValue(col, col.conv.put(value))
}

trait TypeCache[T] {

  val col: TypeCol[T]

  def ===(arg: Arg) = CacheEq(col, col.conv)
  def !==(arg: Arg) = CacheNot(col, col.conv)
  def isNull(arg: NoArg) = CacheIsNull(col, NoArgConv)
  def isNotNull(arg: NoArg) = CacheIsNotNull(col, NoArgConv)

  // updatte

  def ==>(arg: Arg) = CacheSet(col, col.conv)
}






















