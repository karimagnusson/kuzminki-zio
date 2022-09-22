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

package kuzminki.fn.types

import kuzminki.column.TypeCol
import kuzminki.render.{Prefix, NoArgs}


trait FnBase {
  val col: TypeCol[_]
  val template: String
}

trait FnRender extends FnBase {
  def render(prefix: Prefix) = template.format(col.render(prefix))
}

trait FnName extends FnBase {
  def name = "%s_%s".format(template.splitAt(template.indexOf('('))._1, col.name)
}

trait FnArgs extends FnRender with FnName {
  def fnArgs: Vector[Any]
  val args = col.args ++ fnArgs
}

trait FnColArgs extends FnRender with FnName {
  val args = col.args
}



























