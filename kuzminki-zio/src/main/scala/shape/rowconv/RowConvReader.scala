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
import scala.util.{Try, Success, Failure}
import scala.reflect.{classTag, ClassTag}
import kuzminki.api.KuzminkiError
import kuzminki.conv.ValConv


class RowConvReader[R](val cols: Vector[ValConv[_]])(implicit tag: ClassTag[R]) extends RowConv[R] {

  private val indexedCols = cols.zipWithIndex.map(p => (p._1, p._2 + 1))

  private def read(rs: ResultSet): Seq[AnyRef] = {
    indexedCols.map {
      case (col, index) =>
        col.get(rs, index).asInstanceOf[AnyRef]
    }
  }

  def fromRow(rs: ResultSet) = {
    Try {
      
      classTag[R]
        .runtimeClass
        .getConstructors
        .head
        .newInstance(read(rs): _*)
        .asInstanceOf[R]
    
    } match {
      case Success(res) => res
      case Failure(ex) =>
        val name = classTag[R].runtimeClass.getName
        val message = ex.getMessage
        throw KuzminkiError(
          s"Failed to read ($name) $message"
        )
    }
  }
}