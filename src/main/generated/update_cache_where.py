#! /usr/bin/env python


func = """
  def cacheWhere%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    next(new PartShape%s(pick(model)))
  }"""


template = """package kuzminki.update

import kuzminki.api.Model
import kuzminki.render.SectionCollector
import kuzminki.section.operation.UpdateCacheWhereSec
import kuzminki.shape._


class UpdateCacheWhere[M, A](
      model: M,
      coll: SectionCollector,
      changes: PartShape[A]
    ) {

  private def next[B](filters: PartShape[B]) = {
    new StoredUpdate(
      coll.add(UpdateCacheWhereSec(filters.parts)).render,
      changes.conv,
      filters.conv
    )
  }

  def cacheWhere1[P](
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

f = open('../scala/update/UpdateCacheWhere.scala', 'w')
f.write(content)
f.close()