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
import java.sql.Types
import java.math.{BigDecimal => JBigDecimal}
import org.postgresql.util.PGInterval
import org.postgresql.util.PGobject
import kuzminki.api.{Jsonb, KuzminkiError, NoArg}


trait ValConv[T] {
  def get(row: ResultSet, index: Int): T
  def put(value: T): Any
  def opt: ValConv[Option[T]]
}

trait ValConvReg[T] extends ValConv[T] {
  def put(value: T) = value
}

trait ValOptConv[T] extends ValConv[Option[T]] {
  val typeId: Int
  def put(opt: Option[T]) = opt.getOrElse(TypeNull(typeId))
  def opt = throw KuzminkiError("cannot use asOpt on Option column")
}

trait ValSeqConv[T] extends ValConv[Seq[T]] {
  val typeName: String
  def put(vec: Seq[T]) = TypeArray(typeName, vec.map(_.asInstanceOf[Object]))
  def getVector(rs: ResultSet, index: Int, cast: AnyRef => T): Seq[T] = {
    rs.getArray(index)
      .getArray
      .asInstanceOf[Array[AnyRef]]
      .toVector
      .map(cast)
  }
}

trait ValSeqOptConv[T] extends ValConv[Option[Seq[T]]] {
  val typeName: String
  def put(vecOpt: Option[Seq[T]]) = vecOpt match {
    case Some(vec) => TypeArray(typeName, vec.map(_.asInstanceOf[Object]))
    case None => TypeNull(Types.ARRAY)
  } 
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

object NoArgConv extends ValConvReg[NoArg] {
  def get(rs: ResultSet, index: Int) = throw KuzminkiError("cannot read NoArg")
  def opt = throw KuzminkiError("cannot use asOpt on NoArg")
}

trait JsonbRead {
  val read: AnyRef => Jsonb = {
    case obj: String =>
      Jsonb(obj)
    case obj: PGobject =>
      Jsonb(obj.getValue)
    case obj =>
      throw KuzminkiError("Not jsonb [%s]".format(obj.toString))
  } 
}

// types

object StringConv extends ValConvReg[String] {
  def get(rs: ResultSet, index: Int) = rs.getString(index)
  def opt = StringOptConv
}

object BooleanConv extends ValConvReg[Boolean] {
  def get(rs: ResultSet, index: Int) = rs.getBoolean(index)
  def opt = BooleanOptConv
}

object ShortConv extends ValConvReg[Short] {
  def get(rs: ResultSet, index: Int) = rs.getShort(index)
  def opt = ShortOptConv
}

object IntConv extends ValConvReg[Int] {
  def get(rs: ResultSet, index: Int) = rs.getInt(index)
  def opt = IntOptConv
}

object LongConv extends ValConvReg[Long] {
  def get(rs: ResultSet, index: Int) = rs.getLong(index)
  def opt = LongOptConv
}

object FloatConv extends ValConvReg[Float] {
  def get(rs: ResultSet, index: Int) = rs.getFloat(index)
  def opt = FloatOptConv
}

object DoubleConv extends ValConvReg[Double] {
  def get(rs: ResultSet, index: Int) = rs.getDouble(index)
  def opt = DoubleOptConv
}

object BigDecimalConv extends ValConvReg[BigDecimal] {
  def get(rs: ResultSet, index: Int) = BigDecimal(rs.getBigDecimal(index))
  def opt = BigDecimalOptConv
}

object TimeConv extends ValConvReg[Time] {
  def get(rs: ResultSet, index: Int) = rs.getTime(index)
  def opt = TimeOptConv
}

object DateConv extends ValConvReg[Date] {
  def get(rs: ResultSet, index: Int) = rs.getDate(index)
  def opt = DateOptConv
}

object TimestampConv extends ValConvReg[Timestamp] {
  def get(rs: ResultSet, index: Int) = rs.getTimestamp(index)
  def opt = TimestampOptConv
}

object JsonbConv extends ValConvReg[Jsonb] with JsonbRead {
  def get(rs: ResultSet, index: Int) = read(rs.getObject(index))
  def opt = JsonbOptConv
}

object UUIDConv extends ValConvReg[UUID] {
  def get(rs: ResultSet, index: Int) =
    rs.getObject(index).asInstanceOf[UUID]
  def opt = UUIDOptConv
}

object IntervalConv extends ValConvReg[PGInterval] {
  def get(rs: ResultSet, index: Int) =
    rs.getObject(index).asInstanceOf[PGInterval]
  def opt = IntervalOptConv
}

// opt

object StringOptConv extends ValOptConv[String] {
  val typeId = Types.VARCHAR
  def get(rs: ResultSet, index: Int) =
    Option(rs.getString(index))
}

object BooleanOptConv extends ValOptConv[Boolean] {
  val typeId = Types.BOOLEAN
  def get(rs: ResultSet, index: Int) =
    Option(rs.getBoolean(index))
}

object ShortOptConv extends ValOptConv[Short] {
  val typeId = Types.SMALLINT
  def get(rs: ResultSet, index: Int) =
    Option(rs.getShort(index))
}

object IntOptConv extends ValOptConv[Int] {
  val typeId = Types.INTEGER
  def get(rs: ResultSet, index: Int) =
    Option(rs.getInt(index))
}

object LongOptConv extends ValOptConv[Long] {
  val typeId = Types.BIGINT
  def get(rs: ResultSet, index: Int) =
    Option(rs.getLong(index))
}

object FloatOptConv extends ValOptConv[Float] {
  val typeId = Types.FLOAT
  def get(rs: ResultSet, index: Int) =
    Option(rs.getFloat(index))
}

object DoubleOptConv extends ValOptConv[Double] {
  val typeId = Types.DOUBLE
  def get(rs: ResultSet, index: Int) =
    Option(rs.getDouble(index))
}

object BigDecimalOptConv extends ValOptConv[BigDecimal] {
  val typeId = Types.BIGINT
  def get(rs: ResultSet, index: Int) =
    Option(rs.getBigDecimal(index)).map(BigDecimal(_))
}

object TimeOptConv extends ValOptConv[Time] {
  val typeId = Types.TIME
  def get(rs: ResultSet, index: Int) =
    Option(rs.getTime(index))
}

object DateOptConv extends ValOptConv[Date] {
  val typeId = Types.DATE
  def get(rs: ResultSet, index: Int) =
    Option(rs.getDate(index))
}

object TimestampOptConv extends ValOptConv[Timestamp] {
  val typeId = Types.TIMESTAMP
  def get(rs: ResultSet, index: Int) =
    Option(rs.getTimestamp(index))
}

object JsonbOptConv extends ValOptConv[Jsonb] with JsonbRead {
  val typeId = Types.OTHER
  def get(rs: ResultSet, index: Int) =
    Option(rs.getObject(index)).map(read)
}

object UUIDOptConv extends ValOptConv[UUID] {
  val typeId = Types.OTHER
  def get(rs: ResultSet, index: Int) =
    Option(rs.getObject(index)).map(_.asInstanceOf[UUID])
}

object IntervalOptConv extends ValOptConv[PGInterval] {
  val typeId = Types.OTHER
  def get(rs: ResultSet, index: Int) =
    Option(rs.getObject(index)).map(_.asInstanceOf[PGInterval])
}

// seq

object StringSeqConv extends ValSeqConv[String] {
  val typeName = "text"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[String])
  def opt = StringSeqOptConv
}

