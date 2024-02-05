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

import kuzminki.fn.TypeFn
import kuzminki.column._
import kuzminki.shape.CachePart.itemConv


case class SeqUnnestFn[T](col: TypeCol[Seq[T]]) extends TypeCol[T] with SingleColFn {
  val conv = itemConv(col.conv)
  val template = "unnest(%s)"
}

case class SeqLengthFn[T](col: TypeCol[Seq[T]]) extends IntCol with SingleColFn {
  val template = "array_length(%s, 1)"
}

case class SeqTrimFn[T](col: TypeCol[Seq[T]], size: Int) extends TypeCol[Seq[T]] with SingleColFn {
  val conv = col.conv
  val template = s"trim_array(%s, $size)"
}

case class SeqPosFn[T](col: TypeCol[Seq[T]], arg: T) extends IntCol with SingleColArgFn {
  val template = s"array_position(%s, ?)"
}

case class SeqGetFn[T](col: TypeCol[Seq[T]], index: Int) extends TypeCol[T] with SingleColFn {
  val conv = itemConv(col.conv)
  val template = s"(%s)[$index]"
}

case class SeqFirstFn[T](col: TypeCol[Seq[T]]) extends TypeCol[T] with SingleColFn {
  val conv = itemConv(col.conv)
  val template = "(%s)[1]"
}

case class SeqLastFn[T](col: TypeCol[Seq[T]]) extends TypeFn[T] {
  val conv = itemConv(col.conv)
  def name = col.name
  val template = "(%s)[array_length(%s, 1)]"
  val cols = Vector(col, col)
  override val args = col.args
}

case class SeqJoinFn[T](col: TypeCol[Seq[T]], glue: String) extends StringCol with SingleColFn {
  val template = s"array_to_string(%s, '$glue')"
}

case class SeqExtendFn[T](col1: TypeCol[Seq[T]], col2: TypeCol[Seq[T]]) extends TypeFn[T] {
  val conv = itemConv(col1.conv)
  def name = "extend"
  val template = "%s || %s"
  val cols = Vector(col1, col2)
}











