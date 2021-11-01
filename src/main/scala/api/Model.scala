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
import scala.reflect.runtime.universe._
import scala.annotation.tailrec

import kuzminki.column.{ColInfo, TypeCol}
import kuzminki.model.{ModelRead, ModelWrite}


object Model {

  private var stored = Set.empty[Model]

  private def find[M <: Model](implicit tag: ClassTag[M]): Option[M] = {
    
    @tailrec
    def loop(instances: List[Model]): Option[M] = {
      instances match {
        case Nil => None
        case head :: tail => 
          head match {
            case model: M => Some(model)
            case _ => loop(tail)
          }
      }
    }

    loop(stored.toList)
  }

  def get[M <: Model](implicit tag: ClassTag[M]): M = {
    find[M] match {
      case Some(model) =>
        model
      case None =>
        val model = noCache[M]
        stored = stored + model
        model 
    }
  }

  def register[M <: Model](implicit tag: ClassTag[M]): Unit = {
    find[M] match {
      case Some(model) =>
      case None =>
        stored = stored + noCache[M]
    }
  }
  
  def noCache[M <: Model](implicit tag: ClassTag[M]): M = {
    tag.runtimeClass.newInstance.asInstanceOf[M]
  }
}

abstract class Model(val __name: String) extends ModelRead with ModelWrite {

  def column[T](name: String)(implicit creator: ColInfo => TypeCol[T]) = {
    creator(ColInfo(name, __name))
  }
}


















