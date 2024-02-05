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
import kuzminki.api.{Jsonb, KuzminkiError}


object AnyConv {

  def put[T](conv: ValConv[T], value: Any): Any = conv match {
    case StringConv => StringConv.put(value.asInstanceOf[String])
    case BooleanConv => BooleanConv.put(value.asInstanceOf[Boolean])
    case ShortConv => ShortConv.put(value.asInstanceOf[Short])
    case IntConv => IntConv.put(value.asInstanceOf[Int])
    case LongConv => LongConv.put(value.asInstanceOf[Long])
    case FloatConv => FloatConv.put(value.asInstanceOf[Float])
    case DoubleConv => DoubleConv.put(value.asInstanceOf[Double])
    case BigDecimalConv => BigDecimalConv.put(value.asInstanceOf[BigDecimal])
    case TimeConv => TimeConv.put(value.asInstanceOf[Time])
    case DateConv => DateConv.put(value.asInstanceOf[Date])
    case TimestampConv => TimestampConv.put(value.asInstanceOf[Timestamp])
    case JsonbConv => JsonbConv.put(value.asInstanceOf[Jsonb])
    case UUIDConv => UUIDConv.put(value.asInstanceOf[UUID])
    case StringOptConv => StringOptConv.put(value.asInstanceOf[Option[String]])
    case BooleanOptConv => BooleanOptConv.put(value.asInstanceOf[Option[Boolean]])
    case ShortOptConv => ShortOptConv.put(value.asInstanceOf[Option[Short]])
    case IntOptConv => IntOptConv.put(value.asInstanceOf[Option[Int]])
    case LongOptConv => LongOptConv.put(value.asInstanceOf[Option[Long]])
    case FloatOptConv => FloatOptConv.put(value.asInstanceOf[Option[Float]])
    case DoubleOptConv => DoubleOptConv.put(value.asInstanceOf[Option[Double]])
    case BigDecimalOptConv => BigDecimalOptConv.put(value.asInstanceOf[Option[BigDecimal]])
    case TimeOptConv => TimeOptConv.put(value.asInstanceOf[Option[Time]])
    case DateOptConv => DateOptConv.put(value.asInstanceOf[Option[Date]])
    case TimestampOptConv => TimestampOptConv.put(value.asInstanceOf[Option[Timestamp]])
    case JsonbOptConv => JsonbOptConv.put(value.asInstanceOf[Option[Jsonb]])
    case UUIDOptConv => UUIDOptConv.put(value.asInstanceOf[Option[UUID]])
    case StringSeqConv => StringSeqConv.put(value.asInstanceOf[Seq[String]])
    case BooleanSeqConv => BooleanSeqConv.put(value.asInstanceOf[Seq[Boolean]])
    case ShortSeqConv => ShortSeqConv.put(value.asInstanceOf[Seq[Short]])
    case IntSeqConv => IntSeqConv.put(value.asInstanceOf[Seq[Int]])
    case LongSeqConv => LongSeqConv.put(value.asInstanceOf[Seq[Long]])
    case FloatSeqConv => FloatSeqConv.put(value.asInstanceOf[Seq[Float]])
    case DoubleSeqConv => DoubleSeqConv.put(value.asInstanceOf[Seq[Double]])
    case BigDecimalSeqConv => BigDecimalSeqConv.put(value.asInstanceOf[Seq[BigDecimal]])
    case TimeSeqConv => TimeSeqConv.put(value.asInstanceOf[Seq[Time]])
    case DateSeqConv => DateSeqConv.put(value.asInstanceOf[Seq[Date]])
    case TimestampSeqConv => TimestampSeqConv.put(value.asInstanceOf[Seq[Timestamp]])
    case JsonbSeqConv => JsonbSeqConv.put(value.asInstanceOf[Seq[Jsonb]])
    case StringSeqOptConv => StringSeqOptConv.put(value.asInstanceOf[Option[Seq[String]]])
    case BooleanSeqOptConv => BooleanSeqOptConv.put(value.asInstanceOf[Option[Seq[Boolean]]])
    case ShortSeqOptConv => ShortSeqOptConv.put(value.asInstanceOf[Option[Seq[Short]]])
    case IntSeqOptConv => IntSeqOptConv.put(value.asInstanceOf[Option[Seq[Int]]])
    case LongSeqOptConv => LongSeqOptConv.put(value.asInstanceOf[Option[Seq[Long]]])
    case FloatSeqOptConv => FloatSeqOptConv.put(value.asInstanceOf[Option[Seq[Float]]])
    case DoubleSeqOptConv => DoubleSeqOptConv.put(value.asInstanceOf[Option[Seq[Double]]])
    case BigDecimalSeqOptConv => BigDecimalSeqOptConv.put(value.asInstanceOf[Option[Seq[BigDecimal]]])
    case TimeSeqOptConv => TimeSeqOptConv.put(value.asInstanceOf[Option[Seq[Time]]])
    case DateSeqOptConv => DateSeqOptConv.put(value.asInstanceOf[Option[Seq[Date]]])
    case TimestampSeqOptConv => TimestampSeqOptConv.put(value.asInstanceOf[Option[Seq[Timestamp]]])
    case JsonbSeqOptConv => JsonbSeqOptConv.put(value.asInstanceOf[Option[Seq[Jsonb]]])
    case _ => throw KuzminkiError("invalid conversion")
  } 
}