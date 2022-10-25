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
import kuzminki.api.Jsonb
import kuzminki.render.Prefix
import kuzminki.shape.CachePart.itemConv


case class SeqUnnestFn[T](col: TypeCol[Seq[T]]) extends TypeCol[T] with FnCol {
  val conv = itemConv(col.conv)
  def name = col.name
  def template = "unnest(%s)"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}

case class SeqLengthFn[T](col: TypeCol[Seq[T]]) extends IntCol with FnCol {
  def name = col.name
  def template = "array_length(%s, 1)"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}

case class SeqTrimFn[T](col: TypeCol[Seq[T]], arg: Any) extends TypeCol[Seq[T]] with FnCol {
  val conv = col.conv
  def name = col.name
  def template = "trim_array(%s, ?)"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(arg)
}

case class SeqPosFn[T](col: TypeCol[Seq[T]], arg: Any) extends IntCol with FnCol {
  def name = col.name
  def template = "array_position(%s, ?)"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(arg)
}

case class SeqGetFn[T](col: TypeCol[Seq[T]], arg: Any) extends TypeCol[T] with FnCol {
  val conv = itemConv(col.conv)
  def name = col.name
  def template = "(%s)[?]"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(arg)
}

case class SeqFirstFn[T](col: TypeCol[Seq[T]]) extends TypeCol[T] with FnCol {
  val conv = itemConv(col.conv)
  def name = col.name
  def template = "(%s)[1]"
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}

case class SeqLastFn[T](col: TypeCol[Seq[T]]) extends TypeCol[T] with FnCol {
  val conv = itemConv(col.conv)
  def name = col.name
  def template = "(%s)[array_length(%s, 1)]"
  def render(prefix: Prefix) = {
    template.format(col.render(prefix), col.render(prefix))
  }
  val args = col.args
}

case class SeqJoinFn[T](col: TypeCol[Seq[T]], arg: Any) extends TypeCol[T] with FnCol {
  val conv = itemConv(col.conv)
  def name = col.name
  def template = "array_to_string(%s, ?)"
  def render(prefix: Prefix) = {
    template.format(col.render(prefix), col.render(prefix))
  }
  val args = col.args ++ Vector(arg)
}

case class SeqExtendFn[T](col: TypeCol[Seq[T]], col2: TypeCol[Seq[T]]) extends TypeCol[T] with FnCol {
  val conv = itemConv(col.conv)
  def name = col.name
  def template = "%s || %s"
  def render(prefix: Prefix) = {
    template.format(col.render(prefix), col2.render(prefix))
  }
  val args = col.args
}











