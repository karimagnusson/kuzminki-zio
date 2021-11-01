#! /usr/bin/env python


func = """
class PartShape%s[%s](
      shape: Tuple%s[%s]
    ) extends PartShape[Tuple%s[%s]] {

  def parts = {
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

%s
"""


parts = []

for num in range(2, 23):
    types = ', '.join(['P%d' % i for i in range(1, num + 1)])
    col_types = ', '.join(['CachePart[P%d]' % i for i in range(1, num + 1)])
    conds = ', '.join(['cond%d' % i for i in range(1, num + 1)])
    conv = ', '.join(['cond%d.conv' % i for i in range(1, num + 1)])
    
    part = func % (
      str(num),
      types,
      str(num),
      col_types,
      str(num),
      types,
      conds,
      conds,
      conds,
      str(num),
      conv,
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/kuzminki/shape/partshape/PartShapeTupled.scala', 'w')
f.write(content)
f.close()

