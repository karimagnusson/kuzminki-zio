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


object Agg {
  import aggFn._

  def min[T](col: TypeCol[T]) = AggMinFn(col)
  def max[T](col: TypeCol[T]) = AggMaxFn(col)
  def sum[T](col: TypeCol[T]) = AggSumBigDecimalFn(col)
  def avg[T](col: TypeCol[T]) = AggAvgBigDecimalFn(col)

  def sumFloat(col: TypeCol[Float]) = AggSumFloatFn(col)
  def sumDouble(col: TypeCol[Double]) = AggSumDoubleFn(col)
  def sumShort(col: TypeCol[Short]) = AggSumAsLongFn(col)
  def sumInt(col: TypeCol[Int]) = AggSumAsLongFn(col)

  def avgFloat(col: TypeCol[Float]) = AggAvgAsDoubleFn(col)
  def avgDouble(col: TypeCol[Double]) = AggAvgAsDoubleFn(col)
}

package object aggFn {
  
  sealed trait AggFn

  case class AggMinFn[T](col: TypeCol[T]) extends PassConvFn[T] with SingleColFn with AggFn {
    val template = "min(%s)"
  }

  case class AggMaxFn[T](col: TypeCol[T]) extends PassConvFn[T] with SingleColFn with AggFn {
    val template = "max(%s)"
  }

  case class AggSumBigDecimalFn(col: TypeCol[_]) extends BigDecimalCol with SingleColFn with AggFn {
    val template = "sum(%s)::numeric"
  }

  case class AggAvgBigDecimalFn(col: TypeCol[_]) extends BigDecimalCol with SingleColFn with AggFn {
    val template = "avg(%s)::numeric"
  }

  case class AggSumFloatFn(col: TypeCol[_]) extends FloatCol with SingleColFn with AggFn {
    val template = "sum(%s)"
  }

  case class AggSumDoubleFn(col: TypeCol[_]) extends DoubleCol with SingleColFn with AggFn {
    val template = "sum(%s)"
  }

  case class AggSumAsLongFn(col: TypeCol[_]) extends LongCol with SingleColFn with AggFn {
    val template = "sum(%s)"
  }

  case class AggAvgAsDoubleFn(col: TypeCol[_]) extends DoubleCol with SingleColFn with AggFn {
    val template = "avg(%s)"
  }
}







































