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
import kuzminki.conv._
import kuzminki.fn.types._
import kuzminki.api.{Jsonb, Arg, ArgSeq, ArgInt}


trait JsonbSeqMethods extends TypeMethods[Seq[Jsonb]] {

  // fn

  def unnest = SeqUnnestFn(col)
  def length = SeqLengthFn(col)
  def trim(size: Int) = SeqTrimFn(col, size)
  def get(pos: Int) = SeqGetFn(col, pos)
  def pos(value: Jsonb) = SeqPosFn(col, value)
  def first = SeqFirstFn(col)
  def last = SeqLastFn(col)
  def ||(col2: TypeCol[Seq[Jsonb]]) = SeqExtendFn(col, col2)

  // filters

  def ?(value: Jsonb): Filter = FilterSeqHas(col, value)
  def !?(value: Jsonb): Filter = FilterSeqHasNot(col, value)
  def @>(value: Seq[Jsonb]): Filter = FilterContains(col, col.conv.put(value))
  def !@>(value: Seq[Jsonb]): Filter = FilterNotContains(col, col.conv.put(value))
  def <@(value: Seq[Jsonb]): Filter = FilterContainedBy(col, col.conv.put(value))
  def !<@(value: Seq[Jsonb]): Filter = FilterNotContainedBy(col, col.conv.put(value))
  def &&(value: Seq[Jsonb]): Filter = FilterSeqOverlap(col, col.conv.put(value))
  def !&&(value: Seq[Jsonb]): Filter = FilterSeqOverlapNot(col, col.conv.put(value))
  
  // optional

  def ?(opt: Option[Jsonb]): Option[Filter] = opt.map(?)
  def !?(opt: Option[Jsonb]): Option[Filter] = opt.map(!?)
  def @>(opt: Option[Seq[Jsonb]]): Option[Filter] = opt.map(@>)
  def !@>(opt: Option[Seq[Jsonb]]): Option[Filter] = opt.map(!@>)
  def <@(opt: Option[Seq[Jsonb]]): Option[Filter] = opt.map(<@)
  def !<@(opt: Option[Seq[Jsonb]]): Option[Filter] = opt.map(!<@)
  def &&(opt: Option[Seq[Jsonb]]): Option[Filter] = opt.map(&&)
  def !&&(opt: Option[Seq[Jsonb]]): Option[Filter] = opt.map(!&&)

  // update

  def +=(value: Jsonb) = Append(col, value)
  def +=(value: Seq[Jsonb]) = Append(col, col.conv.put(value))
  def :=(value: Jsonb) = Prepend(col, value)
  def :=(value: Seq[Jsonb]) = Prepend(col, col.conv.put(value))
  def -=(value: Jsonb) = Remove(col, value)

  def add(value: Jsonb, sort: String) = AddJsonb(col, value, sort)
  def add(value: Seq[Jsonb], sort: String) = AddJsonb(col, col.conv.put(value), sort)
  def addAsc(value: Jsonb, sort: String) = AddJsonbAsc(col, value, sort)
  def addAsc(value: Seq[Jsonb], sort: String) = AddJsonbAsc(col, col.conv.put(value), sort)
  def addDesc(value: Jsonb, sort: String) = AddJsonbDesc(col, value, sort)
  def addDesc(value: Seq[Jsonb], sort: String) = AddJsonbDesc(col, col.conv.put(value), sort)
  def remove(value: String, sort: String) = RemoveJsonb(col, value, sort)
  def remove(value: Int, sort: String) = RemoveJsonb(col, value, sort)

  // cache

  def use = JsonbSeqCache(col)
}

case class JsonbSeqCache(col: TypeCol[Seq[Jsonb]]) extends TypeCache[Seq[Jsonb]] {

  def ?(arg: Arg) = CacheSeqHas(col, JsonbConv)
  def !?(arg: Arg) = CacheSeqHasNot(col, JsonbConv)
  def @>(arg: Arg) = CacheContains(col, col.conv)
  def !@>(arg: Arg) = CacheContains(col, col.conv)
  def <@(arg: Arg) = CacheContainedBy(col, col.conv)
  def !<@(arg: Arg) = CacheContainedBy(col, col.conv)
  def &&(arg: Arg) = CacheSeqOverlap(col, col.conv)
  def !&&(arg: Arg) = CacheSeqOverlapNot(col, col.conv)

  // update

  def +=(arg: Arg) = CacheAppend(col, JsonbConv)
  def +=(arg: ArgSeq) = CacheAppend(col, col.conv)
  def :=(arg: Arg) = CachePrepend(col, JsonbConv)
  def :=(arg: ArgSeq) = CachePrepend(col, col.conv)
  def -=(arg: Arg) = CacheRemove(col, JsonbConv)

  def add(arg: Arg, sort: String) = CacheSeqAddJsonb(col, JsonbConv, sort)
  def add(arg: ArgSeq, sort: String) = CacheSeqAddJsonb(col, col.conv, sort)
  def addAsc(arg: Arg, sort: String) = CacheSeqAddJsonbDesc(col, JsonbConv, sort)
  def addAsc(arg: ArgSeq, sort: String) = CacheSeqAddJsonbAsc(col, col.conv, sort)
  def addDesc(arg: Arg, sort: String) = CacheSeqAddJsonbDesc(col, JsonbConv, sort)
  def addDesc(arg: ArgSeq, sort: String) = CacheSeqAddJsonbDesc(col, col.conv, sort)
  def remove(value: Arg, sort: String) = RemoveJsonb(col, StringConv, sort)
  def remove(value: ArgInt, sort: String) = RemoveJsonb(col, IntConv, sort)
}






















