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

package kuzminki.section

import kuzminki.model.ModelTable
import kuzminki.sorting.Sorting
import kuzminki.render.{Renderable, Prefix, NoArgs}
import kuzminki.api.KuzminkiError


case class SelectSec(parts: Vector[Renderable]) extends MultiPartRender with NotEmpty {
  val expression = "SELECT %s"
  val glue = ", "
  def error = "no columns selected"
  notEmpty(parts)
}

case class SelectDistinctSec(parts: Vector[Renderable]) extends MultiPartRender with NotEmpty {
  val expression = "SELECT DISTINCT %s"
  val glue = ", "
  def error = "no columns selected"
  notEmpty(parts)
}

case class SelectDistinctOnSec(distincts: Vector[Renderable], parts: Vector[Renderable]) extends Section with NotEmpty {
  def error = "no columns selected"
  notEmpty(distincts)
  notEmpty(parts)
  val expression = "SELECT DISTINCT ON (%s) %s"
  val glue = ", "
  def render(prefix: Prefix) = expression.format(
    distincts.map(_.render(prefix)).mkString(glue),
    parts.map(_.render(prefix)).mkString(glue)
  )
  val args = distincts.map(_.args).flatten ++ parts.map(_.args).flatten
}

case class FromSec(part: ModelTable) extends SinglePartRender {
  val expression = "FROM %s"
}

case class GroupBySec(parts: Vector[Renderable]) extends MultiPartRender with NotEmpty {
  val expression = "GROUP BY %s"
  val glue = ", "
  def error = "WHERE BY cannot be empty"
  notEmpty(parts)
}

case class OrderBySec(parts: Vector[Sorting]) extends MultiPartRender with NotEmpty {
  val expression = "ORDER BY %s"
  val glue = ", "
  def error = "ORDER BY cannot be empty"
  notEmpty(parts)
}

case class OffsetSec(arg: Int) extends SingleArgRender {
  val expression = "OFFSET ?"
}

case class LimitSec(arg: Int) extends SingleArgRender {
  val expression = "LIMIT ?"
}




