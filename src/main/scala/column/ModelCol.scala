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
import kuzminki.render.{NoArgs, Prefix}


trait ModelCol extends AnyCol with NoArgs {          
  val info: ColInfo
  val real = this
  def name = info.name
  def render(prefix: Prefix) = prefix.pick(info)
}


case class StringModelCol(info: ColInfo) extends ModelCol
                                            with StringCol
                                            with ModelTypeCol[String]


case class BooleanModelCol(info: ColInfo) extends ModelCol
                                             with BooleanCol
                                             with ModelTypeCol[Boolean]


case class ShortModelCol(info: ColInfo) extends ModelCol
                                           with ShortCol
                                           with ModelTypeCol[Short]
                                           with NumericMethods[Short]


case class IntModelCol(info: ColInfo) extends ModelCol
                                         with IntCol
                                         with ModelTypeCol[Int]
                                         with NumericMethods[Int]


case class LongModelCol(info: ColInfo) extends ModelCol
                                          with LongCol
                                          with ModelTypeCol[Long]
                                          with NumericMethods[Long]


case class FloatModelCol(info: ColInfo) extends ModelCol
                                           with FloatCol
                                           with ModelTypeCol[Float]
                                           with NumericMethods[Float]


case class DoubleModelCol(info: ColInfo) extends ModelCol
                                            with DoubleCol
                                            with ModelTypeCol[Double]
                                            with NumericMethods[Double]


case class BigDecimalModelCol(info: ColInfo) extends ModelCol
                                                with BigDecimalCol
                                                with ModelTypeCol[BigDecimal]
                                                with NumericMethods[BigDecimal]


case class TimeModelCol(info: ColInfo) extends ModelCol
                                          with TimeCol
                                          with ModelTypeCol[Time]


case class DateModelCol(info: ColInfo) extends ModelCol
                                          with DateCol
                                          with ModelTypeCol[Date]


case class TimestampModelCol(info: ColInfo) extends ModelCol
                                               with TimestampCol
                                               with ModelTypeCol[Timestamp]

// seq

case class StringSeqModelCol(info: ColInfo) extends ModelCol
                                               with StringSeqCol
                                               with SeqFilters[String]
                                               with SeqMethods[String]
                                               with ModelTypeCol[Seq[String]]


case class BooleanSeqModelCol(info: ColInfo) extends ModelCol
                                                with BooleanSeqCol
                                                with SeqFilters[Boolean]
                                                with SeqMethods[Boolean]
                                                with ModelTypeCol[Seq[Boolean]]


case class ShortSeqModelCol(info: ColInfo) extends ModelCol
                                              with ShortSeqCol
                                              with SeqFilters[Short]
                                              with SeqMethods[Short]
                                              with ModelTypeCol[Seq[Short]]


case class IntSeqModelCol(info: ColInfo) extends ModelCol
                                            with IntSeqCol
                                            with SeqFilters[Int]
                                            with SeqMethods[Int]
                                            with ModelTypeCol[Seq[Int]]


case class LongSeqModelCol(info: ColInfo) extends ModelCol
                                             with LongSeqCol
                                             with SeqFilters[Long]
                                             with SeqMethods[Long]
                                             with ModelTypeCol[Seq[Long]]


case class FloatSeqModelCol(info: ColInfo) extends ModelCol
                                              with FloatSeqCol
                                              with SeqFilters[Float]
                                              with SeqMethods[Float]
                                              with ModelTypeCol[Seq[Float]]


case class DoubleSeqModelCol(info: ColInfo) extends ModelCol
                                               with DoubleSeqCol
                                               with SeqFilters[Double]
                                               with SeqMethods[Double]
                                               with ModelTypeCol[Seq[Double]]


case class BigDecimalSeqModelCol(info: ColInfo) extends ModelCol
                                                   with BigDecimalSeqCol
                                                   with SeqFilters[BigDecimal]
                                                   with SeqMethods[BigDecimal]
                                                   with ModelTypeCol[Seq[BigDecimal]]


case class TimeSeqModelCol(info: ColInfo) extends ModelCol
                                             with TimeSeqCol
                                             with SeqFilters[Time]
                                             with SeqMethods[Time]
                                             with ModelTypeCol[Seq[Time]]


case class DateSeqModelCol(info: ColInfo) extends ModelCol
                                             with DateSeqCol
                                             with SeqFilters[Date]
                                             with SeqMethods[Date]
                                             with ModelTypeCol[Seq[Date]]


case class TimestampSeqModelCol(info: ColInfo) extends ModelCol
                                                  with TimestampSeqCol
                                                  with SeqFilters[Timestamp]
                                                  with SeqMethods[Timestamp]
                                                  with ModelTypeCol[Seq[Timestamp]]













