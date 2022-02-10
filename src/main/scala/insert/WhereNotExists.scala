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
import kuzminki.api.KuzminkiError
import kuzminki.column.TypeCol
import kuzminki.model.ModelTable
import kuzminki.filter.types.FilterMatchesNoArg
import kuzminki.section.select.WhereSec
import kuzminki.shape.ParamShape
import kuzminki.render.SectionCollector
import kuzminki.section.insert._

trait WhereNotExists[M <: Model, P] {

  protected val model: M
  protected val coll: SectionCollector
  protected val paramShape: ParamShape[P]

  def whereNotExists(pick: M => Seq[TypeCol[_]]) = {
    whereNotExistsApply(pick(model).toVector)
  }

  @deprecated("use whereNotExists", "0.9.2")
  def whereNotExistsOne[T](pick: M => TypeCol[T]) = {
    whereNotExistsApply(
      Vector(pick(model))
    )
  }

  private def whereNotExistsApply(uniqueCols: Vector[TypeCol[_]]) = {

    if (uniqueCols.isEmpty) {
      throw KuzminkiError("whereNotExists")
    }

    new RenderInsert(
      coll.add(
        InsertBlankWhereNotExistsSec(
          paramShape.size,
          ModelTable(model),
          WhereSec(
            uniqueCols.map(FilterMatchesNoArg(_))
          )
        )
      ),
      new ParamConvReuse(
        paramShape.conv,
        Reuse.fromIndex(paramShape.cols, uniqueCols)
      )
    )
  }
}









