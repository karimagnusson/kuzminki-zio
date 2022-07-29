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

package kuzminki.shape

import java.sql.ResultSet
import kuzminki.conv.ValConv


class RowConvNamed(
  val cols: Vector[Tuple2[String, ValConv[_]]]
) extends RowConv[Seq[Tuple2[String, Any]]] {

  private val indexedCols = cols.zipWithIndex.map {
    case ((name, col), index) => (name, col, index + 1) 
  }

  def fromRow(rs: ResultSet) = {
    indexedCols.toVector.map {
      case (name, col, index) =>
        (name, col.get(rs, index))
    }
  }
}