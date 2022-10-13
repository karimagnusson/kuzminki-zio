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

package kuzminki.render

import java.util.UUID
import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import org.postgresql.util.PGInterval
import kuzminki.api.{Jsonb, NoArg, KuzminkiError}
import kuzminki.conv.{TypeNull, TypeArray}


trait DebugRendered {
  val statement: String
  val args: Vector[Any]

  private val stringValue: Any => String = {
    case value: String        => "'%s'".format(value)
    case value: Boolean       => value.toString
    case value: Short         => value.toString
    case value: Int           => value.toString
    case value: Long          => value.toString
    case value: Float         => value.toString
    case value: Double        => value.toString
    case value: BigDecimal    => value.toString
    case value: Time          => "'%s'::time".format(value.toString)
    case value: Date          => "'%s'::date".format(value.toString)
    case value: Timestamp     => "'%s'::timestamp".format(value.toString)
    case value: Jsonb         => "'%s'::jsonb".format(value.value)
    case value: UUID          => value.toString
    case value: PGInterval    => "interval '%s'".format(value.toString)
    case value: TypeNull      => "NULL"
    case value: TypeArray     => "ARRAY[%s]".format(value.vec.map(stringValue).mkString(", "))
    case value                => throw KuzminkiError(s"type not supported [$value]")
  }

  private val notNoArg: Any => Boolean = {
    case NoArg => false
    case _ => true
  }

  def printStatement: Unit = {
    println(statement)
  }
  
  def printStatementAndArgs: Unit = {
    println(statement)
    println(args.filter(notNoArg))
  }
  
  def printStatementWithArgs: Unit = {
    println(
      statement
        .replace("??", "##")
        .replace("?", "%s")
        .replace("##", "?")
        .format(args.filter(notNoArg).map(stringValue): _*)
    )
  }
}