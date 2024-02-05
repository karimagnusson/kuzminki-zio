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

import java.sql.Timestamp
import org.postgresql.util.PGInterval
import kuzminki.column.TypeCol
import kuzminki.assign._
import kuzminki.conv._
import kuzminki.fn.types._
import kuzminki.fn.Cast
import kuzminki.api.{Arg, NoArg}


trait TimestampMethods extends ComparativeMethods[Timestamp] with InMethods[Timestamp] {

  // fn

  def +(value: PGInterval) = DateTimeIncFn(col, value)
  def -(value: PGInterval) = DateTimeDecFn(col, value)

  def age = TimestampAgeFn(col)

  def epochSecs = ExtractEpochSecsFn(col)
  def epochMillis = ExtractEpochMillisFn(col)

  def century = ExtractCenturyFn(col)
  def decade = ExtractDecadeFn(col)
  def year = ExtractYearFn(col)
  def quarter = ExtractQuarterFn(col)
  def month = ExtractMonthFn(col)
  def week = ExtractWeekFn(col)
  def day = ExtractDayFn(col)
  def dow = ExtractDowFn(col)
  def doy = ExtractDoyFn(col)
  def isoDow = ExtractIsoDowFn(col)
  def isoYear = ExtractIsoDowFn(col)
  def hour = ExtractHourFn(col)
  def minute = ExtractMinuteFn(col)
  def second = ExtractSecondFn(col)
  def microseconds = ExtractMicrosecondsFn(col)
  def milliseconds = ExtractMillisecondsFn(col)
  
  def asDate = Cast.asDate(col)
  def asTime = Cast.asTime(col)
  def asString = Cast.asString(col)

  def format(value: String) = DateTimeFormatFn(col, value)

  // update

  def setNow = TimestampNow(col)
  def +=(value: PGInterval) = DateTimeInc(col, value)
  def -=(value: PGInterval) = DateTimeDec(col, value)

  // update cache

  def use = TimestampCache(col)
}


case class TimestampCache(col: TypeCol[Timestamp]) extends TypeCache[Timestamp]
                                                      with InCache[Timestamp] {

  def setNow(arg: NoArg) = CacheSetNow(col, NoArgConv)
  def +=(arg: Arg) = CacheDateTimeInc(col, IntervalConv)
  def -=(arg: Arg) = CacheDateTimeDec(col, IntervalConv)
}










