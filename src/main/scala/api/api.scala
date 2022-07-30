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

package kuzminki

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import scala.language.implicitConversions

import kuzminki.column._
import kuzminki.filter.Filter
import kuzminki.sorting.Sorting
import kuzminki.assign.Assign
import kuzminki.update.RenderUpdate
import kuzminki.delete.RenderDelete
import kuzminki.insert.RenderInsertData
import kuzminki.select.{
  RenderSelect,
  SelectSubquery,
  AggregationSubquery
}
import kuzminki.render.{
  RawSQLStatement,
  RenderedQuery,
  RenderedOperation
}


package object api {

  // create model col

  implicit val kzimplStringCol: ColInfo => TypeCol[String] = info => StringModelCol(info)
  implicit val kzimplBooleanCol: ColInfo => TypeCol[Boolean] = info => BooleanModelCol(info)
  implicit val kzimplShortCol: ColInfo => TypeCol[Short] = info => ShortModelCol(info)
  implicit val kzimplIntCol: ColInfo => TypeCol[Int] = info => IntModelCol(info)
  implicit val kzimplLongCol: ColInfo => TypeCol[Long] = info => LongModelCol(info)
  implicit val kzimplFloatCol: ColInfo => TypeCol[Float] = info => FloatModelCol(info)
  implicit val kzimplDoubleCol: ColInfo => TypeCol[Double] = info => DoubleModelCol(info)
  implicit val kzimplBigDecimalCol: ColInfo => TypeCol[BigDecimal] = info => BigDecimalModelCol(info)
  implicit val kzimplTimeCol: ColInfo => TypeCol[Time] = info => TimeModelCol(info)
  implicit val kzimplDateCol: ColInfo => TypeCol[Date] = info => DateModelCol(info)
  implicit val kzimplTimestampCol: ColInfo => TypeCol[Timestamp] = info => TimestampModelCol(info)

  implicit val kzimplStringSeqCol: ColInfo => TypeCol[Seq[String]] = info => StringSeqModelCol(info)
  implicit val kzimplBooleanSeqCol: ColInfo => TypeCol[Seq[Boolean]] = info => BooleanSeqModelCol(info)
  implicit val kzimplShortSeqCol: ColInfo => TypeCol[Seq[Short]] = info => ShortSeqModelCol(info)
  implicit val kzimplIntSeqCol: ColInfo => TypeCol[Seq[Int]] = info => IntSeqModelCol(info)
  implicit val kzimplLongSeqCol: ColInfo => TypeCol[Seq[Long]] = info => LongSeqModelCol(info)
  implicit val kzimplFloatSeqCol: ColInfo => TypeCol[Seq[Float]] = info => FloatSeqModelCol(info)
  implicit val kzimplDoubleSeqCol: ColInfo => TypeCol[Seq[Double]] = info => DoubleSeqModelCol(info)
  implicit val kzimplBigDecimalSeqCol: ColInfo => TypeCol[Seq[BigDecimal]] = info => BigDecimalSeqModelCol(info)
  implicit val kzimplTimeSeqCol: ColInfo => TypeCol[Seq[Time]] = info => TimeSeqModelCol(info)
  implicit val kzimplDateSeqCol: ColInfo => TypeCol[Seq[Date]] = info => DateSeqModelCol(info)
  implicit val kzimplTimestampSeqCol: ColInfo => TypeCol[Seq[Timestamp]] = info => TimestampSeqModelCol(info)

  // model type col

  implicit val kzimplToStringModelCol: TypeCol[String] => StringModelCol = col => col.asInstanceOf[StringModelCol]
  implicit val kzimplToBooleanModelCol: TypeCol[Boolean] => BooleanModelCol = col => col.asInstanceOf[BooleanModelCol]
  implicit val kzimplToShortModelCol: TypeCol[Short] => ShortModelCol = col => col.asInstanceOf[ShortModelCol]
  implicit val kzimplToIntModelCol: TypeCol[Int] => IntModelCol = col => col.asInstanceOf[IntModelCol]
  implicit val kzimplToLongModelCol: TypeCol[Long] => LongModelCol = col => col.asInstanceOf[LongModelCol]
  implicit val kzimplToFloatModelCol: TypeCol[Float] => FloatModelCol = col => col.asInstanceOf[FloatModelCol]
  implicit val kzimplToDoubleModelCol: TypeCol[Double] => DoubleModelCol = col => col.asInstanceOf[DoubleModelCol]
  implicit val kzimplToBigDecimalModelCol: TypeCol[BigDecimal] => BigDecimalModelCol = col => col.asInstanceOf[BigDecimalModelCol]
  implicit val kzimplToTimeModelCol: TypeCol[Time] => TimeModelCol = col => col.asInstanceOf[TimeModelCol]
  implicit val kzimplToDateModelCol: TypeCol[Date] => DateModelCol = col => col.asInstanceOf[DateModelCol]
  implicit val kzimplToTimestampModelCol: TypeCol[Timestamp] => TimestampModelCol = col => col.asInstanceOf[TimestampModelCol]

  implicit val kzimplToStringSeqModelCol: TypeCol[Seq[String]] => StringSeqModelCol = col => col.asInstanceOf[StringSeqModelCol]
  implicit val kzimplToBooleanSeqModelCol: TypeCol[Seq[Boolean]] => BooleanSeqModelCol = col => col.asInstanceOf[BooleanSeqModelCol]
  implicit val kzimplToShortSeqModelCol: TypeCol[Seq[Short]] => ShortSeqModelCol = col => col.asInstanceOf[ShortSeqModelCol]
  implicit val kzimplToIntSeqModelCol: TypeCol[Seq[Int]] => IntSeqModelCol = col => col.asInstanceOf[IntSeqModelCol]
  implicit val kzimplToLongSeqModelCol: TypeCol[Seq[Long]] => LongSeqModelCol = col => col.asInstanceOf[LongSeqModelCol]
  implicit val kzimplToFloatSeqModelCol: TypeCol[Seq[Float]] => FloatSeqModelCol = col => col.asInstanceOf[FloatSeqModelCol]
  implicit val kzimplToDoubleSeqModelCol: TypeCol[Seq[Double]] => DoubleSeqModelCol = col => col.asInstanceOf[DoubleSeqModelCol]
  implicit val kzimplToBigDecimalSeqModelCol: TypeCol[Seq[BigDecimal]] => BigDecimalSeqModelCol = col => col.asInstanceOf[BigDecimalSeqModelCol]
  implicit val kzimplToTimeSeqModelCol: TypeCol[Seq[Time]] => TimeSeqModelCol = col => col.asInstanceOf[TimeSeqModelCol]
  implicit val kzimplToDateSeqModelCol: TypeCol[Seq[Date]] => DateSeqModelCol = col => col.asInstanceOf[DateSeqModelCol]
  implicit val kzimplToTimestampSeqModelCol: TypeCol[Seq[Timestamp]] => TimestampSeqModelCol = col => col.asInstanceOf[TimestampSeqModelCol]

  // type col

  implicit val kzimplTypeColToStringCol: TypeCol[String] => StringCol = col => col.asInstanceOf[StringCol]
  implicit val kzimplTypeColToBooleanCol: TypeCol[Boolean] => BooleanCol = col => col.asInstanceOf[BooleanCol]
  implicit val kzimplTypeColToShortCol: TypeCol[Short] => ShortCol = col => col.asInstanceOf[ShortCol]
  implicit val kzimplTypeColToIntCol: TypeCol[Int] => IntCol = col => col.asInstanceOf[IntCol]
  implicit val kzimplTypeColToLongCol: TypeCol[Long] => LongCol = col => col.asInstanceOf[LongCol]
  implicit val kzimplTypeColToFloatCol: TypeCol[Float] => FloatCol = col => col.asInstanceOf[FloatCol]
  implicit val kzimplTypeColToDoubleCol: TypeCol[Double] => DoubleCol = col => col.asInstanceOf[DoubleCol]
  implicit val kzimplTypeColToBigDecimalCol: TypeCol[BigDecimal] => BigDecimalCol = col => col.asInstanceOf[BigDecimalCol]
  implicit val kzimplTypeColToTimeCol: TypeCol[Time] => TimeCol = col => col.asInstanceOf[TimeCol]
  implicit val kzimplTypeColToDateCol: TypeCol[Date] => DateCol = col => col.asInstanceOf[DateCol]
  implicit val kzimplTypeColToTimestampCol: TypeCol[Timestamp] => TimestampCol = col => col.asInstanceOf[TimestampCol]

  implicit val kzimplTypeColToStringSeqCol: TypeCol[Seq[String]] => StringSeqCol = col => col.asInstanceOf[StringSeqCol]
  implicit val kzimplTypeColToBooleanSeqCol: TypeCol[Seq[Boolean]] => BooleanSeqCol = col => col.asInstanceOf[BooleanSeqCol]
  implicit val kzimplTypeColToShortSeqCol: TypeCol[Seq[Short]] => ShortSeqCol = col => col.asInstanceOf[ShortSeqCol]
  implicit val kzimplTypeColToIntSeqCol: TypeCol[Seq[Int]] => IntSeqCol = col => col.asInstanceOf[IntSeqCol]
  implicit val kzimplTypeColToLongSeqCol: TypeCol[Seq[Long]] => LongSeqCol = col => col.asInstanceOf[LongSeqCol]
  implicit val kzimplTypeColToFloatSeqCol: TypeCol[Seq[Float]] => FloatSeqCol = col => col.asInstanceOf[FloatSeqCol]
  implicit val kzimplTypeColToDoubleSeqCol: TypeCol[Seq[Double]] => DoubleSeqCol = col => col.asInstanceOf[DoubleSeqCol]
  implicit val kzimplTypeColToBigDecimalSeqCol: TypeCol[Seq[BigDecimal]] => BigDecimalSeqCol = col => col.asInstanceOf[BigDecimalSeqCol]
  implicit val kzimplTypeColToTimeSeqCol: TypeCol[Seq[Time]] => TimeSeqCol = col => col.asInstanceOf[TimeSeqCol]
  implicit val kzimplTypeColToDateSeqCol: TypeCol[Seq[Date]] => DateSeqCol = col => col.asInstanceOf[DateSeqCol]
  implicit val kzimplTypeColToTimestampSeqCol: TypeCol[Seq[Timestamp]] => TimestampSeqCol = col => col.asInstanceOf[TimestampSeqCol]

  // named cols
  
  implicit val addColName: TypeCol[_] => Tuple2[String, TypeCol[_]] = {
    case col: ModelCol => (col.name, col)
    case _ => throw KuzminkiError("Column used in colsNamed must be a ModelCol")
  }

  // pick one

  implicit val kzimplFilterToSeq: Filter => Seq[Filter] = filter => Seq(filter)
  implicit val kzimplSortingToSeq: Sorting => Seq[Sorting] = sorting => Seq(sorting)
  implicit val kzimplAssignToSeq: Assign => Seq[Assign] = assign => Seq(assign)
  implicit def kzimplTypeColToSeq[T](col: TypeCol[T]): Seq[TypeCol[_]] = Seq(col)

  // raw SQL

  implicit val kzimplRawToQuery: RawSQLStatement => RenderedQuery[Vector[Any]] = rss => rss.toQuery
  implicit val kzimplRawToOperation: RawSQLStatement => RenderedOperation = rss => rss.toOperation

  // render

  implicit def kzimplRenderSelect[R](q: RenderSelect[_, R]): RenderedQuery[R] = q.render
  implicit def kzimplRenderSubquery[R](q: RenderSelect[_, R]): SelectSubquery[R] = q.asSubquery
  implicit val kzimplRenderAggregation: RenderSelect[_, _] => AggregationSubquery = q => q.asAggregation
  implicit val kzimplRenderUpdate: RenderUpdate[_] => RenderedOperation = q => q.render
  implicit val kzimplRenderDelete: RenderDelete[_] => RenderedOperation = q => q.render
  implicit val kzimplRenderInsert: RenderInsertData[_, _] => RenderedOperation = q => q.render

  implicit class RawSQL(val sc: StringContext) extends AnyVal {
    def rsql(args: Any*): RawSQLStatement = {
      val strings = sc.parts.iterator
      val expressions = args.iterator

      var sb = new StringBuilder
      var params = Vector.empty[Any]

      for (part <- strings) {
        sb.append(part)
        if (expressions.hasNext) {
          params = params :+ expressions.next()
          sb.append("?")
        }
      }

      RawSQLStatement(sb.toString, params)
    }
  }
}
















