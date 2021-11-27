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
import kuzminki.jdbc.SingleConnection
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}

import zio._
import zio.console._
import zio.blocking._


object Kuzminki {

  def forConfig(conf: DbConfig) = {
    for {
      connections <- ZIO.foreach(1 to conf.poolSize) { connId =>
          effectBlocking {
            SingleConnection.create(conf.url, conf.props)
          }
        }
      pool <- Queue.bounded[SingleConnection](conf.poolSize)
      _ <- pool.offerAll(connections)
      kuzminki <- ZIO.succeed(new Kuzminki(pool, connections.toList))
    } yield kuzminki
  }
}

class Kuzminki(pool: Queue[SingleConnection], connections: List[SingleConnection]) {

  def query[R](stm: RenderedQuery[R]): RIO[Blocking, Seq[R]] = {
    for {
      conn <- pool.take
      rows <- conn.query(stm).ensuring { pool.offer(conn) }
    } yield rows
  }

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, Seq[R]] = {
    for {
      stm <- Task.effect { render }
      conn <- pool.take
      rows <- conn.query(stm).ensuring { pool.offer(conn) }
    } yield rows
  }

  def queryHead[R](stm: RenderedQuery[R]): RIO[Blocking, R] = {
    for {
      conn <- pool.take
      rows <- conn.query(stm).ensuring { pool.offer(conn) }
      head <- Task.effect { rows.head }
    } yield head
  }

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R] = {
    for {
      stm <- Task.effect { render }
      conn <- pool.take
      rows <- conn.query(stm).ensuring { pool.offer(conn) }
      head <- Task.effect { rows.head }
    } yield head
  }

  def queryHeadOpt[R](stm: RenderedQuery[R]): RIO[Blocking, Option[R]] = {
    for {
      conn <- pool.take
      rows <- conn.query(stm).ensuring { pool.offer(conn) }
      headOpt <- Task.effect { rows.headOption }
    } yield headOpt
  }

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]] = {
    for {
      stm <- Task.effect { render }
      conn <- pool.take
      rows <- conn.query(stm).ensuring { pool.offer(conn) }
      headOpt <- Task.effect { rows.headOption }
    } yield headOpt
  }

  def exec(stm: RenderedOperation): RIO[Blocking, Unit] = {
    for {
      conn <- pool.take
      _ <- conn.exec(stm).ensuring { pool.offer(conn) }
    } yield ()
  }

  def exec(render: => RenderedOperation): RIO[Blocking, Unit] = {
    for {
      stm <- Task.effect { render }
      conn <- pool.take
      _ <- conn.exec(stm).ensuring { pool.offer(conn) }
    } yield ()
  }

  def execNum(stm: RenderedOperation): RIO[Blocking, Int] = {
    for {
      conn <- pool.take
      num <- conn.execNum(stm).ensuring { pool.offer(conn) }
    } yield num
  }

  def execNum(render: => RenderedOperation): RIO[Blocking, Int] = {
    for {
      stm <- Task.effect { render }
      conn <- pool.take
      num <- conn.execNum(stm).ensuring { pool.offer(conn) }
    } yield num
  }

  def close() = {
    for {
      _ <- ZIO.foreach(connections)(_.close())
      _ <- pool.shutdown
    } yield ()
  }
}





















