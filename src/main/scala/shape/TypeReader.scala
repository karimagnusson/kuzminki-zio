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

package kuzminki.shape

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import java.sql.ResultSet

import scala.language.implicitConversions
import scala.util.{Try, Success, Failure}
import scala.reflect.{classTag, ClassTag}
import scala.reflect.runtime.universe._

import kuzminki.conv._
import kuzminki.api.KuzminkiError


object TypeReader {

  private val stringToMember: String => ValConv[_] = {
    case "String" => StringConv
    case "Boolean" => BooleanConv
    case "Int" => IntConv
    case "Short" => ShortConv
    case "Long" => LongConv
    case "BigDecimal" => BigDecimalConv
    case "Double" => DoubleConv
    case "Float" => FloatConv
    case "java.sql.Time" => TimeConv
    case "java.sql.Date" => DateConv
    case "java.sql.Timestamp" => TimestampConv
    case name => throw KuzminkiError(s"Unsupported type: $name")
  }
  
  def create[T : ClassTag : TypeTag]: TypeReader[T] = {

    val members = typeOf[T]
      .members
      .sorted
      .collect { case m: MethodSymbol if m.isCaseAccessor => m }
      .map(_.returnType.toString)
      .toSeq
      .map(stringToMember)

    new CustomTypeReader[T](members.zipWithIndex.map(p => (p._1, p._2 + 1)))
  }
}

trait TypeReader[T] {
  def read(rs: ResultSet): T
}

class CustomTypeReader[T : ClassTag](members: Seq[Tuple2[ValConv[_], Int]]) extends TypeReader[T] {

  private def args(rs: ResultSet): Seq[AnyRef] = {
    members.map {
      case (member, index) =>
        member.get(rs, index).asInstanceOf[AnyRef]
    }
  }

  def read(rs: ResultSet): T = {
    Try {
      
      classTag[T]
        .runtimeClass
        .getConstructors
        .head
        .newInstance(args(rs): _*)
        .asInstanceOf[T]
    
    } match {
      case Success(res) => res
      case Failure(ex) =>
        val name = classTag[T].runtimeClass.getName
        val message = ex.getMessage
        throw KuzminkiError(s"Failed to read ($name) $message")
    }
  }
}






























