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

package kuzminki.filter.types

import kuzminki.filter.Filter
import kuzminki.column.{TypeCol, ModelCol}
import kuzminki.conv._
import kuzminki.shape.CachePart
import kuzminki.api.KuzminkiError
import kuzminki.render.{
  Renderable,
  Prefix,
  PassArgs,
  Wrap
}


object CacheFilter extends Wrap {

  import CachePart.{seqConv, itemConv}
  
  private def test[T](col: TypeCol[T]): Renderable = {
    col.args match {
      case Nil =>
      case _ => throw KuzminkiError("cannot cache a function with an argument")
    }
    col
  }

  def matches[T](col: TypeCol[T]) = CacheEq(test(col), col.conv)
  def not[T](col: TypeCol[T]) = CacheNot(test(col), col.conv)
  def in[T](col: TypeCol[T]) = CacheEq(test(col), seqConv(col.conv))
  def notIn[T](col: TypeCol[T]) = CacheEq(test(col), seqConv(col.conv))

  def gt[T](col: TypeCol[T]) = CacheGt(test(col), col.conv)
  def lt[T](col: TypeCol[T]) = CacheLt(test(col), col.conv)
  def gte[T](col: TypeCol[T]) = CacheGte(test(col), col.conv)
  def lte[T](col: TypeCol[T]) = CacheLte(test(col), col.conv)

  def like[T](col: TypeCol[T]) = CacheLike(test(col), col.conv)
  def startsWith[T](col: TypeCol[T]) = CacheStartsWith(test(col), col.conv)
  def endsWith[T](col: TypeCol[T]) = CacheEndsWith(test(col), col.conv)
  def similarTo[T](col: TypeCol[T]) = CacheSimilarTo(test(col), col.conv)
  
  def reMatch[T](col: TypeCol[T]) = CacheReMatch(test(col), col.conv)
  def reIMatch[T](col: TypeCol[T]) = CacheReIMatch(test(col), col.conv)
  def reNotMatch[T](col: TypeCol[T]) = CacheReNotMatch(test(col), col.conv)
  def reNotIMatch[T](col: TypeCol[T]) = CacheReNotIMatch(test(col), col.conv)

  def seqHas[T](col: TypeCol[Seq[T]]) = CacheSeqHas(test(col), itemConv(col.conv))
  def seqHasNot[T](col: TypeCol[Seq[T]]) = CacheSeqHasNot(test(col), itemConv(col.conv))
  def seqOverlap[T](col: TypeCol[Seq[T]]) = CacheSeqOverlap(test(col), col.conv)
  def seqOverlapNot[T](col: TypeCol[Seq[T]]) = CacheSeqOverlapNot(test(col), col.conv)

  def jsonbEq[T](col: TypeCol[T]) = CacheJsonbEq(test(col), col.conv)
  def jsonbNot[T](col: TypeCol[T]) = CacheJsonbNot(test(col), col.conv)
  def jsonbContains[T](col: TypeCol[T]) = CacheJsonbContains(test(col), col.conv)
  def jsonbContainedBy[T](col: TypeCol[T]) = CacheJsonbContainedBy(test(col), col.conv)
  def jsonbExists[T](col: TypeCol[T]) = CacheJsonbExists(test(col), StringConv)
  def jsonbExistsAny[T](col: TypeCol[T]) = CacheJsonbExistsAny(test(col), StringSeqConv)
  def jsonbExistsAll[T](col: TypeCol[T]) = CacheJsonbExistsAll(test(col), StringSeqConv)
}


trait CacheFilter[P] extends CachePart[P] {
  val col: Renderable
  val conv: ValConv[P]
  val template: String
  def render(prefix: Prefix) = template.format(col.render(prefix))
  val args = Vector.empty[Any]
}


























