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

package kuzminki.api

import scala.reflect.{classTag, ClassTag}
import scala.annotation.tailrec
import kuzminki.column.{ColInfo, TypeCol}
import kuzminki.model.ModelRead


trait Join[A <: Model, B <: Model] {
  val a: A
  val b: B
}

object Join {

  def create[A <: Model, B <: Model](implicit tagA: ClassTag[A], tagB: ClassTag[B]): Join[A, B] = {
    DefaultJoin(Model.get[A], Model.get[B])
  }
}

case class DefaultJoin[A <: Model, B <: Model](a: A, b: B) extends Join[A, B]

abstract class JoinRead[A <: Model, B <: Model](
      implicit tagA: ClassTag[A],
               tagB: ClassTag[B]
    ) extends Join[A, B]
         with ModelRead {

  val a = Model.get[A]
  val b = Model.get[B]

  val convert: Join[A, B] => this.type = _ => this
}


/*
package kuzminki.api

import scala.reflect.{classTag, ClassTag}
import scala.annotation.tailrec
import kuzminki.column.{ColInfo, TypeCol}
import kuzminki.model.ModelRead


sealed trait CustomJoin

trait Join[A <: Model, B <: Model] {
  val a: A
  val b: B
}

object Join {

  import scala.collection.mutable.Map

  def default[A <: Model, B <: Model](implicit tagA: ClassTag[A], tagB: ClassTag[B]) = {
   DefaultJoin(Model.get[A], Model.get[B])
  }

  private var stored = Set.empty[CustomJoin]

  private def find[J <: CustomJoin](implicit tag: ClassTag[J]): Option[J] = {
    
    @tailrec
    def loop(instances: List[CustomJoin]): Option[J] = {
      instances match {
        case Nil => None
        case head :: tail => 
          head match {
            case join: J => Some(join)
            case _ => loop(tail)
          }
      }
    }

    loop(stored.toList)
  }

  def get[J <: CustomJoin](implicit tag: ClassTag[J]): J = {
    find[J] match {
      case Some(join) =>
        join
      case None =>
        val join = noCache[J]
        stored = stored + join
        join
    }
  }

  def register[J <: CustomJoin, A <: Model, B <: Model](implicit tag: ClassTag[J]): Join[A, B] => J = {
    val join = get[J]
    val conv: Join[A, B] => J = _ => join
    conv
  }
  
  def noCache[J <: CustomJoin](implicit tag: ClassTag[J]): J = {
    tag.runtimeClass.newInstance.asInstanceOf[J]
  }
}

case class DefaultJoin[A <: Model, B <: Model](a: A, b: B) extends Join[A, B]

abstract class ExtendedJoin[A <: Model, B <: Model](
      implicit tagA: ClassTag[A],
               tagB: ClassTag[B]
    ) extends Join[A, B]
         with CustomJoin
         with ModelRead {

  val a = Model.get[A]
  val b = Model.get[B]
}
*/

















