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


trait StringOptColValue extends TypeOptCol[String] {
  val conv = StringOptConv
}

trait BooleanOptColValue extends TypeOptCol[Boolean] {
  val conv = BooleanOptConv
}

trait ShortOptColValue extends TypeOptCol[Short] {
  val conv = ShortOptConv
}

trait IntOptColValue extends TypeOptCol[Int] {
  val conv = IntOptConv
}

trait LongOptColValue extends TypeOptCol[Long] {
  val conv = LongOptConv
}

trait FloatOptColValue extends TypeOptCol[Float] {
  val conv = FloatOptConv
}

trait DoubleOptColValue extends TypeOptCol[Double] {
  val conv = DoubleOptConv
}

trait BigDecimalOptColValue extends TypeOptCol[BigDecimal] {
  val conv = BigDecimalOptConv
}

trait TimeOptColValue extends TypeOptCol[Time] {
  val conv = TimeOptConv
}

trait DateOptColValue extends TypeOptCol[Date] {
  val conv = DateOptConv
}

trait TimestampOptColValue extends TypeOptCol[Timestamp] {
  val conv = TimestampOptConv
}














