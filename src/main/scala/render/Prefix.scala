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

import kuzminki.api.{Model, Join, KuzminkiError}
import kuzminki.column.ColInfo


sealed trait Prefix {
  import Prefix.az
  val prefixWrap = "\"%s\".\"%s\""
  val tableWrap = "\"%s\" \"%s\""
  def pick(info: ColInfo): String
  def table(table: String): String
  def forSubquery(prefix: Prefix): Prefix
  def next(char: String) = {
    val az = Prefix.az
    az(az.indexOf(char) + 1)
  }
  def next2(char: String) = {
    val az = Prefix.az
    az(az.indexOf(char) + 2)
  }
}

object Prefix {

  def az = Vector("a", "b", "c", "d",
                  "e", "f", "g", "h",
                  "i", "k", "l", "m",
                  "n", "o", "p", "q",
                  "r", "s", "t", "v",
                  "x", "y", "z")

  def forModel[M <: Model](model: M) =
    new ModelPrefix(model.__name)

  def forJoin[A <: Model, B <: Model](join: Join[A, B]): Prefix =
    new JoinPrefix(join.a.__name, join.b.__name, "a", "b")
}

case class ModelPrefix(table: String) extends Prefix with Wrap {
  
  def pick(info: ColInfo) = wrap(info.name)
  def table(table: String) = wrap(table)
  
  def forSubquery(prefix: Prefix) = prefix match {
    case ModelPrefix(_)             => new SubqueryPrefix(table, "a")
    case SubqueryPrefix(_, last)    => new SubqueryPrefix(table, next(last))
    case JoinPrefix(_, _, _, last)  => new SubqueryPrefix(table, next(last))
  }
}

case class SubqueryPrefix(table: String, c: String) extends Prefix {
  
  def pick(info: ColInfo) = prefixWrap.format(c, info.name)
  def table(table: String) = tableWrap.format(table, c)
  
  def forSubquery(prefix: Prefix) = prefix match {
    case ModelPrefix(_)             => new SubqueryPrefix(table, next(c))
    case SubqueryPrefix(_, last)    => new SubqueryPrefix(table, next(last))
    case JoinPrefix(_, _, _, last)  => new SubqueryPrefix(table, next(last))
  }
}

case class JoinPrefix(tableA: String, tableB: String, a: String, b: String) extends Prefix {

  def pick(info: ColInfo) = info match {
      
    case ColInfo(name, table) if info.table == tableA =>
      prefixWrap.format(a, name)
    
    case ColInfo(name, table) if info.table == tableB =>
      prefixWrap.format(b, name)
    
    case ColInfo(name, table) =>
      throw KuzminkiError(
        s"column $name of table $table is not found in join($tableA, $tableB)"
      )
  }

  def table(table: String) = table match {
    
    case table if table == tableA =>
      tableWrap.format(table, a)
    
    case table if table == tableB =>
      tableWrap.format(table, b)
    
    case table =>
      throw KuzminkiError(
        s"table $table is not a member of join($tableA, $tableB)"
      )
  }

  def forSubquery(prefix: Prefix) = {
    val (na, nb) = prefix match {
      case ModelPrefix(_)           => (a, b)
      case SubqueryPrefix(_, last)  => (next(last), next2(last))
      case JoinPrefix(_, _, la, lb) => (next2(la), next2(lb))
    }
    JoinPrefix(tableA, tableB, na, nb)
  }
}
















