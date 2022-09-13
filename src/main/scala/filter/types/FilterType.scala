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

import kuzminki.filter.Filter
import kuzminki.column.{TypeCol, ModelCol}
import kuzminki.conv._
import kuzminki.shape.CachePart
import kuzminki.api.KuzminkiError
import kuzminki.render.{
  Renderable,
  Prefix,
  PassArgs
}


trait SingleFilter extends Filter {
  val col: TypeCol[_]
  def render(prefix: Prefix) = template.format(col.render(prefix))
}

trait SingleArgFilter extends SingleFilter {
  val arg: Any
  val args = col.args :+ arg
}

trait NoArgFilter extends SingleFilter with PassArgs



























