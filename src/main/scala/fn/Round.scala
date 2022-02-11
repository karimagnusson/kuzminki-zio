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

import kuzminki.column.AnyCol
import kuzminki.render.UnderlyingFunctionRender
import kuzminki.function.types._


object Round {

  import round._

  def bigDecimal(col: AnyCol) = RoundBigDecimal(col)
  def bigDecimal(col: AnyCol, size: Int) = RoundDigitsBigDecimal(col, size)

  def float(col: AnyCol) = RoundFloat(col)
  def float(col: AnyCol, size: Int) = RoundDigitsFloat(col, size)

  def double(col: AnyCol) = RoundDouble(col)
  def double(col: AnyCol, size: Int) = RoundDigitsDouble(col, size)
}


package object round {
  
  trait RoundInteger extends AnyCol {
    val template = "round(%s)"
    def asString = Cast.asString(this)
  }

  case class RoundBigDecimal(underlying: AnyCol) extends BigDecimalFunctionSingle
                                                 with RoundInteger

  case class RoundFloat(underlying: AnyCol) extends FloatFunctionSingle
                                               with RoundInteger

  case class RoundDouble(underlying: AnyCol) extends DoubleFunctionSingle
                                                with RoundInteger


  trait RoundDecimal extends AnyCol with UnderlyingFunctionRender {
    val size: Int
    val template = "round(%s, ?)"
    val args = underlying.args ++ Seq(size)
    def asString = Cast.asString(this)
  }

  case class RoundDigitsBigDecimal(underlying: AnyCol, size: Int) extends BigDecimalFunction
                                                                  with RoundDecimal

  case class RoundDigitsFloat(underlying: AnyCol, size: Int) extends FloatFunction
                                                                with RoundDecimal

  case class RoundDigitsDouble(underlying: AnyCol, size: Int) extends DoubleFunction
                                                                 with RoundDecimal
}




















