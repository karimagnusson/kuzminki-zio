#! /usr/bin/env python


func = """
  def returning%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    next(
      new RowShape%s(pick(model))
    )
  }"""



template = """package kuzminki.insert

import kuzminki.column.TypeCol
import kuzminki.section.insert.ReturningSec
import kuzminki.shape._


abstract class PickInsertDoNothingReturning[M, P](
      model: M,
      coll: InsertCollector[P]
    ) {

  def next[R](rowShape: RowShape[R]) = {
    new RunInsertDoNothingReturning(
      coll.add(ReturningSec(rowShape.cols)),
      rowShape
    )
  }

  def returningRead[R](pick: M => RowReader[R]) = {
    next(
      pick(model)
    )
  }

  def returningSeq(pick: M => Seq[TypeCol[_]]) = {
    next(
      new RowShapeSeq(pick(model))
    )
  }

  def returning1[R](pick: M => TypeCol[R]) = {
    next(
      new RowShapeSingle(pick(model))
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

f = open('../scala/kuzminki/insert/PickInsertDoNothingReturning.scala', 'w')
f.write(content)
f.close()