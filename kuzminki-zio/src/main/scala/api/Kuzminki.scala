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
import kuzminki.jdbc.SingleConnection
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}

import zio._
import zio.blocking._
import zio.duration._
import zio.clock.Clock


object Kuzminki {

  Class.forName("org.postgresql.Driver")

  private def makeConn(conf: DbConfig): IO[SQLException, SingleConnection] = ZIO.effect {
    new SingleConnection(conf)
  }.refineToOrDie[SQLException]

  private def create(conf: DbConfig): ZManaged[Blocking with Clock, Nothing, ZPool[SQLException, SingleConnection]] = {
    val getConn = ZManaged.make(makeConn(conf))(_.close)
    ZPool.make(getConn, Range(conf.minPoolSize, conf.poolSize), 300.seconds)
  }

  @deprecated("this method will be removed", "0.9.5")
  def forConfig(conf: DbConfig) = throw KuzminkiError("This method is deprecated")

  def layer(conf: DbConfig): ZLayer[Blocking with Clock, Nothing, Has[Kuzminki]] =
    create(conf).map(pool => new DefaultApi(new Pool(pool))).toLayer
  
  def layerSplit(getConf: DbConfig, setConf: DbConfig): ZLayer[Blocking with Clock, Nothing, Has[Kuzminki]] = {
    create(getConf).zip(create(setConf)).map {
      case (getPool, setPool) =>
        new SplitApi(new Pool(getPool), new Pool(setPool))
      }.toLayer    
  }
  
  def get = ZIO.access[Has[Kuzminki]](_.get)
}

trait Kuzminki {

  def query[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, List[R]]

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, List[T]]

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, R]

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, T]

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, Option[R]]

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, Option[T]]

  def exec(render: => RenderedOperation): RIO[Blocking with Clock, Unit]

  def execNum(render: => RenderedOperation): RIO[Blocking with Clock, Int]

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking with Clock, Unit]

  @deprecated("this method will be removed", "0.9.5")
  def close = ZIO.fail(KuzminkiError("This method is deprecated"))
}

private class Pool(pool: ZPool[Throwable, SingleConnection]) {

  def use[R](fn: SingleConnection => ZIO[Blocking with Clock, Throwable, R]) = {
    pool.get.use { conn =>
      fn(conn).tapError { _ =>
        for {
          isValid <- conn.isValid
          _       <- ZIO.unless(isValid)(pool.invalidate(conn))
        } yield ()
      }
    }
  }
}

private class DefaultApi(pool: Pool) extends Kuzminki {

  def query[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, List[R]] = for {
    stm     <- ZIO.effect(render)
    rows    <- pool.use(_.query(stm))
  } yield rows

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, List[T]] =
    query(render).map(_.map(transform))

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, R] =
    query(render).map(_.headOption.getOrElse(throw NoRowsException("Query returned no rows")))

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, T] =
    queryHead(render).map(transform)

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, Option[R]] =
    query(render).map(_.headOption)

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, Option[T]] =
    query(render).map(_.headOption.map(transform))

  def exec(render: => RenderedOperation): RIO[Blocking with Clock, Unit] = for {
    stm     <- ZIO.effect(render)
    _       <- pool.use(_.exec(stm))
  } yield ()

  def execNum(render: => RenderedOperation): RIO[Blocking with Clock, Int] = for {
    stm     <- ZIO.effect(render)
    num     <- pool.use(_.execNum(stm))
  } yield num

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking with Clock, Unit] = for {
    _       <- pool.use(_.execList(stms))
  } yield ()
}


private class SplitApi(getPool: Pool, setPool: Pool) extends Kuzminki {

  private def router(stm: String) = stm.split(" ").head match {
    case "SELECT" => getPool
    case _ => setPool
  }

  def query[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, List[R]] = for {
    stm     <- ZIO.effect(render)
    rows    <- router(stm.statement).use(_.query(stm))
  } yield rows

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, List[T]] =
    query(render).map(_.map(transform))

  def queryHead[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, R] =
    query(render).map(_.headOption.getOrElse(throw NoRowsException("Query returned no rows")))

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, T] =
    queryHead(render).map(transform)

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Blocking with Clock, Option[R]] =
    query(render).map(_.headOption)

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Blocking with Clock, Option[T]] =
    query(render).map(_.headOption.map(transform))

  def exec(render: => RenderedOperation): RIO[Blocking with Clock, Unit] = for {
    stm     <- ZIO.effect(render)
    _       <- setPool.use(_.exec(stm))
  } yield ()

  def execNum(render: => RenderedOperation): RIO[Blocking with Clock, Int] = for {
    stm     <- ZIO.effect(render)
    num     <- setPool.use(_.execNum(stm))
  } yield num

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking with Clock, Unit] = for {
    _       <- setPool.use(_.execList(stms))
  } yield ()
}
























