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


case class FilterLike(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s LIKE concat('%%', ?, '%%')"
}

case class FilterStartsWith(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s LIKE concat(?, '%%')"
}

case class FilterEndsWith(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s LIKE concat('%%', ?)"
}

case class FilterSimilarTo(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s SIMILAR TO ?"
}

case class FilterReMatch(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s ~ ?"
}

case class FilterReIMatch(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s ~* ?"
}

case class FilterReNotMatch(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s !~ ?"
}

case class FilterReNotIMatch(col: AnyCol, arg: String) extends SingleArgFilter {
  val template = "%s !~* ?"
}





