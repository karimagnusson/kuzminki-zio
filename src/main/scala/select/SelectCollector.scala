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

package kuzminki.select

import kuzminki.api.KuzminkiError
import kuzminki.render.{RenderCollector, RenderedQuery, Prefix}
import kuzminki.section._
import kuzminki.shape._


case class SelectCollector[R](
  prefix: Prefix,
  rowShape: RowShape[R],
  sections: Vector[Section]
) extends RenderCollector {

  def add(section: Section) = this.copy(sections = sections :+ section)

  def extend(added: Vector[Section]) = this.copy(sections = sections ++ added)

  private def replaceWhereSec[P](sections: Vector[Section], partShape: PartShape[P]) = {

    sections.map {
      
      case WhereBlankSec =>
        WhereCacheSec(partShape.parts)
      
      case WhereSec(conds) =>
        WhereMixedSec(conds, partShape.parts)

      case HavingSec(_) =>
        throw KuzminkiError("HAVING is defined in query")
    
      case HavingBlankSec =>
        throw KuzminkiError("HAVING is defined in query")
      
      case section: Section =>
        section
    }
  }

  def cacheWhere[P](partShape: PartShape[P]) = {

    val modifiedSections = replaceWhereSec(sections, partShape)

    val template = modifiedSections.map(_.render(prefix)).mkString(" ")
    
    val args = modifiedSections.map(_.args).flatten

    new StoredSelect(template, args, partShape.conv, rowShape.conv)
  }

  private def replaceHavingSec[P](sections: Vector[Section], partShape: PartShape[P]) = {

    sections.map {
      
      case HavingBlankSec =>
        HavingCacheSec(partShape.parts)
      
      case HavingSec(conds) =>
        HavingMixedSec(conds, partShape.parts)

      case WhereSec(_) =>
        throw KuzminkiError("WHERE is defined in query")
   
      case WhereBlankSec =>
        throw KuzminkiError("WHERE is defined in query")
      
      case section: Section =>
        section
    }
  }

  def cacheHaving[P](partShape: PartShape[P]) = {

    val modifiedSections = replaceHavingSec(sections, partShape)

    val template = modifiedSections.map(_.render(prefix)).mkString(" ")
    
    val args = modifiedSections.map(_.args).flatten

    new StoredSelect(template, args, partShape.conv, rowShape.conv)
  }
}























