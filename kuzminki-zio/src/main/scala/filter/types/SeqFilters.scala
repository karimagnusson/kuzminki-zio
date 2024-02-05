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


case class FilterSeqHas(col: Renderable, arg: Any) extends ArgFilter {
  val template = "? = ANY(%s)"
}

case class FilterSeqHasNot(col: Renderable, arg: Any) extends ArgFilter {
  val template = "NOT ? = ANY(%s)"
}

case class FilterSeqOverlap(col: Renderable, arg: Any) extends ArgFilter {
  val template = "%s && ?"
}

case class FilterSeqOverlapNot(col: Renderable, arg: Any) extends ArgFilter {
  val template = "NOT %s && ?"
}

// cache

case class CacheSeqHas[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "? = ANY(%s)"
}

case class CacheSeqHasNot[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "NOT ? = ANY(%s)"
}

case class CacheSeqOverlap[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "%s && ?"
}

case class CacheSeqOverlapNot[P](col: Renderable, conv: ValConv[P]) extends CacheFilterCol[P] {
  val template = "NOT %s && ?"
}



