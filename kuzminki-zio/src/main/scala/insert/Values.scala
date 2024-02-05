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
import kuzminki.run.RunOperation
import kuzminki.render.RenderedOperation


class Values[M <: Model](
  builder: ValuesBuilder[M]
) extends PickInsertReturning(builder)
     with RunOperation {

  def render = {
    val coll = builder.collector
    RenderedOperation(
      coll.render,
      coll.args
    )
  }

  def whereNotExists(pick: M => Seq[TypeCol[_]]) = new RenderInsert(
    builder.whereNotExists(pick(builder.model).toVector)
  )

  def onConflictDoNothing = new RenderInsert(
    builder.onConflictDoNothing
  )

  def onConflictOnColumn(pick: M => TypeCol[_]) = new DoUpdate(
    builder,
    pick(builder.model)
  )
}


















