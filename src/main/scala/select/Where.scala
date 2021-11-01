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

import kuzminki.column.AnyCol
import kuzminki.filter.Filter
import kuzminki.section.Section
import kuzminki.section.select.{WhereSec, WhereBlankSec, GroupBySec}


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
      WhereSec(pick(model))
    )
  }

  def whereOne(pick: M => Filter) = {
    toOrderBy(
      WhereSec(
        Seq(pick(model))
      )
    )
  }

  def whereOpts(pick: M => Seq[Option[Filter]]) = {
    toOrderBy(
      pick(model).flatten match {
        case Nil =>
          WhereBlankSec
        case filters =>
          WhereSec(pick(model).flatten)
      }
    )
  }

  def whereOpt(pick: M => Option[Filter]) = {
    toOrderBy(
      pick(model) match {
        case Some(filter) =>
          WhereSec(Seq(filter))
        case None =>
          WhereBlankSec
      }
    )
  }

  // group by

  private def toHaving(cols: Seq[AnyCol]) = {
    new Having(
      model,
      coll.add(GroupBySec(cols))
    )
  }

  def groupByOne(pick: M => AnyCol) = {
    toHaving(
      Seq(pick(model))
    )
  }

  def groupBy(pick: M => Seq[AnyCol]) = {
    toHaving(pick(model))
  }
}




























