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
import java.util.UUID
import scala.language.implicitConversions

import kuzminki.column._
import kuzminki.filter._
import kuzminki.sorting.Sorting
import kuzminki.assign.Assign
import kuzminki.update.RenderUpdate
import kuzminki.delete.RenderDelete
import kuzminki.insert.{RenderInsert, Values}
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

  implicit val kzStringCol: ColInfo => TypeCol[String] = info => StringModelCol(info)
  implicit val kzBooleanCol: ColInfo => TypeCol[Boolean] = info => BooleanModelCol(info)
  implicit val kzShortCol: ColInfo => TypeCol[Short] = info => ShortModelCol(info)
  implicit val kzIntCol: ColInfo => TypeCol[Int] = info => IntModelCol(info)
  implicit val kzLongCol: ColInfo => TypeCol[Long] = info => LongModelCol(info)
  implicit val kzFloatCol: ColInfo => TypeCol[Float] = info => FloatModelCol(info)
  implicit val kzDoubleCol: ColInfo => TypeCol[Double] = info => DoubleModelCol(info)
  implicit val kzBigDecimalCol: ColInfo => TypeCol[BigDecimal] = info => BigDecimalModelCol(info)
  implicit val kzTimeCol: ColInfo => TypeCol[Time] = info => TimeModelCol(info)
  implicit val kzDateCol: ColInfo => TypeCol[Date] = info => DateModelCol(info)
  implicit val kzTimestampCol: ColInfo => TypeCol[Timestamp] = info => TimestampModelCol(info)
  implicit val kzJsonbCol: ColInfo => TypeCol[Jsonb] = info => JsonbModelCol(info)
  implicit val kzUUIDCol: ColInfo => TypeCol[UUID] = info => UUIDModelCol(info)

  implicit val kzStringSeqCol: ColInfo => TypeCol[Seq[String]] = info => StringSeqModelCol(info)
  implicit val kzBooleanSeqCol: ColInfo => TypeCol[Seq[Boolean]] = info => BooleanSeqModelCol(info)
  implicit val kzShortSeqCol: ColInfo => TypeCol[Seq[Short]] = info => ShortSeqModelCol(info)
  implicit val kzIntSeqCol: ColInfo => TypeCol[Seq[Int]] = info => IntSeqModelCol(info)
  implicit val kzLongSeqCol: ColInfo => TypeCol[Seq[Long]] = info => LongSeqModelCol(info)
  implicit val kzFloatSeqCol: ColInfo => TypeCol[Seq[Float]] = info => FloatSeqModelCol(info)
  implicit val kzDoubleSeqCol: ColInfo => TypeCol[Seq[Double]] = info => DoubleSeqModelCol(info)
  implicit val kzBigDecimalSeqCol: ColInfo => TypeCol[Seq[BigDecimal]] = info => BigDecimalSeqModelCol(info)
  implicit val kzTimeSeqCol: ColInfo => TypeCol[Seq[Time]] = info => TimeSeqModelCol(info)
  implicit val kzDateSeqCol: ColInfo => TypeCol[Seq[Date]] = info => DateSeqModelCol(info)
  implicit val kzTimestampSeqCol: ColInfo => TypeCol[Seq[Timestamp]] = info => TimestampSeqModelCol(info)

  // filters

  implicit class KzStringImpl(val col: TypeCol[String]) extends StringMethods
  implicit class KzBooleanImpl(val col: TypeCol[Boolean]) extends TypeMethods[Boolean]
  implicit class KzShortImpl(val col: TypeCol[Short]) extends ComparativeMethods[Short]
  implicit class KzIntImpl(val col: TypeCol[Int]) extends ComparativeMethods[Int]
  implicit class KzLongImpl(val col: TypeCol[Long]) extends ComparativeMethods[Long]
  implicit class KzFloatImpl(val col: TypeCol[Float]) extends ComparativeMethods[Float]
  implicit class KzDoubleImpl(val col: TypeCol[Double]) extends ComparativeMethods[Double]
  implicit class KzBigDecimalImpl(val col: TypeCol[BigDecimal]) extends ComparativeMethods[BigDecimal]
  implicit class KzTimeImpl(val col: TypeCol[Time]) extends ComparativeMethods[Time]
  implicit class KzDateImpl(val col: TypeCol[Date]) extends ComparativeMethods[Date]
  implicit class KzTimestampImpl(val col: TypeCol[Timestamp]) extends ComparativeMethods[Timestamp]
  implicit class KzJsonbImpl(val col: TypeCol[Jsonb]) extends JsonbMethods
  implicit class KzUUIDImpl(val col: TypeCol[UUID]) extends TypeMethods[UUID]

  implicit class KzStringSeqImpl(val col: TypeCol[Seq[String]]) extends SeqMethods[String]
  implicit class KzBooleanSeqImpl(val col: TypeCol[Seq[Boolean]]) extends SeqMethods[Boolean]
  implicit class KzShortSeqImpl(val col: TypeCol[Seq[Short]]) extends SeqMethods[Short]
  implicit class KzIntSeqImpl(val col: TypeCol[Seq[Int]]) extends SeqMethods[Int]
  implicit class KzLongSeqImpl(val col: TypeCol[Seq[Long]]) extends SeqMethods[Long]
  implicit class KzFloatSeqImpl(val col: TypeCol[Seq[Float]]) extends SeqMethods[Float]
  implicit class KzDoubleSeqImpl(val col: TypeCol[Seq[Double]]) extends SeqMethods[Double]
  implicit class KzBigDecimalSeqImpl(val col: TypeCol[Seq[BigDecimal]]) extends SeqMethods[BigDecimal]
  implicit class KzTimeSeqImpl(val col: TypeCol[Seq[Time]]) extends SeqMethods[Time]
  implicit class KzDateSeqImpl(val col: TypeCol[Seq[Date]]) extends SeqMethods[Date]
  implicit class KzTimestampSeqImpl(val col: TypeCol[Seq[Timestamp]]) extends SeqMethods[Timestamp]

  // render

  implicit def kzRenderSelect[R](q: RenderSelect[_, R]): RenderedQuery[R] = q.render
  implicit def kzRenderSubquery[R](q: RenderSelect[_, R]): SelectSubquery[R] = q.asSubquery
  implicit val kzRenderAggregation: RenderSelect[_, _] => AggregationSubquery = q => q.asAggregation
  implicit val kzRenderUpdate: RenderUpdate[_] => RenderedOperation = q => q.render
  implicit val kzRenderDelete: RenderDelete[_] => RenderedOperation = q => q.render
  implicit val kzRenderInsert: RenderInsert => RenderedOperation = q => q.render
  implicit val kzRenderValues: Values[_] => RenderedOperation = q => q.render

  // named cols
  
  implicit val kzAddColName: TypeCol[_] => Tuple2[String, TypeCol[_]] = col => (col.name, col)

  // pick one

  implicit def kzTypeColToSeq[T](col: TypeCol[T]): Seq[TypeCol[_]] = Seq(col)
  implicit val kzFilterToSeq: Filter => Seq[Filter] = filter => Seq(filter)
  implicit val kzFilterOptToSeq: Option[Filter] => Seq[Option[Filter]] = filterOpt => Seq(filterOpt)
  implicit val kzSortingToSeq: Sorting => Seq[Sorting] = sorting => Seq(sorting)
  implicit val kzAssignToSeq: Assign => Seq[Assign] = assign => Seq(assign)

  // streaming

  implicit class KzStreamSelect[M, R](val query: Offset[M, R]) extends RunStream[M, R]

  // raw SQL

  implicit val kzRawToQuery: RawSQLStatement => RenderedQuery[Vector[Any]] = rss => rss.toQuery
  implicit val kzRawToOperation: RawSQLStatement => RenderedOperation = rss => rss.toOperation

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
















