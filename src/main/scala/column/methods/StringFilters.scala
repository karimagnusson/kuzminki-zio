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


trait StringFilters extends SelfRef[String] {

  def like(value: String): Filter = FilterLike(self, value)
  def startsWith(value: String): Filter = FilterStartsWith(self, value)
  def endsWith(value: String): Filter = FilterEndsWith(self, value)
  def similarTo(value: String): Filter = FilterSimilarTo(self, value)

  def reMatch(value: String): Filter = FilterReIMatch(self, value)
  def ~(value: String): Filter = reMatch(value)

  def reIMatch(value: String): Filter = FilterReIMatch(self, value)
  def ~*(value: String): Filter = reIMatch(value)

  def reNotMatch(value: String): Filter = FilterReNotMatch(self, value)
  def !~(value: String): Filter = reNotMatch(value)

  def reNotIMatch(value: String): Filter = FilterReNotIMatch(self, value)
  def !~*(value: String): Filter = reNotIMatch(value)

  // optional

  def like(opt: Option[String]): Option[Filter] = opt.map(like)
  def startsWith(opt: Option[String]): Option[Filter] = opt.map(startsWith)
  def endsWith(opt: Option[String]): Option[Filter] = opt.map(endsWith)
  def similarTo(opt: Option[String]): Option[Filter] = opt.map(similarTo)

  def reMatch(opt: Option[String]): Option[Filter] = opt.map(reMatch)
  def ~(opt: Option[String]): Option[Filter] = opt.map(reMatch)

  def reIMatch(opt: Option[String]): Option[Filter] = opt.map(reIMatch)
  def ~*(opt: Option[String]): Option[Filter] = opt.map(reMatch)

  def reNotMatch(opt: Option[String]): Option[Filter] = opt.map(reNotMatch)
  def !~(opt: Option[String]): Option[Filter] = opt.map(reMatch)

  def reNotIMatch(opt: Option[String]): Option[Filter] = opt.map(reNotIMatch)
  def !~*(opt: Option[String]): Option[Filter] = opt.map(reMatch)

  // cache

  def cacheLike = CacheLike(self)
  def cacheStartsWith = CacheStartsWith(self)
  def cacheEndsWith = CacheEndsWith(self)
  def cacheSimilarTo = CacheSimilarTo(self)
  
  def cacheReMatch = CacheReMatch(self)
  def cacheReIMatch = CacheReIMatch(self)
  def cacheReNotMatch = CacheReNotMatch(self)
  def cacheReNotIMatch = CacheReNotIMatch(self)
}










