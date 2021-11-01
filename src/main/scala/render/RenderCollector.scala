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

import kuzminki.section.Section
import kuzminki.section.select.{WhereBlankSec, HavingBlankSec}


trait RenderCollector {
  val sections: Array[Section]
  val prefix: Prefix

  val notBlank: Section => Boolean = {
    case WhereBlankSec => false
    case HavingBlankSec => false
    case _ => true
  }

  def render = sections.filter(notBlank).map(_.render(prefix)).mkString(" ")
  
  def args = sections.toSeq.map(_.args).flatten.toVector
  
  def statement = SqlWithParams(render, args)
}

























