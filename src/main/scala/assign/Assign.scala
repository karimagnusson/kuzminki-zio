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

import kuzminki.column.ModelCol
import kuzminki.render.{Renderable, Prefix}


trait Assign extends Renderable

case class SetValue(col: ModelCol, value: Any) extends Assign {
  def render(prefix: Prefix) = s"${col.name} = ?"
  val args = Vector(value)
}

case class SetToNull(col: ModelCol) extends Assign {  
  def render(prefix: Prefix) = s"${col.name} = NULL"
  val args = Vector.empty[Any]
}

case class Increment(col: ModelCol, value: Any) extends Assign {
  def render(prefix: Prefix) = s"${col.name} = ${col.name} + ?"
  val args = Vector(value)
}

case class Decrement(col: ModelCol, value: Any) extends Assign {
  def render(prefix: Prefix) = s"${col.name} = ${col.name} - ?"
  val args = Vector(value)
}

case class Append(col: ModelCol, value: Any) extends Assign {
  def render(prefix: Prefix) = s"${col.name} = array_append(${col.name}, ?)"
  val args = Vector(value)
}

case class Prepend(col: ModelCol, value: Any) extends Assign {
  def render(prefix: Prefix) = s"${col.name} = array_prepend(?, ${col.name})"
  val args = Vector(value)
}

case class Remove(col: ModelCol, value: Any) extends Assign {
  def render(prefix: Prefix) = s"${col.name} = array_remove(${col.name}, ?)"
  val args = Vector(value)
}









