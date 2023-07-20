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

package kuzminki.fn

import kuzminki.column.TypeCol
import kuzminki.fn.types._


object Cast {
  def asString(col: TypeCol[_]) = CastString(col)
  def asShort(col: TypeCol[_]) = CastShort(col)
  def asInt(col: TypeCol[_]) = CastInt(col)
  def asLong(col: TypeCol[_]) = CastLong(col)
  def asFloat(col: TypeCol[_]) = CastFloat(col)
  def asDouble(col: TypeCol[_]) = CastDouble(col)
  def asBigDecimal(col: TypeCol[_]) = CastBigDecimal(col)
  def asJsonb(col: TypeCol[_]) = CastJsonb(col)
  def asTimestamp(col: TypeCol[_]) = CastTimestamp(col)
  def asDate(col: TypeCol[_]) = CastDate(col)
  def asTime(col: TypeCol[_]) = CastTime(col)
}





