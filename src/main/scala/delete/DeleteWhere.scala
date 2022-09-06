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

package kuzminki.delete

import kuzminki.api.KuzminkiError
import kuzminki.render.SectionCollector
import kuzminki.section.operation.WhereSec
import kuzminki.filter.Filter


class DeleteWhere[M](
    model: M,
    coll: SectionCollector
  ) extends PickDeleteWhere(model, coll) {

  def all = new RenderDelete(model, coll)

  def where(pick: M => Seq[Filter]) = {
    pick(model) match {
      case Nil =>
        throw KuzminkiError("WHERE conditions cannot be empty")
      case conds =>
        new RenderDelete(
          model,
          coll.add(WhereSec(conds.toVector))
        )
    }
  }

  def whereOpt(pick: M => Seq[Option[Filter]]) = {
    pick(model).flatten match {
      case Nil =>
        new RenderDelete(model, coll)
      case filters =>
        new RenderDelete(
          model,
          coll.add(
            WhereSec(filters.toVector)
          )
        )
    }
  }
}


















