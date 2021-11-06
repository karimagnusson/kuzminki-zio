#! /usr/bin/env python


func = """
  def cols%s[%s](
        pick: Join[A, B] => Tuple%s[%s]
      ) = {
    next(
      new RowShape%s(pick(join))
    )
  }"""



template = """package kuzminki.select

import kuzminki.api.{Model, Join}
import kuzminki.model.ModelTable
import kuzminki.column.TypeCol
import kuzminki.render.Prefix
import kuzminki.section.select._
import kuzminki.shape._


class SelectJoin[A <: Model, B <: Model](val join: Join[A, B]) {

  def next[R](outShape: RowShape[R]) = {
    new JoinOn(
      join,
      SelectCollector(
        Prefix.forJoin(join),
        outShape,
        Array(
          SelectSec(outShape.cols),
          FromSec(ModelTable(join.a))
        )
      )
    )
  }

  def colsRead[R](pick: Join[A, B] => RowReader[R]) = {
    next(
      pick(join)
    )
  }

  def colsSeq(pick: Join[A, B] => Seq[TypeCol[_]]) = {
    next(
      new RowShapeSeq(pick(join))
    )
  }

  def cols1[R](pick: Join[A, B] => TypeCol[R]) = {
    next(
      new RowShapeSingle(pick(join))
    )
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

f = open('../scala/select/SelectJoin.scala', 'w')
f.write(content)
f.close()