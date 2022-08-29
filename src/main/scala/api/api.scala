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
import kuzminki.filter._
import kuzminki.sorting.Sorting
import kuzminki.assign.Assign
import kuzminki.update.RenderUpdate
import kuzminki.delete.RenderDelete
import kuzminki.insert.RenderInsertData
import kuzminki.run.RunStream
import kuzminki.select.{
  RenderSelect,
  SelectSubquery,
  AggregationSubquery,
  Offset
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

  // filters

  implicit class StringMethods(val col: TypeCol[String]) extends StringFilters
  implicit class BooleanMethods(val col: TypeCol[Boolean]) extends TypeFilter[Boolean]
  implicit class ShortMethods(val col: TypeCol[Short]) extends ComparativeFilters[Short]
  implicit class IntMethods(val col: TypeCol[Int]) extends ComparativeFilters[Int]
  implicit class LongMethods(val col: TypeCol[Long]) extends ComparativeFilters[Long]
  implicit class FloatMethods(val col: TypeCol[Float]) extends ComparativeFilters[Float]
  implicit class DoubleMethods(val col: TypeCol[Double]) extends ComparativeFilters[Double]
  implicit class BigDecimalMethods(val col: TypeCol[BigDecimal]) extends ComparativeFilters[BigDecimal]
  implicit class TimeMethods(val col: TypeCol[Time]) extends ComparativeFilters[Time]
  implicit class DateMethods(val col: TypeCol[Date]) extends ComparativeFilters[Date]
  implicit class TimestampMethods(val col: TypeCol[Timestamp]) extends ComparativeFilters[Timestamp]

  implicit class StringSeqMethods(val col: TypeCol[Seq[String]]) extends SeqFilters[String]
  implicit class BooleanSeqMethods(val col: TypeCol[Seq[Boolean]]) extends SeqFilters[Boolean]
  implicit class ShortSeqMethods(val col: TypeCol[Seq[Short]]) extends SeqFilters[Short]
  implicit class IntSeqMethods(val col: TypeCol[Seq[Int]]) extends SeqFilters[Int]
  implicit class LongSeqMethods(val col: TypeCol[Seq[Long]]) extends SeqFilters[Long]
  implicit class FloatSeqMethods(val col: TypeCol[Seq[Float]]) extends SeqFilters[Float]
  implicit class DoubleSeqMethods(val col: TypeCol[Seq[Double]]) extends SeqFilters[Double]
  implicit class BigDecimalSeqMethods(val col: TypeCol[Seq[BigDecimal]]) extends SeqFilters[BigDecimal]
  implicit class TimeSeqMethods(val col: TypeCol[Seq[Time]]) extends SeqFilters[Time]
  implicit class DateSeqMethods(val col: TypeCol[Seq[Date]]) extends SeqFilters[Date]
  implicit class TimestampSeqMethods(val col: TypeCol[Seq[Timestamp]]) extends SeqFilters[Timestamp]

  // render

  implicit def kzimplRenderSelect[R](q: RenderSelect[_, R]): RenderedQuery[R] = q.render
  implicit def kzimplRenderSubquery[R](q: RenderSelect[_, R]): SelectSubquery[R] = q.asSubquery
  implicit val kzimplRenderAggregation: RenderSelect[_, _] => AggregationSubquery = q => q.asAggregation
  implicit val kzimplRenderUpdate: RenderUpdate[_] => RenderedOperation = q => q.render
  implicit val kzimplRenderDelete: RenderDelete[_] => RenderedOperation = q => q.render
  implicit val kzimplRenderInsert: RenderInsertData[_, _] => RenderedOperation = q => q.render

  // named cols
  
  implicit val addColName: TypeCol[_] => Tuple2[String, TypeCol[_]] = col => (col.name, col)

  // pick one

  implicit val kzimplFilterToSeq: Filter => Seq[Filter] = filter => Seq(filter)
  implicit val kzimplFilterOptToSeq: Option[Filter] => Seq[Option[Filter]] = filterOpt => Seq(filterOpt)
  implicit val kzimplSortingToSeq: Sorting => Seq[Sorting] = sorting => Seq(sorting)
  implicit val kzimplAssignToSeq: Assign => Seq[Assign] = assign => Seq(assign)
  implicit def kzimplTypeColToSeq[T](col: TypeCol[T]): Seq[TypeCol[_]] = Seq(col)

  // streaming

  implicit class StreamSelect[M, R](val query: Offset[M, R]) extends RunStream[M, R]

  // raw SQL

  implicit val kzimplRawToQuery: RawSQLStatement => RenderedQuery[Vector[Any]] = rss => rss.toQuery
  implicit val kzimplRawToOperation: RawSQLStatement => RenderedOperation = rss => rss.toOperation

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
















