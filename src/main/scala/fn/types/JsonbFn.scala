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
import kuzminki.conv._
import kuzminki.api.{Jsonb, KuzminkiError}
import kuzminki.render.Prefix


trait JsonbFn extends FnRender

case class JsonbPickFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with JsonbFn {
  def template = "%s->?"
  val args = col.args ++ Vector(arg)
}

case class JsonbPickStrFn(col: TypeCol[Jsonb], arg: Any) extends StringCol with JsonbFn {
  def template = "%s->>?"
  val args = col.args ++ Vector(arg)
}

case class JsonbPathFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with JsonbFn {
  val template = "%s#>?"
  val args = col.args ++ Vector(arg)
}

case class JsonbPathStrFn(col: TypeCol[Jsonb], arg: Any) extends StringCol with JsonbFn {
  def template = "%s#>>?"
  val args = col.args ++ Vector(arg)
}

case class JsonbConcatFn(col: TypeCol[Jsonb], col2: TypeCol[Jsonb]) extends JsonbCol with FnCol {
  def name = col.name
  def template = "%s || %s"
  def render(prefix: Prefix) = template.format(col.render(prefix), col2.render(prefix))
  val args = col.args ++ col2.args
}

case class JsonbDropFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with JsonbFn {
  def template = "%s - ?"
  val args = col.args ++ Vector(arg)
}

case class JsonbDropManyFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with JsonbFn {
  def template = "%s - ?"
  val args = col.args ++ Vector(arg)
}

case class JsonbDropPathFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with JsonbFn {
  def template = "%s #- ?"
  val args = col.args ++ Vector(arg)
}

case class JsonbResFn(cols: Seq[Tuple2[String, TypeCol[_]]]) extends JsonbCol with FnCol {
  def name = "json"
  def template = "json_build_object(%s)"
  def render(prefix: Prefix) = template.format(
    cols.map {
      case (name, col) =>
        "'%s', %s".format(name, col.render(prefix))
    }.mkString(", ")
  )
  val args = cols.map(t => t._2.args).flatten.toVector
}













