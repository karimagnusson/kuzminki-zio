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


class DoUpdate[M <: Model, P](
  builder: ValuesBuilder[M],
  conflictCol: TypeCol[_]
) {

  def doNothing = new RenderInsert(
    builder.onConflictColDoNothing(conflictCol)
  )

  def doUpdate(pick: M => Seq[TypeCol[_]]) = new RenderInsert(
    builder.onConflictColDoUpdate(
      conflictCol,
      pick(builder.model).toVector
    )
  )
}


class DoUpdateStored[M <: Model, P](
  builder: InsertBuilder[M, P],
  conflictCol: TypeCol[_]
) {

  def doNothing = {
    new RenderStoredInsert(
      builder.onConflictColDoNothing(conflictCol),
      builder.paramShape.conv
    )
  }

  def doUpdate(pick: M => Seq[TypeCol[_]]) = {
    val updateCols = pick(builder.model).toVector
    new RenderStoredInsert(
      builder.onConflictColDoUpdate(conflictCol, updateCols),
      builder.onConflictColDoUpdateReuse(updateCols)
    )
  }
}








