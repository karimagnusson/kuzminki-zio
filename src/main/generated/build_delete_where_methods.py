#! /usr/bin/env python


func = """
  def buildWhere%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    next(new PartShape%s(pick(model)))
  }"""


template = """package kuzminki.delete

import kuzminki.api.Model
import kuzminki.section.operation.UpdateCacheWhereSec
import kuzminki.shape._


abstract class BuildDeleteWhereMethods[M](model: M, coll: DeleteCollector) {

  private def next[A](paramConv: PartShape[A]) = {
    new StoredDeleteCondition(
      coll.add(UpdateCacheWhereSec(paramConv.parts)).render,
      coll.args,
      paramConv.conv
    )
  }

  def buildWhere1[P](
        pick: M => CachePart[P]
      ) = {
    next(new PartShapeSingle(pick(model)))
  }
%s
}
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
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/delete/BuildDeleteWhereMethods.scala', 'w')
f.write(content)
f.close()