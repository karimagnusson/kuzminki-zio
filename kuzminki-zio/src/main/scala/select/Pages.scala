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
import zio._
import zio.blocking._
import kuzminki.api.{Kuzminki, KuzminkiError}
import kuzminki.render.RenderedQuery
import kuzminki.api.db
import zio.clock.Clock


object Pages {
  def apply[R](query: RenderedQuery[R], limit: Int) = new Pages(query, limit)
}

class Pages[R](query: RenderedQuery[R], limit: Int) {

  query.statement.split(' ').foreach {
    case "OFFSET" | "LIMIT" =>
      throw KuzminkiError("query for pages cannot contain OFFSET or LIMIT")
    case _ =>
  }

  private var isDone = false
  private var offset = 0
  private val pageStatement = query.statement + " OFFSET ? LIMIT ?"

  private def nextQuery = {
    val args = query.args ++ Vector(offset, limit)
    offset = offset + limit
    new RenderedQuery(pageStatement, args, query.rowConv)
  }

  private val markDone: List[R] => List[R] = { rows =>
      if (rows.size < limit) isDone = true
      rows
  }

  def getOriginal = new Pages(query, limit)

  def nextOpt: ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, Option[List[R]]] =
    if (isDone) ZIO.succeed(None)
    else db.query(nextQuery).map(markDone).map(Some(_))


  def next: ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, List[R]] = {
    nextOpt.map {
      case Some(rows) => rows
      case None => List.empty[R]
    }
  }

  private def pageQuery(page: Int) = {
    if (page < 1) throw KuzminkiError("page must be greater than 0")
    val offset = (page - 1) * limit
    val args = query.args ++ Vector(offset, limit)
    new RenderedQuery(pageStatement, args, query.rowConv)
  }

  def pageOpt(num: Int): ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, Option[List[R]]] = {
    page(num).map {
      case Nil => None
      case rows => Some(rows)
    }
  }

  def page(num: Int): ZIO[Has[Kuzminki] with Blocking with Clock, SQLException, List[R]] = 
    db.query(pageQuery(num))
}









