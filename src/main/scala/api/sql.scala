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

package kuzminki.api

import scala.util.{Try, Success, Failure}
import scala.concurrent.duration._

import kuzminki.api._
import kuzminki.select.{
  Select,
  SelectJoin,
  Where,
  JoinOn
}
import kuzminki.delete.{Delete, DeleteWhere}
//import kuzminki.insert.Insert
//import kuzminki.operation.{Update, Delete, OperationWhere}
//import kuzminki.fn.Count


object sql {

  def select[M <: Model](model: M): Select[M] = {
    new Select(model)
  }

  def select[A <: Model, B <: Model](a: A, b: B): SelectJoin[A, B] = {
    select(DefaultJoin(a, b))
  }

  def select[A <: Model, B <: Model](join: Join[A, B]): SelectJoin[A, B] = {
    new SelectJoin(join)
  }

  //def update[M <: Model](model: M): Update[M] = {
  //  new Update(model)
  //}

  def delete[M <: Model](model: M): DeleteWhere[M] = {
    Delete.from(model)
  }
}

/*
trait KuzminkiApi {

  protected val db: Driver

  def select[M <: Model](model: M): Select[M] = {
    new Select(model, db)
  }

  def select[A <: Model, B <: Model](a: A, b: B): SelectJoin[A, B] = {
    select(DefaultJoin(a, b))
  }

  def select[A <: Model, B <: Model](join: Join[A, B]): SelectJoin[A, B] = {
    new SelectJoin(join, db)
  }

  def insert[M <: Model](model: M): Insert[M] = {
    new Insert(model, db)
  }

  def update[M <: Model](model: M): Update[M] = {
    new Update(model, db)
  }

  def delete[M <: Model](model: M): OperationWhere[M] = {
    Delete.from(model, db)
  }

  def count[M <: Model](model: M): Where[M, Long] = {
    new Select(model, db).cols1(t => Count.all)
  }

  def count[A <: Model, B <: Model](a: A, b: B): JoinOn[A, B, Long] = {
    count(DefaultJoin(a, b))
  }

  def count[A <: Model, B <: Model](join: Join[A, B]): JoinOn[A, B, Long] = {
    new SelectJoin(join, db).cols1(t => Count.all)
  }

  def rawSelect(statement: SqlWithParams) = db.rawSelect(statement)

  def rawSelectHead(statement: SqlWithParams) = db.rawSelectHead(statement)

  def rawSelectHeadOpt(statement: SqlWithParams) = db.rawSelectHeadOpt(statement)

  def rawExec(statement: SqlWithParams) = db.rawExec(statement)

  def rawExecNum(statement: SqlWithParams) = db.rawExecNum(statement)

  def close() = db.close()
}
*/




















