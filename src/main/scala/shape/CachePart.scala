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

import kuzminki.api.KuzminkiError
import kuzminki.render.Renderable
import kuzminki.conv._


trait CachePart[T] extends Renderable {
  val conv: ValConv[T]
}

object CachePart {

  def itemConv[T](conv: ValConv[Seq[T]]): ValConv[T] = conv match {
    case StringSeqConv => StringConv
    case BooleanSeqConv => BooleanConv
    case ShortSeqConv => ShortConv
    case IntSeqConv => IntConv
    case LongSeqConv => LongConv
    case FloatSeqConv => FloatConv
    case DoubleSeqConv => DoubleConv
    case BigDecimalSeqConv => BigDecimalConv
    case TimeSeqConv => TimeConv
    case DateSeqConv => DateConv
    case TimestampSeqConv => TimestampConv
    case _ => throw KuzminkiError("must be an array column")
  }

  def seqConv[T](conv: ValConv[T]): ValConv[Seq[T]] = conv match {
    case StringConv => StringSeqConv
    case BooleanConv => BooleanSeqConv
    case ShortConv => ShortSeqConv
    case IntConv => IntSeqConv
    case LongConv => LongSeqConv
    case FloatConv => FloatSeqConv
    case DoubleConv => DoubleSeqConv
    case BigDecimalConv => BigDecimalSeqConv
    case TimeConv => TimeSeqConv
    case DateConv => DateSeqConv
    case TimestampConv => TimestampSeqConv
    case _ => throw KuzminkiError("must be an array column")
  }
}











