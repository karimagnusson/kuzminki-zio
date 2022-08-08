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
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}

import zio._
import zio.blocking._


object db { 

  def query[R](render: => RenderedQuery[R]): RIO[Has[Kuzminki] with Blocking, List[R]] = {
    for {
      db   <- Kuzminki.get
      rows <- db.query(render)
    } yield rows
  }

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Has[Kuzminki] with Blocking, List[T]] = {
    for {
      db   <- Kuzminki.get
      rows <- db.queryAs(render, transform)
    } yield rows
  }

  def queryHead[R](render: => RenderedQuery[R]): RIO[Has[Kuzminki] with Blocking, R] = {
    for {
      db   <- Kuzminki.get
      head <- db.queryHead(render)
    } yield head
  }

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Has[Kuzminki] with Blocking, T] = {
    for {
      db   <- Kuzminki.get
      rows <- db.queryHeadAs(render, transform)
    } yield rows
  }

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Has[Kuzminki] with Blocking, Option[R]] = {
    for {
      db      <- Kuzminki.get
      headOpt <- db.queryHeadOpt(render)
    } yield headOpt
  }

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Has[Kuzminki] with Blocking, Option[T]] = {
    for {
      db   <- Kuzminki.get
      rows <- db.queryHeadOptAs(render, transform)
    } yield rows
  }

  def exec(render: => RenderedOperation): RIO[Has[Kuzminki] with Blocking, Unit] = {
    for {
      db <- Kuzminki.get
      _  <- db.exec(render)
    } yield ()
  }

  def execNum(render: => RenderedOperation): RIO[Has[Kuzminki] with Blocking, Int] = {
    for {
      db   <- Kuzminki.get
      num  <- db.execNum(render)
    } yield num
  }
}





















