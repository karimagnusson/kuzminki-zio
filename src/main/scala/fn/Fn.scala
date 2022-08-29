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

import kuzminki.column._
import kuzminki.fn.types._
import kuzminki.column.TypeCol
import kuzminki.render.Prefix

// coalesce

object Fn {

  import general._

  // string

  def coalesce[T](col: TypeCol[T], default: T) = Coalesce(col, default)

  def concat(cols: TypeCol[_]*) = Concat(cols.toVector)

  def concatWs(glue: String, cols: TypeCol[_]*) = ConcatWs(glue, cols.toVector)

  def substr(col: TypeCol[String], start: Int) = Substr(col, start, None)

  def substr(col: TypeCol[String], start: Int, len: Int) = Substr(col, start, Some(len))

  def replace(col: TypeCol[String], from: String, to: String) = Replace(col, from, to)

  def trim(col: TypeCol[String]) = CustomStringFn(col, "trim(%s)")

  def upper(col: TypeCol[String]) = CustomStringFn(col, "upper(%s)")

  def lower(col: TypeCol[String]) = CustomStringFn(col, "lower(%s)")

  def initcap(col: TypeCol[String]) = CustomStringFn(col, "initcap(%s)")

  // numeric

  def round[T](col: TypeCol[BigDecimal], size: Int) = Round(col, size)

  def roundStr[T](col: TypeCol[BigDecimal], size: Int) = RoundStr(col, size)

  def roundAny[T](col: TypeCol[_], size: Int) = RoundAny(col, size)

  def roundAnyStr[T](col: TypeCol[BigDecimal], size: Int) = RoundAnyStr(col, size)
}


package object general {

  case class CustomStringFn(col: TypeCol[String], template: String) extends StringFn

  case class Coalesce[T](col: TypeCol[T], default: T) extends TypeFn[T] {
    val template = "coalesce(%s, ?)"
    val params = Vector(default)
  }

  case class Concat(cols: Vector[TypeCol[_]]) extends StringCol {
    val template = "concat(%s)"
    def name = cols.map(_.name).mkString("_")
    def render(prefix: Prefix) = {
      template.format(
        cols.map(_.render(prefix)).mkString(", ")
      )
    }
    val args = cols.map(_.args).flatten
  }

  case class ConcatWs(glue: String, cols: Vector[TypeCol[_]]) extends StringCol {
    val template = s"concat_ws('$glue', %s)"
    def name = cols.map(_.name).mkString("_")
    def render(prefix: Prefix) = {
      template.format(
        cols.map(_.render(prefix)).mkString(", ")
      )
    }
    val args = cols.map(_.args).flatten
  }

  case class Substr(
    col: TypeCol[String],
    start: Int,
    lenOpt: Option[Int]
  ) extends StringFn {
    val template = lenOpt match {
      case Some(len) => s"substr(%s, $start, $len)"
      case None => s"substr(%s, $start)"
    }
  }

  case class Replace(
    col: TypeCol[String],
    from: String,
    to: String
  ) extends StringFn {
    val template = s"replace(%s, '$from', '$to')"
  }

  // round

  trait RoundFn extends FnRender {
    val size: Int
    def name = col.name
    val args = col.args ++ Vector(size)
  }

  case class Round(col: TypeCol[BigDecimal], size: Int) extends BigDecimalCol with RoundFn {
    val template = "round(%s, ?)"
  }

  case class RoundStr(col: TypeCol[BigDecimal], size: Int) extends StringCol with RoundFn {
    val template = "round(%s, ?)::text"
  }

  case class RoundAny(col: TypeCol[_], size: Int) extends BigDecimalCol with RoundFn {
    val template = "round(%s::numeric, ?)"
  }

  case class RoundAnyStr(col: TypeCol[_], size: Int) extends StringCol with RoundFn {
    val template = "round(%s::numeric, ?)::text"
  }
}














