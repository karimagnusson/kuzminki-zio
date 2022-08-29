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

import kuzminki.column.TypeCol
import kuzminki.fn.types._


object Agg {
  import aggFn._

  def min[T](col: TypeCol[T]) = AggTypeFn(col, "min(%s)")
  def max[T](col: TypeCol[T]) = AggTypeFn(col, "max(%s)")
  def sum[T](col: TypeCol[T]) = AggBigDecimalFn(col, "sum(%s)::numeric")
  def avg[T](col: TypeCol[T]) = AggBigDecimalFn(col, "avg(%s)::numeric")

  def sumFloat(col: TypeCol[Float]) = AggFloatFn(col, "sum(%s)")
  def sumDouble(col: TypeCol[Double]) = AggDoubleFn(col, "sum(%s)")
  def sumShort(col: TypeCol[Short]) = AggLongFn(col, "sum(%s)")
  def sumInt(col: TypeCol[Int]) = AggLongFn(col, "sum(%s)")

  def avgFloat(col: TypeCol[Float]) = AggDoubleFn(col, "sum(%s)")
  def avgDouble(col: TypeCol[Double]) = AggDoubleFn(col, "sum(%s)")
}

package object aggFn {
  sealed trait AggFn
  case class AggTypeFn[T](col: TypeCol[T], template: String) extends TypeFn[T] with AggFn
  case class AggLongFn(col: TypeCol[_], template: String) extends LongFn with AggFn
  case class AggFloatFn(col: TypeCol[_], template: String) extends FloatFn with AggFn
  case class AggDoubleFn(col: TypeCol[_], template: String) extends DoubleFn with AggFn
  case class AggBigDecimalFn(col: TypeCol[_], template: String) extends BigDecimalFn with AggFn
}







































