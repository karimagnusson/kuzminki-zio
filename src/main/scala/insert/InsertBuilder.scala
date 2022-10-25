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

package kuzminki.insert

import kuzminki.api.{Model, CacheArg}
import kuzminki.column.{ModelCol, TypeCol}
import kuzminki.assign.ValueEq
import kuzminki.api.KuzminkiError
import kuzminki.section.WhereSec
import kuzminki.select._
import kuzminki.model.ModelTable
import kuzminki.shape.ParamShape
import kuzminki.section._
import kuzminki.render.{Prefix, Renderable, SectionCollector}


case class InsertBuilder[M <: Model, P](
  model: M,
  paramShape: ParamShape[P]
) extends BuilderMethods[M] {

  val cols = paramShape.cols
  def values = Vector.fill(paramShape.size)(CacheArg)

  def toValuesBuilder(params: P) =
    ValuesBuilder(model, cols, paramShape.conv.fromShape(params))

  def whereNotExistsReuse(uniqueCols: Vector[TypeCol[_]]) = {
    new ParamConvReuse(
      paramShape.conv,
      Reuse.fromIndex(cols, uniqueCols)
    )
  }

  def onConflictColDoUpdateReuse(updateCols: Vector[TypeCol[_]]) = {
    new ParamConvReuse(
      paramShape.conv,
      Reuse.fromIndex(cols, updateCols)
    )
  }
}


case class ValuesBuilder[M <: Model](
  model: M,
  cols: Vector[Renderable],
  values: Vector[Any]
) extends BuilderMethods[M]


trait BuilderMethods[M <: Model] {

  val model: M
  val cols: Vector[Renderable]
  def values: Vector[Any]
  val prefix = Prefix.forModel(model)

  def collector = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
    ))
  }

  def returning(returningCols: Vector[TypeCol[_]]) = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      ReturningSec(returningCols)
    ))
  }

  def fromSelect(sub: Renderable) = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertSubquerySec(sub)
    ))
  }

  def cacheBlank = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values)
    ))
  }

  def whereNotExists(uniqueCols: Vector[TypeCol[_]]) = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertWhereNotExistsSec(
        values,
        ModelTable(model),
        WhereSec(conditional(uniqueCols))
      )
    ))
  }

  def onConflictDoNothing = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      InsertOnConflictSec,
      InsertDoNothingSec
    ))
  }

  def onConflictColDoNothing(conflictCol: TypeCol[_]) = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      InsertOnConflictColumnSec(conflictCol),
      InsertDoNothingSec
    ))
  }

  def onConflictColDoUpdate(conflictCol: TypeCol[_], updateCols: Vector[TypeCol[_]]) = {
    SectionCollector(prefix, Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      InsertOnConflictColumnSec(conflictCol),
      InsertDoUpdateSec(upsert(conflictCol, updateCols))
    ))
  }

  private def upsert(conflictCol: TypeCol[_], updateCols: Vector[TypeCol[_]]) = {
    
    updateCols.map {
      case col: ModelCol =>
      case _ => throw KuzminkiError("update columns cannot be a function")
    }

    if (updateCols.isEmpty) {
      throw KuzminkiError("no update columns selected")
    }

    if (updateCols.contains(conflictCol)) {
      throw KuzminkiError("cannot update the conflicting column")
    }

    val colValue = (cols zip values).toMap
    
    updateCols.map { col => 
      colValue.get(col) match {
        case Some(value) => ValueEq(col, value)
        case None => throw KuzminkiError(
          s"column must be an insert column to do upsert"
        )
      }
    }
  }

  private def conditional(uniqueCols: Vector[TypeCol[_]]) = {
    
    uniqueCols.map {
      case col: ModelCol =>
      case _ => throw KuzminkiError("update columns cannot be a function")
    }

    if (uniqueCols.isEmpty) {
      throw KuzminkiError("no update columns selected")
    }

    val colValue = (cols zip values).toMap
    
    uniqueCols.map { col => 
      colValue.get(col) match {
        case Some(value) => ValueEq(col, value)
        case None => throw KuzminkiError(
          s"column must be an insert column to do upsert"
        )
      }
    }
  }
}

























