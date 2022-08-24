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

import zio._
import kuzminki.render.RenderedQuery
import kuzminki.shape.RowConv
import kuzminki.api.{db, Kuzminki}


object StreamQuery {
  def apply[T](query: RenderedQuery[T]) = {
    new StreamQuery(
      query.statement,
      query.rowConv,
      query.args.splitAt(query.args.size - 2)._1,
      query.args(query.args.size - 1).asInstanceOf[Int]
    )
  }
}


class StreamQuery[T](
  statement: String,
  rowConv: RowConv[T],
  baseArgs: Vector[Any],
  limit: Int) {

  private var offset = 0

  private def nextQuery = {
    val args = baseArgs ++ Vector(offset, limit)
    offset = offset + limit
    new RenderedQuery(statement, args, rowConv)
  }

  private val toChunk: Seq[T] => Option[(Chunk[T], StreamQuery[T])] = {
    case Nil => None
    case batch => Some((Chunk.fromIterable(batch), this))
  }

  def next = db.query(nextQuery).map(toChunk)
}







