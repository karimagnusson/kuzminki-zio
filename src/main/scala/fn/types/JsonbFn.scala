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

package kuzminki.fn.types

import kuzminki.fn.JsonbFn
import kuzminki.column._
import kuzminki.api.Jsonb


trait JsonbOpr

case class JsonbPickFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with SingleColArgFn with JsonbOpr {
  val template = "%s->?"
}

case class JsonbPickStrFn(col: TypeCol[Jsonb], arg: Any) extends StringCol with SingleColArgFn with JsonbOpr {
  val template = "%s->>?"
}

case class JsonbPathFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with SingleColArgFn with JsonbOpr {
  val template = "%s#>?"
}

case class JsonbPathStrFn(col: TypeCol[Jsonb], arg: Any) extends StringCol with SingleColArgFn with JsonbOpr {
  val template = "%s#>>?"
}

case class JsonbDropFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with SingleColArgFn with JsonbOpr {
  val template = "%s - ?"
}

case class JsonbDropManyFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with SingleColArgFn with JsonbOpr {
  val template = "%s - ?"
}

case class JsonbDropPathFn(col: TypeCol[Jsonb], arg: Any) extends JsonbCol with SingleColArgFn with JsonbOpr {
  val template = "%s #- ?"
}

case class JsonbConcatFn(col1: TypeCol[Jsonb], col2: TypeCol[Jsonb]) extends JsonbFn with JsonbOpr {
  def name = "jsonb_concat"
  val template = "%s || %s"
  val cols = Vector(col1, col2)
}

case class JsonbResFn(kv: Seq[Tuple2[String, TypeCol[_]]]) extends JsonbFn with JsonbOpr {
  def name = "json"
  val template = "json_build_object(%s)".format(
    kv.map(_._1).map(name => s"'$name', %s").mkString(", ")
  )
  val cols = kv.map(_._2).toVector
}













