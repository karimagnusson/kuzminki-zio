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

import kuzminki.column._
import kuzminki.api.Jsonb


trait JsonbFilterFn extends FnRender {
  val key: Any
  def name = "%s_%s".format(col.name, key.toString)
  def fnArgs = Vector(key)
  val args = col.args ++ fnArgs

}

case class JsonbPickFn(col: TypeCol[Jsonb], key: Any) extends JsonbCol with JsonbFilterFn {
  val template = "%s->?"
}

case class JsonbPickStrFn(col: TypeCol[Jsonb], key: Any) extends StringCol with JsonbFilterFn {
  val template = "%s->>?"
}

case class JsonbPathFn(col: TypeCol[Jsonb], key: Any) extends JsonbCol with JsonbFilterFn {
  val template = "%s#>?"
}

case class JsonbPathStrFn(col: TypeCol[Jsonb], key: Any) extends StringCol with JsonbFilterFn {
  val template = "%s#>>?"
}