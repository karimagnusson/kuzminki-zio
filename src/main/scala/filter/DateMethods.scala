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

package kuzminki.filter

import java.sql.Date
import org.postgresql.util.PGInterval
import kuzminki.column.TypeCol
import kuzminki.assign._
import kuzminki.conv._
import kuzminki.filter.types._
import kuzminki.fn.types._
import kuzminki.fn.Cast
import kuzminki.api.{Arg, NoArg}


trait DateMethods extends ComparativeMethods[Date] with InMethods[Date] {

  // fn

  def +(value: PGInterval) = DateTimeIncFn(col, value)
  def -(value: PGInterval) = DateTimeDecFn(col, value)

  def century = ExtractCenturyFn(col)
  def decade = ExtractDecadeFn(col)
  def quarter = ExtractQuarterFn(col)
  def year = ExtractYearFn(col)
  def isoYear = ExtractIsoDowFn(col)
  def month = ExtractMonthFn(col)
  def week = ExtractWeekFn(col)
  def day = ExtractDayFn(col)
  def dow = ExtractDowFn(col)
  def isoDow = ExtractIsoDowFn(col)
  def doy = ExtractDoyFn(col)
  
  def asTimestamp = Cast.asTimestamp(col)
  def asString = Cast.asString(col)

  def format(value: String) = DateTimeFormatFn(col, value)

  // update

  def setNow = TimestampNow(col)
  def +=(value: PGInterval) = DateTimeInc(col, value)
  def -=(value: PGInterval) = DateTimeDec(col, value)

  // cache

  def use = DateCache(col)
}


case class DateCache(col: TypeCol[Date]) extends TypeCache[Date]
                                            with InCache[Date] {

  def setNow(arg: NoArg) = CacheSetNow(col, NoArgConv)
  def +=(arg: Arg) = CacheDateTimeInc(col, IntervalConv)
  def -=(arg: Arg) = CacheDateTimeDec(col, IntervalConv)
}












