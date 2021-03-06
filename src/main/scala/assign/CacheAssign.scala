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

import kuzminki.column.TypeCol
import kuzminki.shape.CachePart
import kuzminki.render.{Renderable, Prefix}


trait CacheAssign[T] extends CachePart[T] {
  val col: TypeCol[T]
  val template = ""
  val conv = col.conv
  val args = Vector.empty[Any]
}


case class CacheSet[T](col: TypeCol[T]) extends CacheAssign[T] {
  def render(prefix: Prefix) = {
    val name = col.render(prefix)
    s"$name = ?"
  }
}

case class CacheIncrement[T](col: TypeCol[T]) extends CacheAssign[T] {
  def render(prefix: Prefix) = {
    val name = col.render(prefix)
    s"$name = $name + ?"
  }
}

case class CacheDecrement[T](col: TypeCol[T]) extends CacheAssign[T] {
  def render(prefix: Prefix) = {
    val name = col.render(prefix)
    s"$name = $name - ?"
  }
}









