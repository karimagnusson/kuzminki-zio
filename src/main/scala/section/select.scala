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

package kuzminki.section

import kuzminki.model.ModelTable
import kuzminki.column.AnyCol
import kuzminki.sorting.Sorting
import kuzminki.render.{Renderable, Prefix, NoArgs}


package object select extends FilterSections {

  case class SelectSec(parts: Seq[AnyCol]) extends NotEmpty(parts) with MultiPartRender {
    def error = "no columns selected"
    val expression = "SELECT %s"
    val glue = ", "
  }

  case class FromSec(part: ModelTable) extends SinglePartRender {
    val expression = "FROM %s"
  }

  case class GroupBySec(parts: Seq[AnyCol]) extends NotEmpty(parts) with MultiPartRender {
    def error = "WHERE BY cannot be empty"
    val expression = "GROUP BY %s"
    val glue = ", "
  }

  case class OrderBySec(parts: Seq[Sorting]) extends NotEmpty(parts) with MultiPartRender {
    def error = "ORDER BY cannot be empty"
    val expression = "ORDER BY %s"
    val glue = ", "
  }

  case class OffsetSec(arg: Int) extends SingleArgRender {
    val expression = "OFFSET ?"
  }

  case class LimitSec(arg: Int) extends SingleArgRender {
    val expression = "LIMIT ?"
  }

  // cache

  case class WhereCacheSec(cacheConds: Seq[Renderable]) extends CacheCondition {
    val expression = "WHERE %s"
  }

  case class WhereMixedSec(conds: Seq[Renderable], cacheConds: Seq[Renderable]) extends MixedCondition {
    val expression = "WHERE %s"
  }

  case class HavingCacheSec(cacheConds: Seq[Renderable]) extends CacheCondition {
    val expression = "HAVING %s"
  }

  case class HavingMixedSec(conds: Seq[Renderable], cacheConds: Seq[Renderable]) extends MixedCondition {
    val expression = "HAVING %s"
  }

  object OffsetCacheSec extends Section with NoRender {
    val expression = "OFFSET ?"
    def args = Seq(CacheOffsetArgs)
  }

  // join

  case class OnSec(leftCol: AnyCol, rightCol: AnyCol) extends Section with NoArgs {
    val expression = "ON %s = %s"
    def render(prefix: Prefix) = expression.format(leftCol.render(prefix), rightCol.render(prefix))
  }

  case class InnerJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "INNER JOIN %s"
  }


  case class LeftJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "LEFT JOIN %s"
  }


  case class LeftOuterJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "LEFT OUTER JOIN %s"
  }


  case class RightJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "RIGHT JOIN %s"
  }


  case class RightOuterJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "RIGHT OUTER JOIN %s"
  }


  case class FullOuterJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "FULL OUTER JOIN %s"
  }


  case class CrossJoinSec(part: ModelTable) extends SinglePartRender {
    val expression = "CROSS JOIN %s"
  }
}



