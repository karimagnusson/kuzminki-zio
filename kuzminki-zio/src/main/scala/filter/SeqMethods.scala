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
import kuzminki.shape.CachePart.itemConv
import kuzminki.fn.types._
import kuzminki.api.{Arg, ArgSeq}


trait SeqMethods[T] extends TypeMethods[Seq[T]] {

  // fn

  def unnest = SeqUnnestFn(col)
  def length = SeqLengthFn(col)
  def trim(size: Int) = SeqTrimFn(col, size)
  def get(pos: Int) = SeqGetFn(col, pos)
  def pos(value: T) = SeqPosFn(col, value)
  def first = SeqFirstFn(col)
  def last = SeqLastFn(col)
  def join(glue: String) = SeqJoinFn(col, glue)
  def ||(col2: TypeCol[Seq[T]]) = SeqExtendFn(col, col2)

  // filters

  def ?(value: T): Filter = FilterSeqHas(col, value)
  def !?(value: T): Filter = FilterSeqHasNot(col, value)
  def @>(value: Seq[T]): Filter = FilterContains(col, col.conv.put(value))
  def !@>(value: Seq[T]): Filter = FilterNotContains(col, col.conv.put(value))
  def <@(value: Seq[T]): Filter = FilterContainedBy(col, col.conv.put(value))
  def !<@(value: Seq[T]): Filter = FilterNotContainedBy(col, col.conv.put(value))
  def &&(value: Seq[T]): Filter = FilterSeqOverlap(col, col.conv.put(value))
  def !&&(value: Seq[T]): Filter = FilterSeqOverlapNot(col, col.conv.put(value))
  
  // optional

  def ?(opt: Option[T]): Option[Filter] = opt.map(?)
  def !?(opt: Option[T]): Option[Filter] = opt.map(!?)
  def @>(opt: Option[Seq[T]]): Option[Filter] = opt.map(@>)
  def !@>(opt: Option[Seq[T]]): Option[Filter] = opt.map(!@>)
  def <@(opt: Option[Seq[T]]): Option[Filter] = opt.map(<@)
  def !<@(opt: Option[Seq[T]]): Option[Filter] = opt.map(!<@)
  def &&(opt: Option[Seq[T]]): Option[Filter] = opt.map(&&)
  def !&&(opt: Option[Seq[T]]): Option[Filter] = opt.map(!&&)

  // update

  def +=(value: T) = Append(col, value)
  def +=(value: Seq[T]) = Append(col, col.conv.put(value))
  def :=(value: T) = Prepend(col, value)
  def :=(value: Seq[T]) = Prepend(col, col.conv.put(value))
  def -=(value: T) = Remove(col, value)

  def add(value: T) = Add(col, value)
  def add(value: Seq[T]) = Add(col, col.conv.put(value))
  def addAsc(value: T) = AddAsc(col, value)
  def addAsc(value: Seq[T]) = AddAsc(col, col.conv.put(value))
  def addDesc(value: T) = AddDesc(col, value)
  def addDesc(value: Seq[T]) = AddDesc(col, col.conv.put(value))

  // cache

  def use = SeqCache(col) 
}


case class SeqCache[T](col: TypeCol[Seq[T]]) extends TypeCache[Seq[T]] {

  def ?(arg: Arg) = CacheSeqHas(col, itemConv(col.conv))
  def !?(arg: Arg) = CacheSeqHasNot(col, itemConv(col.conv))
  def @>(arg: Arg) = CacheContains(col, col.conv)
  def !@>(arg: Arg) = CacheContains(col, col.conv)
  def <@(arg: Arg) = CacheContainedBy(col, col.conv)
  def !<@(arg: Arg) = CacheContainedBy(col, col.conv)
  def &&(arg: Arg) = CacheSeqOverlap(col, col.conv)
  def !&&(arg: Arg) = CacheSeqOverlapNot(col, col.conv)

  // update

  def +=(arg: Arg) = CacheAppend(col, itemConv(col.conv))
  def +=(arg: ArgSeq) = CacheAppend(col, col.conv)
  def :=(arg: Arg) = CachePrepend(col, itemConv(col.conv))
  def :=(arg: ArgSeq) = CachePrepend(col, col.conv)
  def -=(arg: Arg) = CacheRemove(col, itemConv(col.conv))
  
  def add(arg: Arg) = CacheSeqAdd(col, itemConv(col.conv))
  def add(arg: ArgSeq) = CacheSeqAdd(col, col.conv)
  def addAsc(arg: Arg) = CacheSeqAddAsc(col, itemConv(col.conv))
  def addAsc(arg: ArgSeq) = CacheSeqAddAsc(col, col.conv)
  def addDesc(arg: Arg) = CacheSeqAddDesc(col, itemConv(col.conv))
  def addDesc(arg: ArgSeq) = CacheSeqAddDesc(col, col.conv)
}






















