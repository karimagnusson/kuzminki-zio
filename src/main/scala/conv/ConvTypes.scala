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

package kuzminki.conv

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import java.sql.ResultSet


object StringConv extends ValConv[String] {
  def get(rs: ResultSet, index: Int) = rs.getString(index)
}

object BooleanConv extends ValConv[Boolean] {
  def get(rs: ResultSet, index: Int) = rs.getBoolean(index)
}

object ShortConv extends ValConv[Short] {
  def get(rs: ResultSet, index: Int) = rs.getShort(index)
}

object IntConv extends ValConv[Int] {
  def get(rs: ResultSet, index: Int) = rs.getInt(index)
}

object LongConv extends ValConv[Long] {
  def get(rs: ResultSet, index: Int) = rs.getLong(index)
}

object FloatConv extends ValConv[Float] {
  def get(rs: ResultSet, index: Int) = rs.getFloat(index)
}

object DoubleConv extends ValConv[Double] {
  def get(rs: ResultSet, index: Int) = rs.getDouble(index)
}

object BigDecimalConv extends ValConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) = rs.getBigDecimal(index)
}

object TimeConv extends ValConv[Time] {
  def get(rs: ResultSet, index: Int) = rs.getTime(index)
}

object DateConv extends ValConv[Date] {
  def get(rs: ResultSet, index: Int) = rs.getDate(index)
}

object TimestampConv extends ValConv[Timestamp] {
  def get(rs: ResultSet, index: Int) = rs.getTimestamp(index)
}










