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

import kuzminki.api.KuzminkiError
import kuzminki.column._


trait RowTypeNames {

   protected val baseColTypeName: TypeCol[_] => String = {
    case col: StringCol         => "String"
    case col: BooleanCol        => "Boolean"
    case col: ShortCol          => "Short"
    case col: IntCol            => "Int"
    case col: LongCol           => "Long"
    case col: FloatCol          => "Float"
    case col: DoubleCol         => "Double"
    case col: BigDecimalCol     => "BigDecimal"
    case col: TimeCol           => "java.sql.Time"
    case col: DateCol           => "java.sql.Date"
    case col: TimestampCol      => "java.sql.Timestamp"
    case col: UUIDCol           => "java.util.UUID"
    case col: JsonbCol          => "kuzminki.api.Jsonb"
    case col: StringSeqCol      => "Vector[String]"
    case col: BooleanSeqCol     => "Vector[Boolean]"
    case col: IntSeqCol         => "Vector[Int]"
    case col: ShortSeqCol       => "Vector[Short]"
    case col: LongSeqCol        => "Vector[Long]"
    case col: BigDecimalSeqCol  => "Vector[BigDecimal]"
    case col: DoubleSeqCol      => "Vector[Double]"
    case col: FloatSeqCol       => "Vector[Float]"
    case col: TimeSeqCol        => "Vector[java.sql.Time]"
    case col: DateSeqCol        => "Vector[java.sql.Date]"
    case col: TimestampSeqCol   => "Vector[java.sql.Timestamp]"
    case col => throw KuzminkiError(s"Unsupported column: [${col.getClass.getName}]")
  }

  protected val colTypeName: TypeCol[_] => String = {
    case col: OptCol[_] => Try {
        baseColTypeName(col.col)
      } match {
        case Success(name) => s"Option[$name]"
        case Failure(ex) =>
          throw KuzminkiError(s"Unsupported column: [${col.getClass.getName}]")
      }
    case col => baseColTypeName(col)
  }

  protected def productTypeNames[T](implicit tag: TypeTag[T]) = {
    typeOf[T]
      .members
      .sorted
      .collect { case m: MethodSymbol if m.isCaseAccessor => m }
      .map(_.returnType.toString)
      .toVector
  }

  protected def validate[T](rti: RowTypeInfo[T]) = {

    val colTypeNames = rti.cols.map(colTypeName)
    val memberTypeNames = productTypeNames(rti.tTag)

    if (colTypeNames != memberTypeNames) {
      throw KuzminkiError(
        Seq(
          "Read error",
          "Column types: (%s)".format(colTypeNames.mkString(", ")),
          "Type members: (%s)".format(memberTypeNames.mkString(", "))
        ).mkString("\n")
      )
    }
  }
}