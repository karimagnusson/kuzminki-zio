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

package kuzminki.filter

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import java.util.UUID

import kuzminki.column.TypeCol
import kuzminki.api.Jsonb


trait filters {
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
}