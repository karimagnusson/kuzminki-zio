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

package kuzminki.insert

import kuzminki.api.Model
import kuzminki.shape.ParamShape
import kuzminki.section.insert._
import kuzminki.render.{
  SectionCollector,
  RenderedOperation
}


class InsertOptions[M <: Model, P](
      protected val model: M,
      protected val coll: SectionCollector,
      protected val paramShape: ParamShape[P]
    ) extends PickInsertReturning[M, P]
         with WhereNotExists[M, P]
         with OnConflict[M, P]
         with InsertSubquery[P] {

  def cache = {
    new StoredInsert(
      coll.add(
        InsertBlankValuesSec(paramShape.size)
      ).render,
      paramShape.conv
    )
  }

  def render(params: P) = {
    val sections = coll.add(
      InsertValuesSec(
        paramShape.conv.fromShape(params)
      )
    )
    RenderedOperation(
      sections.render,
      sections.args
    )
  }

  def renderList(paramsList: List[P]) = {
    val sections = coll.add(
      InsertMultipleValuesSec(
        paramsList.map { params =>
          paramShape.conv.fromShape(params)
        }
      )
    )
    RenderedOperation(
      sections.render,
      sections.args
    )
  }
}


















