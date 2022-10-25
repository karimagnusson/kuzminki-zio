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
import kuzminki.fn.types._
import kuzminki.column.TypeCol
import kuzminki.render.Prefix
import kuzminki.api.KuzminkiError
import org.postgresql.util.PGInterval


case class RoundFn(col: TypeCol[_], arg: Any) extends BigDecimalCol with FnRender {
  def template = col match {
    case col: BigDecimalCol => "round(%s, ?)"
    case _ => "round(%s::numeric, ?)"
  }
  val args = col.args ++ Vector(arg)
}

case class RoundStrFn(col: TypeCol[_], arg: Any) extends StringCol with FnRender {
  def template = col match {
    case col: BigDecimalCol => "round(%s, ?)::text"
    case _ => "round(%s::numeric, ?)::text"
  }
  val args = col.args ++ Vector(arg)
}














