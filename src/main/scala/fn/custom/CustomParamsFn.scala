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


sealed trait CustomParamsFn {
  val conv: ValConv[_]
  def name: String
  def template: String
  val cols: Vector[TypeCol[_]]
  val params: Vector[Any]

  def render(prefix: Prefix) = try {
    template.format(cols.map(_.render(prefix)): _*)
  } catch {
    case _ : Throwable => throw KuzminkiError(s"template and columns do not match [$template]")
  }

  def args = { 
    def loop(chars: List[Char], cols: List[TypeCol[_]], args: List[Any], res: Vector[Seq[Any]]): Vector[Any] = {
      chars match {
        case Nil => res.flatten
        case '#' :: charTail => cols match {
          case col :: colsTail => loop(charTail, colsTail, args, res :+ col.args)
          case _ => throw new Exception("")
        }
        case '?' :: charTail => args match {
          case arg :: argsTail => loop(charTail, cols, argsTail, res :+ Seq(arg))
          case _ => throw new Exception("")
        }
        case _  :: charTail => loop(charTail, cols, args, res)
      }
    }
    val tmpl = template.replaceAll("%%", "").replaceAll("%s", "#").replaceAll("[?]{2}", "")
    try {
      loop(tmpl.toCharArray.toList, cols.toList, params.toList, Vector.empty[Seq[Any]])
    } catch {
      case _ : Throwable => throw KuzminkiError(s"template and function args do not match [$template]")
    }
  }
}

trait TypeParamsFn[T] extends TypeCol[T] with CustomParamsFn {
  val conv: ValConv[T]
}

trait StringParamsFn extends StringCol with CustomParamsFn
trait BooleanParamsFn extends BooleanCol with CustomParamsFn
trait ShortParamsFn extends ShortCol with CustomParamsFn
trait IntParamsFn extends IntCol with CustomParamsFn
trait LongParamsFn extends LongCol with CustomParamsFn
trait FloatParamsFn extends FloatCol with CustomParamsFn
trait DoubleParamsFn extends DoubleCol with CustomParamsFn
trait BigDecimalParamsFn extends BigDecimalCol with CustomParamsFn
trait TimeParamsFn extends TimeCol with CustomParamsFn
trait DateParamsFn extends DateCol with CustomParamsFn
trait TimestampParamsFn extends TimestampCol with CustomParamsFn
trait JsonbParamsFn extends JsonbCol with CustomParamsFn
trait UUIDParamsFn extends UUIDCol with CustomParamsFn
trait StringSeqParamsFn extends StringSeqCol with CustomParamsFn
trait BooleanSeqParamsFn extends BooleanSeqCol with CustomParamsFn
trait ShortSeqParamsFn extends ShortSeqCol with CustomParamsFn
trait IntSeqParamsFn extends IntSeqCol with CustomParamsFn
trait LongSeqParamsFn extends LongSeqCol with CustomParamsFn
trait FloatSeqParamsFn extends FloatSeqCol with CustomParamsFn
trait DoubleSeqParamsFn extends DoubleSeqCol with CustomParamsFn
trait BigDecimalSeqParamsFn extends BigDecimalSeqCol with CustomParamsFn
trait TimeSeqParamsFn extends TimeSeqCol with CustomParamsFn
trait DateSeqParamsFn extends DateSeqCol with CustomParamsFn
trait TimestampSeqParamsFn extends TimestampSeqCol with CustomParamsFn





