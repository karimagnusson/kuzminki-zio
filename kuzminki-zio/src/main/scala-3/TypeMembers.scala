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

import scala.util.matching.Regex
import kuzminki.api.KuzminkiError


object TypeMembers {

  import org.tpolecat.typename.TypeName
  
  val regularValue: Regex = """^([a-zA-Z\.]+)$""".r
  val subTypeValue: Regex = """^([a-zA-Z\.]+)\[([a-zA-Z\.]+)\]$""".r

  val standardize: String => String = {
    case regularValue(v) => v.split('.').last
    case subTypeValue(s, v) => s.split('.').last + "[" + v.split('.').last + "]"
    case v => throw KuzminkiError(s"invalid type: $v")
  }

  inline def getTypes[T](using p: scala.deriving.Mirror.ProductOf[T]): Vector[String] = {
    val x: Tuple.Map[p.MirroredElemTypes, TypeName] =
      scala.compiletime.summonAll[Tuple.Map[p.MirroredElemTypes, TypeName]]
    val y: List[TypeName[?]] = x.toList.asInstanceOf
    y.map(_.value).map(standardize).toVector
  }
}