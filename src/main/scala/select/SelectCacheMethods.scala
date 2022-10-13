package kuzminki.select

import kuzminki.shape._
import kuzminki.filter.types.CacheFilter


abstract class SelectCacheMethods[M, R](model: M, coll: SelectCollector[R]) {

  def pickWhere1[P](pick: M => CacheFilter[P]) =
    new SelectCacheWhere(coll, new PartShapeSingle(pick(model)))

  def pickHaving1[P](pick: M => CacheFilter[P]) =
    new SelectCacheHaving(coll, new PartShapeSingle(pick(model)))

  def pickWhere2[P1, P2](
    pick: M => Tuple2[CacheFilter[P1], CacheFilter[P2]]
  ) = new SelectCacheWhere(coll, new PartShape2(pick(model)))

  def pickHaving2[P1, P2](
    pick: M => Tuple2[CacheFilter[P1], CacheFilter[P2]]
  ) = new SelectCacheHaving(coll, new PartShape2(pick(model)))

  def pickWhere3[P1, P2, P3](
    pick: M => Tuple3[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3]]
  ) = new SelectCacheWhere(coll, new PartShape3(pick(model)))

  def pickHaving3[P1, P2, P3](
    pick: M => Tuple3[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3]]
  ) = new SelectCacheHaving(coll, new PartShape3(pick(model)))

  def pickWhere4[P1, P2, P3, P4](
    pick: M => Tuple4[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4]]
  ) = new SelectCacheWhere(coll, new PartShape4(pick(model)))

  def pickHaving4[P1, P2, P3, P4](
    pick: M => Tuple4[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4]]
  ) = new SelectCacheHaving(coll, new PartShape4(pick(model)))

  def pickWhere5[P1, P2, P3, P4, P5](
    pick: M => Tuple5[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5]]
  ) = new SelectCacheWhere(coll, new PartShape5(pick(model)))

  def pickHaving5[P1, P2, P3, P4, P5](
    pick: M => Tuple5[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5]]
  ) = new SelectCacheHaving(coll, new PartShape5(pick(model)))

  def pickWhere6[P1, P2, P3, P4, P5, P6](
    pick: M => Tuple6[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6]]
  ) = new SelectCacheWhere(coll, new PartShape6(pick(model)))

  def pickHaving6[P1, P2, P3, P4, P5, P6](
    pick: M => Tuple6[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6]]
  ) = new SelectCacheHaving(coll, new PartShape6(pick(model)))

  def pickWhere7[P1, P2, P3, P4, P5, P6, P7](
    pick: M => Tuple7[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7]]
  ) = new SelectCacheWhere(coll, new PartShape7(pick(model)))

  def pickHaving7[P1, P2, P3, P4, P5, P6, P7](
    pick: M => Tuple7[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7]]
  ) = new SelectCacheHaving(coll, new PartShape7(pick(model)))

  def pickWhere8[P1, P2, P3, P4, P5, P6, P7, P8](
    pick: M => Tuple8[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8]]
  ) = new SelectCacheWhere(coll, new PartShape8(pick(model)))

  def pickHaving8[P1, P2, P3, P4, P5, P6, P7, P8](
    pick: M => Tuple8[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8]]
  ) = new SelectCacheHaving(coll, new PartShape8(pick(model)))

  def pickWhere9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    pick: M => Tuple9[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9]]
  ) = new SelectCacheWhere(coll, new PartShape9(pick(model)))

  def pickHaving9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    pick: M => Tuple9[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9]]
  ) = new SelectCacheHaving(coll, new PartShape9(pick(model)))

  def pickWhere10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    pick: M => Tuple10[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10]]
  ) = new SelectCacheWhere(coll, new PartShape10(pick(model)))

  def pickHaving10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    pick: M => Tuple10[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10]]
  ) = new SelectCacheHaving(coll, new PartShape10(pick(model)))

  def pickWhere11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    pick: M => Tuple11[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11]]
  ) = new SelectCacheWhere(coll, new PartShape11(pick(model)))

  def pickHaving11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    pick: M => Tuple11[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11]]
  ) = new SelectCacheHaving(coll, new PartShape11(pick(model)))

  def pickWhere12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    pick: M => Tuple12[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12]]
  ) = new SelectCacheWhere(coll, new PartShape12(pick(model)))

  def pickHaving12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    pick: M => Tuple12[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12]]
  ) = new SelectCacheHaving(coll, new PartShape12(pick(model)))

  def pickWhere13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    pick: M => Tuple13[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13]]
  ) = new SelectCacheWhere(coll, new PartShape13(pick(model)))

  def pickHaving13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    pick: M => Tuple13[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13]]
  ) = new SelectCacheHaving(coll, new PartShape13(pick(model)))

  def pickWhere14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    pick: M => Tuple14[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14]]
  ) = new SelectCacheWhere(coll, new PartShape14(pick(model)))

  def pickHaving14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    pick: M => Tuple14[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14]]
  ) = new SelectCacheHaving(coll, new PartShape14(pick(model)))

  def pickWhere15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    pick: M => Tuple15[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15]]
  ) = new SelectCacheWhere(coll, new PartShape15(pick(model)))

  def pickHaving15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    pick: M => Tuple15[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15]]
  ) = new SelectCacheHaving(coll, new PartShape15(pick(model)))

  def pickWhere16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    pick: M => Tuple16[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16]]
  ) = new SelectCacheWhere(coll, new PartShape16(pick(model)))

  def pickHaving16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    pick: M => Tuple16[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16]]
  ) = new SelectCacheHaving(coll, new PartShape16(pick(model)))

  def pickWhere17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    pick: M => Tuple17[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17]]
  ) = new SelectCacheWhere(coll, new PartShape17(pick(model)))

  def pickHaving17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    pick: M => Tuple17[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17]]
  ) = new SelectCacheHaving(coll, new PartShape17(pick(model)))

  def pickWhere18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    pick: M => Tuple18[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18]]
  ) = new SelectCacheWhere(coll, new PartShape18(pick(model)))

  def pickHaving18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    pick: M => Tuple18[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18]]
  ) = new SelectCacheHaving(coll, new PartShape18(pick(model)))

  def pickWhere19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    pick: M => Tuple19[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19]]
  ) = new SelectCacheWhere(coll, new PartShape19(pick(model)))

  def pickHaving19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    pick: M => Tuple19[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19]]
  ) = new SelectCacheHaving(coll, new PartShape19(pick(model)))

  def pickWhere20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    pick: M => Tuple20[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20]]
  ) = new SelectCacheWhere(coll, new PartShape20(pick(model)))

  def pickHaving20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    pick: M => Tuple20[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20]]
  ) = new SelectCacheHaving(coll, new PartShape20(pick(model)))

  def pickWhere21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    pick: M => Tuple21[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21]]
  ) = new SelectCacheWhere(coll, new PartShape21(pick(model)))

  def pickHaving21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    pick: M => Tuple21[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21]]
  ) = new SelectCacheHaving(coll, new PartShape21(pick(model)))

  def pickWhere22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    pick: M => Tuple22[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21], CacheFilter[P22]]
  ) = new SelectCacheWhere(coll, new PartShape22(pick(model)))

  def pickHaving22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    pick: M => Tuple22[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21], CacheFilter[P22]]
  ) = new SelectCacheHaving(coll, new PartShape22(pick(model)))
}
