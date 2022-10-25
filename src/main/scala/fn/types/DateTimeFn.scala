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
import org.postgresql.util.PGInterval
import kuzminki.column._
import kuzminki.fn.types._
import kuzminki.render.Prefix
import kuzminki.api.Jsonb

// common

case class DateTimeIncFn[T](col: TypeCol[T], value: PGInterval) extends TypeCol[T] with FnCol {
  val conv = col.conv
  def name = col.name
  def template = "%s + ?"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(value)
}

case class DateTimeDecFn[T](col: TypeCol[T], value: PGInterval) extends TypeCol[T] with FnCol {
  val conv = col.conv
  def name = col.name
  def template = "%s - ?"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(value)
}

// timestamp

case class DateTimeFormatFn(col: TypeCol[_], format: String) extends StringCol with FnCol {
  def name = col.name
  def template = "to_char(%s, ?)"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(format)
}

case class TimestampAgeFn(col: TypeCol[Timestamp]) extends IntervalCol with FnCol {
  def name = col.name
  def template = "age(%s)"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}










