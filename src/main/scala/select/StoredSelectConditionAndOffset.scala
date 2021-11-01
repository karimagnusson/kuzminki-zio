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

import kuzminki.render.SqlWithParams
import kuzminki.client.Driver
import kuzminki.render.Prefix
import kuzminki.shape.ParamConv
import kuzminki.shape.RowConv


class StoredSelectConditionAndOffset[P, R](
      db: Driver,
      template: String,
      cacheArgs: Tuple3[Vector[Any], Vector[Any], Vector[Any]],
      paramConv: ParamConv[P],
      rowConv: RowConv[R]
    ) {

  private val (args1, args2, args3) = cacheArgs

  private def transformParams(params: P, offset: Int) = {
    args1 ++ paramConv.fromShape(params) ++ args2 ++ Vector(offset) ++ args3
  }

  private def statement(params: P, offset: Int) = {
    SqlWithParams(
      template,
      transformParams(params, offset)
    )
  }

  def run(params: P, offset: Int) = {
    db.select(statement(params, offset), rowConv) 
  }

  def runAs[T](params: P, offset: Int)(implicit custom: R => T) = {
    db.selectAs(statement(params, offset), rowConv, custom)  
  }

  def headOpt(params: P, offset: Int) = {
    db.selectHeadOpt(statement(params, offset), rowConv)
  }

  def headOptAs[T](params: P, offset: Int)(implicit custom: R => T) = {
    db.selectHeadOptAs(statement(params, offset), rowConv, custom)
  }

  def head(params: P, offset: Int) = {
    db.selectHead(statement(params, offset), rowConv)
  }

  def headAs[T](params: P, offset: Int)(implicit custom: R => T) = {
    db.selectHeadAs(statement(params, offset), rowConv, custom)
  }
  
  def render(prefix: Prefix) = template
  
  def args = args1 ++ args2 ++ args3

  def sql(handler: String => Unit): StoredSelectConditionAndOffset[P, R] = {
    handler(template)
    this
  }
}





















