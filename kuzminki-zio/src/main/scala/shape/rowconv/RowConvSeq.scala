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


class RowConvSeq(val cols: Vector[ValConv[_]]) extends RowConv[Seq[Any]] {

  private val indexedCols = cols.zipWithIndex.map(p => (p._1, p._2 + 1))

  def fromRow(rs: ResultSet) = {
    indexedCols.toVector.map {
      case (col, index) =>
        col.get(rs, index)
    }
  }
}