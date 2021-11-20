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
import kuzminki.api.KuzminkiError
import kuzminki.shape.ParamShape
import kuzminki.assign.SetUpsert
import kuzminki.render.SectionCollector
import kuzminki.section.insert._


class DoUpdate[M, P](
      model: M,
      coll: SectionCollector,
      paramShape: ParamShape[P],
      conflictCol: ModelCol
    ) {

  def doNothing = {
    new RenderInsert(
      coll.extend(Array(
        InsertBlankValuesSec(paramShape.size),
        InsertOnConflictSec,
        InsertDoNothingSec
      )),
      paramShape.conv
    )
  }

  def doUpdate(pick: M => Seq[ModelCol]) = {
    doUpdateApply(
      pick(model)
    )
  }

  def doUpdateOne(pick: M => ModelCol) = {
    doUpdateApply(
      Seq(pick(model))
    )
  }

  protected def validate(
        conflictCol: ModelCol,
        updateCols: Seq[ModelCol]
      ): Unit = {

    if (updateCols.isEmpty) {
      throw KuzminkiError("no update columns selected")
    }

    if (updateCols.contains(conflictCol)) {
      throw KuzminkiError("cannot update the conflicting column")
    }
  }

  private def doUpdateApply(updateCols: Seq[ModelCol]) = {
    validate(conflictCol, updateCols)
    new RenderInsert(
      coll.extend(Array(
        InsertBlankValuesSec(paramShape.size),
        InsertOnConflictColumnSec(conflictCol),
        InsertDoUpdateNoArgsSec(updateCols.map(SetUpsert(_)))
      )),
      new ParamConvReuse(
        paramShape.conv,
        Reuse.fromIndex(paramShape.cols, updateCols)
      )
    )
  }
}












