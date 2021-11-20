#! /usr/bin/env python


func = """
  def cols%s[%s](pick: M => Tuple%s[%s]) = {
    next(new ParamShape%s(pick(model)))
  }"""



template = """package kuzminki.insert

import kuzminki.api.Model
import kuzminki.model.ModelTable
import kuzminki.column.TypeCol
import kuzminki.assign.SetValue
import kuzminki.render.SectionCollector
import kuzminki.section.Section
import kuzminki.section.insert._
import kuzminki.shape._


class Insert[M <: Model](model: M) {

  def data(pick: M => Seq[SetValue]) = {
    new InsertDataOptions(
      model,
      SectionCollector(
        Array(
          InsertIntoSec(ModelTable(model)),
          InsertDataSec(pick(model))
        )
      )
    )
  }

  private def next[P](paramShape: ParamShape[P]) = {
    new InsertOptions(
      model,
      SectionCollector(
        Array(
          InsertIntoSec(ModelTable(model)),
          InsertColumnsSec(paramShape.cols)
        )
      ),
      paramShape
    )
  }

  def colsWrite[P](pick: M => ParamShapeWrite[P]) = {
    next(pick(model))
  }

  def cols1[P](pick: M => TypeCol[P]) = {
    next(new ParamShapeSingle(pick(model)))
  }
  %s
}"""


parts = []

for num in range(2, 23):
    func_types = ', '.join(['R%d' % i for i in range(1, num + 1)])
    col_types = ', '.join(['TypeCol[R%d]' % i for i in range(1, num + 1)])
    part = func % (str(num), func_types, str(num), col_types, str(num),)
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/insert/Insert.scala', 'w')
f.write(content)
f.close()