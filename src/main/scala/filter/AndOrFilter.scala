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

package kuzminki.filter

import kuzminki.render.Prefix


trait AndOrFilter extends Filter {
  val filters: Seq[Filter]
  val template = "(%s)"
  val glue: String
  def render(prefix: Prefix) = {
    filters match {
      case Seq(first) =>
        first.render(prefix)
      case _ =>
        template.format(filters.map(_.render(prefix)).mkString(glue))
    }
  }
  def args = filters.map(_.args).flatten
}



