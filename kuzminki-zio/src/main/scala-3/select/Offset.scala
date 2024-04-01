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

import java.sql.SQLException
import scala.deriving.Mirror.ProductOf
import kuzminki.api.Kuzminki
import kuzminki.section.OffsetSec
import zio.blocking._
import zio.clock.Clock
import zio._
import zio.stream.ZStream


class Offset[M, R](model: M, coll: SelectCollector[R]) extends Limit(model, coll) {

  def offset(num: Int) = {
    new Limit(
      model,
      coll.add(
        OffsetSec(num)
      )
    )
  }

  def asPages(size: Int) = Pages(render, size)

  def stream: ZStream[Has[Kuzminki] with Blocking with Clock, SQLException, R] = stream(100)

  def stream(size: Int): ZStream[Has[Kuzminki] with Blocking with Clock, SQLException, R] = {
    val gen = new StreamQuery(asPages(size))
    ZStream.unfoldChunkM(gen)(a => a.next)
  }

  def streamType[T](
    using mirror: ProductOf[T],
          ev: R <:< mirror.MirroredElemTypes
  ): ZStream[Has[Kuzminki] with Blocking with Clock, SQLException, T] = {
    stream.map((r: R) => mirror.fromProduct(r))
  }

  def streamType[T](size: Int)(
    using mirror: ProductOf[T],
          ev: R <:< mirror.MirroredElemTypes
  ): ZStream[Has[Kuzminki] with Blocking with Clock, SQLException, T] = {
    stream(size).map((r: R) => mirror.fromProduct(r))
  }
}