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
import kuzminki.api.{Jsonb, Arg, ArgInt}


trait JsonbMethods extends TypeMethods[Jsonb] {

  val col: TypeCol[Jsonb]

  // fn

  def ->(value: String): TypeCol[Jsonb] = JsonbPickFn(col, value)
  def ->(value: Int): TypeCol[Jsonb] = JsonbPickFn(col, value)
  def ->>(value: String): TypeCol[String] = JsonbPickStrFn(col, value)
  def ->>(value: Int): TypeCol[String] = JsonbPickStrFn(col, value)
  def #>(value: Seq[String]): TypeCol[Jsonb] = JsonbPathFn(col, StringSeqConv.put(value))
  def #>>(value: Seq[String]): TypeCol[String] = JsonbPathStrFn(col, StringSeqConv.put(value))
  def ||(col2: TypeCol[Jsonb]): TypeCol[Jsonb] = JsonbConcatFn(col, col2)
  def -(value: String): TypeCol[Jsonb] = JsonbDropFn(col, value)
  def -(value: Int): TypeCol[Jsonb] = JsonbDropFn(col, value)
  def -(value: Seq[String]): TypeCol[Jsonb] = JsonbDropManyFn(col, StringSeqConv.put(value))
  def #-(value: Seq[String]): TypeCol[Jsonb] = JsonbDropPathFn(col, StringSeqConv.put(value))

  // filters

  def ?(value: String): Filter = FilterJsonbExists(col, value)
  def !?(value: String): Filter = FilterJsonbExists(col, value)
  def ?|(value: Seq[String]): Filter = FilterJsonbExistsAny(col, StringSeqConv.put(value))
  def ?&(value: Seq[String]): Filter = FilterJsonbExistsAll(col, StringSeqConv.put(value))

  def @>(value: Jsonb): Filter = FilterContains(col, value)
  def !@>(value: Jsonb): Filter = FilterNotContains(col, value)
  def <@(value: Jsonb): Filter = FilterContainedBy(col, value)
  def !<@(value: Jsonb): Filter = FilterNotContainedBy(col, value)

  // optional

  def ?(opt: Option[String]): Option[Filter] = opt.map(?)
  def !?(opt: Option[String]): Option[Filter] = opt.map(!?)
  def ?|(opt: Option[Seq[String]]): Option[Filter] = opt.map(?|)
  def ?&(opt: Option[Seq[String]]): Option[Filter] = opt.map(?&)

  def @>(opt: Option[Jsonb]): Option[Filter] = opt.map(@>)
  def !@>(opt: Option[Jsonb]): Option[Filter] = opt.map(!@>)
  def <@(opt: Option[Jsonb]): Option[Filter] = opt.map(<@)
  def !<@(opt: Option[Jsonb]): Option[Filter] = opt.map(!<@)

  // update

  def +=(value: Jsonb) = JsonbUpdate(col, value)
  def -=(value: String) = JsonbDel(col, value)
  def -=(value: Int) = JsonbDel(col, value)
  def #-=(value: Seq[String]) = JsonbDelPath(col, StringSeqConv.put(value))

  // cache

  def use = JsonbCache(col)
}


case class JsonbCache(col: TypeCol[Jsonb]) extends TypeCache[Jsonb] {
  
  def ?(arg: Arg) = CacheJsonbExists(col, StringConv)
  def !?(arg: Arg) = CacheJsonbExists(col, StringConv)
  def ?|(arg: Arg) = CacheJsonbExistsAny(col, StringSeqConv)
  def ?&(arg: Arg) = CacheJsonbExistsAll(col, StringSeqConv)

  def @>(arg: Arg) = CacheContains(col, col.conv)
  def !@>(arg: Arg) = CacheNotContains(col, col.conv)
  def <@(arg: Arg) = CacheContainedBy(col, col.conv)
  def !<@(arg: Arg) = CacheContainedBy(col, col.conv)

  // update

  def +=(arg: Arg) = CacheJsonbUpdate(col, col.conv)
  def -=(arg: Arg) = CacheJsonbDelKey(col, StringConv)
  def -=(arg: ArgInt) = CacheJsonbDelIndex(col, IntConv)
  def #-=(arg: Arg) = CacheJsonbDelPath(col, StringSeqConv)
}
















