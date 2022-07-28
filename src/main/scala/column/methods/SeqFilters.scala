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

import kuzminki.filter._
import kuzminki.filter.types._
import kuzminki.select.SelectSubquery
import kuzminki.assign.{Append, Prepend, Remove}


trait SeqFilters[T] {

  val self: TypeCol[Seq[T]]

  def matches(value: Seq[T]): Filter = FilterSeqMatches(self, value)
  def ===(value: Seq[T]): Filter = matches(value)
  
  def not(value: Seq[T]): Filter = FilterSeqNot(self, value)
  def !==(value: Seq[T]): Filter = not(value)

  def has(value: T) = FilterSeqHas(self, value)
  def hasNot(value: T) = FilterSeqHasNot(self, value)

  def overlap(value: Seq[T]) = FilterSeqOverlap(self, value)
  def overlapNot(value: Seq[T]) = FilterSeqOverlapNot(self, value)
  
  // optional

  def matches(opt: Option[Seq[T]]): Option[Filter] = opt.map(matches)
  def ===(opt: Option[Seq[T]]): Option[Filter] = opt.map(matches)
  
  def not(opt: Option[Seq[T]]): Option[Filter] = opt.map(not)
  def !==(opt: Option[Seq[T]]): Option[Filter] = opt.map(not)

  def has(opt: Option[T]): Option[Filter] = opt.map(has)
  def hasNot(opt: Option[T]): Option[Filter] = opt.map(hasNot)

  def overlap(opt: Option[Seq[T]]): Option[Filter] = opt.map(overlap)
  def overlapNot(opt: Option[Seq[T]]): Option[Filter] = opt.map(overlapNot)

  // null

  def isNull: Filter = FilterIsNull(self)
  def isNotNull: Filter = FilterIsNotNull(self)

  // cache

  def cacheEq = CacheSeqEq(self)
  def cacheNot = CacheSeqNot(self)
}






















