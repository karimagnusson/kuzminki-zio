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

import kuzminki.api.KuzminkiError
import kuzminki.column._


object RowTypeNames {

  protected val baseColTypeName: TypeCol[_] => String = {
    case col: StringCol         => "String"
    case col: BooleanCol        => "Boolean"
    case col: ShortCol          => "Short"
    case col: IntCol            => "Int"
    case col: LongCol           => "Long"
    case col: FloatCol          => "Float"
    case col: DoubleCol         => "Double"
    case col: BigDecimalCol     => "BigDecimal"
    case col: TimeCol           => "Time"
    case col: DateCol           => "Date"
    case col: TimestampCol      => "Timestamp"
    case col: UUIDCol           => "UUID"
    case col: JsonbCol          => "Jsonb"
    case col: StringSeqCol      => "Vector[String]"
    case col: BooleanSeqCol     => "Vector[Boolean]"
    case col: IntSeqCol         => "Vector[Int]"
    case col: ShortSeqCol       => "Vector[Short]"
    case col: LongSeqCol        => "Vector[Long]"
    case col: BigDecimalSeqCol  => "Vector[BigDecimal]"
    case col: DoubleSeqCol      => "Vector[Double]"
    case col: FloatSeqCol       => "Vector[Float]"
    case col: TimeSeqCol        => "Vector[Time]"
    case col: DateSeqCol        => "Vector[Date]"
    case col: TimestampSeqCol   => "Vector[Timestamp]"
    case col: JsonbSeqCol       => "Vector[Jsonb]"
    case col => throw KuzminkiError(s"Unsupported column: [${col.getClass.getName}]")
  }

  protected val colTypeName: TypeCol[_] => String = {
    case col: OptCol[_] =>
      val name = baseColTypeName(col.col)
      s"Option[$name]"
    case col =>
      baseColTypeName(col)
  }

  def validate(cols: Vector[TypeCol[_]], fields: Vector[String]): Unit = {
    if (cols.map(colTypeName) != fields) {
      throw KuzminkiError(
        Seq(
          "Read error",
          "Column types: (%s)".format(cols.mkString(", ")),
          "Type members: (%s)".format(fields.mkString(", "))
        ).mkString("\n")
      )
    }
  }
}