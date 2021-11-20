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

import kuzminki.render.RenderedQuery
import kuzminki.shape.ParamConv
import kuzminki.shape.RowConv


class StoredSelectCondition[P, R](
      statement: String,
      cacheArgs: Tuple2[Vector[Any], Vector[Any]],
      paramConv: ParamConv[P],
      rowConv: RowConv[R]
    ) {

  def render(params: P) = {
    RenderedQuery(
      statement,
      cacheArgs._1 ++ paramConv.fromShape(params) ++ cacheArgs._2,
      rowConv
    )
  }
}





















