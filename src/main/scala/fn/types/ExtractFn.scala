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

package kuzminki.fn.types

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import kuzminki.column._
import kuzminki.render.Prefix
import kuzminki.api.Jsonb


trait ExtractFn extends IntCol with FnColArgs {
  val field: String
  val col: TypeCol[_]
  def template = s"EXTRACT($field FROM %s)::int"
}

case class ExtractCenturyFn(col: TypeCol[_]) extends ExtractFn {
  val field = "CENTURY"
}

case class ExtractDayFn(col: TypeCol[_]) extends ExtractFn {
  val field = "DAY"
}

case class ExtractDecadeFn(col: TypeCol[_]) extends ExtractFn {
  val field = "DECADE"
}

case class ExtractDowFn(col: TypeCol[_]) extends ExtractFn {
  val field = "DOW"
}

case class ExtractDoyFn(col: TypeCol[_]) extends ExtractFn {
  val field = "DOY"
}

case class ExtractHourFn(col: TypeCol[_]) extends ExtractFn {
  val field = "HOUR"
}

case class ExtractIsoDowFn(col: TypeCol[_]) extends ExtractFn {
  val field = "ISODOW"
}

case class ExtractIsoYearFn(col: TypeCol[_]) extends ExtractFn {
  val field = "ISOYEAR"
}

case class ExtractMicrosecondsFn(col: TypeCol[_]) extends ExtractFn {
  val field = "MICROSECONDS"
}

case class ExtractMillisecondsFn(col: TypeCol[_]) extends ExtractFn {
  val field = "MILLISECONDS"
}

case class ExtractMinuteFn(col: TypeCol[_]) extends ExtractFn {
  val field = "MINUTE"
}

case class ExtractMonthFn(col: TypeCol[_]) extends ExtractFn {
  val field = "MONTH"
}

case class ExtractQuarterFn(col: TypeCol[_]) extends ExtractFn {
  val field = "QUARTER"
}

case class ExtractSecondFn(col: TypeCol[_]) extends ExtractFn {
  val field = "SECOND"
}

case class ExtractWeekFn(col: TypeCol[_]) extends ExtractFn {
  val field = "WEEK"
}

case class ExtractYearFn(col: TypeCol[_]) extends ExtractFn {
  val field = "YEAR"
}

case class ExtractEpochSecsFn(col: TypeCol[_]) extends LongCol with FnCol {
  def name = col.name
  val template = "EXTRACT(EPOCH FROM %s)::bigint"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}

case class ExtractEpochMillisFn(col: TypeCol[_]) extends LongCol with FnCol {
  def name = col.name
  val template = "(EXTRACT(EPOCH FROM %s) * 1000)::bigint"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}

// cast




















