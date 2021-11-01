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

import kuzminki.column.AnyCol


case class FilterMatches(col: AnyCol, arg: Any) extends SingleArgFilter {
  val template = "%s = ?"
}

case class FilterNot(col: AnyCol, arg: Any) extends SingleArgFilter {
  val template = "%s != ?"
}

case class FilterIn(col: AnyCol, argSeq: Seq[Any]) extends ArrayFilter {
  val template = "%s = ANY(ARRAY[%s])"
}

case class FilterNotIn(col: AnyCol, argSeq: Seq[Any]) extends ArrayFilter {
  val template = "%s != ANY(ARRAY[%s])"
}

case class FilterBetween(col: AnyCol, argSeq: Seq[Any]) extends SingleFilter {
  val template = "%s = BETWEEN ? AND ?"
  val args = col.args ++ argSeq
}

case class FilterIsNull(col: AnyCol) extends NoArgFilter {
  val template = "%s IS NULL"
}

case class FilterIsNotNull(col: AnyCol) extends NoArgFilter {
  val template = "%s IS NOT NULL"
}