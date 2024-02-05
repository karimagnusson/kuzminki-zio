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

package kuzminki.jdbc

import java.util.UUID
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.PreparedStatement
import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import scala.collection.mutable.ListBuffer
import org.postgresql.util.PGInterval
import org.postgresql.util.PGobject

import zio._
import zio.blocking._

import kuzminki.conv.{TypeNull, TypeArray}
import kuzminki.api.{
  DbConfig,
  Jsonb,
  NoArg,
  InvalidArgException,
  ResultTypeException
}
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}


class SingleConnection(conf: DbConfig) {

  private lazy val conn = DriverManager.getConnection(conf.url, conf.props)

  private val notNoArg: Any => Boolean = {
    case NoArg => false
    case _ => true
  }

  private def arrayArg(value: TypeArray) = {
    conn.createArrayOf(value.typeName, value.vec.toArray)
  }

  private def jsonbArg(value: Jsonb) = {
    val obj = new PGobject()
    obj.setType("jsonb")
    obj.setValue(value.value)
    obj
  }

  private def setArg(jdbcStm: PreparedStatement, arg: Any, index: Int): Unit = {
    arg match {
      case value: String        => jdbcStm.setString(index, value)
      case value: Boolean       => jdbcStm.setBoolean(index, value)
      case value: Short         => jdbcStm.setShort(index, value)
      case value: Int           => jdbcStm.setInt(index, value)
      case value: Long          => jdbcStm.setLong(index, value)
      case value: Float         => jdbcStm.setFloat(index, value)
      case value: Double        => jdbcStm.setDouble(index, value)
      case value: BigDecimal    => jdbcStm.setBigDecimal(index, value.bigDecimal)
      case value: Time          => jdbcStm.setTime(index, value)
      case value: Date          => jdbcStm.setDate(index, value)
      case value: Timestamp     => jdbcStm.setTimestamp(index, value)
      case value: Jsonb         => jdbcStm.setObject(index, jsonbArg(value))
      case value: UUID          => jdbcStm.setObject(index, value)
      case value: PGInterval    => jdbcStm.setObject(index, value)
      case value: TypeNull      => jdbcStm.setNull(index, value.typeId)
      case value: TypeArray     => jdbcStm.setArray(index, arrayArg(value))
      case _ => throw InvalidArgException(s"type not supported [$arg]")
    }
  }

  private def getStatement(sql: String, args: Vector[Any]) = {

    val jdbcStm = conn.prepareStatement(sql)
   
    if (args.nonEmpty) {
      args.filter(notNoArg).zipWithIndex.foreach {
        case (arg, index) =>
          setArg(jdbcStm, arg, index + 1)
      }
    }
    jdbcStm
  }

  def query[R](stm: RenderedQuery[R]): ZIO[Blocking, SQLException, List[R]] = {
    effectBlocking {
      val jdbcStm = getStatement(stm.statement, stm.args)
      val jdbcResultSet = jdbcStm.executeQuery()
      val buff = ListBuffer.empty[R]
      while (jdbcResultSet.next()) {
        try {
          buff += stm.rowConv.fromRow(jdbcResultSet)
        } catch {
          case ex: Throwable =>
            throw ResultTypeException("Your model may not match the table", ex)
        }
      }
      jdbcResultSet.close()
      jdbcStm.close()
      buff.toList
    }.refineToOrDie[SQLException]
  }

  def exec(stm: RenderedOperation): ZIO[Blocking, SQLException, Unit] = {
    effectBlocking {
      val jdbcStm = getStatement(stm.statement, stm.args)
      jdbcStm.execute()
      jdbcStm.close()
    }.unit.refineToOrDie[SQLException]
  }

  def execNum(stm: RenderedOperation): ZIO[Blocking, SQLException, Int] = {
    effectBlocking {
      val jdbcStm = getStatement(stm.statement, stm.args)
      val num = jdbcStm.executeUpdate()
      jdbcStm.close()
      num
    }.refineToOrDie[SQLException]
  }

  def execList(stms: Seq[RenderedOperation]): ZIO[Blocking, SQLException, Unit] = {
    effectBlocking {
      conn.setAutoCommit(false)
      stms.foreach { stm => 
        val jdbcStm = getStatement(stm.statement, stm.args)
        jdbcStm.execute()
      }
      conn.commit()
      conn.setAutoCommit(true)
    }
    .tapError(e => ZIO.succeed {
      conn.rollback()
      conn.setAutoCommit(true)
    })
    .unit
    .refineToOrDie[SQLException]
  }

  def isValid: URIO[Blocking, Boolean] =
    effectBlocking(conn.isValid(10)).catchAll(_ => ZIO.succeed(false))

  def close: URIO[Blocking, Unit] = effectBlocking(conn.close()).orDie
}

















