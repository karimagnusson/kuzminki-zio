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
}

package object castFn {

  trait CastFn extends FnRender {
    val col: TypeCol[_]
    val args = col.args
  }

  case class CastString(col: TypeCol[_]) extends StringCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::text"
      case _ => "%s::text"
    }
    def name = "string_%s".format(col.name)
  }

  case class CastShort(col: TypeCol[_]) extends ShortCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::smallint"
      case _ => "%s::smallint"
    }
    def name = "short_%s".format(col.name)
  }

  case class CastInt(col: TypeCol[_]) extends IntCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::int"
      case _ => "%s::int"
    }
    def name = "int_%s".format(col.name)
  }

  case class CastLong(col: TypeCol[_]) extends LongCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::bigint"
      case _ => "%s::bigint"
    }
    def name = "long_%s".format(col.name)
  }

  case class CastFloat(col: TypeCol[_]) extends FloatCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::real"
      case _ => "%s::real"
    }
    def name = "float_%s".format(col.name)
  }

  case class CastDouble(col: TypeCol[_]) extends DoubleCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::float8"
      case _ => "%s::float8"
    }
    def name = "double_%s".format(col.name)
  }

  case class CastBigDecimal(col: TypeCol[_]) extends BigDecimalCol with CastFn {
    val template = col match {
      case c: JsonbFilterFn => "(%s)::numeric"
      case _ => "%s::numeric"
    }
    def name = "bigdecimal_%s".format(col.name)
  }

  case class CastJsonb(col: TypeCol[_]) extends BigDecimalCol with CastFn {
    val template = "%s::jsonb"
    def name = "jsonb_%s".format(col.name)
  }
}



