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

import kuzminki.column._
import kuzminki.assign._
import kuzminki.filter.types._
import kuzminki.fn.types._
import kuzminki.api.Jsonb


trait JsonbMethods{

  val col: TypeCol[Jsonb]

  // filters

  def matches(obj: Jsonb): Filter = FilterJsonbMatches(col, obj.value)
  def ===(obj: Jsonb): Filter = matches(obj)

  def not(obj: Jsonb): Filter = FilterJsonbNot(col, obj.value)
  def !==(obj: Jsonb): Filter = not(obj)

  def contains(obj: Jsonb): Filter = FilterJsonbContains(col, obj.value)
  def @>(obj: Jsonb): Filter = contains(obj)

  def containedBy(obj: Jsonb): Filter = FilterJsonbContainedBy(col, obj.value)
  def <@(obj: Jsonb): Filter = contains(obj)

  def exists(value: String): Filter = FilterJsonbExists(col, value)
  def ?(value: String): Filter = exists(value)

  def existsAny(values: Seq[String]): Filter = FilterJsonbExistsAny(col, values)
  def ?|(values: Seq[String]): Filter = existsAny(values)

  def existsAll(values: Seq[String]): Filter = FilterJsonbExistsAll(col, values)
  def ?&(values: Seq[String]): Filter = existsAll(values)

  // modifiers

  def pick(value: String): TypeCol[Jsonb] = JsonbPickFn(col, value)
  def ->(value: String): TypeCol[Jsonb] = pick(value)

  def pick(value: Int): TypeCol[Jsonb] = JsonbPickFn(col, value)
  def ->(value: Int): TypeCol[Jsonb] = pick(value)

  def pickStr(value: String): TypeCol[String] = JsonbPickStrFn(col, value)
  def ->>(value: String): TypeCol[String] = pickStr(value)

  def pickStr(value: Int): TypeCol[String] = JsonbPickStrFn(col, value)
  def ->>(value: Int): TypeCol[String] = pickStr(value)

  def path(values: Seq[String]): TypeCol[Jsonb] = JsonbPathFn(col, values)
  def #>(values: Seq[String]): TypeCol[Jsonb] = path(values)

  def pathStr(values: Seq[String]): TypeCol[String] = JsonbPathStrFn(col, values)
  def #>>(values: Seq[String]): TypeCol[String] = pathStr(values)

  // cache

  def oprEq = CacheFilter.jsonbEq(col)
  def oprNot = CacheFilter.jsonbNot(col)
  def oprContains = CacheFilter.jsonbContains(col)
  def oprContainedBy = CacheFilter.jsonbContainedBy(col)
  def oprExists = CacheFilter.jsonbExists(col)
  def oprExistsAny = CacheFilter.jsonbExistsAny(col)
  def oprExistsAll = CacheFilter.jsonbExistsAll(col)

  // update

  def set(obj: Jsonb) = JsonbSetValue(col, obj.value)
  def ==>(obj: Jsonb) = set(obj)
  def setToNull = SetToNull(col)
  
  def update(obj: Jsonb) = JsonbUpdate(col, obj.value)
  def +=(obj: Jsonb) = update(obj)
  
  def del(value: String) = JsonbDel(col, value)
  def -(value: String) = del(value)
  
  def del(value: Int) = JsonbDel(col, value)
  def -(value: Int) = del(value)
  
  def delPath(value: Seq[Any]) = JsonbDelPath(col, value)
  def -&(value: Seq[Any]) = delPath(value)

  // update cache

  def modSet = CacheMod.jsonbSet(col)
  def modUpdate = CacheMod.jsonbUpdate(col)
  def modDelKey = CacheMod.jsonbDelKey(col)
  def modDelIndex = CacheMod.jsonbDelIndex(col)
  def modDelPath = CacheMod.jsonbDelPath(col)
}



















