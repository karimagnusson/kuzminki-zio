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

import kuzminki.assign.ValueEq
import kuzminki.model.ModelTable
import kuzminki.render.{Renderable, Prefix}


trait Fill {
  def fill(size: Int) = (Vector.fill(size)("?")).mkString(", ")
}

case class InsertIntoSec(part: ModelTable) extends SinglePartRender {
  val expression = "INSERT INTO %s"
}

case class InsertColumnsSec(parts: Vector[Renderable]) extends MultiPartRender {
  val expression = "(%s)"
  val glue = ", "
}

case class InsertValuesSec(values: Vector[Any]) extends Section with Fill {
  val expression = "VALUES (%s)"
  def render(prefix: Prefix) = expression.format(fill(values.size))
  val args = values
}

case class InsertWhereNotExistsSec(values: Vector[Any], table: ModelTable, where: WhereSec) extends Section with Fill {
  val expression = "SELECT %s WHERE NOT EXISTS (SELECT 1 FROM %s %s)"
  def render(prefix: Prefix) = expression.format(
    fill(values.size),
    table.render(prefix),
    where.render(prefix)
  )
  val args = values ++ where.args
}

// on conflict

object InsertOnConflictSec extends TextOnlyRender {
  val expression = "ON CONFLICT"
}

case class InsertOnConflictColumnSec(part: Renderable) extends SinglePartRender {
  val expression = "ON CONFLICT (%s)"
}

object InsertDoNothingSec extends TextOnlyRender {
  val expression = "DO NOTHING"
}

case class InsertDoUpdateSec(parts: Vector[ValueEq]) extends MultiPartRender {
  val expression = "DO UPDATE SET %s"
  val glue = ", "
}

case class InsertSubquerySec(part: Renderable) extends SinglePartRender {
  val expression = "%s"
}






