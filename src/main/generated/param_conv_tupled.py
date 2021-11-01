#! /usr/bin/env python


func = """
class ParamConv%s[%s](
      shape: Tuple%s[%s]
    ) extends ParamConv[Tuple%s[%s]] {

  def fromShape(params: Tuple%s[%s]) = {
    params match {
      case (%s) =>
        Vector(%s)
    }
  }
}"""


template = """package kuzminki.shape

import kuzminki.conv.ValConv

%s
"""


parts = []

for num in range(2, 23):
    types = ', '.join(['P%d' % i for i in range(1, num + 1)])
    col_types = ', '.join(['ValConv[P%d]' % i for i in range(1, num + 1)])
    col = ', '.join(['par%d' % i for i in range(1, num + 1)])
    arg = ', '.join(['arg%d' % i for i in range(1, num + 1)])
    
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
      col
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/kuzminki/shape/paramconv/ParamConvTupled.scala', 'w')
f.write(content)
f.close()

