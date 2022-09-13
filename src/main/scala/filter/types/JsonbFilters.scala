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
import kuzminki.api.Jsonb
import kuzminki.conv.ValConv
import kuzminki.render.Renderable


case class FilterJsonbMatches(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s = ?::jsonb"
}

case class FilterJsonbNot(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s != ?::jsonb"
}

case class FilterJsonbContains(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s @> ?::jsonb"
}

case class FilterJsonbContainedBy(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s <@ ?::jsonb"
}

case class FilterJsonbExists(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s ?? ?"
}

case class FilterJsonbExistsAny(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s ??| ?"
}

case class FilterJsonbExistsAll(col: TypeCol[Jsonb], arg: Any) extends SingleArgFilter {
  val template = "%s ??& ?"
}

// cache

case class CacheJsonbEq[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s = ?::jsonb"
}

case class CacheJsonbNot[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s != ?::jsonb"
}

case class CacheJsonbContains[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s = ?::jsonb"
}

case class CacheJsonbContainedBy[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s <@ ?::jsonb"
}

case class CacheJsonbExists[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s ?? ?"
}

case class CacheJsonbExistsAny[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s ??| ?"
}

case class CacheJsonbExistsAll[P](col: Renderable, conv: ValConv[P]) extends CacheFilter[P] {
  val template = "%s ??& ?"
}














