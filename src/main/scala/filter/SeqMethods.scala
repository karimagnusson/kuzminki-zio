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
import kuzminki.select.SelectSubquery
import kuzminki.assign.{Append, Prepend, Remove}


trait SeqMethods[T] {

  val col: TypeCol[Seq[T]]

  def matches(value: Seq[T]): Filter = FilterMatches(col, value)
  def ===(value: Seq[T]): Filter = matches(value)
  
  def not(value: Seq[T]): Filter = FilterNot(col, value)
  def !==(value: Seq[T]): Filter = not(value)

  def has(value: T): Filter = FilterSeqHas(col, value)
  def hasNot(value: T): Filter = FilterSeqHasNot(col, value)

  def overlap(value: Seq[T]): Filter = FilterSeqOverlap(col, value)
  def overlapNot(value: Seq[T]): Filter = FilterSeqOverlapNot(col, value)
  
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

  def isNull: Filter = FilterIsNull(col)
  def isNotNull: Filter = FilterIsNotNull(col)

  // cache

  def oprEq = CacheFilter.matches(col)
  def oprNot = CacheFilter.not(col)
  def oprHas = CacheFilter.seqHas(col)
  def oprhasNot = CacheFilter.seqHasNot(col)
  def oprOverlap = CacheFilter.seqOverlap(col)
  def oprOverlapNot = CacheFilter.seqOverlapNot(col)

  // update

  def append(value: T) = Append(col, value)
  def prepend(value: T) = Prepend(col, value)
  def remove(value: T) = Remove(col, value)

  // update cache

  def modAppend = CacheMod.append(col)
  def modPrepend = CacheMod.prepend(col)
  def modRemove = CacheMod.remove(col)
}






















