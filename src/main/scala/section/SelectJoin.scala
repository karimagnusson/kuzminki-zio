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
import kuzminki.column.TypeCol
import kuzminki.sorting.Sorting
import kuzminki.render.{Renderable, Prefix, NoArgs}


package object join {

  sealed trait JoinSec extends SinglePartRender

  case class OnSec(leftCol: TypeCol[_], rightCol: TypeCol[_]) extends Section with NoArgs {
    val expression = "ON %s = %s"
    def render(prefix: Prefix) = expression.format(leftCol.render(prefix), rightCol.render(prefix))
  }

  case class InnerJoinSec(part: ModelTable) extends JoinSec {
    val expression = "INNER JOIN %s"
  }


  case class LeftJoinSec(part: ModelTable) extends JoinSec {
    val expression = "LEFT JOIN %s"
  }


  case class LeftOuterJoinSec(part: ModelTable) extends JoinSec {
    val expression = "LEFT OUTER JOIN %s"
  }


  case class RightJoinSec(part: ModelTable) extends JoinSec {
    val expression = "RIGHT JOIN %s"
  }


  case class RightOuterJoinSec(part: ModelTable) extends JoinSec {
    val expression = "RIGHT OUTER JOIN %s"
  }


  case class FullOuterJoinSec(part: ModelTable) extends JoinSec {
    val expression = "FULL OUTER JOIN %s"
  }


  case class CrossJoinSec(part: ModelTable) extends JoinSec {
    val expression = "CROSS JOIN %s"
  }
}
















