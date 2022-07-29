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

  Class.forName("org.postgresql.Driver")

  private def createPool(conf: DbConfig): RIO[Blocking, Pool] = for {
    connections <- ZIO.foreach(1 to conf.poolSize) { _ =>
                     effectBlocking {
                       SingleConnection.create(conf.url, conf.props)
                     }
                   }
    queue       <- Queue.bounded[SingleConnection](conf.poolSize)
    _           <- queue.offerAll(connections)
  } yield Pool(queue, connections.toList)

  def forConfig(conf: DbConfig) = create(conf)

  def create(conf: DbConfig): RIO[Blocking, Kuzminki] = for {
    pool <- createPool(conf)
  } yield new DefaultApi(pool)

  def layer(conf: DbConfig): ZLayer[Blocking, Throwable, Has[Kuzminki]] = {
    ZLayer.fromAcquireRelease(create(conf))(_.close)
  }

  def createSplit(getConf: DbConfig,
                  setConf: DbConfig): RIO[Blocking, Kuzminki] = for {
    getPool <- createPool(getConf)
    setPool <- createPool(setConf)
  } yield new SplitApi(getPool, setPool)

  def layerSplit(getConf: DbConfig,
                 setConf: DbConfig): ZLayer[Blocking, Throwable, Has[Kuzminki]] = {
    ZLayer.fromAcquireRelease(createSplit(getConf, setConf))(_.close)
  }

  def get = ZIO.access[Has[Kuzminki]](_.get)
}

trait Kuzminki {

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, List[R]]

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, List[T]]

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R]

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, T]

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]]

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, Option[T]]

  def exec(render: => RenderedOperation): RIO[Blocking, Unit]

  def execNum(render: => RenderedOperation): RIO[Blocking, Int]

  def close: URIO[Blocking, Unit]
}


private case class Pool(
  queue: Queue[SingleConnection],
  all: List[SingleConnection]
)


private class DefaultApi(pool: Pool) extends Kuzminki {

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, List[R]] = for {
    stm  <- Task.effect { render }
    conn <- pool.queue.take
    rows <- conn.query(stm).ensuring { pool.queue.offer(conn) }
  } yield rows

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, List[T]] = for {
    stm  <- Task.effect { render }
    conn <- pool.queue.take
    rows <- conn.query(stm).ensuring { pool.queue.offer(conn) }
    res  <- Task.effect { rows.map(transform) }
  } yield res

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R] = for {
    stm  <- Task.effect { render }
    conn <- pool.queue.take
    rows <- conn.query(stm).ensuring { pool.queue.offer(conn) }
    head <- Task.effect { rows.head }
  } yield head

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, T] = for {
    stm  <- Task.effect { render }
    conn <- pool.queue.take
    rows <- conn.query(stm).ensuring { pool.queue.offer(conn) }
    head <- Task.effect { transform(rows.head) }
  } yield head

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]] = for {
    stm     <- Task.effect { render }
    conn    <- pool.queue.take
    rows    <- conn.query(stm).ensuring { pool.queue.offer(conn) }
    headOpt <- Task.effect { rows.headOption }
  } yield headOpt

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, Option[T]] = for {
    stm     <- Task.effect { render }
    conn    <- pool.queue.take
    rows    <- conn.query(stm).ensuring { pool.queue.offer(conn) }
    headOpt <- Task.effect { rows.headOption.map(transform) }
  } yield headOpt

  def exec(render: => RenderedOperation): RIO[Blocking, Unit] = for {
    stm  <- Task.effect { render }
    conn <- pool.queue.take
    _    <- conn.exec(stm).ensuring { pool.queue.offer(conn) }
  } yield ()

  def execNum(render: => RenderedOperation): RIO[Blocking, Int] = for {
    stm  <- Task.effect { render }
    conn <- pool.queue.take
    num  <- conn.execNum(stm).ensuring { pool.queue.offer(conn) }
  } yield num

  def close: URIO[Blocking, Unit] = for {
    _ <- ZIO.foreach(pool.all)(_.close()).orDie
    _ <- pool.queue.shutdown
  } yield ()
}


private class SplitApi(getPool: Pool, setPool: Pool) extends Kuzminki {

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, List[R]] = for {
    stm  <- Task.effect { render }
    conn <- getPool.queue.take
    rows <- conn.query(stm).ensuring { getPool.queue.offer(conn) }
  } yield rows

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, List[T]] = for {
    stm  <- Task.effect { render }
    conn <- getPool.queue.take
    rows <- conn.query(stm).ensuring { getPool.queue.offer(conn) }
    res  <- Task.effect { rows.map(transform) }
  } yield res

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R] = for {
    stm  <- Task.effect { render }
    conn <- getPool.queue.take
    rows <- conn.query(stm).ensuring { getPool.queue.offer(conn) }
    head <- Task.effect { rows.head }
  } yield head

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, T] = for {
    stm  <- Task.effect { render }
    conn <- getPool.queue.take
    rows <- conn.query(stm).ensuring { getPool.queue.offer(conn) }
    head <- Task.effect { transform(rows.head) }
  } yield head

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]] = for {
    stm     <- Task.effect { render }
    conn    <- getPool.queue.take
    rows    <- conn.query(stm).ensuring { getPool.queue.offer(conn) }
    headOpt <- Task.effect { rows.headOption }
  } yield headOpt

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, Option[T]] = for {
    stm     <- Task.effect { render }
    conn    <- getPool.queue.take
    rows    <- conn.query(stm).ensuring { getPool.queue.offer(conn) }
    headOpt <- Task.effect { rows.headOption.map(transform) }
  } yield headOpt

  def exec(render: => RenderedOperation): RIO[Blocking, Unit] = for {
    stm  <- Task.effect { render }
    conn <- setPool.queue.take
    _    <- conn.exec(stm).ensuring { setPool.queue.offer(conn) }
  } yield ()

  def execNum(render: => RenderedOperation): RIO[Blocking, Int] = for {
    stm  <- Task.effect { render }
    conn <- setPool.queue.take
    num  <- conn.execNum(stm).ensuring { setPool.queue.offer(conn) }
  } yield num

  def close: URIO[Blocking, Unit] = for {
    _ <- ZIO.foreach(getPool.all)(_.close()).orDie
    _ <- getPool.queue.shutdown
    _ <- ZIO.foreach(setPool.all)(_.close()).orDie
    _ <- setPool.queue.shutdown
  } yield ()
}
























