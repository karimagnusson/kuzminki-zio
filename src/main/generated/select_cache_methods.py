#! /usr/bin/env python


func = """
  def cacheWhere%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.cacheWhere(new PartShape%s(pick(model)))
  }

  def cacheWhereWithOffset%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.cacheWhereWithOffset(new PartShape%s(pick(model)))
  }

  def cacheHaving%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.cacheHaving(new PartShape%s(pick(model)))
  }

  def cacheHavingWithOffset%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.cacheHavingWithOffset(new PartShape%s(pick(model)))
  }"""


template = """package kuzminki.select

import kuzminki.shape._


abstract class SelectCacheMethods[M, R](model: M, coll: SelectCollector[R]) {

  def cacheWhere1[P](pick: M => CachePart[P]) = {
    coll.cacheWhere(new PartShapeSingle(pick(model)))
  }

  def cacheWhereWithOffset1[P](pick: M => CachePart[P]) = {
    coll.cacheWhereWithOffset(new PartShapeSingle(pick(model)))
  }

  def cacheHaving1[P](pick: M => CachePart[P]) = {
    coll.cacheHaving(new PartShapeSingle(pick(model)))
  }

  def cacheHavingWithOffset1[P](pick: M => CachePart[P]) = {
    coll.cacheHavingWithOffset(new PartShapeSingle(pick(model)))
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
      str(num),
      types,
      str(num),
      col_types,
      str(num),
      str(num),
      types,
      str(num),
      col_types,
      str(num),
      str(num),
      types,
      str(num),
      col_types,
      str(num),
    )
    
    parts.append(part)

content = template % "\n".join(parts)

f = open('../scala/select/SelectCacheMethods.scala', 'w')
f.write(content)
f.close()




















