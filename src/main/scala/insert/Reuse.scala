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

package kuzminki.insert

import kuzminki.api.KuzminkiError
import kuzminki.column.AnyCol


object Reuse {

  def noChange: Reuse = NoChange

  def fromIndex(insertCols: Vector[AnyCol], reuseCols: Vector[AnyCol]): Reuse = {

    val indexes = reuseCols.map { col =>
      insertCols.indexOf(col) match {
        case -1 =>
          throw KuzminkiError(
            "column [%s] is not among inserted columns".format("col")
          )
        case index: Int => index
      }
    }

    new ReuseIndexes(indexes)
  }
}  

trait Reuse {
  val indexes: Vector[Int]
  def extend(values: Vector[Any]): Vector[Any]
}

class ReuseIndexes(val indexes: Vector[Int]) extends Reuse {
  def extend(values: Vector[Any]): Vector[Any] = {
    values ++ indexes.map(values)
  }
}

object NoChange extends Reuse {
  val indexes = Vector.empty[Int]
  def extend(values: Vector[Any]) = values
}