object BooleanSeqConv extends ValSeqConv[Boolean] {
  val typeName = "bool"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Boolean])
  def opt = BooleanSeqOptConv
}

object ShortSeqConv extends ValSeqConv[Short] {
  val typeName = "int2"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Short])
  def opt = ShortSeqOptConv
}

object IntSeqConv extends ValSeqConv[Int] {
  val typeName = "int4"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Int])
  def opt = IntSeqOptConv
}

object LongSeqConv extends ValSeqConv[Long] {
  val typeName = "int8"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Long])
  def opt = LongSeqOptConv
}

object FloatSeqConv extends ValSeqConv[Float] {
  val typeName = "float4"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Float])
  def opt = FloatSeqOptConv
}

object DoubleSeqConv extends ValSeqConv[Double] {
  val typeName = "float8"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Double])
  def opt = DoubleSeqOptConv
}

object BigDecimalSeqConv extends ValSeqConv[BigDecimal] {
  val typeName = "decimal"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => BigDecimal(o.asInstanceOf[JBigDecimal]))
  def opt = BigDecimalSeqOptConv
}

object TimeSeqConv extends ValSeqConv[Time] {
  val typeName = "time"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Time])
  def opt = TimeSeqOptConv
}

object DateSeqConv extends ValSeqConv[Date] {
  val typeName = "date"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Date])
  def opt = DateSeqOptConv
}

