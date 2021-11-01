#! /usr/bin/env python


func = """
class RowShape%s[%s](
      shape: Tuple%s[%s]
    ) extends RowShape[Tuple%s[%s]] {

  val cols = {
    shape match {
      case (%s) => Seq(%s)
    }
  }

  def conv = {
    shape match {
      case (%s) => 
        new RowConv%s(Tuple%s(%s))
    }
  }

}"""



template = """package kuzminki.shape

import io.rdbc.sapi.Row
import kuzminki.column.TypeCol

%s
"""


parts = []

for num in range(2, 23):
    types = ', '.join(['R%d' % i for i in range(1, num + 1)])
    col_types = ', '.join(['TypeCol[R%d]' % i for i in range(1, num + 1)])
    col = ', '.join(['col%d' % i for i in range(1, num + 1)])
    col_get = ', '.join(['col%d.conv' % i for i in range(1, num + 1)])
    
    part = func % (
      str(num),
      types,
      str(num),
      col_types,
      str(num),
      types,
      col,
      col,
      col,
      str(num),
      str(num),
      col_get,
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/kuzminki/shape/rowshape/RowShapeTupled.scala', 'w')
f.write(content)
f.close()



