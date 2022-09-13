package kuzminki.update

import kuzminki.api.Model
import kuzminki.model.ModelTable
import kuzminki.render.SectionCollector
import kuzminki.section.operation.{UpdateSec, UpdateCacheSetSec}
import kuzminki.assign.CacheMod
import kuzminki.shape._


abstract class PickUpdateSet[M <: Model](model: M) {

  private def next[S1](changes: PartShape[S1]) = {
    new PickUpdateWhere(
      model,
      SectionCollector(
        Vector(
          UpdateSec(ModelTable(model)),
          UpdateCacheSetSec(changes.parts)
        )
      ),
      changes
    )
  }

  def pickSet1[P](pick: M => CacheMod[P]) = {
    next(new PartShapeSingle(pick(model)))
  }

  def pickSet2[P1, P2](
    pick: M => Tuple2[CacheMod[P1], CacheMod[P2]]
  ) = {
    next(new PartShape2(pick(model)))
  }

  def pickSet3[P1, P2, P3](
    pick: M => Tuple3[CacheMod[P1], CacheMod[P2], CacheMod[P3]]
  ) = {
    next(new PartShape3(pick(model)))
  }

  def pickSet4[P1, P2, P3, P4](
    pick: M => Tuple4[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4]]
  ) = {
    next(new PartShape4(pick(model)))
  }

  def pickSet5[P1, P2, P3, P4, P5](
    pick: M => Tuple5[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5]]
  ) = {
    next(new PartShape5(pick(model)))
  }

  def pickSet6[P1, P2, P3, P4, P5, P6](
    pick: M => Tuple6[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6]]
  ) = {
    next(new PartShape6(pick(model)))
  }

  def pickSet7[P1, P2, P3, P4, P5, P6, P7](
    pick: M => Tuple7[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7]]
  ) = {
    next(new PartShape7(pick(model)))
  }

  def pickSet8[P1, P2, P3, P4, P5, P6, P7, P8](
    pick: M => Tuple8[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8]]
  ) = {
    next(new PartShape8(pick(model)))
  }

  def pickSet9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    pick: M => Tuple9[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9]]
  ) = {
    next(new PartShape9(pick(model)))
  }

  def pickSet10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    pick: M => Tuple10[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10]]
  ) = {
    next(new PartShape10(pick(model)))
  }

  def pickSet11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    pick: M => Tuple11[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11]]
  ) = {
    next(new PartShape11(pick(model)))
  }

  def pickSet12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    pick: M => Tuple12[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12]]
  ) = {
    next(new PartShape12(pick(model)))
  }

  def pickSet13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    pick: M => Tuple13[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13]]
  ) = {
    next(new PartShape13(pick(model)))
  }

  def pickSet14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    pick: M => Tuple14[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14]]
  ) = {
    next(new PartShape14(pick(model)))
  }

  def pickSet15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    pick: M => Tuple15[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15]]
  ) = {
    next(new PartShape15(pick(model)))
  }

  def pickSet16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    pick: M => Tuple16[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16]]
  ) = {
    next(new PartShape16(pick(model)))
  }

  def pickSet17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    pick: M => Tuple17[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16], CacheMod[P17]]
  ) = {
    next(new PartShape17(pick(model)))
  }

  def pickSet18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    pick: M => Tuple18[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16], CacheMod[P17], CacheMod[P18]]
  ) = {
    next(new PartShape18(pick(model)))
  }

  def pickSet19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    pick: M => Tuple19[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16], CacheMod[P17], CacheMod[P18], CacheMod[P19]]
  ) = {
    next(new PartShape19(pick(model)))
  }

  def pickSet20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    pick: M => Tuple20[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16], CacheMod[P17], CacheMod[P18], CacheMod[P19], CacheMod[P20]]
  ) = {
    next(new PartShape20(pick(model)))
  }

  def pickSet21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    pick: M => Tuple21[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16], CacheMod[P17], CacheMod[P18], CacheMod[P19], CacheMod[P20], CacheMod[P21]]
  ) = {
    next(new PartShape21(pick(model)))
  }

  def pickSet22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    pick: M => Tuple22[CacheMod[P1], CacheMod[P2], CacheMod[P3], CacheMod[P4], CacheMod[P5], CacheMod[P6], CacheMod[P7], CacheMod[P8], CacheMod[P9], CacheMod[P10], CacheMod[P11], CacheMod[P12], CacheMod[P13], CacheMod[P14], CacheMod[P15], CacheMod[P16], CacheMod[P17], CacheMod[P18], CacheMod[P19], CacheMod[P20], CacheMod[P21], CacheMod[P22]]
  ) = {
    next(new PartShape22(pick(model)))
  }
}
