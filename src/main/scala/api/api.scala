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
import java.sql.SQLException
import scala.language.implicitConversions

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
  implicit val kzJsonbSeqCol: ColInfo => TypeCol[Seq[Jsonb]] = info => JsonbSeqModelCol(info)

  // filters

  implicit class KzStringImpl(val col: TypeCol[String]) extends StringMethods
  implicit class KzBooleanImpl(val col: TypeCol[Boolean]) extends BooleanMethods
  implicit class KzShortImpl(val col: TypeCol[Short]) extends NumericMethods[Short]
  implicit class KzIntImpl(val col: TypeCol[Int]) extends NumericMethods[Int]
  implicit class KzLongImpl(val col: TypeCol[Long]) extends NumericMethods[Long]
  implicit class KzFloatImpl(val col: TypeCol[Float]) extends NumericMethods[Float]
  implicit class KzDoubleImpl(val col: TypeCol[Double]) extends NumericMethods[Double]
  implicit class KzBigDecimalImpl(val col: TypeCol[BigDecimal]) extends NumericMethods[BigDecimal]
  implicit class KzTimeImpl(val col: TypeCol[Time]) extends TimeMethods
  implicit class KzDateImpl(val col: TypeCol[Date]) extends DateMethods
  implicit class KzTimestampImpl(val col: TypeCol[Timestamp]) extends TimestampMethods
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
  implicit class KzJsonbSeqImpl(val col: TypeCol[Seq[Jsonb]]) extends JsonbSeqMethods

  // render

  implicit def kzRenderSelect[R](q: RenderSelect[_, R]): RenderedQuery[R] = q.render
  implicit def kzRenderSubquery[R](q: RenderSelect[_, R]): Subquery[R] = q.asSubquery
  implicit def kzRenderSubqueryInsert[P, R](q: SelectCache[P, R]): SubqueryInsertFc[P, R] = q.asSubqueryInsertFc
  implicit def kzRenderSubqueryIn[P, R](q: SelectCacheSingle[P, R]): SubqueryInFc[P, R] = q.asSubqueryInFc
  implicit val kzRenderAggregation: RenderSelect[_, _] => AggregationSubquery = q => q.asAggregation
  implicit val kzRenderUpdate: RenderUpdate[_] => RenderedOperation = q => q.render
  implicit val kzRenderDelete: RenderDelete[_] => RenderedOperation = q => q.render
  implicit val kzRenderInsert: RenderInsert => RenderedOperation = q => q.render
  implicit val kzRenderValues: Values[_] => RenderedOperation = q => q.render

  // named cols
  
  implicit def kzAddColName[T](col: TypeCol[T]): Tuple2[String, TypeCol[T]] = (col.name, col)

  // pick one

  implicit def kzTypeColToSeq[T](col: TypeCol[T]): Seq[TypeCol[_]] = Seq(col)
  implicit val kzFilterToSeq: Filter => Seq[Filter] = filter => Seq(filter)
  implicit val kzFilterOptToSeq: Option[Filter] => Seq[Option[Filter]] = filterOpt => Seq(filterOpt)
  implicit val kzSortingToSeq: Sorting => Seq[Sorting] = sorting => Seq(sorting)
  implicit val kzAssignToSeq: Assign => Seq[Assign] = assign => Seq(assign)

  // streaming

  implicit class KzStreamSelect[M, R](val query: Offset[M, R]) extends RunStream[M, R]

  // debug

  implicit class DebugRunQuery[R](query: RunQuery[R]) {
    def printSql = {
      query.render.printStatement
      query
    }
    def printSqlAndArgs = {
      query.render.printStatementAndArgs
      query
    }
    def printSqlWithArgs = {
      query.render.printStatementWithArgs
      query
    }
  }

  implicit class DebugRunQueryParams[P, R](query: RunQueryParams[P, R]) {
    def printSql = {
      println(query.statement)
      query
    }
    def printSqlAndArgs(params: P) = {
      query.render(params).printStatementAndArgs
      query
    }
    def printSqlWithArgs(params: P) = {
      query.render(params).printStatementWithArgs
      query
    }
  }

  implicit class DebugRunOperation(query: RunOperation) {
    def printSql = {
      query.render.printStatement
      query
    }
    def printSqlAndArgs = {
      query.render.printStatementAndArgs
      query
    }
    def printSqlWithArgs = {
      query.render.printStatementWithArgs
      query
    }
  }

  implicit class DebugRunOperationParams[P](query: RunOperationParams[P]) {
    def printSql = {
      println(query.statement)
      query
    }
    def printSqlAndArgs(params: P) = {
      query.render(params).printStatementAndArgs
      query
    }
    def printSqlWithArgs(params: P) = {
      query.render(params).printStatementWithArgs
      query
    }
  }

  implicit class DebugRunUpdate[P1, P2](query: RunUpdate[P1, P2]) {
    def printSql = {
      println(query.statement)
      query
    }
    def printSqlAndArgs(p1: P1, p2: P2) = {
      query.render(p1, p2).printStatementAndArgs
      query
    }
    def printSqlWithArgs(p1: P1, p2: P2) = {
      query.render(p1, p2).printStatementWithArgs
      query
    }
  }

  implicit class DebugRunUpdateParams[P1, P2, R](query: RunUpdateReturning[P1, P2, R]) {
    def printSql = {
      println(query.statement)
      query
    }
    def printSqlAndArgs(p1: P1, p2: P2) = {
      query.render(p1, p2).printStatementAndArgs
      query
    }
    def printSqlWithArgs(p1: P1, p2: P2) = {
      query.render(p1, p2).printStatementWithArgs
      query
    }
  }

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
















