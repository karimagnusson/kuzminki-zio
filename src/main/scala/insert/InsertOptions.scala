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
import kuzminki.select.SelectSubquery


class InsertOptions[M <: Model, P](
  builder: InsertBuilder[M, P]
) extends PickInsertStoredReturning(builder) {

  // no cache

  def values(params: P) = new Values(
    builder.toValuesBuilder(params)
  )

  def fromSelect(sub: SelectSubquery[P]) = new RenderInsert(
    builder.fromSelect(sub)
  )

  // cache

  def cache = {
    new StoredInsert(
      builder.collector.render,
      builder.paramShape.conv
    )
  }

  def whereNotExists(pick: M => Seq[TypeCol[_]]) = {
    val uniqueCols = pick(builder.model).toVector
    new RenderStoredInsert(
      builder.whereNotExists(uniqueCols),
      builder.whereNotExistsReuse(uniqueCols)
    )
  }

  // on conflict

  def onConflictDoNothing = {
    new RenderStoredInsert(
      builder.onConflictDoNothing,
      builder.paramShape.conv
    )
  }

  def onConflictOnColumn(pick: M => TypeCol[_]) = {
    new DoUpdateStored(
      builder,
      pick(builder.model)
    )
  }
}


















