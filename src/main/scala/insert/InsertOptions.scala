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
import kuzminki.column.TypeCol
import kuzminki.model.ModelTable
import kuzminki.shape.ParamShape
import kuzminki.section.insert._
import kuzminki.run.RunOperationParams
import kuzminki.select.SelectSubquery
import kuzminki.section.select.WhereSec
import kuzminki.filter.types.FilterMatchesNoArg
import kuzminki.render.{
  SectionCollector,
  RenderedOperation
}


class InsertOptions[M <: Model, P](
  parts: InsertParts[M, P]
) extends PickInsertStoredReturning(parts) {

  lazy val coll = parts.toBlankColl

  // no cache

  def values(params: P) = {
    new Values(parts.toValuesParts(params))
  }

  def fromSelect(sub: SelectSubquery[P]) = {
    new RenderInsert(
      parts.toColl.add(
        InsertSubquerySec(sub)
      )
    )
  }

  // cache

  def cache = {
    new StoredInsert(
      parts.toBlankColl.render,
      parts.paramShape.conv
    )
  }

  def whereNotExists(pick: M => Seq[TypeCol[_]]) = {
    val uniqueCols = pick(parts.model).toVector
    new RenderStoredInsert(
      coll.add(
        InsertWhereNotExistsSec(
          parts.paramShape.cols,
          ModelTable(parts.model),
          WhereSec(
            uniqueCols.map(FilterMatchesNoArg(_))
          )
        )
      ),
      new ParamConvReuse(
        parts.paramShape.conv,
        Reuse.fromIndex(parts.paramShape.cols, uniqueCols)
      )
    )
  }

  // on conflict

  def onConflictDoNothing = {
    new RenderStoredInsert(
      parts.toBlankColl.extend(Vector(
        InsertOnConflictSec,
        InsertDoNothingSec
      )),
      parts.paramShape.conv
    )
  }

  def onConflictOnColumn(pick: M => TypeCol[_]) = {
    new DoUpdateStored(
      parts,
      pick(parts.model)
    )
  }
}


















