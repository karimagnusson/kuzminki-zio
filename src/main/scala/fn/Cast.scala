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

package kuzminki.fn

import kuzminki.column._
import kuzminki.fn.types._
import kuzminki.render.NoArgs


object Cast {
  import castFn._
  def asString(col: TypeCol[_]) = CastString(col)
  def asShort(col: TypeCol[_]) = CastShort(col)
  def asInt(col: TypeCol[_]) = CastInt(col)
  def asLong(col: TypeCol[_]) = CastLong(col)
  def asFloat(col: TypeCol[_]) = CastFloat(col)
  def asDouble(col: TypeCol[_]) = CastDouble(col)
  def asBigDecimal(col: TypeCol[_]) = CastBigDecimal(col)
  def asJsonb(col: TypeCol[_]) = CastJsonb(col)
  def asTimestamp(col: TypeCol[_]) = CastTimestamp(col)
  def asDate(col: TypeCol[_]) = CastDate(col)
  def asTime(col: TypeCol[_]) = CastTime(col)
}

package object castFn {

  trait CastFn extends FnRender {
    val castAs: String
    val col: TypeCol[_]
    val args = col.args
    def template = col match {
      case c: JsonbFn => "(%s)::" + castAs
      case _ => "%s::" + castAs
    }
    def name = "cast_%s".format(col.name)
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
}



