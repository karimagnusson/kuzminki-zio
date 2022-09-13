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

package kuzminki.assign

import kuzminki.column.{TypeCol, ModelCol}
import kuzminki.render.{Renderable, Prefix, Wrap}
import kuzminki.api.KuzminkiError
import kuzminki.api.Jsonb


abstract class Assign(col: TypeCol[_], valueOpt: Option[Any]) extends Renderable with Wrap {
  val template: String
  def name = wrap(col.name)
  def render(prefix: Prefix) = template
  val args = valueOpt match {
    case Some(value) => Vector(value)
    case None => Vector.empty[Any]
  }
  col match {
    case col: ModelCol =>
    case _ => throw KuzminkiError("cannot update a function") 
  }
}

// general

case class SetValue(col: TypeCol[_], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = ?"
}

case class SetToNull(col: TypeCol[_]) extends Assign(col, None) {
  val template = s"$name = NULL"
}

// numeric

case class Increment(col: TypeCol[_], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = $name + ?"
}

case class Decrement(col: TypeCol[_], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = $name - ?"
}

// array

case class Append(col: TypeCol[_], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = array_append($name, ?)"
}

case class Prepend(col: TypeCol[_], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = array_prepend(?, $name)"
}

case class Remove(col: TypeCol[_], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = array_remove($name, ?)"
}

// jsonb

case class JsonbSetValue(col: TypeCol[Jsonb], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = ?::jsonb"
}

case class JsonbUpdate(col: TypeCol[Jsonb], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = $name || ?::jsonb"
}

case class JsonbDel(col: TypeCol[Jsonb], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = $name - ?"
}

case class JsonbDelPath(col: TypeCol[Jsonb], value: Any) extends Assign(col, Some(value)) {
  val template = s"$name = $name #- ?"
}











