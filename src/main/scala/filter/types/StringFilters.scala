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

package kuzminki.filter.types

import kuzminki.column.TypeCol
import kuzminki.filter.Filter
import kuzminki.column.TypeCol
import kuzminki.render.Prefix
import kuzminki.conv.ValConv
import kuzminki.render.Renderable


case class FilterLike(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s LIKE concat('%%', ?, '%%')"
}

case class FilterStartsWith(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s LIKE concat(?, '%%')"
}

case class FilterEndsWith(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s LIKE concat('%%', ?)"
}

case class FilterSimilarTo(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s SIMILAR TO ?"
}

case class FilterReMatch(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s ~ ?"
}

case class FilterReIMatch(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s ~* ?"
}

case class FilterReNotMatch(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s !~ ?"
}

case class FilterReNotIMatch(col: Renderable, arg: String) extends ArgFilter {
  val template = "%s !~* ?"
}

// cache

case class CacheLike[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s LIKE concat('%%', ?, '%%')"
}

case class CacheStartsWith[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s LIKE concat(?, '%%')"
}

case class CacheEndsWith[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s LIKE concat('%%', ?)"
}

case class CacheSimilarTo[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s SIMILAR TO ?"
}

case class CacheReMatch[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s ~ ?"
}

case class CacheReIMatch[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s ~* ?"
}

case class CacheReNotMatch[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s !~ ?"
}

case class CacheReNotIMatch[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s !~* ?"
}




