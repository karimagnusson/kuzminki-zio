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


case class FilterGt(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s > ?"
}

case class FilterLt(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s < ?"
}

case class FilterGte(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s >= ?"
}

case class FilterLte(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s <= ?"
}

// col filters

case class FilterColGt(col: Renderable, col2: Renderable) extends ColFilter {
  val template = "%s > ?"
}

case class FilterColLt(col: Renderable, col2: Renderable) extends ColFilter {
  val template = "%s < ?"
}

case class FilterColGte(col: Renderable, col2: Renderable) extends ColFilter {
  val template = "%s >= ?"
}

case class FilterColLte(col: Renderable, col2: Renderable) extends ColFilter {
  val template = "%s <= ?"
}

// cache

case class CacheGt[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s > ?"
}

case class CacheLt[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s < ?"
}

case class CacheGte[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s >= ?"
}

case class CacheLte[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s <= ?"
}