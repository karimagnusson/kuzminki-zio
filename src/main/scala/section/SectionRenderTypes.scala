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

import kuzminki.render.{Renderable, Prefix, NoArgs}


trait NoRender extends Section {
  def render(picker: Prefix) = expression
}

trait SingleArgRender extends NoRender {
  val arg: Any
  val args = Vector(arg)
}

trait TextOnlyRender extends NoRender with NoArgs

trait SinglePartRender extends Section {
  val part: Renderable
  def render(prefix: Prefix) = expression.format(part.render(prefix))
  val args = part.args
}

trait MultiPartRender extends Section {
  val parts: Vector[Renderable]
  val glue: String
  def render(prefix: Prefix) = expression.format(parts.map(_.render(prefix)).mkString(glue))
  val args = parts.map(_.args).flatten
}

// cache

trait CacheCondition extends Section {
  val cacheConds: Vector[Renderable]
  def render(prefix: Prefix) = {
    expression.format(
      cacheConds.map(_.render(prefix)).mkString(" AND ")
    )
  }
  val args = Vector(CacheCondArgs)
}

trait MixedCondition extends Section {
  val conds: Vector[Renderable]
  val cacheConds: Vector[Renderable]
  def render(prefix: Prefix) = {
    val both = conds ++ cacheConds
    expression.format(
      both.map(_.render(prefix)).mkString(" AND ")
    )
  }
  val args = conds.map(_.args).flatten ++ Vector(CacheCondArgs)
}