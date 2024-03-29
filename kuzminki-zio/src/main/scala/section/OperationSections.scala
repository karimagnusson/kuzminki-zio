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

import kuzminki.assign.Assign
import kuzminki.model.ModelTable
import kuzminki.render.{Renderable, Prefix}
import kuzminki.api.CacheArg


case class CountFromSec(part: ModelTable) extends SinglePartRender {
  val expression = "SELECT count(*) FROM %s"
}

case class DeleteFromSec(part: ModelTable) extends SinglePartRender {
  val expression = "DELETE FROM %s"
}

case class UpdateSec(part: ModelTable) extends SinglePartRender {
  val expression = "UPDATE %s"
}

case class UpdateSetSec(parts: Vector[Assign]) extends MultiPartRender {
  val expression = "SET %s"
  val glue = ", "
}

// cache

case class UpdateCacheSetSec(parts: Vector[Renderable]) extends Section {
  val expression = "SET %s"
  def render(prefix: Prefix) = expression.format(parts.map(_.render(prefix)).mkString(", "))
  val args = parts.map(_ => CacheArg)
}

case class UpdateCacheWhereSec(parts: Vector[Renderable]) extends Section {
  val expression = "WHERE %s"
  def render(prefix: Prefix) = expression.format(parts.map(_.render(prefix)).mkString(", "))
  val args = parts.map(_ => CacheArg)
}




























