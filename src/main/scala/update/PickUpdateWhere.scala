package kuzminki.update

import kuzminki.api.Model
import kuzminki.render.SectionCollector
import kuzminki.section.operation.UpdateCacheWhereSec
import kuzminki.filter.types.CacheFilter
import kuzminki.shape._


class PickUpdateWhere[M, A](
  model: M,
  coll: SectionCollector,
  changes: PartShape[A]
) {

  private def next[B](filters: PartShape[B]) = {
    new RenderStoredUpdate(
      model,
      coll.add(UpdateCacheWhereSec(filters.parts)),
      changes.conv,
      filters.conv
    )
  }

  def pickWhere1[P](
    pick: M => CacheFilter[P]
  ) = {
    next(new PartShapeSingle(pick(model)))
  }

  def pickWhere2[P1, P2](
    pick: M => Tuple2[CacheFilter[P1], CacheFilter[P2]]
  ) = {
    next(new PartShape2(pick(model)))
  }

  def pickWhere3[P1, P2, P3](
    pick: M => Tuple3[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3]]
  ) = {
    next(new PartShape3(pick(model)))
  }

  def pickWhere4[P1, P2, P3, P4](
    pick: M => Tuple4[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4]]
  ) = {
    next(new PartShape4(pick(model)))
  }

  def pickWhere5[P1, P2, P3, P4, P5](
    pick: M => Tuple5[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5]]
  ) = {
    next(new PartShape5(pick(model)))
  }

  def pickWhere6[P1, P2, P3, P4, P5, P6](
    pick: M => Tuple6[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6]]
  ) = {
    next(new PartShape6(pick(model)))
  }

  def pickWhere7[P1, P2, P3, P4, P5, P6, P7](
    pick: M => Tuple7[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7]]
  ) = {
    next(new PartShape7(pick(model)))
  }

  def pickWhere8[P1, P2, P3, P4, P5, P6, P7, P8](
    pick: M => Tuple8[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8]]
  ) = {
    next(new PartShape8(pick(model)))
  }

  def pickWhere9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    pick: M => Tuple9[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9]]
  ) = {
    next(new PartShape9(pick(model)))
  }

  def pickWhere10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    pick: M => Tuple10[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10]]
  ) = {
    next(new PartShape10(pick(model)))
  }

  def pickWhere11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    pick: M => Tuple11[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11]]
  ) = {
    next(new PartShape11(pick(model)))
  }

  def pickWhere12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    pick: M => Tuple12[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12]]
  ) = {
    next(new PartShape12(pick(model)))
  }

  def pickWhere13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    pick: M => Tuple13[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13]]
  ) = {
    next(new PartShape13(pick(model)))
  }

  def pickWhere14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    pick: M => Tuple14[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14]]
  ) = {
    next(new PartShape14(pick(model)))
  }

  def pickWhere15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    pick: M => Tuple15[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15]]
  ) = {
    next(new PartShape15(pick(model)))
  }

  def pickWhere16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    pick: M => Tuple16[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16]]
  ) = {
    next(new PartShape16(pick(model)))
  }

  def pickWhere17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    pick: M => Tuple17[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17]]
  ) = {
    next(new PartShape17(pick(model)))
  }

  def pickWhere18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    pick: M => Tuple18[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18]]
  ) = {
    next(new PartShape18(pick(model)))
  }

  def pickWhere19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    pick: M => Tuple19[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19]]
  ) = {
    next(new PartShape19(pick(model)))
  }

  def pickWhere20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    pick: M => Tuple20[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20]]
  ) = {
    next(new PartShape20(pick(model)))
  }

  def pickWhere21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    pick: M => Tuple21[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21]]
  ) = {
    next(new PartShape21(pick(model)))
  }

  def pickWhere22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    pick: M => Tuple22[CacheFilter[P1], CacheFilter[P2], CacheFilter[P3], CacheFilter[P4], CacheFilter[P5], CacheFilter[P6], CacheFilter[P7], CacheFilter[P8], CacheFilter[P9], CacheFilter[P10], CacheFilter[P11], CacheFilter[P12], CacheFilter[P13], CacheFilter[P14], CacheFilter[P15], CacheFilter[P16], CacheFilter[P17], CacheFilter[P18], CacheFilter[P19], CacheFilter[P20], CacheFilter[P21], CacheFilter[P22]]
  ) = {
    next(new PartShape22(pick(model)))
  }
}
