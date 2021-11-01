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

package kuzminki.select

import kuzminki.api.{Model, Join}
import kuzminki.model.ModelTable
import kuzminki.column.ModelCol
import kuzminki.section.select.{
  InnerJoinSec,
  OnSec
}


class JoinOn[A <: Model, B <: Model, R](join: Join[A, B], coll: SelectCollector[R]) {

  def joinOn(pickLeft: A => ModelCol, pickRight: B => ModelCol) = {
    new Where(
      join,
      coll.extend(Array(
        InnerJoinSec(ModelTable(join.b)),
        OnSec(pickLeft(join.a), pickRight(join.b))
      ))
    )
  }
}