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

import kuzminki.api.Model
import kuzminki.column.{ModelCol, TypeCol}
import kuzminki.assign.SetValue
import kuzminki.api.KuzminkiError
import kuzminki.section.select.WhereSec
import kuzminki.select.SelectSubquery
import kuzminki.assign.SetUpsert
import kuzminki.model.ModelTable
import kuzminki.shape.ParamShape
import kuzminki.section.insert._
import kuzminki.render.SectionCollector
import kuzminki.filter.types.FilterMatchesNoArg


case class InsertBuilder[M <: Model, P](
  model: M,
  paramShape: ParamShape[P]
) {

  val cols = paramShape.cols

  def toValuesBuilder(params: P) =
    ValuesBuilder(model, cols, paramShape.conv.fromShape(params))

  def collector = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertBlankValuesSec(cols),
    ))
  }

  def returning(returningCols: Vector[TypeCol[_]]) = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertBlankValuesSec(cols),
      ReturningSec(returningCols)
    ))
  }

  def fromSelect[P](sub: SelectSubquery[P]) = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertSubquerySec(sub)
    ))
  }

  def cacheBlank = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertBlankValuesSec(cols)
    ))
  }

  def whereNotExists(uniqueCols: Vector[TypeCol[_]]) = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertWhereNotExistsSec(
        cols,
        ModelTable(model),
        WhereSec(
          uniqueCols.map(FilterMatchesNoArg(_))
        )
      )
    ))
  }

  def whereNotExistsReuse(uniqueCols: Vector[TypeCol[_]]) = {
    new ParamConvReuse(
      paramShape.conv,
      Reuse.fromIndex(cols, uniqueCols)
    )
  }

  def onConflictDoNothing = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertBlankValuesSec(cols),
      InsertOnConflictSec,
      InsertDoNothingSec
    ))
  }

  def onConflictColDoNothing(conflictCol: TypeCol[_]) = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertBlankValuesSec(cols),
      InsertOnConflictColumnSec(conflictCol),
      InsertDoNothingSec
    ))
  }

  def onConflictColDoUpdate(
    conflictCol: TypeCol[_],
    updateCols: Vector[TypeCol[_]]
  ) = {
    ValidateDoUpdate.run(conflictCol, updateCols)
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertBlankValuesSec(cols),
      InsertOnConflictColumnSec(conflictCol),
      InsertDoUpdateNoArgsSec(updateCols.map(SetUpsert(_)))
    ))
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
  cols: Vector[TypeCol[_]],
  values: Vector[Any]
) {

  def collector = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
    ))
  }

  def returning(returningCols: Vector[TypeCol[_]]) = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      ReturningSec(returningCols)
    ))
  }

  def whereNotExists(uniqueCols: Vector[TypeCol[_]]) = {
    val colValue = (cols zip values).toMap
    val conds = uniqueCols.map { col =>
      colValue.get(col) match {
        case Some(value) => SetValue(col, value)
        case None => throw KuzminkiError(
          s"column [${col.name}] must be an insert column to do upsert"
        )
      }
    }
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertWhereNotExistsSec(
        values,
        ModelTable(model),
        WhereSec(conds.toVector)
      )
    ))
  }

  def onConflictDoNothing = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      InsertOnConflictSec,
      InsertDoNothingSec
    ))
  }

  def onConflictColDoNothing(conflictCol: TypeCol[_]) = {
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      InsertOnConflictColumnSec(conflictCol),
      InsertDoNothingSec
    ))
  }

  def onConflictColDoUpdate(
    conflictCol: TypeCol[_],
    updateCols: Vector[TypeCol[_]]
  ) = {
    ValidateDoUpdate.run(conflictCol, updateCols)
    val colValue = (cols zip values).toMap
    val upserts = updateCols.map { col => 
      colValue.get(col) match {
        case Some(value) => SetValue(col, value)
        case None => throw KuzminkiError(
          s"column [${col.name}] must be an insert column to do upsert"
        )
      }
    }
    SectionCollector(Vector(
      InsertIntoSec(ModelTable(model)),
      InsertColumnsSec(cols),
      InsertValuesSec(values),
      InsertOnConflictColumnSec(conflictCol),
      InsertDoUpdateSec(upserts)
    ))
  }
}


object ValidateDoUpdate {

  def run(conflictCol: TypeCol[_], updateCols: Vector[TypeCol[_]]): Unit = {

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
  }
}































