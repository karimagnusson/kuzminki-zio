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


object StringOptConv extends ValOptConv[String] {
  def get(rs: ResultSet, index: Int) = Option(rs.getString(index))
}

object BooleanOptConv extends ValOptConv[Boolean] {
  def get(rs: ResultSet, index: Int) = Option(rs.getBoolean(index))
}

object ShortOptConv extends ValOptConv[Short] {
  def get(rs: ResultSet, index: Int) = Option(rs.getShort(index))
}

object IntOptConv extends ValOptConv[Int] {
  def get(rs: ResultSet, index: Int) = Option(rs.getInt(index))
}

object LongOptConv extends ValOptConv[Long] {
  def get(rs: ResultSet, index: Int) = Option(rs.getLong(index))
}

object FloatOptConv extends ValOptConv[Float] {
  def get(rs: ResultSet, index: Int) = Option(rs.getFloat(index))
}

object DoubleOptConv extends ValOptConv[Double] {
  def get(rs: ResultSet, index: Int) = Option(rs.getDouble(index))
}

object BigDecimalOptConv extends ValOptConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) = Option(rs.getBigDecimal(index))
}

object TimeOptConv extends ValOptConv[Time] {
  def get(rs: ResultSet, index: Int) = Option(rs.getTime(index))
}

object DateOptConv extends ValOptConv[Date] {
  def get(rs: ResultSet, index: Int) = Option(rs.getDate(index))
}

object TimestampOptConv extends ValOptConv[Timestamp] {
  def get(rs: ResultSet, index: Int) = Option(rs.getTimestamp(index))
}








