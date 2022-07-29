package kuzminki.select

import kuzminki.api.Model
import kuzminki.model.ModelTable
import kuzminki.column.TypeCol
import kuzminki.render.Prefix
import kuzminki.section.select._
import kuzminki.shape._


class Select[M <: Model](val model: M) {

  def next[R](rowShape: RowShape[R]) = {
    new Where(
      model,
      SelectCollector(
        Prefix.forModel,
        rowShape,
        Vector(
          SelectSec(rowShape.cols),
          FromSec(ModelTable(model))
        )
      )
    )
  }

  def colsType[R](pick: M => RowReader[R]) = {
    next(
      pick(model)
    )
  }

  def colsSeq(pick: M => Seq[TypeCol[_]]) = {
    next(
      new RowShapeSeq(pick(model))
    )
  }

  def colsNamed(pick: M => Seq[Tuple2[String, TypeCol[_]]]) = {
    next(
      new RowShapeNamed(pick(model))
    )
  }

  def cols1[R](pick: M => TypeCol[R]) = {
    next(
      new RowShapeSingle(pick(model))
    )
  }
  
  def cols2[R1, R2](
        pick: M => Tuple2[TypeCol[R1], TypeCol[R2]]
      ) = {
    next(
      new RowShape2(pick(model))
    )
  }

  def cols3[R1, R2, R3](
        pick: M => Tuple3[TypeCol[R1], TypeCol[R2], TypeCol[R3]]
      ) = {
    next(
      new RowShape3(pick(model))
    )
  }

  def cols4[R1, R2, R3, R4](
        pick: M => Tuple4[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4]]
      ) = {
    next(
      new RowShape4(pick(model))
    )
  }

  def cols5[R1, R2, R3, R4, R5](
        pick: M => Tuple5[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5]]
      ) = {
    next(
      new RowShape5(pick(model))
    )
  }

  def cols6[R1, R2, R3, R4, R5, R6](
        pick: M => Tuple6[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6]]
      ) = {
    next(
      new RowShape6(pick(model))
    )
  }

  def cols7[R1, R2, R3, R4, R5, R6, R7](
        pick: M => Tuple7[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7]]
      ) = {
    next(
      new RowShape7(pick(model))
    )
  }

  def cols8[R1, R2, R3, R4, R5, R6, R7, R8](
        pick: M => Tuple8[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8]]
      ) = {
    next(
      new RowShape8(pick(model))
    )
  }

  def cols9[R1, R2, R3, R4, R5, R6, R7, R8, R9](
        pick: M => Tuple9[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9]]
      ) = {
    next(
      new RowShape9(pick(model))
    )
  }

  def cols10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10](
        pick: M => Tuple10[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10]]
      ) = {
    next(
      new RowShape10(pick(model))
    )
  }

  def cols11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11](
        pick: M => Tuple11[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11]]
      ) = {
    next(
      new RowShape11(pick(model))
    )
  }

  def cols12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12](
        pick: M => Tuple12[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12]]
      ) = {
    next(
      new RowShape12(pick(model))
    )
  }

  def cols13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13](
        pick: M => Tuple13[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13]]
      ) = {
    next(
      new RowShape13(pick(model))
    )
  }

  def cols14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14](
        pick: M => Tuple14[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14]]
      ) = {
    next(
      new RowShape14(pick(model))
    )
  }

  def cols15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15](
        pick: M => Tuple15[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15]]
      ) = {
    next(
      new RowShape15(pick(model))
    )
  }

  def cols16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16](
        pick: M => Tuple16[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16]]
      ) = {
    next(
      new RowShape16(pick(model))
    )
  }

  def cols17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17](
        pick: M => Tuple17[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17]]
      ) = {
    next(
      new RowShape17(pick(model))
    )
  }

  def cols18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18](
        pick: M => Tuple18[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18]]
      ) = {
    next(
      new RowShape18(pick(model))
    )
  }

  def cols19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19](
        pick: M => Tuple19[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19]]
      ) = {
    next(
      new RowShape19(pick(model))
    )
  }

  def cols20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20](
        pick: M => Tuple20[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20]]
      ) = {
    next(
      new RowShape20(pick(model))
    )
  }

  def cols21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21](
        pick: M => Tuple21[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20], TypeCol[R21]]
      ) = {
    next(
      new RowShape21(pick(model))
    )
  }

  def cols22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22](
        pick: M => Tuple22[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20], TypeCol[R21], TypeCol[R22]]
      ) = {
    next(
      new RowShape22(pick(model))
    )
  }
}