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

import kuzminki.fn.StringFn
import kuzminki.column._


case class CoalesceFn[T](col: TypeCol[T], arg: Any) extends PassConvFn[T] with SingleColArgFn {
  val template = "coalesce(%s, ?)"
}

case class ConcatFn(cols: Vector[TypeCol[_]]) extends StringFn {
  def name = "concat"
  val template = s"concat(%s)".format(Vector.fill(cols.size)("%s").mkString(", "))
}

case class ConcatWsFn(glue: String, cols: Vector[TypeCol[_]]) extends StringFn {
  def name = "concat"
  val template = s"concat_ws('$glue', %s)".format(Vector.fill(cols.size)("%s").mkString(", "))
}

case class SubstrFn(
  col: TypeCol[String],
  start: Int,
  lenOpt: Option[Int]
) extends StringFn {
  def name = col.name
  val template = lenOpt match {
    case Some(len) => s"substr(%s, $start, $len)"
    case None => s"substr(%s, $start)"
  }
  val cols = Vector(col)
}

case class ReplaceFn(
  col: TypeCol[String],
  from: String,
  to: String
) extends StringFn {
  def name = col.name
  val template = s"replace(%s, '$from', '$to')"
  val cols = Vector(col)
}

case class TrimFn(col: TypeCol[String]) extends StringCol with SingleColFn {
  val template = "trim(%s)"
}

case class UpperFn(col: TypeCol[String]) extends StringCol with SingleColFn {
  val template = "upper(%s)"
}

case class LowerFn(col: TypeCol[String]) extends StringCol with SingleColFn {
  val template = "lower(%s)"
}

case class InitcapFn(col: TypeCol[String]) extends StringCol with SingleColFn {
  val template = "initcap(%s)"
}














