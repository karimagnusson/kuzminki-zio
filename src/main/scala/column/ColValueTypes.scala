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
  val conv = StringConv
}

trait BooleanColValue extends TypeCol[Boolean] {
  val conv = BooleanConv
}

trait ShortColValue extends TypeCol[Short] {
  val conv = ShortConv
}

trait IntColValue extends TypeCol[Int] {
  val conv = IntConv
}

trait LongColValue extends TypeCol[Long] {
  val conv = LongConv
}

trait FloatColValue extends TypeCol[Float] {
  val conv = FloatConv
}

trait DoubleColValue extends TypeCol[Double] {
  val conv = DoubleConv
}

trait BigDecimalColValue extends TypeCol[BigDecimal] {
  val conv = BigDecimalConv
}

trait TimeColValue extends TypeCol[Time] {
  val conv = TimeConv
}

trait DateColValue extends TypeCol[Date] {
  val conv = DateConv
}

trait TimestampColValue extends TypeCol[Timestamp] {
  val conv = TimestampConv
}

// seq

trait StringSeqColValue extends TypeCol[Seq[String]] {
  val conv = StringSeqConv
}

trait BooleanSeqColValue extends TypeCol[Seq[Boolean]] {
  val conv = BooleanSeqConv
}

trait ShortSeqColValue extends TypeCol[Seq[Short]] {
  val conv = ShortSeqConv
}

trait IntSeqColValue extends TypeCol[Seq[Int]] {
  val conv = IntSeqConv
}

trait LongSeqColValue extends TypeCol[Seq[Long]] {
  val conv = LongSeqConv
}

trait FloatSeqColValue extends TypeCol[Seq[Float]] {
  val conv = FloatSeqConv
}

trait DoubleSeqColValue extends TypeCol[Seq[Double]] {
  val conv = DoubleSeqConv
}

trait BigDecimalSeqColValue extends TypeCol[Seq[BigDecimal]] {
  val conv = BigDecimalSeqConv
}

trait TimeSeqColValue extends TypeCol[Seq[Time]] {
  val conv = TimeSeqConv
}

trait DateSeqColValue extends TypeCol[Seq[Date]] {
  val conv = DateSeqConv
}

trait TimestampSeqColValue extends TypeCol[Seq[Timestamp]] {
  val conv = TimestampSeqConv
}


















