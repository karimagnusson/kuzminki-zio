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

import kuzminki.column._
import kuzminki.fn.types._
import kuzminki.column.TypeCol
import kuzminki.render.Prefix
import kuzminki.api.KuzminkiError
import org.postgresql.util.PGInterval


case class CustomStringFn(col: TypeCol[String], template: String) extends StringNoArgsFn with FnCol

case class CoalesceFn[T](col: TypeCol[T], arg: Any) extends TypeArgsFn[T] {
  def template = "coalesce(%s, ?)"
  def fnArgs = Vector(arg)
}

case class ConcatFn(cols: Vector[TypeCol[_]]) extends StringCol with FnCol {
  def name = "concat"
  def template = "concat(%s)"
  def render(prefix: Prefix) = {
    template.format(
      cols.map(_.render(prefix)).mkString(", ")
    )
  }
  val args = cols.map(_.args).flatten
}

case class ConcatWsFn(glue: String, cols: Vector[TypeCol[_]]) extends StringCol with FnCol {
  def name = "concat"
  def template = s"concat_ws('$glue', %s)"
  def render(prefix: Prefix) = {
    template.format(
      cols.map(_.render(prefix)).mkString(", ")
    )
  }
  val args = cols.map(_.args).flatten
}

case class SubstrFn(
  col: TypeCol[String],
  start: Int,
  lenOpt: Option[Int]
) extends StringNoArgsFn {
  def template = lenOpt match {
    case Some(len) => s"substr(%s, $start, $len)"
    case None => s"substr(%s, $start)"
  }
}

case class ReplaceFn(
  col: TypeCol[String],
  from: String,
  to: String
) extends StringNoArgsFn {
  def template = s"replace(%s, '$from', '$to')"
}















