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
import java.util.UUID
import java.sql.ResultSet
import java.math.{BigDecimal => JBigDecimal}
import kuzminki.api.{Jsonb, KuzminkiError}


trait ValConv[T] {
  def get(row: ResultSet, index: Int): T
  def opt: ValConv[Option[T]]
}

trait ValSeqConv[T] extends ValConv[Seq[T]] {
  def opt = throw KuzminkiError("cannot use asOpt on Array column, use default")
  def cast(rs: ResultSet, index: Int): Seq[AnyRef] = {
    rs.getArray(index)
      .getArray
      .asInstanceOf[Array[AnyRef]]
      .toVector
  }
}

trait ValOptConv[T] extends ValConv[Option[T]] {
  def opt = throw KuzminkiError("cannot use asOpt on Option column")
}

case class DefaultOptConv[T](underlying: ValConv[T]) extends ValOptConv[T] {
  def get(rs: ResultSet, index: Int) =
    Option(underlying.get(rs, index))
}

object BigDecimalOptConv extends ValOptConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getBigDecimal(index)).map(BigDecimal(_))
}

object JsonbOptConv extends ValOptConv[Jsonb] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getString(index)).map(Jsonb(_))
}

object UUIDOptConv extends ValOptConv[UUID] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getObject(index)).map(_.asInstanceOf[UUID])
}

// types

object StringConv extends ValConv[String] {
  def get(rs: ResultSet, index: Int) = rs.getString(index)
  def opt = DefaultOptConv(this)
}

object BooleanConv extends ValConv[Boolean] {
  def get(rs: ResultSet, index: Int) = rs.getBoolean(index)
  def opt = DefaultOptConv(this)
}

object ShortConv extends ValConv[Short] {
  def get(rs: ResultSet, index: Int) = rs.getShort(index)
  def opt = DefaultOptConv(this)
}

object IntConv extends ValConv[Int] {
  def get(rs: ResultSet, index: Int) = rs.getInt(index)
  def opt = DefaultOptConv(this)
}

object LongConv extends ValConv[Long] {
  def get(rs: ResultSet, index: Int) = rs.getLong(index)
  def opt = DefaultOptConv(this)
}

object FloatConv extends ValConv[Float] {
  def get(rs: ResultSet, index: Int) = rs.getFloat(index)
  def opt = DefaultOptConv(this)
}

object DoubleConv extends ValConv[Double] {
  def get(rs: ResultSet, index: Int) = rs.getDouble(index)
  def opt = DefaultOptConv(this)
}

object BigDecimalConv extends ValConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) = BigDecimal(rs.getBigDecimal(index))
  def opt = BigDecimalOptConv
}

object TimeConv extends ValConv[Time] {
  def get(rs: ResultSet, index: Int) = rs.getTime(index)
  def opt = DefaultOptConv(this)
}

object DateConv extends ValConv[Date] {
  def get(rs: ResultSet, index: Int) = rs.getDate(index)
  def opt = DefaultOptConv(this)
}

object TimestampConv extends ValConv[Timestamp] {
  def get(rs: ResultSet, index: Int) = rs.getTimestamp(index)
  def opt = DefaultOptConv(this)
}

object JsonbConv extends ValConv[Jsonb] {
  def get(rs: ResultSet, index: Int) = Jsonb(rs.getString(index))
  def opt = JsonbOptConv
}

object UUIDConv extends ValConv[UUID] {
  def get(rs: ResultSet, index: Int) = rs.getObject(index).asInstanceOf[UUID]
  def opt = UUIDOptConv
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

object JsonbSeqConv extends ValSeqConv[Jsonb] {
  def get(rs: ResultSet, index: Int): Seq[Jsonb] =
    cast(rs, index).map(o => Jsonb(o.asInstanceOf[String]))
}


















