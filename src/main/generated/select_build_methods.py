#! /usr/bin/env python


func = """
  def buildWhere%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.buildWhere(new PartShape%s(pick(model)))
  }

  def buildWhereWithOffset%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.buildWhereWithOffset(new PartShape%s(pick(model)))
  }

  def buildHaving%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.buildHaving(new PartShape%s(pick(model)))
  }

  def buildHavingWithOffset%s[%s](
        pick: M => Tuple%s[%s]
      ) = {
    coll.buildHavingWithOffset(new PartShape%s(pick(model)))
  }"""


template = """package kuzminki.select

import kuzminki.shape._


abstract class SelectBuildMethods[M, R](model: M, coll: SelectCollector[R]) {

  def buildWhere1[P](pick: M => CachePart[P]) = {
    coll.buildWhere(new PartShapeSingle(pick(model)))
  }

  def buildWhereWithOffset1[P](pick: M => CachePart[P]) = {
    coll.buildWhereWithOffset(new PartShapeSingle(pick(model)))
  }

  def buildHaving1[P](pick: M => CachePart[P]) = {
    coll.buildHaving(new PartShapeSingle(pick(model)))
  }

  def buildHavingWithOffset1[P](pick: M => CachePart[P]) = {
    coll.buildHavingWithOffset(new PartShapeSingle(pick(model)))
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

f = open('../scala/select/SelectBuildMethods.scala', 'w')
f.write(content)
f.close()




















