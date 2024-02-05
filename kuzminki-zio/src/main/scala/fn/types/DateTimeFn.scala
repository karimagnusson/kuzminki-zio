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

import java.sql.Timestamp
import org.postgresql.util.PGInterval
import kuzminki.column._

// common

case class DateTimeIncFn[T](col: TypeCol[T], arg: PGInterval) extends PassConvFn[T] with SingleColArgFn {
  val template = "%s + ?"
}

case class DateTimeDecFn[T](col: TypeCol[T], arg: PGInterval) extends PassConvFn[T] with SingleColArgFn {
  val template = "%s - ?"
}

// timestamp

case class DateTimeFormatFn(col: TypeCol[_], arg: String) extends StringCol with SingleColArgFn {
  val template = "to_char(%s, ?)"
}

case class TimestampAgeFn(col: TypeCol[Timestamp]) extends IntervalCol with SingleColFn {
  val template = "age(%s)"
}










