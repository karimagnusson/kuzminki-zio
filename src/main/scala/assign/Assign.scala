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

import kuzminki.column.{ModelCol, TypeCol}
import kuzminki.render.{Renderable, Prefix}
import kuzminki.api.KuzminkiError


trait Assign extends Renderable {
  val template: String
  def validateCol(col: Renderable): Unit = col match {
    case col: ModelCol =>
    case _ => throw KuzminkiError("cannot update a function") 
  }
}

abstract class AssignOne(col: Renderable) extends Assign {
  def render(prefix: Prefix) = template.format(col.render(prefix))
  validateCol(col)
}

abstract class AssignTwo(col: Renderable) extends Assign {
  def render(prefix: Prefix) = template.format(col.render(prefix), col.render(prefix))
  validateCol(col)
}

// general

case class SetValue(col: Renderable, arg: Any) extends AssignOne(col) {
  val template = "%s = ?"
  val args = Vector(arg)
}

// numeric

case class Increment(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s + ?"
  val args = Vector(arg)
}

case class Decrement(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s - ?"
  val args = Vector(arg)
}

// array

case class Append(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s || ?"
  val args = Vector(arg)
}

case class Prepend(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = ? || %s"
  val args = Vector(arg)
}

case class Remove(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = array_remove(%s, ?)"
  val args = Vector(arg)
}

case class Add(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = ARRAY(SELECT DISTINCT e FROM unnest(%s || ?) AS a(e))"
  val args = Vector(arg)
}

case class AddAsc(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = ARRAY(SELECT DISTINCT e FROM unnest(%s || ?) AS a(e) ORDER BY e ASC)"
  val args = Vector(arg)
}

case class AddDesc(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = ARRAY(SELECT DISTINCT e FROM unnest(%s || ?) AS a(e) ORDER BY e DESC)"
  val args = Vector(arg)
}

case class AddJsonb(col: Renderable, arg: Any, sort: String) extends AssignTwo(col) {
  val template = s"%s = ARRAY(SELECT DISTINCT ON (e->'$sort') e FROM unnest(%s || ?) AS a(e))"
  val args = Vector(arg)
}

case class AddJsonbAsc(col: Renderable, arg: Any, sort: String) extends AssignTwo(col) {
  val template = s"%s = ARRAY(SELECT DISTINCT ON (e->'$sort') e FROM unnest(%s || ?) AS a(e) ORDER BY e->'$sort' ASC)"
  val args = Vector(arg)
}

case class AddJsonbDesc(col: Renderable, arg: Any, sort: String) extends AssignTwo(col) {
  val template = s"%s = ARRAY(SELECT DISTINCT ON (e->'$sort') e FROM unnest(%s || ?) AS a(e) ORDER BY e->'$sort' DESC)"
  val args = Vector(arg)
}

case class RemoveJsonb(col: Renderable, arg: Any, sort: String) extends AssignTwo(col) {
  val template = s"%s = ARRAY(SELECT e FROM unnest(%s) AS a(e)) WHERE e->>'$sort' != ?"
  val args = Vector(arg)
}

// jsonb

case class JsonbUpdate(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s || ?"
  val args = Vector(arg)
}

case class JsonbDel(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s - ?"
  val args = Vector(arg)
}

case class JsonbDelPath(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s #- ?"
  val args = Vector(arg)
}

// timestamp / date / time

case class TimestampNow(col: Renderable) extends AssignOne(col) {
  val template = "%s = now()"
  val args = Vector.empty[Any]
}

case class TimeNow(col: Renderable) extends AssignOne(col) {
  val template = "%s = timeofday()"
  val args = Vector.empty[Any]
}

case class DateTimeInc(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s + ?"
  val args = Vector(arg)
}

case class DateTimeDec(col: Renderable, arg: Any) extends AssignTwo(col) {
  val template = "%s = %s - ?"
  val args = Vector(arg)
}









