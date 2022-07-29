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
import java.math.{BigDecimal => JBigDecimal}


trait ValConv[T] {
  def get(row: ResultSet, index: Int): T
}

trait ValSeqConv[T] extends ValConv[Seq[T]] {
  def cast(rs: ResultSet, index: Int): Seq[AnyRef] = {
     rs.getArray(index)
      .getArray
      .asInstanceOf[Array[AnyRef]]
      .toVector
  }
}

case class ValOptConv[T](underlying: ValConv[T]) extends ValConv[Option[T]] {
  def get(rs: ResultSet, index: Int) = Option(underlying.get(rs, index))
}

// types

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
  def get(rs: ResultSet, index: Int) = BigDecimal(rs.getBigDecimal(index))
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

// seq

object StringSeqConv extends ValSeqConv[String] {
  def get(rs: ResultSet, index: Int): Seq[String] =
    cast(rs, index).map(_.asInstanceOf[String])
}

object BooleanSeqConv extends ValSeqConv[Boolean] {
  def get(rs: ResultSet, index: Int): Seq[Boolean] =
    cast(rs, index).map(_.asInstanceOf[Boolean])
}

object ShortSeqConv extends ValSeqConv[Short] {
  def get(rs: ResultSet, index: Int): Seq[Short] =
    cast(rs, index).map(_.asInstanceOf[Short])
}

object IntSeqConv extends ValSeqConv[Int] {
  def get(rs: ResultSet, index: Int): Seq[Int] =
    cast(rs, index).map(_.asInstanceOf[Int])
}

object LongSeqConv extends ValSeqConv[Long] {
  def get(rs: ResultSet, index: Int): Seq[Long] =
    cast(rs, index).map(_.asInstanceOf[Long])
}

object FloatSeqConv extends ValSeqConv[Float] {
  def get(rs: ResultSet, index: Int): Seq[Float] =
    cast(rs, index).map(_.asInstanceOf[Float])
}

object DoubleSeqConv extends ValSeqConv[Double] {
  def get(rs: ResultSet, index: Int): Seq[Double] =
    cast(rs, index).map(_.asInstanceOf[Double])
}

object BigDecimalSeqConv extends ValSeqConv[BigDecimal] {
  def get(rs: ResultSet, index: Int): Seq[BigDecimal] =
    cast(rs, index).map(o => BigDecimal(o.asInstanceOf[JBigDecimal]))
}

object TimeSeqConv extends ValSeqConv[Time] {
  def get(rs: ResultSet, index: Int): Seq[Time] =
    cast(rs, index).map(_.asInstanceOf[Time])
}

object DateSeqConv extends ValSeqConv[Date] {
  def get(rs: ResultSet, index: Int): Seq[Date] =
    cast(rs, index).map(_.asInstanceOf[Date])
}

object TimestampSeqConv extends ValSeqConv[Timestamp] {
  def get(rs: ResultSet, index: Int): Seq[Timestamp] =
    cast(rs, index).map(_.asInstanceOf[Timestamp])
}


















