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


object Min {
  import min._
  def short(col: ShortCol) = MinShort(col)
  def int(col: IntCol) = MinInt(col)
  def long(col: LongCol) = MinLong(col)
  def bigDecimal(col: BigDecimalCol) = MinBigDecimal(col)
  def float(col: FloatCol) = MinFloat(col)
  def double(col: DoubleCol) = MinDouble(col)
  def time(col: TimeCol) = MinTime(col)
  def date(col: DateCol) = MinDate(col)
  def timestamp(col: TimestampCol) = MinTimestamp(col)
}


package object min {

  trait MinTemplate {
    val template = "min(%s)"
  }

  case class MinBigDecimal(underlying: AnyCol) extends BigDecimalFunctionSingle
                                               with Aggregation
                                               with MinTemplate {

    def asString = Cast.asString(this)
    def round = Round.bigDecimal(this)
    def round(prec: Int) = Round.bigDecimal(this, prec)
  }

  case class MinShort(underlying: AnyCol) extends ShortFunctionSingle
                                             with Aggregation
                                             with MinTemplate {

    def asString = Cast.asString(this)
  }

  case class MinInt(underlying: AnyCol) extends IntFunctionSingle
                                           with Aggregation
                                           with MinTemplate {

    def asString = Cast.asString(this)
  }

  case class MinLong(underlying: AnyCol) extends LongFunctionSingle
                                            with Aggregation
                                            with MinTemplate {

    def asString = Cast.asString(this)
  }

  case class MinFloat(underlying: AnyCol) extends FloatFunctionSingle
                                             with Aggregation
                                             with MinTemplate {

    def asString = Cast.asString(this)
    def round = Round.float(this)
  }

  case class MinDouble(underlying: AnyCol) extends DoubleFunctionSingle
                                              with Aggregation
                                              with MinTemplate {

    def asString = Cast.asString(this)
    def round = Round.double(this)
  }

  case class MinTime(underlying: AnyCol) extends TimeFunctionSingle
                                            with Aggregation
                                            with MinTemplate

  case class MinDate(underlying: AnyCol) extends DateFunctionSingle
                                            with Aggregation
                                            with MinTemplate

  case class MinTimestamp(underlying: AnyCol) extends TimestampFunctionSingle
                                                 with Aggregation
                                                 with MinTemplate
}





































