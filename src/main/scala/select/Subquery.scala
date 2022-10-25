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

package kuzminki.select

import kuzminki.conv.ValConv
import kuzminki.column.TypeCol
import kuzminki.section._
import kuzminki.shape._
import kuzminki.shape.CachePart.seqConv
import kuzminki.filter.types.FilterParentEqCol
import kuzminki.render.{Renderable, Prefix}


class Subquery[R](coll: SelectCollector[R]) extends Renderable {
  def render(parPrefix: Prefix) = coll.renderSubquery(parPrefix)
  val args = coll.args
}


class SubqueryCol[R](
  coll: SelectCollector[R],
  colConv: ValConv[R]
) extends TypeCol[Seq[R]] {

  def name = "subquery"
  def template = "ARRAY(%s)"
  val conv = seqConv(colConv)

  def renderParts(parts: Vector[Renderable], parPrefix: Prefix) = parts.map {
    case filter: FilterParentEqCol => filter.renderParent(parPrefix)
    case filter => filter
  }

  def renderParent(section: Section, parPrefix: Prefix) = section match {
    case WhereSec(parts) =>
      WhereSec(renderParts(parts, parPrefix))
    case HavingSec(parts) =>
      HavingSec(renderParts(parts, parPrefix))
    case sec => sec
  }

  def render(parPrefix: Prefix) = {
    val subPrefix = coll.prefix.forSubquery(parPrefix)
    val stm = coll
      .sections
      .map(sec => renderParent(sec, parPrefix))
      .map(_.render(subPrefix))
      .mkString(" ")
    template.format(stm)
  }

  val args = coll.args
}

// cache

trait SubqueryFc extends Renderable {
  val prefix: Prefix
  val sections: Vector[Section]
  def render(parPrefix: Prefix) = {
    val subPrefix = prefix.forSubquery(parPrefix)
    sections
      .map(_.render(subPrefix))
      .mkString(" ")
  }
  val args = sections.map(_.args).flatten.toVector
}

case class SubqueryInFc[P, R](
  prefix: Prefix,
  sections: Vector[Section],
  valConv: ValConv[P],
  rowShape: RowShape[R]
) extends SubqueryFc


case class SubqueryInsertFc[P, R](
  prefix: Prefix,
  sections: Vector[Section],
  paramConv: ParamConv[P],
  rowShape: RowShape[R]
) extends SubqueryFc

























