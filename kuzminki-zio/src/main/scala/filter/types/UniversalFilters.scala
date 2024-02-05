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

import kuzminki.conv.ValConv
import kuzminki.render.Renderable


case class FilterMatches(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s = ?"
}

case class FilterNot(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s != ?"
}

case class FilterIn(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s = ANY(?)"
}

case class FilterNotIn(col: Renderable, arg: Any) extends ArgFilter {
  val template = "NOT %s = ANY(?)"
}

case class FilterIsNull(col: Renderable) extends NoArgFilter {
  val template = "%s IS NULL"
}

case class FilterIsNotNull(col: Renderable) extends NoArgFilter {
  val template = "%s IS NOT NULL"
}

// col filters

case class FilterColMatches(col: Renderable, col2: Renderable) extends ColFilter {
  val template = "%s = %s"
}

case class FilterColNot(col: Renderable, col2: Renderable) extends ColFilter {
  val template = "%s != %s"
}

// cache


case class CacheEq[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s = ?"
}

case class CacheNot[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s != ?"
}

case class CacheIn[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s = ANY(?)"
}

case class CacheNotIn[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "NOT %s = ANY(?)"
}

case class CacheIsNull[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s IS NULL"
}

case class CacheIsNotNull[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s IS NOT NULL"
}



















