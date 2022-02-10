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

package kuzminki.update

import kuzminki.api.Model
import kuzminki.model.ModelTable
import kuzminki.assign.Assign
import kuzminki.render.SectionCollector
import kuzminki.section.operation.{UpdateSec, UpdateSetSec}


class Update[M <: Model](
      model: M
    ) extends UpdateCacheSetMethods(model) {

  def set(pick: M => Seq[Assign]) = {
    new UpdateWhere(
      model,
      SectionCollector(
        Vector(
          UpdateSec(ModelTable(model)),
          UpdateSetSec(pick(model).toVector)
        )
      )
    )
  }

  @deprecated("use set", "0.9.2")
  def setOne(pick: M => Assign) = {
    new UpdateWhere(
      model,
      SectionCollector(
        Vector(
          UpdateSec(ModelTable(model)),
          UpdateSetSec(
            Vector(pick(model))
          )
        )
      )
    )
  }
}





















