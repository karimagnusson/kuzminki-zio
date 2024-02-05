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
import kuzminki.select.{Subquery, SubqueryInFc}
import kuzminki.shape.CachePart.seqConv
import kuzminki.api.Arg


trait InMethods[T] {
  
  val col: TypeCol[T]

  // filters

  def in(values: Seq[T]): Filter = FilterIn(col, seqConv(col.conv).put(values))
  def notIn(value: Seq[T]): Filter = FilterNotIn(col, value)

  // optional

  def in(opt: Option[Seq[T]]): Option[Filter] = opt.map(in)
  def notIn(opt: Option[Seq[T]]): Option[Filter] = opt.map(notIn)

  // subquery

  def in(sub: Subquery[T]): Filter = FilterInSubquery(col, sub)
  def notIn(sub: Subquery[T]): Filter = FilterNotInSubquery(col, sub)
}


trait InCache[T] {

  val col: TypeCol[T]

  def in(arg: Arg) = CacheIn(col, seqConv(col.conv))
  def notIn(arg: Arg) = CacheNotIn(col, seqConv(col.conv))

  def in[P](sub: SubqueryInFc[P, T]) = CacheInSubquery(col, sub, sub.valConv)
  def notIn[P](sub: SubqueryInFc[P, T]) = CacheNotInSubquery(col, sub, sub.valConv)
}