object TimestampSeqConv extends ValSeqConv[Timestamp] {
  val typeName = "timestamp"
  def get(rs: ResultSet, index: Int) =
    getVector(rs, index, o => o.asInstanceOf[Timestamp])
  def opt = TimestampSeqOptConv
}

object JsonbSeqConv extends ValConv[Seq[Jsonb]] with JsonbRead {
  
  val typeName = "jsonb"
  
  def asObject(jsonb: Jsonb): Object = {
    val obj = new PGobject()
    obj.setType("jsonb")
    obj.setValue(jsonb.value)
    obj.asInstanceOf[Object]
  }
  
  def put(vec: Seq[Jsonb]) = TypeArray(typeName, vec.map(asObject(_)))
  
  def get(rs: ResultSet, index: Int): Seq[Jsonb] = {
    rs.getArray(index)
      .getArray
      .asInstanceOf[Array[AnyRef]]
      .toVector
      .map(read)
  }
  
  def opt = JsonbSeqOptConv
}

// seq opt

object StringSeqOptConv extends ValSeqOptConv[String] {
  val typeName = "text"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[String])
}

object BooleanSeqOptConv extends ValSeqOptConv[Boolean] {
  val typeName = "bool"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Boolean])
}

object ShortSeqOptConv extends ValSeqOptConv[Short] {
  val typeName = "int2"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Short])
}

object IntSeqOptConv extends ValSeqOptConv[Int] {
  val typeName = "int4"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Int])
}

object LongSeqOptConv extends ValSeqOptConv[Long] {
  val typeName = "int8"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Long])
}

object FloatSeqOptConv extends ValSeqOptConv[Float] {
  val typeName = "float4"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Float])
}

object DoubleSeqOptConv extends ValSeqOptConv[Double] {
  val typeName = "float8"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Double])
}

object BigDecimalSeqOptConv extends ValSeqOptConv[BigDecimal] {
  val typeName = "decimal"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => BigDecimal(o.asInstanceOf[JBigDecimal]))
}

object TimeSeqOptConv extends ValSeqOptConv[Time] {
  val typeName = "time"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Time])
}

object DateSeqOptConv extends ValSeqOptConv[Date] {
  val typeName = "date"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Date])
}

object TimestampSeqOptConv extends ValSeqOptConv[Timestamp] {
  val typeName = "timestamp"
  def get(rs: ResultSet, index: Int) =
    getVectorOpt(rs, index, o => o.asInstanceOf[Timestamp])
}

object JsonbSeqOptConv extends ValConv[Option[Seq[Jsonb]]] with JsonbRead {
  
  val typeName = "jsonb"
  
  def asObject(jsonb: Jsonb): Object = {
    val obj = new PGobject()
    obj.setType("jsonb")
    obj.setValue(jsonb.value)
    obj.asInstanceOf[Object]
  }
  
  def put(vecOpt: Option[Seq[Jsonb]]) = vecOpt match {
    case Some(vec) => TypeArray(typeName, vec.map(asObject(_)))
    case None => TypeNull(Types.ARRAY)
  } 

  def get(rs: ResultSet, index: Int): Option[Seq[Jsonb]] = {
    Option(rs.getArray(index)).map { arr =>
      arr
        .getArray
        .asInstanceOf[Array[AnyRef]]
        .toVector
        .map(read)
    }
  }

  def opt = throw KuzminkiError("cannot use asOpt on Option column")
}











