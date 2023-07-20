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

import java.sql.SQLException
import kuzminki.api._
import kuzminki.jdbc.SingleConnection
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}

import zio._
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
  } yield new Pool(queue, connections.toList)

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

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking, Unit]

  def close: URIO[Blocking, Unit]
}


private class Pool(queue: Queue[SingleConnection], all: List[SingleConnection]) {
  
  val get = ZManaged.make(queue.take)(queue.offer(_))

  def close = for {
    _ <- ZIO.foreach(all)(_.close()).orDie
    _ <- queue.shutdown
  } yield ()
}


private class DefaultApi(pool: Pool) extends Kuzminki {

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, List[R]] = for {
    stm     <- ZIO.effect(render)
    rows    <- pool.get.use(_.query(stm))
  } yield rows

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, List[T]] =
    query(render).map(_.map(transform))

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R] =
    query(render).map(_.headOption.getOrElse(throw NoRowsException("Query returned no rows")))

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, T] =
    queryHead(render).map(transform)

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]] =
    query(render).map(_.headOption)

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, Option[T]] =
    query(render).map(_.headOption.map(transform))

  def exec(render: => RenderedOperation): RIO[Blocking, Unit] = for {
    stm     <- ZIO.effect(render)
    _       <- pool.get.use(_.exec(stm))
  } yield ()

  def execNum(render: => RenderedOperation): RIO[Blocking, Int] = for {
    stm     <- ZIO.effect(render)
    num     <- pool.get.use(_.execNum(stm))
  } yield num

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking, Unit] = for {
    _       <- pool.get.use(_.execList(stms))
  } yield ()

  def close = pool.close
}


private class SplitApi(getPool: Pool, setPool: Pool) extends Kuzminki {

  private def router(stm: String) = stm.split(" ").head match {
    case "SELECT" => getPool
    case _ => setPool
  }

  def query[R](render: => RenderedQuery[R]): RIO[Blocking, List[R]] = for {
    stm     <- ZIO.effect(render)
    rows    <- router(stm.statement).get.use(_.query(stm))
  } yield rows

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, List[T]] =
    query(render).map(_.map(transform))

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking, R] =
    query(render).map(_.headOption.getOrElse(throw NoRowsException("Query returned no rows")))

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, T] =
    queryHead(render).map(transform)

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking, Option[R]] =
    query(render).map(_.headOption)

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking, Option[T]] =
    query(render).map(_.headOption.map(transform))

  def exec(render: => RenderedOperation): RIO[Blocking, Unit] = for {
    stm     <- ZIO.effect(render)
    _       <- setPool.get.use(_.exec(stm))
  } yield ()

  def execNum(render: => RenderedOperation): RIO[Blocking, Int] = for {
    stm     <- ZIO.effect(render)
    num     <- setPool.get.use(_.execNum(stm))
  } yield num

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking, Unit] = for {
    _       <- setPool.get.use(_.execList(stms))
  } yield ()

  def close = for {
    _ <- getPool.close
    _ <- setPool.close
  } yield ()
}
























