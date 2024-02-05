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

import kuzminki.column._
import kuzminki.filter._
import kuzminki.sorting.Sorting
import kuzminki.assign.Assign
import kuzminki.update.RenderUpdate
import kuzminki.delete.RenderDelete
import kuzminki.insert.{RenderInsert, Values}
import kuzminki.run._
import kuzminki.select._
import kuzminki.render._


package object api extends filters {

   // create model col

  given kzStringCol: Conversion[ColInfo, TypeCol[String]] = (x: ColInfo) => StringModelCol(x)
  given kzBooleanCol: Conversion[ColInfo, TypeCol[Boolean]] = (x: ColInfo) => BooleanModelCol(x)
  given kzShortCol: Conversion[ColInfo, TypeCol[Short]] = (x: ColInfo) => ShortModelCol(x)
  given kzIntCol: Conversion[ColInfo, TypeCol[Int]] = (x: ColInfo) => IntModelCol(x)
  given kzLongCol: Conversion[ColInfo, TypeCol[Long]] = (x: ColInfo) => LongModelCol(x)
  given kzFloatCol: Conversion[ColInfo, TypeCol[Float]] = (x: ColInfo) => FloatModelCol(x)
  given kzDoubleCol: Conversion[ColInfo, TypeCol[Double]] = (x: ColInfo) => DoubleModelCol(x)
  given kzBigDecimalCol: Conversion[ColInfo, TypeCol[BigDecimal]] = (x: ColInfo) => BigDecimalModelCol(x)
  given kzTimeCol: Conversion[ColInfo, TypeCol[Time]] = (x: ColInfo) => TimeModelCol(x)
  given kzDateCol: Conversion[ColInfo, TypeCol[Date]] = (x: ColInfo) => DateModelCol(x)
  given kzTimestampCol: Conversion[ColInfo, TypeCol[Timestamp]] = (x: ColInfo) => TimestampModelCol(x)
  given kzJsonbCol: Conversion[ColInfo, TypeCol[Jsonb]] = (x: ColInfo) => JsonbModelCol(x)
  given kzUUIDCol: Conversion[ColInfo, TypeCol[UUID]] = (x: ColInfo) => UUIDModelCol(x)

  given kzStringSeqCol: Conversion[ColInfo, TypeCol[Seq[String]]] = (x: ColInfo) => StringSeqModelCol(x)
  given kzBooleanSeqCol: Conversion[ColInfo, TypeCol[Seq[Boolean]]] = (x: ColInfo) => BooleanSeqModelCol(x)
  given kzShortSeqCol: Conversion[ColInfo, TypeCol[Seq[Short]]] = (x: ColInfo) => ShortSeqModelCol(x)
  given kzIntSeqCol: Conversion[ColInfo, TypeCol[Seq[Int]]] = (x: ColInfo) => IntSeqModelCol(x)
  given kzLongSeqCol: Conversion[ColInfo, TypeCol[Seq[Long]]] = (x: ColInfo) => LongSeqModelCol(x)
  given kzFloatSeqCol: Conversion[ColInfo, TypeCol[Seq[Float]]] = (x: ColInfo) => FloatSeqModelCol(x)
  given kzDoubleSeqCol: Conversion[ColInfo, TypeCol[Seq[Double]]] = (x: ColInfo) => DoubleSeqModelCol(x)
  given kzBigDecimalSeqCol: Conversion[ColInfo, TypeCol[Seq[BigDecimal]]] = (x: ColInfo) => BigDecimalSeqModelCol(x)
  given kzTimeSeqCol: Conversion[ColInfo, TypeCol[Seq[Time]]] = (x: ColInfo) => TimeSeqModelCol(x)
  given kzDateSeqCol: Conversion[ColInfo, TypeCol[Seq[Date]]] = (x: ColInfo) => DateSeqModelCol(x)
  given kzTimestampSeqCol: Conversion[ColInfo, TypeCol[Seq[Timestamp]]] = (x: ColInfo) => TimestampSeqModelCol(x)
  given kzJsonbSeqCol: Conversion[ColInfo, TypeCol[Seq[Jsonb]]] = (x: ColInfo) => JsonbSeqModelCol(x)

  // render

  given kzRenderSelect[R]: Conversion[RenderSelect[_, R], RenderedQuery[R]] = x => x.render
  given kzRenderSubquery[R]: Conversion[RenderSelect[_, R], Subquery[R]] = x => x.asSubquery
  given kzRenderSubqueryInsert[P, R]: Conversion[SelectCache[P, R], SubqueryInsertFc[P, R]] = x => x.asSubqueryInsertFc
  given kzRenderSubqueryIn[P, R]: Conversion[SelectCacheSingle[P, R], SubqueryInFc[P, R]] = x => x.asSubqueryInFc
  given kzRenderAggregation: Conversion[RenderSelect[_, _], AggregationSubquery] = x => x.asAggregation
  given kzRenderUpdate: Conversion[RenderUpdate[_], RenderedOperation] = x => x.render
  given kzRenderDelete: Conversion[RenderDelete[_], RenderedOperation] = x => x.render
  given kzRenderInsert: Conversion[RenderInsert, RenderedOperation] = x => x.render
  given kzRenderValues: Conversion[Values[_], RenderedOperation] = x => x.render

  // named cols
  
  given kzAddColName[T]: Conversion[TypeCol[T], Tuple2[String, TypeCol[T]]] = (x: TypeCol[T]) => (x.name, x)

  // pick one

  given kzFilterToSeq: Conversion[Filter, Seq[Filter]] = (x: Filter) => Seq(x)
  given kzTypeColToSeq: Conversion[TypeCol[_], Seq[TypeCol[_]]] = (x: TypeCol[_]) => Seq(x)
  given kzFilterOptToSeq: Conversion[Option[Filter], Seq[Option[Filter]]] = (x: Option[Filter]) => Seq(x)
  given kzSortingToSeq: Conversion[Sorting, Seq[Sorting]] = (x: Sorting) => Seq(x)
  given kzAssignToSeq: Conversion[Assign, Seq[Assign]] = (x: Assign) => Seq(x)

  // raw SQL

  given kzRawToQuery: Conversion[RawSQLStatement, RenderedQuery[Vector[Any]]] = x => x.toQuery
  given kzRawToOperation: Conversion[RawSQLStatement, RenderedOperation] = x => x.toOperation

  extension (sc: StringContext) {
    def rsql(args: Any*): RawSQLStatement = {
      val strings = sc.parts.iterator
      val expressions = args.iterator

      val sb = new StringBuilder
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










