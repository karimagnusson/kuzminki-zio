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

import kuzminki.column.TypeCol
import kuzminki.render.Prefix


trait PassConvFn[T] extends TypeCol[T] {
  val col: TypeCol[T]
  val conv = col.conv
}

trait SingleColFn {
  val col: TypeCol[_]
  def template: String
  def name = col.name
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args
}

trait SingleColArgFn {
  val col: TypeCol[_]
  val arg: Any
  def template: String
  def name = col.name
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = col.args ++ Vector(arg)
}


