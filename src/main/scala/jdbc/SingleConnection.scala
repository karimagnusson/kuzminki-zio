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

import java.util.Properties
import java.util.UUID
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.{Statement, PreparedStatement}
import java.sql.ResultSet
import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import scala.collection.mutable.ListBuffer

import zio._
import zio.blocking._

import kuzminki.api.{DbConfig, KuzminkiError, Jsonb}
import kuzminki.shape.RowConv
import kuzminki.render.{
  RenderedQuery,
  RenderedOperation
}


object SingleConnection {

  def create(url: String, props: Properties) = {
    val conn = DriverManager.getConnection(url, props)
    new SingleConnection(conn)
  }
}


class SingleConnection(conn: Connection) {

  private val pgTypeName: Any => String = {
    case v: String      => "text"
    case v: Boolean     => "bool"
    case v: Short       => "int2"
    case v: Int         => "int4"
    case v: Long        => "int8"
    case v: Float       => "float4"
    case v: Double      => "float8"
    case v: BigDecimal  => "decimal"
    case v: Time        => "time"
    case v: Date        => "date"
    case v: Timestamp   => "timestamp"
    case v: Any         => throw KuzminkiError(s"type not supported [$v]")
  }

  private def arrayArg(arg: Seq[Any]) = {
    conn.createArrayOf(
      pgTypeName(arg.head),
      arg.toArray.map(_.asInstanceOf[Object])
    )
  }

  private def setArg(jdbcStm: PreparedStatement, arg: Any, index: Int): Unit = {
    arg match {
      case value: String      => jdbcStm.setString(index, value)
      case value: Boolean     => jdbcStm.setBoolean(index, value)
      case value: Short       => jdbcStm.setShort(index, value)
      case value: Int         => jdbcStm.setInt(index, value)
      case value: Long        => jdbcStm.setLong(index, value)
      case value: Float       => jdbcStm.setFloat(index, value)
      case value: Double      => jdbcStm.setDouble(index, value)
      case value: BigDecimal  => jdbcStm.setBigDecimal(index, value.bigDecimal)
      case value: Time        => jdbcStm.setTime(index, value)
      case value: Date        => jdbcStm.setDate(index, value)
      case value: Timestamp   => jdbcStm.setTimestamp(index, value)
      case value: Jsonb       => jdbcStm.setString(index, value.value)
      case value: UUID        => jdbcStm.setObject(index, value)
      case value: Seq[_]      => jdbcStm.setArray(index, arrayArg(value))
      case _                  => throw KuzminkiError(s"type not supported [$arg]")
    }
  }

  private val isNil: Any => Boolean = {
    case Nil => true
    case _ => false
  }

  private def emptyArrTempl(sql: String, args: Vector[Any]) = {
    val formattable = sql.replace("?", "%s")
    val placeholders = args.map {
      case Nil => "'{}'"
      case _ => "?"
    }
    val sqlMod = formattable.format(placeholders: _*)
    val argsMod = args.filterNot(isNil)
    (sqlMod, argsMod)
  }

  private def getStatement(sqlR: String, argsR: Vector[Any]) = {
    
    val (sql, args) = argsR.exists(isNil) match {
      case true =>  emptyArrTempl(sqlR, argsR)
      case false => (sqlR, argsR)
    }

    val jdbcStm = conn.prepareStatement(sql)
   
    if (args.nonEmpty) {
      args.zipWithIndex.foreach {
        case (arg, index) =>
          setArg(jdbcStm, arg, index + 1)
      }
    }
    jdbcStm
  }

  def query[R](stm: RenderedQuery[R]): RIO[Blocking, List[R]] = {
    effectBlocking {
      val jdbcStm = getStatement(stm.statement, stm.args)
      val jdbcResultSet = jdbcStm.executeQuery()
      var buff = ListBuffer.empty[R]
      while (jdbcResultSet.next()) {
        buff += stm.rowConv.fromRow(jdbcResultSet)
      }
      jdbcResultSet.close()
      jdbcStm.close()
      buff.toList
    }
  }

  def exec(stm: RenderedOperation): RIO[Blocking, Unit] = {
    effectBlocking {
      val jdbcStm = getStatement(stm.statement, stm.args)
      jdbcStm.execute()
      jdbcStm.close()
      ()
    }
  }

  def execNum(stm: RenderedOperation): RIO[Blocking, Int] = {
    effectBlocking {
      val jdbcStm = getStatement(stm.statement, stm.args)
      val num = jdbcStm.executeUpdate()
      jdbcStm.close()
      num
    }
  }

  def execList(stms: Seq[RenderedOperation]): RIO[Blocking, Unit] = {
    effectBlocking {
      try {
        conn.setAutoCommit(false)
        stms.foreach { stm => 
          val jdbcStm = getStatement(stm.statement, stm.args)
          jdbcStm.execute()
        }
        conn.commit()
        conn.setAutoCommit(true)
        ()
      } catch {
        case th: Throwable =>
          conn.rollback()
          conn.setAutoCommit(true)
          throw th
      }
    }
  }

  def close() = {
    effectBlocking {
      conn.close()
    }
  }
}

















