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

import kuzminki.column._
import kuzminki.conv.ValConv
import kuzminki.api.KuzminkiError
import kuzminki.render.Prefix


sealed trait CustomFn {
  val conv: ValConv[_]
  def name: String
  def template: String
  val cols: Vector[TypeCol[_]]

  def render(prefix: Prefix) = try {
    if (cols.isEmpty) {
      template
    } else {
      template.format(cols.map(_.render(prefix)): _*)
    }
  } catch {
    case _ : Throwable => throw KuzminkiError(s"template and columns do not match [$template]")
  }

  def args = {
    if (cols.isEmpty) {
      Vector.empty[Any]
    } else {
      cols.map(_.args).flatten
    }
  }
}

trait TypeFn[T] extends TypeCol[T] with CustomFn {
  val conv: ValConv[T]
}

trait StringFn extends StringCol with CustomFn
trait BooleanFn extends BooleanCol with CustomFn
trait ShortFn extends ShortCol with CustomFn
trait IntFn extends IntCol with CustomFn
trait LongFn extends LongCol with CustomFn
trait FloatFn extends FloatCol with CustomFn
trait DoubleFn extends DoubleCol with CustomFn
trait BigDecimalFn extends BigDecimalCol with CustomFn
trait TimeFn extends TimeCol with CustomFn
trait DateFn extends DateCol with CustomFn
trait TimestampFn extends TimestampCol with CustomFn
trait JsonbFn extends JsonbCol with CustomFn
trait UUIDFn extends UUIDCol with CustomFn
trait StringSeqFn extends StringSeqCol with CustomFn
trait BooleanSeqFn extends BooleanSeqCol with CustomFn
trait ShortSeqFn extends ShortSeqCol with CustomFn
trait IntSeqFn extends IntSeqCol with CustomFn
trait LongSeqFn extends LongSeqCol with CustomFn
trait FloatSeqFn extends FloatSeqCol with CustomFn
trait DoubleSeqFn extends DoubleSeqCol with CustomFn
trait BigDecimalSeqFn extends BigDecimalSeqCol with CustomFn
trait TimeSeqFn extends TimeSeqCol with CustomFn
trait DateSeqFn extends DateSeqCol with CustomFn
trait TimestampSeqFn extends TimestampSeqCol with CustomFn









