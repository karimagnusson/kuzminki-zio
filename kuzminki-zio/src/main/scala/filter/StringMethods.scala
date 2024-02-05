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
import kuzminki.fn.types._
import kuzminki.api.Arg


trait StringMethods extends TypeMethods[String] with InMethods[String] {

  // fn

  def concat(cols: TypeCol[_]*) = ConcatFn((Seq(col) ++ cols).toVector)
  def concatWs(glue: String, cols: TypeCol[_]*) = ConcatWsFn(glue, (Seq(col) ++ cols).toVector)
  def substr(start: Int) = SubstrFn(col, start, None)
  def substr(start: Int, len: Int) = SubstrFn(col, start, Some(len))
  def replace(from: String, to: String) = ReplaceFn(col, from, to)
  def trim = TrimFn(col)
  def upper = UpperFn(col)
  def lower = LowerFn(col)
  def initcap = InitcapFn(col)

  def asShort = CastShort(col)
  def asInt = CastInt(col)
  def asLong = CastLong(col)
  def asFloat = CastFloat(col)
  def asDouble = CastDouble(col)
  def asBigDecimal = CastBigDecimal(col)

  // filters

  def like(value: String): Filter = FilterLike(col, value)
  def similarTo(value: String): Filter = FilterSimilarTo(col, value)

  def begins(value: String): Filter = FilterStartsWith(col, value)
  def ends(value: String): Filter = FilterEndsWith(col, value)

  @deprecated("this method will be removed, Use 'begins'", "0.9.5")
  def startsWith(value: String): Filter = FilterStartsWith(col, value)
  @deprecated("this method will be removed, Use 'ends", "0.9.5")
  def endsWith(value: String): Filter = FilterEndsWith(col, value)

  def ~(value: String): Filter = FilterReMatch(col, value)
  def ~*(value: String): Filter = FilterReIMatch(col, value)
  def !~(value: String): Filter = FilterReNotMatch(col, value)
  def !~*(value: String): Filter = FilterReNotIMatch(col, value)

  // optional

  def like(opt: Option[String]): Option[Filter] = opt.map(like)
  def similarTo(opt: Option[String]): Option[Filter] = opt.map(similarTo)

  def begins(opt: Option[String]): Option[Filter] = opt.map(begins)
  def ends(opt: Option[String]): Option[Filter] = opt.map(ends)

  @deprecated("this method will be removed, Use 'begins'", "0.9.5")
  def startsWith(opt: Option[String]): Option[Filter] = opt.map(startsWith)
  @deprecated("this method will be removed, Use 'ends'", "0.9.5")
  def endsWith(opt: Option[String]): Option[Filter] = opt.map(endsWith)

  def ~(opt: Option[String]): Option[Filter] = opt.map(~)
  def ~*(opt: Option[String]): Option[Filter] = opt.map(~*)
  def !~(opt: Option[String]): Option[Filter] = opt.map(!~)
  def !~*(opt: Option[String]): Option[Filter] = opt.map(!~*)

  // cache

  def use = StringCache(col)
}


case class StringCache(col: TypeCol[String]) extends TypeCache[String]
                                                with InCache[String] {
  
  def like(arg: Arg) = CacheLike(col, col.conv)
  def similarTo(arg: Arg) = CacheSimilarTo(col, col.conv)

  def begins(arg: Arg) = CacheStartsWith(col, col.conv)
  def ends(arg: Arg) = CacheEndsWith(col, col.conv)

  @deprecated("this method will be removed, Use 'begins'", "0.9.5")
  def startsWith(arg: Arg) = CacheStartsWith(col, col.conv)
  @deprecated("this method will be removed, Use 'ends'", "0.9.5")
  def endsWith(arg: Arg) = CacheEndsWith(col, col.conv)
  
  def ~(arg: Arg) = CacheReMatch(col, col.conv)
  def ~*(arg: Arg) = CacheReIMatch(col, col.conv)
  def !~(arg: Arg) = CacheReNotMatch(col, col.conv)
  def !~*(arg: Arg) = CacheReNotIMatch(col, col.conv)
}







