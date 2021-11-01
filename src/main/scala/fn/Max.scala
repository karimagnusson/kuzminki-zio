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

import java.time.{Instant, LocalDate, LocalDateTime, ZonedDateTime}
import kuzminki.column._
import kuzminki.function.Aggregation
import kuzminki.function.types._


object Max {
  import max._
  def short(col: ShortCol) = MaxShort(col)
  def int(col: IntCol) = MaxInt(col)
  def long(col: LongCol) = MaxLong(col)
  def bigDecimal(col: BigDecimalCol) = MaxBigDecimal(col)
  def float(col: FloatCol) = MaxFloat(col)
  def double(col: DoubleCol) = MaxDouble(col)
  def time(col: TimeCol) = MaxTime(col)
  def date(col: DateCol) = MaxDate(col)
  def timestamp(col: TimestampCol) = MaxTimestamp(col)
}


package object max {

  trait MaxTemplate {
    val template = "max(%s)"
  }

  case class MaxBigDecimal(underlying: AnyCol) extends BigDecimalFunctionSingle
                                               with Aggregation
                                               with MaxTemplate {

    def asString = Cast.asString(this)
    def round = Round.bigDecimal(this)
    def round(prec: Int) = Round.bigDecimal(this, prec)
  }

  case class MaxShort(underlying: AnyCol) extends ShortFunctionSingle
                                             with Aggregation
                                             with MaxTemplate {

    def asString = Cast.asString(this)
  }

  case class MaxInt(underlying: AnyCol) extends IntFunctionSingle
                                           with Aggregation
                                           with MaxTemplate {

    def asString = Cast.asString(this)
  }

  case class MaxLong(underlying: AnyCol) extends LongFunctionSingle
                                            with Aggregation
                                            with MaxTemplate {

    def asString = Cast.asString(this)
  }

  case class MaxFloat(underlying: AnyCol) extends FloatFunctionSingle
                                             with Aggregation
                                             with MaxTemplate {

    def asString = Cast.asString(this)
    def round = Round.float(this)
  }

  case class MaxDouble(underlying: AnyCol) extends DoubleFunctionSingle
                                              with Aggregation
                                              with MaxTemplate {

    def asString = Cast.asString(this)
    def round = Round.double(this)
  }

  case class MaxTime(underlying: AnyCol) extends TimeFunctionSingle
                                            with Aggregation
                                            with MaxTemplate

  case class MaxDate(underlying: AnyCol) extends DateFunctionSingle
                                            with Aggregation
                                            with MaxTemplate

  case class MaxTimestamp(underlying: AnyCol) extends TimestampFunctionSingle
                                                 with Aggregation
                                                 with MaxTemplate
}









































