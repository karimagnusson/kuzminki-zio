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

import kuzminki.api.KuzminkiError
import kuzminki.column.TypeCol
import kuzminki.api.Jsonb
import kuzminki.conv.JsonbConv


trait FillValues {
  
  def fillByValues(args: Vector[Any]) = {
    args.map {
      case obj: Jsonb => "?::jsonb"
      case _ => "?"
    }.mkString(", ")
  }
  
  def fillByCols(args: Vector[TypeCol[_]]) = {
    args.map(_.conv).map {
      case JsonbConv => "?::jsonb"
      case _ => "?"
    }.mkString(", ")
  }

  //def fillNoBrackets(size: Int) = Vector.fill(size)("?").mkString(", ")
  //def fillBrackets(size: Int) = "(%s)".format(fillNoBrackets(size))
}

abstract class NotEmpty(parts: Vector[Any]) {
  def error: String
  if (parts.isEmpty) {
    throw KuzminkiError(error)
  }
}