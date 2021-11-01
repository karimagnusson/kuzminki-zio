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

import kuzminki.filter.Filter
import kuzminki.section.Section
import kuzminki.section.select.{HavingSec, HavingBlankSec}


class Having[M, R](
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
    toOrderBy(HavingBlankSec)
  }

  def having(pick: M => Seq[Filter]) = {
    toOrderBy(
      HavingSec(pick(model)) 
    )
  }

  def havingOne(pick: M => Filter) = {
    toOrderBy(
      HavingSec(
        Seq(pick(model))
      )
    )
  }

  def havingOpts(pick: M => Seq[Option[Filter]]) = {
    toOrderBy(
      pick(model).flatten match {
        case Nil =>
          HavingBlankSec
        case filters =>
          HavingSec(pick(model).flatten)
      }
    )
  }

  def havingOpt(pick: M => Option[Filter]) = {
    toOrderBy(
      pick(model) match {
        case Some(cond) =>
          HavingSec(Seq(cond))
        case None =>
          HavingBlankSec
      }
    )
  }
}