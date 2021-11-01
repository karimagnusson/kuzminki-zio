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

import scala.language.implicitConversions
import scala.util.{ Try, Success, Failure }
import scala.reflect.{classTag, ClassTag}
import scala.reflect.runtime.universe._
import kuzminki.column.TypeCol


object RowReader extends RowTypeNames { 

  def create[T](rti: RowTypeInfo[T]) = {
    validate(rti)
    new RowReader(rti.cols, rti.cTag)
  }
}


class RowReader[R](val cols: Seq[TypeCol[_]], tag: ClassTag[R]) extends RowShape[R] {
  def conv = new RowConvReader(cols.map(_.conv))(tag)
}

























