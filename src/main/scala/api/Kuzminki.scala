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
import kuzminki.jdbc.Driver
import kuzminki.select.{
  Select,
  SelectJoin,
  Where,
  JoinOn,
  StoredSelect
}
//import kuzminki.insert.Insert
//import kuzminki.operation.{Update, Delete, OperationWhere}
//import kuzminki.fn.Count

import zio._
import zio.console._
import zio.blocking._


object Kuzminki {

  def blocking(dbName: String) = {
    val driver = new Driver(dbName)
    new Kuzminki(driver)
  }

  def async(dbName: String): RIO[Blocking, Kuzminki] = {
    effectBlockingInterrupt {
      blocking(dbName)
    }
  }
}

class Kuzminki(driver: Driver) {

  def query[R](stm: StoredSelect[R]): RIO[Blocking, Seq[R]] = {
    for {
      rows <- driver.query(stm)
    } yield rows
  }

  def query[R](build: => StoredSelect[R]): RIO[Blocking, Seq[R]] = {
    for {
      stm <- Task.effect { build }
      rows <- driver.query(stm)
    } yield rows
  }

  def queryAs[R, T](stm: StoredSelect[R])
                   (implicit modify: R => T): RIO[Blocking, Seq[T]] = {
    for {
      rows <- driver.query(stm)
      modifiedRows <- Task.effect { rows.map(modify)}
    } yield modifiedRows
  }

  def queryAs[R, T](build: => StoredSelect[R])
                   (implicit modify: R => T): RIO[Blocking, Seq[T]] = {
    for {
      stm <- Task.effect { build }
      rows <- driver.query(stm)
      modifiedRows <- Task.effect { rows.map(modify)}
    } yield modifiedRows
  }

  def queryHead[R](stm: StoredSelect[R]): RIO[Blocking, R] = {
    for {
      rows <- driver.query(stm)
      head <- Task.effect { rows.head }
    } yield head
  }

  def queryHead[R](build: => StoredSelect[R]): RIO[Blocking, R] = {
    for {
      stm <- Task.effect { build }
      rows <- driver.query(stm)
      head <- Task.effect { rows.head }
    } yield head
  }

  def queryHeadAs[R, T](stm: StoredSelect[R])
                       (implicit modify: R => T): RIO[Blocking, T] = {
    for {
      rows <- driver.query(stm)
      modifiedHead <- Task.effect { modify(rows.head) }
    } yield modifiedHead
  }

  def queryHeadAs[R, T](build: => StoredSelect[R])
                       (implicit modify: R => T): RIO[Blocking, T] = {
    for {
      stm <- Task.effect { build }
      rows <- driver.query(stm)
      modifiedHead <- Task.effect { modify(rows.head) }
    } yield modifiedHead
  }

   def queryHeadOpt[R](stm: StoredSelect[R]): RIO[Blocking, Option[R]] = {
    for {
      rows <- driver.query(stm)
      headOpt <- Task.effect { rows.headOption }
    } yield headOpt
  }

  def queryHeadOpt[R](build: => StoredSelect[R]): RIO[Blocking, Option[R]] = {
    for {
      stm <- Task.effect { build }
      rows <- driver.query(stm)
      headOpt <- Task.effect { rows.headOption }
    } yield headOpt
  }

  def queryHeadOptAs[R, T](stm: StoredSelect[R])
                          (implicit modify: R => T): RIO[Blocking, Option[T]] = {
    for {
      rows <- driver.query(stm)
      modifiedHeadOpt <- Task.effect { rows.headOption.map(modify) }
    } yield modifiedHeadOpt
  }

  def queryHeadOptAs[R, T](build: => StoredSelect[R])
                          (implicit modify: R => T): RIO[Blocking, Option[T]] = {
    for {
      stm <- Task.effect { build }
      rows <- driver.query(stm)
      modifiedHeadOpt <- Task.effect { rows.headOption.map(modify) }
    } yield modifiedHeadOpt
  }

  def close() = driver.close()
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




















