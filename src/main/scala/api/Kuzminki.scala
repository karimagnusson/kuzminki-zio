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

import kuzminki.api._
import kuzminki.jdbc.Driver
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}

import zio._
import zio.console._
import zio.blocking._


object Kuzminki {

  def blocking(config: DbConfig) = {
    val driver = new Driver(config)
    new Kuzminki(driver)
  }

  def async(config: DbConfig): RIO[Blocking, Kuzminki] = {
    effectBlockingInterrupt {
      blocking(config)
    }
  }
}

class Kuzminki(driver: Driver) {

  def query[R](stm: RenderedQuery[R]): RIO[Blocking, Seq[R]] = {
    for {
      rows <- driver.query(stm)
    } yield rows
  }

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, Seq[R]] = {
    for {
      stm <- Task.effect { render }
      rows <- driver.query(stm)
    } yield rows
  }

  def queryAs[R, T](stm: RenderedQuery[R])
                   (implicit modify: R => T): RIO[Blocking, Seq[T]] = {
    for {
      rows <- driver.query(stm)
      modifiedRows <- Task.effect { rows.map(modify)}
    } yield modifiedRows
  }

  def queryAs[R, T](render: => RenderedQuery[R])
                   (implicit modify: R => T): RIO[Blocking, Seq[T]] = {
    for {
      stm <- Task.effect { render }
      rows <- driver.query(stm)
      modifiedRows <- Task.effect { rows.map(modify)}
    } yield modifiedRows
  }

  def queryHead[R](stm: RenderedQuery[R]): RIO[Blocking, R] = {
    for {
      rows <- driver.query(stm)
      head <- Task.effect { rows.head }
    } yield head
  }

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R] = {
    for {
      stm <- Task.effect { render }
      rows <- driver.query(stm)
      head <- Task.effect { rows.head }
    } yield head
  }

  def queryHeadAs[R, T](stm: RenderedQuery[R])
                       (implicit modify: R => T): RIO[Blocking, T] = {
    for {
      rows <- driver.query(stm)
      modifiedHead <- Task.effect { modify(rows.head) }
    } yield modifiedHead
  }

  def queryHeadAs[R, T](render: => RenderedQuery[R])
                       (implicit modify: R => T): RIO[Blocking, T] = {
    for {
      stm <- Task.effect { render }
      rows <- driver.query(stm)
      modifiedHead <- Task.effect { modify(rows.head) }
    } yield modifiedHead
  }

   def queryHeadOpt[R](stm: RenderedQuery[R]): RIO[Blocking, Option[R]] = {
    for {
      rows <- driver.query(stm)
      headOpt <- Task.effect { rows.headOption }
    } yield headOpt
  }

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]] = {
    for {
      stm <- Task.effect { render }
      rows <- driver.query(stm)
      headOpt <- Task.effect { rows.headOption }
    } yield headOpt
  }

  def queryHeadOptAs[R, T](stm: RenderedQuery[R])
                          (implicit modify: R => T): RIO[Blocking, Option[T]] = {
    for {
      rows <- driver.query(stm)
      modifiedHeadOpt <- Task.effect { rows.headOption.map(modify) }
    } yield modifiedHeadOpt
  }

  def queryHeadOptAs[R, T](render: => RenderedQuery[R])
                          (implicit modify: R => T): RIO[Blocking, Option[T]] = {
    for {
      stm <- Task.effect { render }
      rows <- driver.query(stm)
      modifiedHeadOpt <- Task.effect { rows.headOption.map(modify) }
    } yield modifiedHeadOpt
  }

  def exec(stm: RenderedOperation): RIO[Blocking, Unit] = {
    for {
      _ <- driver.exec(stm)
    } yield ()
  }

  def exec(render: => RenderedOperation): RIO[Blocking, Unit] = {
    for {
      stm <- Task.effect { render }
      _ <- driver.exec(stm)
    } yield ()
  }

  def execNum(stm: RenderedOperation): RIO[Blocking, Int] = {
    for {
      num <- driver.execNum(stm)
    } yield num
  }

  def execNum(render: => RenderedOperation): RIO[Blocking, Int] = {
    for {
      stm <- Task.effect { render }
      num <- driver.execNum(stm)
    } yield num
  }

  def close() = driver.close()
}





















