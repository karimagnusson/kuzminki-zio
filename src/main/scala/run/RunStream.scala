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

package kuzminki.run

import zio._
import zio.stream.ZStream
import kuzminki.select.{Offset, Pages}


trait RunStream[M, R] {

  val  query: Offset[M, R]

  def asPages(size: Int) = Pages(query.render, size)

  def stream = streamBatchBuffer(100, 3)

  def streamBatch(size: Int) = streamBatchBuffer(size, 3)

  def streamBatchBuffer(batchSize: Int, bufferSize: Int) = {
    val gen = new StreamQuery(asPages(batchSize))
    ZStream.unfoldChunkM(gen)(a => a.next).buffer(bufferSize)
  }
}


class StreamQuery[T](pages: Pages[T]) {

  private val toChunk: Option[List[T]] => Option[(Chunk[T], StreamQuery[T])] = { opt =>
    opt.map(batch => (Chunk.fromIterable(batch), this))
  }

  def next = pages.nextOpt.map(toChunk)
}