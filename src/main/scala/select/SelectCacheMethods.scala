package kuzminki.select

import kuzminki.shape._
import kuzminki.filter.types.CacheFilter


abstract class SelectCacheMethods[M, R](model: M, coll: SelectCollector[R]) {

  def cacheWhere1[P](pick: M => CacheFilter[P]) = {
    coll.cacheWhere(new PartShapeSingle(pick(model)))
  }

  def cacheHaving1[P](pick: M => CacheFilter[P]) = {
    coll.cacheHaving(new PartShapeSingle(pick(model)))
  }

  def cacheWhere2[P1, P2](
    pick: M => Tuple2[CacheFilter[P1], CacheFilter[P2]]
  ) = {
    coll.cacheWhere(new PartShape2(pick(model)))
  }

  def cacheHaving2[P1, P2](
    pick: M => Tuple2[CacheFilter[P1], CacheFilter[P2]]
  ) = {
    coll.cacheHaving(new PartShape2(pick(model)))
  }

  def cacheWhere3[P1, P2, P3](
    pick: M => Tuple3[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3]]
  ) = {
    coll.cacheWhere(new PartShape3(pick(model)))
  }

  def cacheHaving3[P1, P2, P3](
    pick: M => Tuple3[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3]]
  ) = {
    coll.cacheHaving(new PartShape3(pick(model)))
  }

  def cacheWhere4[P1, P2, P3, P4](
    pick: M => Tuple4[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4]]
  ) = {
    coll.cacheWhere(new PartShape4(pick(model)))
  }

  def cacheHaving4[P1, P2, P3, P4](
    pick: M => Tuple4[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4]]
  ) = {
    coll.cacheHaving(new PartShape4(pick(model)))
  }

  def cacheWhere5[P1, P2, P3, P4, P5](
    pick: M => Tuple5[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5]]
  ) = {
    coll.cacheWhere(new PartShape5(pick(model)))
  }

  def cacheHaving5[P1, P2, P3, P4, P5](
    pick: M => Tuple5[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5]]
  ) = {
    coll.cacheHaving(new PartShape5(pick(model)))
  }

  def cacheWhere6[P1, P2, P3, P4, P5, P6](
    pick: M => Tuple6[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6]]
  ) = {
    coll.cacheWhere(new PartShape6(pick(model)))
  }

  def cacheHaving6[P1, P2, P3, P4, P5, P6](
    pick: M => Tuple6[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6]]
  ) = {
    coll.cacheHaving(new PartShape6(pick(model)))
  }

  def cacheWhere7[P1, P2, P3, P4, P5, P6, P7](
    pick: M => Tuple7[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7]]
  ) = {
    coll.cacheWhere(new PartShape7(pick(model)))
  }

  def cacheHaving7[P1, P2, P3, P4, P5, P6, P7](
    pick: M => Tuple7[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7]]
  ) = {
    coll.cacheHaving(new PartShape7(pick(model)))
  }

  def cacheWhere8[P1, P2, P3, P4, P5, P6, P7, P8](
    pick: M => Tuple8[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8]]
  ) = {
    coll.cacheWhere(new PartShape8(pick(model)))
  }

  def cacheHaving8[P1, P2, P3, P4, P5, P6, P7, P8](
    pick: M => Tuple8[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8]]
  ) = {
    coll.cacheHaving(new PartShape8(pick(model)))
  }

  def cacheWhere9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    pick: M => Tuple9[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9]]
  ) = {
    coll.cacheWhere(new PartShape9(pick(model)))
  }

  def cacheHaving9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    pick: M => Tuple9[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9]]
  ) = {
    coll.cacheHaving(new PartShape9(pick(model)))
  }

  def cacheWhere10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    pick: M => Tuple10[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10]]
  ) = {
    coll.cacheWhere(new PartShape10(pick(model)))
  }

  def cacheHaving10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    pick: M => Tuple10[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10]]
  ) = {
    coll.cacheHaving(new PartShape10(pick(model)))
  }

  def cacheWhere11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    pick: M => Tuple11[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11]]
  ) = {
    coll.cacheWhere(new PartShape11(pick(model)))
  }

  def cacheHaving11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    pick: M => Tuple11[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11]]
  ) = {
    coll.cacheHaving(new PartShape11(pick(model)))
  }

  def cacheWhere12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    pick: M => Tuple12[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12]]
  ) = {
    coll.cacheWhere(new PartShape12(pick(model)))
  }

  def cacheHaving12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    pick: M => Tuple12[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12]]
  ) = {
    coll.cacheHaving(new PartShape12(pick(model)))
  }

  def cacheWhere13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    pick: M => Tuple13[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13]]
  ) = {
    coll.cacheWhere(new PartShape13(pick(model)))
  }

  def cacheHaving13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    pick: M => Tuple13[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13]]
  ) = {
    coll.cacheHaving(new PartShape13(pick(model)))
  }

  def cacheWhere14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    pick: M => Tuple14[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14]]
  ) = {
    coll.cacheWhere(new PartShape14(pick(model)))
  }

  def cacheHaving14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    pick: M => Tuple14[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14]]
  ) = {
    coll.cacheHaving(new PartShape14(pick(model)))
  }

  def cacheWhere15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    pick: M => Tuple15[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15]]
  ) = {
    coll.cacheWhere(new PartShape15(pick(model)))
  }

  def cacheHaving15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    pick: M => Tuple15[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15]]
  ) = {
    coll.cacheHaving(new PartShape15(pick(model)))
  }

  def cacheWhere16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    pick: M => Tuple16[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16]]
  ) = {
    coll.cacheWhere(new PartShape16(pick(model)))
  }

  def cacheHaving16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    pick: M => Tuple16[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16]]
  ) = {
    coll.cacheHaving(new PartShape16(pick(model)))
  }

  def cacheWhere17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    pick: M => Tuple17[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17]]
  ) = {
    coll.cacheWhere(new PartShape17(pick(model)))
  }

  def cacheHaving17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    pick: M => Tuple17[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17]]
  ) = {
    coll.cacheHaving(new PartShape17(pick(model)))
  }

  def cacheWhere18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    pick: M => Tuple18[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18]]
  ) = {
    coll.cacheWhere(new PartShape18(pick(model)))
  }

  def cacheHaving18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    pick: M => Tuple18[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18]]
  ) = {
    coll.cacheHaving(new PartShape18(pick(model)))
  }

  def cacheWhere19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    pick: M => Tuple19[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19]]
  ) = {
    coll.cacheWhere(new PartShape19(pick(model)))
  }

  def cacheHaving19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    pick: M => Tuple19[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19]]
  ) = {
    coll.cacheHaving(new PartShape19(pick(model)))
  }

  def cacheWhere20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    pick: M => Tuple20[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20]]
  ) = {
    coll.cacheWhere(new PartShape20(pick(model)))
  }

  def cacheHaving20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    pick: M => Tuple20[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20]]
  ) = {
    coll.cacheHaving(new PartShape20(pick(model)))
  }

  def cacheWhere21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    pick: M => Tuple21[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21]]
  ) = {
    coll.cacheWhere(new PartShape21(pick(model)))
  }

  def cacheHaving21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    pick: M => Tuple21[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21]]
  ) = {
    coll.cacheHaving(new PartShape21(pick(model)))
  }

  def cacheWhere22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    pick: M => Tuple22[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21], CacheFilter[P22]]
  ) = {
    coll.cacheWhere(new PartShape22(pick(model)))
  }

  def cacheHaving22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    pick: M => Tuple22[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21], CacheFilter[P22]]
  ) = {
    coll.cacheHaving(new PartShape22(pick(model)))
  }
}
