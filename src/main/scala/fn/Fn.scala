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

package kuzminki.fn

import kuzminki.column.TypeCol
import kuzminki.render.{Prefix, UnderlyingFunctionRender, UnderlyingArgs}
import kuzminki.function.ColFunction
import kuzminki.function.types.StringFunction

// coalesce

object Fn {

  import general._

  def coalesce[T](col: TypeCol[T], default: T) = Coalesce(col, default)

  def concat(cols: TypeCol[_]*) = Concat(cols)

  def concatWs(glue: String, cols: TypeCol[_]*) = ConcatWs(glue, cols)

  def substr(col: TypeCol[String], start: Int) = Substr(col, start, None)

  def substr(col: TypeCol[String], start: Int, len: Int) = Substr(col, start, Some(len))

  def replace(col: TypeCol[String], from: String, to: String) = Replace(col, from, to)

  def trim(col: TypeCol[String]) = Trim(col)

  def upper(col: TypeCol[String]) = Upper(col)

  def lower(col: TypeCol[String]) = Lower(col)

  def initcap(col: TypeCol[String]) = Initcap(col)
}


package object general {

  case class Coalesce[T](underlying: TypeCol[T], default: T) extends TypeCol[T]
                                                                with ColFunction {

    val template = "coalesce(%s, ?)"
    def conv = underlying.conv
    def render(prefix: Prefix) = template.format(underlying.render(prefix))
    def args = underlying.args ++ Seq(default)
  }


  case class Concat(cols: Seq[TypeCol[_]]) extends StringFunction {
    val template = "concat(%s)"
    def render(prefix: Prefix) = {
      template.format(
        cols.map(_.render(prefix)).mkString(", ")
      )
    }
    def args = cols.map(_.args).flatten
  }


  case class ConcatWs(glue: String, cols: Seq[TypeCol[_]]) extends StringFunction {
    val template = s"concat_ws('$glue', %s)"
    def render(prefix: Prefix) = {
      template.format(
        cols.map(_.render(prefix)).mkString(", ")
      )
    }
    def args = cols.map(_.args).flatten
  }


  case class Substr(
        underlying: TypeCol[String],
        start: Int,
        lenOpt: Option[Int]
      ) extends StringFunction
           with UnderlyingFunctionRender
           with UnderlyingArgs {

    val template = lenOpt match {
      case Some(len) => s"substr(%s, $start, $len)"
      case None => s"substr(%s, $start)"
    }
  }


  case class Replace(
        underlying: TypeCol[String],
        from: String,
        to: String
      ) extends StringFunction
           with UnderlyingFunctionRender
           with UnderlyingArgs {

    val template = s"replace(%s, '$from', '$to')"
  }


  case class Trim(underlying: TypeCol[String]) extends StringFunction
                                                  with UnderlyingFunctionRender
                                                  with UnderlyingArgs {

    val template = "replace(%s)"
  }

  case class Upper(underlying: TypeCol[String]) extends StringFunction
                                                  with UnderlyingFunctionRender
                                                  with UnderlyingArgs {

    val template = "upper(%s)"
  }

  case class Lower(underlying: TypeCol[String]) extends StringFunction
                                                  with UnderlyingFunctionRender
                                                  with UnderlyingArgs {

    val template = "replace(%s)"
  }

  case class Initcap(underlying: TypeCol[String]) extends StringFunction
                                                  with UnderlyingFunctionRender
                                                  with UnderlyingArgs {

    val template = "initcap(%s)"
  }
}














