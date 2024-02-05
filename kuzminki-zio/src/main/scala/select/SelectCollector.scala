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

import kuzminki.render.Prefix
import kuzminki.section._
import kuzminki.shape._


case class SelectCollector[R](
  prefix: Prefix,
  rowShape: RowShape[R],
  sections: Vector[Section]
) {

  def add(section: Section) = this.copy(sections = sections :+ section)

  def extend(added: Vector[Section]) = this.copy(sections = sections ++ added)

  val notBlank: Section => Boolean = {
    case WhereBlankSec => false
    case HavingBlankSec => false
    case _ => true
  }

  private def replaceBlank[P](partShape: PartShape[P]) = sections.map {
    case WhereBlankSec =>
      WhereSec(partShape.parts)
    case WhereSec(conds) =>
      WhereSec(conds ++ partShape.parts)
    case section: Section =>
      section
  }

  def render = sections.filter(notBlank).map(_.render(prefix)).mkString(" ")
  
  def args = sections.map(_.args).flatten.toVector

  def renderCache[P](partShape: PartShape[P]) = {
    val modifiedSections = replaceBlank(partShape)
    new StoredSelect(
      modifiedSections.filter(notBlank).map(_.render(prefix)).mkString(" "),
      modifiedSections.map(_.args).flatten,
      partShape.conv,
      rowShape.conv
    )
  }

  // subquery

  def renderSubquery(parPrefix: Prefix) = {
    val subPrefix = prefix.forSubquery(parPrefix)
    sections.filter(notBlank).map(_.render(subPrefix)).mkString(" ")
  }

  def asSubqueryInsertFc[P](partShape: PartShape[P]) = {
    SubqueryInsertFc(prefix, replaceBlank(partShape), partShape.conv, rowShape)
  }

  def asSubqueryInFc[P](partShape: PartShapeSingle[P]) = {
    SubqueryInFc(prefix, replaceBlank(partShape), partShape.cond.conv, rowShape)
  }
}























