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
import org.postgresql.util.PGInterval
import kuzminki.api.{Jsonb, KuzminkiError}


trait ValConv[T] {
  def get(row: ResultSet, index: Int): T
  def opt: ValConv[Option[T]]
}

trait ValOptConv[T] extends ValConv[Option[T]] {
  def opt = throw KuzminkiError("cannot use asOpt on Option column")
}

trait ValSeqConv[T] extends ValConv[Seq[T]] {
  def getVector(rs: ResultSet, index: Int, cast: AnyRef => T): Seq[T] = {
    rs.getArray(index)
      .getArray
      .asInstanceOf[Array[AnyRef]]
      .toVector
      .map(cast)
  }
}

trait ValSeqOptConv[T] extends ValConv[Option[Seq[T]]] {
  def getVectorOpt(rs: ResultSet, index: Int, cast: AnyRef => T): Option[Seq[T]] = {
    Option(rs.getArray(index)).map { arr =>
      arr
        .getArray
        .asInstanceOf[Array[AnyRef]]
        .toVector
        .map(cast)
    }
  }
  def opt = throw KuzminkiError("cannot use asOpt on Option column")
}

// types

object StringConv extends ValConv[String] {
  def get(rs: ResultSet, index: Int) = rs.getString(index)
  def opt = StringOptConv
}

object BooleanConv extends ValConv[Boolean] {
  def get(rs: ResultSet, index: Int) = rs.getBoolean(index)
  def opt = BooleanOptConv
}

object ShortConv extends ValConv[Short] {
  def get(rs: ResultSet, index: Int) = rs.getShort(index)
  def opt = ShortOptConv
}

object IntConv extends ValConv[Int] {
  def get(rs: ResultSet, index: Int) = rs.getInt(index)
  def opt = IntOptConv
}

object LongConv extends ValConv[Long] {
  def get(rs: ResultSet, index: Int) = rs.getLong(index)
  def opt = LongOptConv
}

object FloatConv extends ValConv[Float] {
  def get(rs: ResultSet, index: Int) = rs.getFloat(index)
  def opt = FloatOptConv
}

object DoubleConv extends ValConv[Double] {
  def get(rs: ResultSet, index: Int) = rs.getDouble(index)
  def opt = DoubleOptConv
}

object BigDecimalConv extends ValConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) = BigDecimal(rs.getBigDecimal(index))
  def opt = BigDecimalOptConv
}

object TimeConv extends ValConv[Time] {
  def get(rs: ResultSet, index: Int) = rs.getTime(index)
  def opt = TimeOptConv
}

object DateConv extends ValConv[Date] {
  def get(rs: ResultSet, index: Int) = rs.getDate(index)
  def opt = DateOptConv
}

object TimestampConv extends ValConv[Timestamp] {
  def get(rs: ResultSet, index: Int) = rs.getTimestamp(index)
  def opt = TimestampOptConv
}

object JsonbConv extends ValConv[Jsonb] {
  def get(rs: ResultSet, index: Int) = Jsonb(rs.getString(index))
  def opt = JsonbOptConv
}

object UUIDConv extends ValConv[UUID] {
  def get(rs: ResultSet, index: Int) = rs.getObject(index).asInstanceOf[UUID]
  def opt = UUIDOptConv
}

object IntervalConv extends ValConv[PGInterval] {
  def get(rs: ResultSet, index: Int) = rs.getObject(index).asInstanceOf[PGInterval]
  def opt = IntervalOptConv
}

// seq

object StringSeqConv extends ValSeqConv[String] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[String])
  def opt = StringSeqOptConv
}

object BooleanSeqConv extends ValSeqConv[Boolean] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Boolean])
  def opt = BooleanSeqOptConv
}

object ShortSeqConv extends ValSeqConv[Short] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Short])
  def opt = ShortSeqOptConv
}

object IntSeqConv extends ValSeqConv[Int] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Int])
  def opt = IntSeqOptConv
}

object LongSeqConv extends ValSeqConv[Long] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Long])
  def opt = LongSeqOptConv
}

object FloatSeqConv extends ValSeqConv[Float] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Float])
  def opt = FloatSeqOptConv
}

object DoubleSeqConv extends ValSeqConv[Double] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Double])
  def opt = DoubleSeqOptConv
}

object BigDecimalSeqConv extends ValSeqConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => BigDecimal(o.asInstanceOf[JBigDecimal]))
  def opt = BigDecimalSeqOptConv
}

object TimeSeqConv extends ValSeqConv[Time] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Time])
  def opt = TimeSeqOptConv
}

object DateSeqConv extends ValSeqConv[Date] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Date])
  def opt = DateSeqOptConv
}

object TimestampSeqConv extends ValSeqConv[Timestamp] {
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Timestamp])
  def opt = TimestampSeqOptConv
}

// opt

object StringOptConv extends ValOptConv[String] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getString(index))
}

object BooleanOptConv extends ValOptConv[Boolean] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getBoolean(index))
}

object ShortOptConv extends ValOptConv[Short] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getShort(index))
}

object IntOptConv extends ValOptConv[Int] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getInt(index))
}

object LongOptConv extends ValOptConv[Long] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getLong(index))
}

object FloatOptConv extends ValOptConv[Float] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getFloat(index))
}

object DoubleOptConv extends ValOptConv[Double] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getDouble(index))
}

object BigDecimalOptConv extends ValOptConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getBigDecimal(index)).map(BigDecimal(_))
}

object TimeOptConv extends ValOptConv[Time] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getTime(index))
}

object DateOptConv extends ValOptConv[Date] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getDate(index))
}

object TimestampOptConv extends ValOptConv[Timestamp] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getTimestamp(index))
}

object JsonbOptConv extends ValOptConv[Jsonb] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getString(index)).map(Jsonb(_))
}

object UUIDOptConv extends ValOptConv[UUID] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getObject(index)).map(_.asInstanceOf[UUID])
}

object IntervalOptConv extends ValOptConv[PGInterval] {
  def get(rs: ResultSet, index: Int) =
    Option(rs.getObject(index)).map(_.asInstanceOf[PGInterval])
}

// seq opt

object StringSeqOptConv extends ValSeqOptConv[String] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[String])
}

object BooleanSeqOptConv extends ValSeqOptConv[Boolean] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Boolean])
}

object ShortSeqOptConv extends ValSeqOptConv[Short] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Short])
}

object IntSeqOptConv extends ValSeqOptConv[Int] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Int])
}

object LongSeqOptConv extends ValSeqOptConv[Long] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Long])
}

object FloatSeqOptConv extends ValSeqOptConv[Float] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Float])
}

object DoubleSeqOptConv extends ValSeqOptConv[Double] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Double])
}

object BigDecimalSeqOptConv extends ValSeqOptConv[BigDecimal] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => BigDecimal(o.asInstanceOf[JBigDecimal]))
}

object TimeSeqOptConv extends ValSeqOptConv[Time] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Time])
}

object DateSeqOptConv extends ValSeqOptConv[Date] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Date])
}

object TimestampSeqOptConv extends ValSeqOptConv[Timestamp] {
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Timestamp])
}














