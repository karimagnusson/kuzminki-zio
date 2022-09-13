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

package kuzminki.assign

import kuzminki.column.{TypeCol, ModelCol}
import kuzminki.render.{Renderable, Prefix, NoArgs}
import kuzminki.api.KuzminkiError


case class SetUpsert(col: TypeCol[_]) extends Renderable with NoArgs {
  def render(prefix: Prefix) = "%s = ?".format(col.render(prefix))
  col match {
    case col: ModelCol =>
    case _ => throw KuzminkiError("cannot upsert a function") 
  }
}



