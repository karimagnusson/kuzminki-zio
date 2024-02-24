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

import scala.reflect.{classTag, ClassTag}
import scala.deriving.Mirror.ProductOf
import kuzminki.column.TypeCol
import kuzminki.shape._


trait ModelWrite {

  import org.tpolecat.typename.TypeName

  @deprecated("this method will be removed", "0.9.5")
  inline def write[T](colArgs: TypeCol[_]*)(using p: ProductOf[T], cTag: ClassTag[T]) = {
    val cols = colArgs.toVector
    val names = TypeMembers.getTypes[T]
    RowTypeNames.validate(cols, names)
    new ParamShapeWrite(cols, cTag)
  }
}