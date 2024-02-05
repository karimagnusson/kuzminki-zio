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

package kuzminki.fn.types

import kuzminki.column._


trait CastFn extends SingleColFn {
  val castAs: String
  def template = col match {
    case _ : JsonbOpr => "(%s)::" + castAs
    case _ => "%s::" + castAs
  }
}

case class CastString(col: TypeCol[_]) extends StringCol with CastFn {
  val castAs = "text"
}

case class CastShort(col: TypeCol[_]) extends ShortCol with CastFn {
  val castAs = "smallint"
}

case class CastInt(col: TypeCol[_]) extends IntCol with CastFn {
  val castAs = "int"
}

case class CastLong(col: TypeCol[_]) extends LongCol with CastFn {
  val castAs = "bigint"
}

case class CastFloat(col: TypeCol[_]) extends FloatCol with CastFn {
  val castAs = "real"
}

case class CastDouble(col: TypeCol[_]) extends DoubleCol with CastFn {
  val castAs = "float8"
}

case class CastBigDecimal(col: TypeCol[_]) extends BigDecimalCol with CastFn {
  val castAs = "numeric"
}

case class CastJsonb(col: TypeCol[_]) extends BigDecimalCol with CastFn {
  val castAs = "jsonb"
}

case class CastTimestamp(col: TypeCol[_]) extends TimestampCol with CastFn {
  val castAs = "timestamp"
}

case class CastDate(col: TypeCol[_]) extends DateCol with CastFn {
  val castAs = "date"
}

case class CastTime(col: TypeCol[_]) extends TimeCol with CastFn {
  val castAs = "time"
}