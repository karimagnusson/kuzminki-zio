package kuzminki.select

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

  def cacheWhere2[P1, P2](
        pick: M => Tuple2[CachePart[P1], CachePart[P2]]
      ) = {
    coll.cacheWhere(new PartShape2(pick(model)))
  }

  def cacheWhereWithOffset2[P1, P2](
        pick: M => Tuple2[CachePart[P1], CachePart[P2]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape2(pick(model)))
  }

  def cacheHaving2[P1, P2](
        pick: M => Tuple2[CachePart[P1], CachePart[P2]]
      ) = {
    coll.cacheHaving(new PartShape2(pick(model)))
  }

  def cacheHavingWithOffset2[P1, P2](
        pick: M => Tuple2[CachePart[P1], CachePart[P2]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape2(pick(model)))
  }

  def cacheWhere3[P1, P2, P3](
        pick: M => Tuple3[CachePart[P1], CachePart[P2], CachePart[P3]]
      ) = {
    coll.cacheWhere(new PartShape3(pick(model)))
  }

  def cacheWhereWithOffset3[P1, P2, P3](
        pick: M => Tuple3[CachePart[P1], CachePart[P2], CachePart[P3]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape3(pick(model)))
  }

  def cacheHaving3[P1, P2, P3](
        pick: M => Tuple3[CachePart[P1], CachePart[P2], CachePart[P3]]
      ) = {
    coll.cacheHaving(new PartShape3(pick(model)))
  }

  def cacheHavingWithOffset3[P1, P2, P3](
        pick: M => Tuple3[CachePart[P1], CachePart[P2], CachePart[P3]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape3(pick(model)))
  }

  def cacheWhere4[P1, P2, P3, P4](
        pick: M => Tuple4[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4]]
      ) = {
    coll.cacheWhere(new PartShape4(pick(model)))
  }

  def cacheWhereWithOffset4[P1, P2, P3, P4](
        pick: M => Tuple4[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape4(pick(model)))
  }

  def cacheHaving4[P1, P2, P3, P4](
        pick: M => Tuple4[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4]]
      ) = {
    coll.cacheHaving(new PartShape4(pick(model)))
  }

  def cacheHavingWithOffset4[P1, P2, P3, P4](
        pick: M => Tuple4[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape4(pick(model)))
  }

  def cacheWhere5[P1, P2, P3, P4, P5](
        pick: M => Tuple5[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5]]
      ) = {
    coll.cacheWhere(new PartShape5(pick(model)))
  }

  def cacheWhereWithOffset5[P1, P2, P3, P4, P5](
        pick: M => Tuple5[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape5(pick(model)))
  }

  def cacheHaving5[P1, P2, P3, P4, P5](
        pick: M => Tuple5[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5]]
      ) = {
    coll.cacheHaving(new PartShape5(pick(model)))
  }

  def cacheHavingWithOffset5[P1, P2, P3, P4, P5](
        pick: M => Tuple5[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape5(pick(model)))
  }

  def cacheWhere6[P1, P2, P3, P4, P5, P6](
        pick: M => Tuple6[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6]]
      ) = {
    coll.cacheWhere(new PartShape6(pick(model)))
  }

  def cacheWhereWithOffset6[P1, P2, P3, P4, P5, P6](
        pick: M => Tuple6[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape6(pick(model)))
  }

  def cacheHaving6[P1, P2, P3, P4, P5, P6](
        pick: M => Tuple6[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6]]
      ) = {
    coll.cacheHaving(new PartShape6(pick(model)))
  }

  def cacheHavingWithOffset6[P1, P2, P3, P4, P5, P6](
        pick: M => Tuple6[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape6(pick(model)))
  }

  def cacheWhere7[P1, P2, P3, P4, P5, P6, P7](
        pick: M => Tuple7[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7]]
      ) = {
    coll.cacheWhere(new PartShape7(pick(model)))
  }

  def cacheWhereWithOffset7[P1, P2, P3, P4, P5, P6, P7](
        pick: M => Tuple7[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape7(pick(model)))
  }

  def cacheHaving7[P1, P2, P3, P4, P5, P6, P7](
        pick: M => Tuple7[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7]]
      ) = {
    coll.cacheHaving(new PartShape7(pick(model)))
  }

  def cacheHavingWithOffset7[P1, P2, P3, P4, P5, P6, P7](
        pick: M => Tuple7[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape7(pick(model)))
  }

  def cacheWhere8[P1, P2, P3, P4, P5, P6, P7, P8](
        pick: M => Tuple8[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8]]
      ) = {
    coll.cacheWhere(new PartShape8(pick(model)))
  }

  def cacheWhereWithOffset8[P1, P2, P3, P4, P5, P6, P7, P8](
        pick: M => Tuple8[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape8(pick(model)))
  }

  def cacheHaving8[P1, P2, P3, P4, P5, P6, P7, P8](
        pick: M => Tuple8[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8]]
      ) = {
    coll.cacheHaving(new PartShape8(pick(model)))
  }

  def cacheHavingWithOffset8[P1, P2, P3, P4, P5, P6, P7, P8](
        pick: M => Tuple8[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape8(pick(model)))
  }

  def cacheWhere9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
        pick: M => Tuple9[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9]]
      ) = {
    coll.cacheWhere(new PartShape9(pick(model)))
  }

  def cacheWhereWithOffset9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
        pick: M => Tuple9[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape9(pick(model)))
  }

  def cacheHaving9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
        pick: M => Tuple9[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9]]
      ) = {
    coll.cacheHaving(new PartShape9(pick(model)))
  }

  def cacheHavingWithOffset9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
        pick: M => Tuple9[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape9(pick(model)))
  }

  def cacheWhere10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
        pick: M => Tuple10[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10]]
      ) = {
    coll.cacheWhere(new PartShape10(pick(model)))
  }

  def cacheWhereWithOffset10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
        pick: M => Tuple10[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape10(pick(model)))
  }

  def cacheHaving10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
        pick: M => Tuple10[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10]]
      ) = {
    coll.cacheHaving(new PartShape10(pick(model)))
  }

  def cacheHavingWithOffset10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
        pick: M => Tuple10[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape10(pick(model)))
  }

  def cacheWhere11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
        pick: M => Tuple11[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11]]
      ) = {
    coll.cacheWhere(new PartShape11(pick(model)))
  }

  def cacheWhereWithOffset11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
        pick: M => Tuple11[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape11(pick(model)))
  }

  def cacheHaving11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
        pick: M => Tuple11[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11]]
      ) = {
    coll.cacheHaving(new PartShape11(pick(model)))
  }

  def cacheHavingWithOffset11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
        pick: M => Tuple11[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape11(pick(model)))
  }

  def cacheWhere12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
        pick: M => Tuple12[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12]]
      ) = {
    coll.cacheWhere(new PartShape12(pick(model)))
  }

  def cacheWhereWithOffset12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
        pick: M => Tuple12[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape12(pick(model)))
  }

  def cacheHaving12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
        pick: M => Tuple12[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12]]
      ) = {
    coll.cacheHaving(new PartShape12(pick(model)))
  }

  def cacheHavingWithOffset12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
        pick: M => Tuple12[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape12(pick(model)))
  }

  def cacheWhere13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
        pick: M => Tuple13[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13]]
      ) = {
    coll.cacheWhere(new PartShape13(pick(model)))
  }

  def cacheWhereWithOffset13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
        pick: M => Tuple13[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape13(pick(model)))
  }

  def cacheHaving13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
        pick: M => Tuple13[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13]]
      ) = {
    coll.cacheHaving(new PartShape13(pick(model)))
  }

  def cacheHavingWithOffset13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
        pick: M => Tuple13[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape13(pick(model)))
  }

  def cacheWhere14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
        pick: M => Tuple14[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14]]
      ) = {
    coll.cacheWhere(new PartShape14(pick(model)))
  }

  def cacheWhereWithOffset14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
        pick: M => Tuple14[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape14(pick(model)))
  }

  def cacheHaving14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
        pick: M => Tuple14[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14]]
      ) = {
    coll.cacheHaving(new PartShape14(pick(model)))
  }

  def cacheHavingWithOffset14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
        pick: M => Tuple14[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape14(pick(model)))
  }

  def cacheWhere15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
        pick: M => Tuple15[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15]]
      ) = {
    coll.cacheWhere(new PartShape15(pick(model)))
  }

  def cacheWhereWithOffset15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
        pick: M => Tuple15[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape15(pick(model)))
  }

  def cacheHaving15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
        pick: M => Tuple15[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15]]
      ) = {
    coll.cacheHaving(new PartShape15(pick(model)))
  }

  def cacheHavingWithOffset15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
        pick: M => Tuple15[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape15(pick(model)))
  }

  def cacheWhere16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
        pick: M => Tuple16[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16]]
      ) = {
    coll.cacheWhere(new PartShape16(pick(model)))
  }

  def cacheWhereWithOffset16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
        pick: M => Tuple16[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape16(pick(model)))
  }

  def cacheHaving16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
        pick: M => Tuple16[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16]]
      ) = {
    coll.cacheHaving(new PartShape16(pick(model)))
  }

  def cacheHavingWithOffset16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
        pick: M => Tuple16[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape16(pick(model)))
  }

  def cacheWhere17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
        pick: M => Tuple17[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17]]
      ) = {
    coll.cacheWhere(new PartShape17(pick(model)))
  }

  def cacheWhereWithOffset17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
        pick: M => Tuple17[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape17(pick(model)))
  }

  def cacheHaving17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
        pick: M => Tuple17[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17]]
      ) = {
    coll.cacheHaving(new PartShape17(pick(model)))
  }

  def cacheHavingWithOffset17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
        pick: M => Tuple17[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape17(pick(model)))
  }

  def cacheWhere18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
        pick: M => Tuple18[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18]]
      ) = {
    coll.cacheWhere(new PartShape18(pick(model)))
  }

  def cacheWhereWithOffset18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
        pick: M => Tuple18[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape18(pick(model)))
  }

  def cacheHaving18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
        pick: M => Tuple18[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18]]
      ) = {
    coll.cacheHaving(new PartShape18(pick(model)))
  }

  def cacheHavingWithOffset18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
        pick: M => Tuple18[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape18(pick(model)))
  }

  def cacheWhere19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
        pick: M => Tuple19[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19]]
      ) = {
    coll.cacheWhere(new PartShape19(pick(model)))
  }

  def cacheWhereWithOffset19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
        pick: M => Tuple19[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape19(pick(model)))
  }

  def cacheHaving19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
        pick: M => Tuple19[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19]]
      ) = {
    coll.cacheHaving(new PartShape19(pick(model)))
  }

  def cacheHavingWithOffset19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
        pick: M => Tuple19[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape19(pick(model)))
  }

  def cacheWhere20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
        pick: M => Tuple20[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20]]
      ) = {
    coll.cacheWhere(new PartShape20(pick(model)))
  }

  def cacheWhereWithOffset20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
        pick: M => Tuple20[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape20(pick(model)))
  }

  def cacheHaving20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
        pick: M => Tuple20[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20]]
      ) = {
    coll.cacheHaving(new PartShape20(pick(model)))
  }

  def cacheHavingWithOffset20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
        pick: M => Tuple20[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape20(pick(model)))
  }

  def cacheWhere21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
        pick: M => Tuple21[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21]]
      ) = {
    coll.cacheWhere(new PartShape21(pick(model)))
  }

  def cacheWhereWithOffset21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
        pick: M => Tuple21[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape21(pick(model)))
  }

  def cacheHaving21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
        pick: M => Tuple21[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21]]
      ) = {
    coll.cacheHaving(new PartShape21(pick(model)))
  }

  def cacheHavingWithOffset21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
        pick: M => Tuple21[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape21(pick(model)))
  }

  def cacheWhere22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
        pick: M => Tuple22[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21], CachePart[P22]]
      ) = {
    coll.cacheWhere(new PartShape22(pick(model)))
  }

  def cacheWhereWithOffset22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
        pick: M => Tuple22[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21], CachePart[P22]]
      ) = {
    coll.cacheWhereWithOffset(new PartShape22(pick(model)))
  }

  def cacheHaving22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
        pick: M => Tuple22[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21], CachePart[P22]]
      ) = {
    coll.cacheHaving(new PartShape22(pick(model)))
  }

  def cacheHavingWithOffset22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
        pick: M => Tuple22[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21], CachePart[P22]]
      ) = {
    coll.cacheHavingWithOffset(new PartShape22(pick(model)))
  }
}
