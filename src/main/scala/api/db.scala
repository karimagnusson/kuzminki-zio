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
      inst <- Kuzminki.get
      rows <- inst.query(render)
    } yield rows
  }

  def queryAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Has[Kuzminki] with Blocking, List[T]] = {
    for {
      inst <- Kuzminki.get
      rows <- inst.queryAs(render, transform)
    } yield rows
  }

  def queryHead[R](render: => RenderedQuery[R]): RIO[Has[Kuzminki] with Blocking, R] = {
    for {
      inst <- Kuzminki.get
      head <- inst.queryHead(render)
    } yield head
  }

  def queryHeadAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Has[Kuzminki] with Blocking, T] = {
    for {
      inst <- Kuzminki.get
      rows <- inst.queryHeadAs(render, transform)
    } yield rows
  }

  def queryHeadOpt[R](render: => RenderedQuery[R]): RIO[Has[Kuzminki] with Blocking, Option[R]] = {
    for {
      inst    <- Kuzminki.get
      headOpt <- inst.queryHeadOpt(render)
    } yield headOpt
  }

  def queryHeadOptAs[R, T](render: => RenderedQuery[R], transform: R => T): RIO[Has[Kuzminki] with Blocking, Option[T]] = {
    for {
      inst <- Kuzminki.get
      rows <- inst.queryHeadOptAs(render, transform)
    } yield rows
  }

  def exec(render: => RenderedOperation): RIO[Has[Kuzminki] with Blocking, Unit] = {
    for {
      inst <- Kuzminki.get
      _    <- inst.exec(render)
    } yield ()
  }

  def execNum(render: => RenderedOperation): RIO[Has[Kuzminki] with Blocking, Int] = {
    for {
      inst <- Kuzminki.get
      num  <- inst.execNum(render)
    } yield num
  }

  def execList(stms: Seq[RenderedOperation]): RIO[Has[Kuzminki] with Blocking, Unit] = {
    for {
      inst <- Kuzminki.get
      _    <- inst.execList(stms)
    } yield ()
  }
}





















