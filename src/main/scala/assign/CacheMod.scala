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
import kuzminki.shape.CachePart
import kuzminki.render.{Renderable, Prefix}
import kuzminki.api.{KuzminkiError, CacheArg}
import kuzminki.conv.ValConv


trait CacheMod[P] extends CachePart[P] {
  val conv: ValConv[P]
  val template: String
  val args = Vector(CacheArg)
  def validateCol(col: Renderable): Unit = col match {
    case col: ModelCol =>
    case _ => throw KuzminkiError("cannot update a function") 
  }
}

abstract class CacheModOne[P](col: Renderable) extends CacheMod[P] {
  def render(prefix: Prefix) = template.format(col.render(prefix))
  validateCol(col)
}

abstract class CacheModTwo[P](col: Renderable) extends CacheMod[P] {
  def render(prefix: Prefix) = template.format(col.render(prefix), col.render(prefix))
  validateCol(col)
}

case class CacheSet[P](col: Renderable, conv: ValConv[P]) extends CacheModOne[P](col) {
  val template = s"%s = ?"
}

// numeric

case class CacheIncrement[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s + ?"
}

case class CacheDecrement[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s - ?"
}

// array

case class CacheAppend[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = ? || %s"
}

case class CachePrepend[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = ? || %s"
}

case class CacheRemove[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = array_remove(%s, ?)"
}

case class CacheSeqAdd[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = "%s = ARRAY(SELECT DISTINCT e FROM unnest(%s || ?) AS a(e))"
}

case class CacheSeqAddAsc[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = "%s = ARRAY(SELECT DISTINCT e FROM unnest(%s || ?) AS a(e) ORDER BY e ASC)"
}

case class CacheSeqAddDesc[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = "%s = ARRAY(SELECT DISTINCT e FROM unnest(%s || ?) AS a(e) ORDER BY e DESC)"
}

case class CacheSeqAddJsonb[P](col: Renderable, conv: ValConv[P], sort: String) extends CacheModTwo[P](col) {
  val template = s"%s = ARRAY(SELECT DISTINCT ON (e->'$sort') e FROM unnest(%s || ?) AS a(e))"
}

case class CacheSeqAddJsonbAsc[P](col: Renderable, conv: ValConv[P], sort: String) extends CacheModTwo[P](col) {
  val template = s"%s = ARRAY(SELECT DISTINCT ON (e->'$sort') e FROM unnest(%s || ?) AS a(e) ORDER BY e->'$sort' ASC)"
}

case class CacheSeqAddJsonbDesc[P](col: Renderable, conv: ValConv[P], sort: String) extends CacheModTwo[P](col) {
  val template = s"%s = ARRAY(SELECT DISTINCT ON (e->'$sort') e FROM unnest(%s || ?) AS a(e) ORDER BY e->'$sort' DESC)"
}

case class CacheSeqRemoveJsonb[P](col: Renderable, conv: ValConv[P], sort: String) extends CacheModTwo[P](col) {
  val template = s"%s = ARRAY(SELECT e FROM unnest(%s) AS a(e)) WHERE e->>'$sort' != ?"
}

// jsonb

case class CacheJsonbSet[P](col: Renderable, conv: ValConv[P]) extends CacheModOne[P](col) {
  val template = s"%s = ?"
}

case class CacheJsonbUpdate[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s || ?"
}

case class CacheJsonbDelKey[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s - ?"
}

case class CacheJsonbDelIndex[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s - ?"
}

case class CacheJsonbDelPath[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s #- ?"
}

// timestamp / date / time

case class CacheSetNow[P](col: Renderable, conv: ValConv[P]) extends CacheModOne[P](col) {
  val template = s"%s = now()"
}

case class CacheSetTimeNow[P](col: Renderable, conv: ValConv[P]) extends CacheModOne[P](col) {
  val template = s"%s = timeofday()"
}

case class CacheDateTimeInc[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s + ?"
}

case class CacheDateTimeDec[P](col: Renderable, conv: ValConv[P]) extends CacheModTwo[P](col) {
  val template = s"%s = %s - ?"
}














