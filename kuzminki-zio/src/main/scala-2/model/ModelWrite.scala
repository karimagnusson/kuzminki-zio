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

package kuzminki.model

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._
import kuzminki.column.TypeCol
import kuzminki.shape._


trait ModelWrite {
  
  @deprecated("this method will be removed", "0.9.5")
  def write[T](colArgs: TypeCol[_]*)(implicit cTag: ClassTag[T], tTag: TypeTag[T]) = {
    val cols = colArgs.toVector
    val names = TypeMembers.getTypes[T]
    RowTypeNames.validate(cols, names)
    new ParamShapeWrite(cols, cTag)
  }
}