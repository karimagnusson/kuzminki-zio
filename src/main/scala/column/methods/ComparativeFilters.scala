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

import kuzminki.filter.Filter
import kuzminki.filter.types._


trait ComparativeFilters[T] extends SelfRef[T] {

  def gt(value: T): Filter = FilterGt(self, value)
  def >(value: T): Filter = gt(value)

  def gte(value: T): Filter = FilterGte(self, value)
  def >=(value: T): Filter = gte(value)

  def lt(value: T): Filter = FilterLt(self, value)
  def <(value: T): Filter = lt(value)

  def lte(value: T): Filter = FilterLte(self, value)
  def <=(value: T): Filter = lte(value)

  // optional

  def gt(opt: Option[T]): Option[Filter] = opt.map(gt)
  def >(opt: Option[T]): Option[Filter] = opt.map(gt)

  def gte(opt: Option[T]): Option[Filter] = opt.map(gte)
  def >=(opt: Option[T]): Option[Filter] = opt.map(gte)

  def lt(opt: Option[T]): Option[Filter] = opt.map(lt)
  def <(opt: Option[T]): Option[Filter] = opt.map(lt)

  def lte(opt: Option[T]): Option[Filter] = opt.map(lte)
  def <=(opt: Option[T]): Option[Filter] = opt.map(lte)

  // cache

  def cacheGt = CacheGt(self)
  def cacheLt = CacheLt(self)
  def cacheGte = CacheGte(self)
  def cacheLte = CacheLte(self)
}















