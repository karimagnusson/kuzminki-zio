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

import kuzminki.column.AnyCol
import kuzminki.function.types._


object Cast {
  import cast._
  def asString(col: AnyCol) = CastString(col)
  def asFloat(col: AnyCol) = CastFloat(col)
  def asDouble(col: AnyCol) = CastDouble(col)
  def asShort(col: AnyCol) = CastShort(col)
  def asInt(col: AnyCol) = CastInt(col)
  def asLong(col: AnyCol) = CastLong(col)
  def asBigDecimal(col: AnyCol) = CastBigDecimal(col)
}


package object cast {

  case class CastString(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::text"
  }

  case class CastFloat(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::real"
  }

  case class CastDouble(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::float8"
  }

  case class CastShort(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::smallint"
  }

  case class CastInt(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::int"
  }

  case class CastLong(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::text"
  }

  case class CastBigDecimal(underlying: AnyCol) extends StringFunctionSingle {
    val template = "%s::numeric"
  }
}



