#! /usr/bin/env python


func = """
  def cols%s[%s](pick: M => Tuple%s[%s]) = {
    next(
      new RowShape%s(pick(model))
    )
  }"""



template = """package kuzminki.model


class Aggregate[M <: Model](model: M, db: Conn) {

  private def next[R](shape: RowShape[R]) = {
    new AggregateOptions(
      model,
      SelectCollector(
        db,
        Prefix.forModel,
        shape,
        Array(
          SelectSec(shape.cols),
          FromSec(ModelTable(model))
        )
      )
    )
  }

  def colsRead[R](pick: M => RowReader[R]) = {
    next(
      pick(model)
    )
  }

  def colsAs[R](pick: M => Seq[TypeCol[_]])(implicit typeReader: TypeReader[R]) = {
    next(
      new RowShapeType(pick(model), typeReader)
    )
  }

  def colsAsSeq(pick: M => Seq[TypeCol[_]]) = {
    next(
      new RowShapeSeq(pick(model))
    )
  }

  def cols1[R](pick: M => TypeCol[R]) = {
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

f = open('../scala/kuzminki/aggregate/Aggregate.scala', 'w')
f.write(content)
f.close()