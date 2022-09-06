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


trait Assign extends Renderable with Wrap {
  val col: TypeCol[_]
  val name = wrap(col.name)
  def validateCol() = col match {
    case col: ModelCol =>
    case _ => throw KuzminkiError("cannot update a function") 
  }
}

// general

case class SetValue(col: TypeCol[_], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = ?"
  val args = Vector(value)
}

case class SetToNull(col: TypeCol[_]) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = NULL"
  val args = Vector.empty[Any]
}

// numeric

case class Increment(col: TypeCol[_], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = $name + ?"
  val args = Vector(value)
}

case class Decrement(col: TypeCol[_], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = $name - ?"
  val args = Vector(value)
}

// array

case class Append(col: TypeCol[_], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = array_append($name, ?)"
  val args = Vector(value)
}

case class Prepend(col: TypeCol[_], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = array_prepend(?, $name)"
  val args = Vector(value)
}

case class Remove(col: TypeCol[_], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = array_remove($name, ?)"
  val args = Vector(value)
}

// jsonb

case class JsonbSetValue(col: TypeCol[Jsonb], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = ?::jsonb"
  val args = Vector(value)
}

case class JsonbUpdate(col: TypeCol[Jsonb], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = $name || ?::jsonb"
  val args = Vector(value)
}

case class JsonbDel(col: TypeCol[Jsonb], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = $name - ?"
  val args = Vector(value)
}

case class JsonbDelPath(col: TypeCol[Jsonb], value: Any) extends Assign {
  validateCol()
  def render(prefix: Prefix) = s"$name = $name #- ?"
  val args = Vector(value)
}











