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

import kuzminki.column.TypeCol
import kuzminki.filter.Filter
import kuzminki.section.Section
import kuzminki.section.{
  Section,
  WhereSec,
  WhereBlankSec,
  SelectSec,
  SelectDistinctSec,
  SelectDistinctOnSec,
  GroupBySec
}


class Where[M, R](
    model: M,
    coll: SelectCollector[R]
  ) {

  private def toOrderBy(section: Section) = {
    new OrderBy(
      model,
      coll.add(section)
    )
  }

  def all = {
    toOrderBy(WhereBlankSec)
  }

  def where(pick: M => Seq[Filter]) = {
    toOrderBy(
      WhereSec(pick(model).toVector)
    )
  }

  def whereOpt(pick: M => Seq[Option[Filter]]) = {
    toOrderBy(
      pick(model).flatten match {
        case Nil =>
          WhereBlankSec
        case filters =>
          WhereSec(filters.toVector)
      }
    )
  }

  // distinct

  def distinct = {
    val sections = coll.sections.map {
      case sec: SelectSec => SelectDistinctSec(sec.parts)
      case sec => sec
    }
    new Where(
      model,
      SelectCollector(
        coll.prefix,
        coll.rowShape,
        sections
      )
    )
  }

  def distinctOn(pick: M => Seq[TypeCol[_]]) = {
    val picks = pick(model)
    val sections = coll.sections.map {
      case sec: SelectSec =>
        SelectDistinctOnSec(
          sec.parts.filter(p => picks.contains(p)),
          sec.parts
        )
      case sec => sec
    }
    new Where(
      model,
      SelectCollector(
        coll.prefix,
        coll.rowShape,
        sections
      )
    )
  }
}
























