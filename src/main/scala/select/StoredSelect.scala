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
import kuzminki.shape.RowConv


class StoredSelect[R](
      db: Driver,
      statement: SqlWithParams,
      rowConv: RowConv[R]
    ) {
  
  def run() = {
    db.select(statement, rowConv) 
  }

  def runAs[T](implicit custom: R => T) = {
    db.selectAs(statement, rowConv, custom)  
  }

  def headOpt() = {
    db.selectHeadOpt(statement, rowConv)
  }

  def headOptAs[T](implicit custom: R => T) = {
    db.selectHeadOptAs(statement, rowConv, custom)
  }

  def head() = {
    db.selectHead(statement, rowConv)
  }

  def headAs[T](implicit custom: R => T) = {
    db.selectHeadAs(statement, rowConv, custom)
  }

  /*
  def runCount() = {
    db.count(statement)
  }
  */

  def render(prefix: Prefix) = statement.sql
  
  def args = statement.params.toSeq
  
  def sql(handler: String => Unit) = {
    handler(statement.sql)
    this
  }
}





















