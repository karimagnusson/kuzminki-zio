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

import kuzminki.fn.types._
import kuzminki.column.TypeCol
import org.postgresql.util.PGInterval

// coalesce

object Fn {

  // string

  def coalesce[T](col: TypeCol[T], default: T) = CoalesceFn(col, default)

  def concat(cols: TypeCol[_]*) = ConcatFn(cols.toVector)

  def concatWs(glue: String, cols: TypeCol[_]*) = ConcatWsFn(glue, cols.toVector)

  def substr(col: TypeCol[String], start: Int) = SubstrFn(col, start, None)

  def substr(col: TypeCol[String], start: Int, len: Int) = SubstrFn(col, start, Some(len))

  def replace(col: TypeCol[String], from: String, to: String) = ReplaceFn(col, from, to)

  def trim(col: TypeCol[String]) = TrimFn(col)

  def upper(col: TypeCol[String]) = UpperFn(col)

  def lower(col: TypeCol[String]) = LowerFn(col)

  def initcap(col: TypeCol[String]) = InitcapFn(col)

  // numeric

  def round[T](col: TypeCol[BigDecimal], size: Int) = RoundFn(col, size)

  def roundStr[T](col: TypeCol[BigDecimal], size: Int) = RoundStrFn(col, size)

  // json

  def json(cols: Seq[Tuple2[String, TypeCol[_]]]) = JsonbResFn(cols)

  def interval(
    years: Int = 0,
    months: Int = 0,
    days: Int = 0,
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Double = 0.0
  ) = new PGInterval(years, months, days, hours, minutes, seconds)
}

















