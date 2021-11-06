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
import kuzminki.render.{RenderCollector, Prefix}
import kuzminki.section._
import kuzminki.section.select._
import kuzminki.shape._


case class SelectCollector[R](
      prefix: Prefix,
      rowShape: RowShape[R],
      sections: Array[Section]
    ) extends RenderCollector {

  def add(section: Section) = this.copy(sections = sections :+ section)

  def extend(added: Array[Section]) = this.copy(sections = sections ++ added)

  def build = StoredSelect(render, args, rowShape.conv)

  private def validataWhere(): Unit = {
    
    sections.foreach {
    
      case HavingSec(_) =>
        throw KuzminkiError("HAVING is defined in query")
    
      case HavingBlankSec =>
        throw KuzminkiError("HAVING is defined in query")
    
      case _ =>
    }
  }

  private def validataHaving(): Unit = {
   
    sections.foreach {
   
      case WhereSec(_) =>
        throw KuzminkiError("WHERE is defined in query")
   
      case WhereBlankSec =>
        throw KuzminkiError("WHERE is defined in query")
   
      case _ =>
    }
  }

  private def validataOffset(): Unit = {
    
    sections.foreach {
    
      case sec: OffsetSec =>
        throw KuzminkiError("OFFSET is already defined")
    
      case _ =>
    }
  }

  private def replaceWhereSec[P](modifiedSections: Array[Section], partShape: PartShape[P]) = {

    modifiedSections.map {
      
      case WhereBlankSec =>
        WhereCacheSec(partShape.parts)
      
      case WhereSec(conds) =>
        WhereMixedSec(conds, partShape.parts)
      
      case section: Section =>
        section
    }
  }

  private def replaceHavingSec[P](modifiedSections: Array[Section], partShape: PartShape[P]) = {

    modifiedSections.map {
      
      case HavingBlankSec =>
        HavingCacheSec(partShape.parts)
      
      case HavingSec(conds) =>
        HavingMixedSec(conds, partShape.parts)
      
      case section: Section =>
        section
    }
  }

  private def insertOffsetCacheSec(modifiedSections: Array[Section]) = {
    
    modifiedSections.last match {
    
      case limitSec: LimitSec =>
        modifiedSections.dropRight(1) ++ Array(OffsetCacheSec, limitSec)
    
      case _ =>
        modifiedSections :+ OffsetCacheSec
    }
  }

  private def argSplit(args: Vector[Any], splitter: CacheArgs) = {

    val index = args.indexOf(splitter)
    val count = args.count(_ == splitter)

    if (count != 1) {
      throw KuzminkiError("Invalid query")
    }

    args.splitAt(index) match {
      case (args1, args2) =>
        (args1, args2.tail)
    }
  }

  private def renderForCache[P](modifiedSections: Array[Section]) = {
    
    val template = modifiedSections.map(_.render(prefix)).mkString(" ")
    
    val args = modifiedSections.map(_.args).flatten.toVector

    val argParts = argSplit(args, CacheCondArgs)
    
    (template, argParts)
  }

  private def renderForCacheWithOffset[P](modifiedSections: Array[Section]) = {

    val (template, argsTwoParts) = renderForCache(modifiedSections)

    val argsThreeParts = argsTwoParts match {
      case (args1, rest) =>
        argSplit(rest, CacheOffsetArgs) match {
          case (args2, args3) =>
            (args1, args2, args3)
        }
    }

    (template, argsThreeParts)
  }

  def buildWhere[P](partShape: PartShape[P]) = {

    validataWhere()

    val sectionsWithWhere = replaceWhereSec(sections, partShape)

    val (template, args) = renderForCache(sectionsWithWhere)

    new StoredSelectCondition(template, args, partShape.conv, rowShape.conv)
  }

  def buildWhereWithOffset[P](partShape: PartShape[P]) = {

    validataWhere()
    validataOffset()

    val sectionsWithWhere = replaceWhereSec(sections, partShape)

    val sectionsWithWhereAndOffset = insertOffsetCacheSec(sectionsWithWhere)

    val (template, args) = renderForCacheWithOffset(sectionsWithWhereAndOffset)

    new StoredSelectConditionAndOffset(template, args, partShape.conv, rowShape.conv)
  }

  def buildHaving[P](partShape: PartShape[P]) = {

    validataHaving()

    val sectionsWithHaving = replaceHavingSec(sections, partShape)

    val (template, args) = renderForCache(sectionsWithHaving)

    new StoredSelectCondition(template, args, partShape.conv, rowShape.conv)
  }

  def buildHavingWithOffset[P](partShape: PartShape[P]) = {

    validataHaving()
    validataOffset()

    val sectionsWithHaving = replaceHavingSec(sections, partShape)

    val sectionsWithHavingAndOffset = insertOffsetCacheSec(sectionsWithHaving)

    val (template, args) = renderForCacheWithOffset(sectionsWithHavingAndOffset)

    new StoredSelectConditionAndOffset(template, args, partShape.conv, rowShape.conv)
  }
}























