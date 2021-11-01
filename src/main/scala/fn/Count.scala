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

import kuzminki.column.AnyCol
import kuzminki.render.{Prefix, NoArgs}
import kuzminki.function.types.LongFunctionSingle


object Count {
  def all = Count(CountAll)
}

case class Count(underlying: AnyCol) extends LongFunctionSingle {
  val template = "count(%s)"
}

object CountAll extends AnyCol with NoArgs {
  val self = this
  def render(prefix: Prefix) = "*"
}