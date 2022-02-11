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
  ) extends CacheDeleteWhereMethods(model, coll) {

  def all() = new RenderDelete(model, coll)

  @deprecated("use where", "0.9.2")
  def whereOne(pick: M => Filter) = {
    new RenderDelete(
      model,
      coll.add(
        WhereSec(
          Vector(pick(model))
        )
      )
    )
  }

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

  def whereOpts(pick: M => Seq[Option[Filter]]) = {
    pick(model).flatten match {
      case Nil =>
        new RenderDelete(model, coll)
      case filters =>
        new RenderDelete(
          model,
          coll.add(WhereSec(filters.toVector))
        )
    }
  }

  def whereOpt(pick: M => Option[Filter]) = {
    pick(model) match {
      case Some(filter) =>
        new RenderDelete(
          model,
          coll.add(
            WhereSec(Vector(filter))
          )
        )
      case None =>
        new RenderDelete(model, coll)
    }
  }
}


















