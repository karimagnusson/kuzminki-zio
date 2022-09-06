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
import kuzminki.shape.ParamShape
import kuzminki.assign.SetUpsert
import kuzminki.render.SectionCollector
import kuzminki.section.insert._


private object ValidateDoUpdate {

  def validate(conflictCol: TypeCol[_], updateCols: Vector[TypeCol[_]]): Unit = {

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


class DoUpdate[M <: Model, P](
  parts: ValuesParts[M],
  conflictCol: TypeCol[_]
) {

  def doNothing = {
    new RenderInsert(
      parts.toColl.extend(Vector(
        InsertOnConflictColumnSec(conflictCol),
        InsertDoNothingSec
      ))
    )
  }

  def doUpdate(pick: M => Seq[TypeCol[_]]) = {
    
    val updateCols = pick(parts.model).toVector

    ValidateDoUpdate.validate(conflictCol, updateCols)

    val upserts = {
      val colValue = (parts.cols zip parts.values).toMap
      updateCols.map { col => 
        colValue.get(col) match {
          case Some(value) => SetValue(col, value)
          case None => throw KuzminkiError(
            s"column [${col.name}] must be an insert column to do upsert"
          )
        }
      }
    }

    new RenderInsert(
      parts.toColl.extend(Vector(
        InsertOnConflictColumnSec(conflictCol),
        InsertDoUpdateSec(upserts)
      ))
    )
  }
}


class DoUpdateStored[M <: Model, P](
    parts: InsertParts[M, P],
    conflictCol: TypeCol[_]
  ) {

  def doNothing = {
    new RenderStoredInsert(
      parts.toBlankColl.extend(Vector(
        InsertBlankValuesSec(parts.paramShape.cols),
        InsertOnConflictSec,
        InsertDoNothingSec
      )),
      parts.paramShape.conv
    )
  }

  def doUpdate(pick: M => Seq[TypeCol[_]]) = {
    
    val updateCols = pick(parts.model).toVector

    ValidateDoUpdate.validate(conflictCol, updateCols)

    new RenderStoredInsert(
      parts.toBlankColl.extend(Vector(
        InsertOnConflictColumnSec(conflictCol),
        InsertDoUpdateNoArgsSec(updateCols.map(SetUpsert(_)))
      )),
      new ParamConvReuse(
        parts.paramShape.conv,
        Reuse.fromIndex(parts.paramShape.cols, updateCols)
      )
    )
  }
}








