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

  implicit val implTimeCol: ColInfo => TypeCol[Time] = info => TimeModelCol(info)
  implicit val implDateCol: ColInfo => TypeCol[Date] = info => DateModelCol(info)
  implicit val implTimestampCol: ColInfo => TypeCol[Timestamp] = info => TimestampModelCol(info)

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
















