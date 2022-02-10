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

import kuzminki.column.ModelCol
import kuzminki.shape.ParamShape
import kuzminki.render.SectionCollector
import kuzminki.section.insert._


trait OnConflict[M, P] {

  protected val model: M
  protected val coll: SectionCollector
  protected val paramShape: ParamShape[P]

  def onConflictDoNothing = {
    new RenderInsert(
      coll.extend(Vector(
        InsertBlankValuesSec(paramShape.size),
        InsertOnConflictSec,
        InsertDoNothingSec
      )),
      paramShape.conv
    )
  }

  def onConflictOnColumn(pick: M => ModelCol) = {
    new DoUpdate(
      model,
      coll,
      paramShape,
      pick(model)
    )
  }
}