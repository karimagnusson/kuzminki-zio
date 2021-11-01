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
import kuzminki.function.Aggregation
import kuzminki.function.types._


object Sum {
  import sum._
  def bigDecimal(col: BigDecimalCol) = SumBigDecimal(col)
  def float(col: FloatCol) = SumFloat(col)
  def double(col: DoubleCol) = SumDouble(col)
  def short(col: ShortCol) = SumLong(col)
  def int(col: IntCol) = SumLong(col)
  def long(col: LongCol) = SumBigDecimal(col)
}


package object sum {
  
  trait SumTemplate {
    val template = "sum(%s)"
  }

  case class SumBigDecimal(underlying: AnyCol) extends BigDecimalFunctionSingle
                                                  with Aggregation
                                                  with SumTemplate {

    def asString = Cast.asString(this)
    def round = Round.bigDecimal(this)
    def round(prec: Int) = Round.bigDecimal(this, prec)
  }

  case class SumLong(underlying: AnyCol) extends LongFunctionSingle
                                            with Aggregation
                                            with SumTemplate {

    def asString = Cast.asString(this)
  }

  case class SumFloat(underlying: AnyCol) extends FloatFunctionSingle
                                             with Aggregation
                                             with SumTemplate {

    def asString = Cast.asString(this)
    def round = Round.float(this)
  }

  case class SumDouble(underlying: AnyCol) extends DoubleFunctionSingle
                                              with Aggregation
                                              with SumTemplate {

    def asString = Cast.asString(this)
    def round = Round.double(this)
  }
}






















