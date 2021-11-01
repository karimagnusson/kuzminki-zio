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

package kuzminki

import kuzminki.column._


import java.sql.Time
import java.sql.Date
import java.sql.Timestamp

package object api {

  // create model col

  implicit val implStringCol: ColInfo => TypeCol[String] = info => StringModelCol(info)
  implicit val implBooleanCol: ColInfo => TypeCol[Boolean] = info => BooleanModelCol(info)
  
  implicit val implShortCol: ColInfo => TypeCol[Short] = info => ShortModelCol(info)
  implicit val implIntCol: ColInfo => TypeCol[Int] = info => IntModelCol(info)
  implicit val implLongCol: ColInfo => TypeCol[Long] = info => LongModelCol(info)
  implicit val implFloatCol: ColInfo => TypeCol[Float] = info => FloatModelCol(info)
  implicit val implDoubleCol: ColInfo => TypeCol[Double] = info => DoubleModelCol(info)
  implicit val implBigDecimalCol: ColInfo => TypeCol[BigDecimal] = info => BigDecimalModelCol(info)

  implicit val implTimeCol: ColInfo => TypeCol[Time] = info => TimeModelCol(info)
  implicit val implDateCol: ColInfo => TypeCol[Date] = info => DateModelCol(info)
  implicit val implTimestampCol: ColInfo => TypeCol[Timestamp] = info => TimestampModelCol(info)

  // model type col

  implicit val toStringModelCol: TypeCol[String] => StringModelCol = col => col.asInstanceOf[StringModelCol]
  implicit val toBooleanModelCol: TypeCol[Boolean] => BooleanModelCol = col => col.asInstanceOf[BooleanModelCol]
  
  implicit val toShortModelCol: TypeCol[Short] => ShortModelCol = col => col.asInstanceOf[ShortModelCol]
  implicit val toIntModelCol: TypeCol[Int] => IntModelCol = col => col.asInstanceOf[IntModelCol]
  implicit val toLongModelCol: TypeCol[Long] => LongModelCol = col => col.asInstanceOf[LongModelCol]
  implicit val toFloatModelCol: TypeCol[Float] => FloatModelCol = col => col.asInstanceOf[FloatModelCol]
  implicit val toDoubleModelCol: TypeCol[Double] => DoubleModelCol = col => col.asInstanceOf[DoubleModelCol]
  implicit val toBigDecimalModelCol: TypeCol[BigDecimal] => BigDecimalModelCol = col => col.asInstanceOf[BigDecimalModelCol]

  implicit val toTimeModelCol: TypeCol[Time] => TimeModelCol = col => col.asInstanceOf[TimeCol]
  implicit val toDateModelCol: TypeCol[Date] => DateModelCol = col => col.asInstanceOf[DateCol]
  implicit val toTimestampModelCol: TypeCol[Timestamp] => TimestampModelCol = col => col.asInstanceOf[TimestampCol]

  // type col

  implicit val typeColToStringCol: TypeCol[String] => StringCol = col => col.asInstanceOf[StringCol]
  implicit val typeColToBooleanCol: TypeCol[Boolean] => BooleanCol = col => col.asInstanceOf[BooleanCol]
  
  implicit val typeColToShortCol: TypeCol[Short] => ShortCol = col => col.asInstanceOf[ShortCol]
  implicit val typeColToIntCol: TypeCol[Int] => IntCol = col => col.asInstanceOf[IntCol]
  implicit val typeColToLongCol: TypeCol[Long] => LongCol = col => col.asInstanceOf[LongCol]
  implicit val typeColToFloatCol: TypeCol[Float] => FloatCol = col => col.asInstanceOf[FloatCol]
  implicit val typeColToDoubleCol: TypeCol[Double] => DoubleCol = col => col.asInstanceOf[DoubleCol]
  implicit val typeColToBigDecimalCol: TypeCol[BigDecimal] => BigDecimalCol = col => col.asInstanceOf[BigDecimalCol]

  implicit val typeColToTimeCol: TypeCol[Time] => TimeCol = col => col.asInstanceOf[TimeCol]
  implicit val typeColToDateCol: TypeCol[Date] => DateCol = col => col.asInstanceOf[DateCol]
  implicit val typeColToTimestampCol: TypeCol[Timestamp] => TimestampCol = col => col.asInstanceOf[TimestampCol]
}
















