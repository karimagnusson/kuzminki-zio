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

import zio._
import zio.blocking._
import kuzminki.api.{db, Kuzminki}
import kuzminki.shape.ParamConv
import kuzminki.render.{
  RunOperationParams,
  RunOperationAsSink,
  RenderedOperation
}


class StoredDeleteCondition[P](
    statement: String,
    args: Vector[Any],
    paramConv: ParamConv[P]
  ) extends RunOperationParams[P]
       with RunOperationAsSink[P] {

  def render(params: P) = {
    RenderedOperation(
      statement,
      args ++ paramConv.fromShape(params)
    )
  }

  def debugSql(handler: String => Unit) = {
    handler(statement)
    this
  }
}