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

import kuzminki.shape.{ParamConv, RowConv}
import kuzminki.run.{
  RunQueryParams,
  RunOperationParams,
  RunOperationAsSink
}
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}


class StoredInsert[P](
  statement: String,
  paramConv: ParamConv[P]
) extends RunOperationParams[P]
     with RunOperationAsSink[P] {

  def render(params: P) = {
    RenderedOperation(
      statement,
      paramConv.fromShape(params)
    )
  }

  def printSql = {
    println(statement)
    this
  }
}


class StoredInsertReturning[P, R](
  statement: String,
  paramConv: ParamConv[P],
  rowConv: RowConv[R]
) extends RunQueryParams[P, R] {

  def render(params: P) = {
    RenderedQuery(
      statement,
      paramConv.fromShape(params),
      rowConv
    )
  }

  def printSql = {
    println(statement)
    this
  }
}






