#! /usr/bin/env python


func = """
class RowConv%s[%s](
      shape: Tuple%s[%s]
    ) extends RowConv[Tuple%s[%s]] {

  def fromRow(rs: ResultSet): Tuple%s[%s] = {
    shape match {
      case (%s) =>
        (%s)
    }
  }
}"""



template = """package kuzminki.shape

import java.sql.ResultSet
import kuzminki.conv.ValConv

%s
"""


parts = []

for num in range(2, 23):
    types = ', '.join(['R%d' % i for i in range(1, num + 1)])
    col_types = ', '.join(['ValConv[R%d]' % i for i in range(1, num + 1)])
    col = ', '.join(['col%d' % i for i in range(1, num + 1)])
    col_get = ', '.join(['col%d.get(rs, %d)' % (i, i) for i in range(1, num + 1)])
    
    part = func % (
      str(num),
      types,
      str(num),
      col_types,
      str(num),
      types,
      str(num),
      types,
      col,
      col_get,
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/shape/rowconv/RowConvTupled.scala', 'w')
f.write(content)
f.close()



