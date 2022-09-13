package kuzminki.insert

import kuzminki.api.Model
import kuzminki.model.ModelTable
import kuzminki.column.TypeCol
import kuzminki.assign.SetValue
import kuzminki.render.SectionCollector
import kuzminki.section.Section
import kuzminki.section.insert._
import kuzminki.shape._


class Insert[M <: Model](model: M) {

  def data(pick: M => Seq[SetValue]) = {
    val pairs = pick(model).toVector
    new Values(
      ValuesBuilder(
        model,
        pairs.map(_.col),
        pairs.map(_.value)
      )
    )
  }

  private def next[P](paramShape: ParamShape[P]) = {
    new InsertOptions(
      InsertBuilder(
        model,
        paramShape
      )
    )
  }

  def colsType[P](pick: M => ParamShapeWrite[P]) = {
    next(pick(model))
  }

  def cols1[P](pick: M => TypeCol[P]) = {
    next(new ParamShapeSingle(pick(model)))
  }
  
  def cols2[R1, R2](pick: M => Tuple2[TypeCol[R1], TypeCol[R2]]) = {
    next(new ParamShape2(pick(model)))
  }

  def cols3[R1, R2, R3](pick: M => Tuple3[TypeCol[R1], TypeCol[R2], TypeCol[R3]]) = {
    next(new ParamShape3(pick(model)))
  }

  def cols4[R1, R2, R3, R4](pick: M => Tuple4[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4]]) = {
    next(new ParamShape4(pick(model)))
  }

  def cols5[R1, R2, R3, R4, R5](pick: M => Tuple5[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5]]) = {
    next(new ParamShape5(pick(model)))
  }

  def cols6[R1, R2, R3, R4, R5, R6](pick: M => Tuple6[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6]]) = {
    next(new ParamShape6(pick(model)))
  }

  def cols7[R1, R2, R3, R4, R5, R6, R7](pick: M => Tuple7[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7]]) = {
    next(new ParamShape7(pick(model)))
  }

  def cols8[R1, R2, R3, R4, R5, R6, R7, R8](pick: M => Tuple8[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8]]) = {
    next(new ParamShape8(pick(model)))
  }

  def cols9[R1, R2, R3, R4, R5, R6, R7, R8, R9](pick: M => Tuple9[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9]]) = {
    next(new ParamShape9(pick(model)))
  }

  def cols10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10](pick: M => Tuple10[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10]]) = {
    next(new ParamShape10(pick(model)))
  }

  def cols11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11](pick: M => Tuple11[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11]]) = {
    next(new ParamShape11(pick(model)))
  }

  def cols12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12](pick: M => Tuple12[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12]]) = {
    next(new ParamShape12(pick(model)))
  }

  def cols13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13](pick: M => Tuple13[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13]]) = {
    next(new ParamShape13(pick(model)))
  }

  def cols14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14](pick: M => Tuple14[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14]]) = {
    next(new ParamShape14(pick(model)))
  }

  def cols15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15](pick: M => Tuple15[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15]]) = {
    next(new ParamShape15(pick(model)))
  }

  def cols16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16](pick: M => Tuple16[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16]]) = {
    next(new ParamShape16(pick(model)))
  }

  def cols17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17](pick: M => Tuple17[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17]]) = {
    next(new ParamShape17(pick(model)))
  }

  def cols18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18](pick: M => Tuple18[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18]]) = {
    next(new ParamShape18(pick(model)))
  }

  def cols19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19](pick: M => Tuple19[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19]]) = {
    next(new ParamShape19(pick(model)))
  }

  def cols20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20](pick: M => Tuple20[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20]]) = {
    next(new ParamShape20(pick(model)))
  }

  def cols21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21](pick: M => Tuple21[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20], TypeCol[R21]]) = {
    next(new ParamShape21(pick(model)))
  }

  def cols22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22](pick: M => Tuple22[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20], TypeCol[R21], TypeCol[R22]]) = {
    next(new ParamShape22(pick(model)))
  }
}