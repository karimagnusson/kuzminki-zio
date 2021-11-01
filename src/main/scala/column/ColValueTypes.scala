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

package kuzminki.column

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import kuzminki.conv._


trait StringColValue extends TypeCol[String] {
  def conv = StringConv
}

trait BooleanColValue extends TypeCol[Boolean] {
  def conv = BooleanConv
}

trait ShortColValue extends TypeCol[Short] {
  def conv = ShortConv
}

trait IntColValue extends TypeCol[Int] {
  def conv = IntConv
}

trait LongColValue extends TypeCol[Long] {
  def conv = LongConv
}

trait FloatColValue extends TypeCol[Float] {
  def conv = FloatConv
}

trait DoubleColValue extends TypeCol[Double] {
  def conv = DoubleConv
}

trait BigDecimalColValue extends TypeCol[BigDecimal] {
  def conv = BigDecimalConv
}

trait TimeColValue extends TypeCol[Time] {
  def conv = TimeConv
}

trait DateColValue extends TypeCol[Date] {
  def conv = DateConv
}

trait TimestampColValue extends TypeCol[Timestamp] {
  def conv = TimestampConv
}





















