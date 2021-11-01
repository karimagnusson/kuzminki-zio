#! /usr/bin/env python


func = """
  def cols%s[%s](pick: Join[A, B] => Tuple%s[%s]) = {
    next(
      new RowShape%s(pick(join))
    )
  }"""



template = """package kuzminki.model


class AggregateJoin[A <: Model, B <: Model](join: Join[A, B], db: Conn) {

  private def next[R](shape: RowShape[R]) = {
    new AggregateJoinOn(
      join,
      SelectCollector(
        db,
        Prefix.forJoin(join),
        shape,
        Array(
          SelectSec(shape.cols),
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

  def colsAs[R](pick: Join[A, B] => Seq[TypeCol[_]])(implicit typeReader: TypeReader[R]) = {
    next(
      new RowShapeType(pick(join), typeReader)
    )
  }

  def colsAsSeq(pick: Join[A, B] => Seq[TypeCol[_]]) = {
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

f = open('../scala/kuzminki/aggregate/AggregateJoin.scala', 'w')
f.write(content)
f.close()