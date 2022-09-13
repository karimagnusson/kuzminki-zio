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
import kuzminki.shape.CachePart
import kuzminki.render.{Renderable, Prefix, Wrap}
import kuzminki.api.{KuzminkiError, Jsonb}
import kuzminki.conv._


object CacheMod extends Wrap {

  import CachePart.itemConv
  
  private def name[T](col: TypeCol[T]): String = {
    col match {
      case col: ModelCol =>
      case _ => throw KuzminkiError("cannot cache a function")
    }
    wrap(col.name)
  }

  def set[T](col: TypeCol[T]) = CacheSet(name(col), col.conv)

  def inc[T](col: TypeCol[T]) = CacheIncrement(name(col), col.conv)
  def dec[T](col: TypeCol[T]) = CacheDecrement(name(col), col.conv)

  def append[T](col: TypeCol[Seq[T]]) = CacheAppend(name(col), itemConv(col.conv))
  def prepend[T](col: TypeCol[Seq[T]]) = CachePrepend(name(col), itemConv(col.conv))
  def remove[T](col: TypeCol[Seq[T]]) = CacheRemove(name(col), itemConv(col.conv))

  def jsonbSet[T](col: TypeCol[T]) = CacheJsonbSet(name(col), col.conv)
  def jsonbUpdate[T](col: TypeCol[T]) = CacheJsonbUpdate(name(col), col.conv)
  def jsonbDelKey[T, P](col: TypeCol[T]) = CacheJsonbDelKey(name(col), StringConv)
  def jsonbDelIndex[T, P](col: TypeCol[T]) = CacheJsonbDelIndex(name(col), IntConv)
  def jsonbDelPath[T, P](col: TypeCol[T]) = CacheJsonbDelPath(name(col), StringSeqConv)
}

trait CacheMod[P] extends CachePart[P] with Wrap {
  val conv: ValConv[P]
  val name: String
  val template: String
  def render(prefix: Prefix) = template
  val args = Vector.empty[Any]
}

case class CacheSet[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = ?"
}

// numeric

case class CacheIncrement[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = $name + ?"
}

case class CacheDecrement[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = $name - ?"
}

// array

case class CacheAppend[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = array_append($name, ?)"
}

case class CachePrepend[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = array_prepend(?, $name)"
}

case class CacheRemove[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = array_remove($name, ?)"
}

// jsonb

case class CacheJsonbSet[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = ?::jsonb"
}

case class CacheJsonbUpdate[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = $name || ?::jsonb"
}

case class CacheJsonbDelKey[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = $name - ?"
}

case class CacheJsonbDelIndex[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = $name - ?"
}

case class CacheJsonbDelPath[P](name: String, conv: ValConv[P]) extends CacheMod[P] {
  val template = s"$name = $name #- ?"
}
















