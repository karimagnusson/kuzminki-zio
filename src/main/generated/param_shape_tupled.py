#! /usr/bin/env python


func = """
class ParamShape%s[%s](
      shape: Tuple%s[%s]
    ) extends ParamShape[Tuple%s[%s]] {

  def size = %s

  def cols = {
    shape match {
      case (%s) =>
        Seq(%s)
    }
  }

  def conv = {
    shape match {
      case (%s) =>
        new ParamConv%s(%s)
    }
  }
}"""


template = """package kuzminki.shape

import kuzminki.column.TypeCol
import kuzminki.shape._

%s
"""


parts = []

for num in range(2, 23):
    types = ', '.join(['P%d' % i for i in range(1, num + 1)])
    col_types = ', '.join(['TypeCol[P%d]' % i for i in range(1, num + 1)])
    col = ', '.join(['col%d' % i for i in range(1, num + 1)])
    conv = ', '.join(['col%d.conv' % i for i in range(1, num + 1)])
    
    part = func % (
      str(num),
      types,
      str(num),
      col_types,
      str(num),
      types,
      str(num),
      col,
      col,
      col,
      str(num),
      conv,
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/kuzminki/shape/paramshape/ParamShapeTupled.scala', 'w')
f.write(content)
f.close()

