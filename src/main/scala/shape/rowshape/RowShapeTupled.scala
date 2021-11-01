package kuzminki.shape

import kuzminki.column.TypeCol


class RowShape2[R1, R2](
      shape: Tuple2[TypeCol[R1], TypeCol[R2]]
    ) extends RowShape[Tuple2[R1, R2]] {

  val cols = {
    shape match {
      case (col1, col2) => Seq(col1, col2)
    }
  }

  def conv = {
    shape match {
      case (col1, col2) => 
        new RowConv2(Tuple2(col1.conv, col2.conv))
    }
  }

}

class RowShape3[R1, R2, R3](
      shape: Tuple3[TypeCol[R1], TypeCol[R2], TypeCol[R3]]
    ) extends RowShape[Tuple3[R1, R2, R3]] {

  val cols = {
    shape match {
      case (col1, col2, col3) => Seq(col1, col2, col3)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3) => 
        new RowConv3(Tuple3(col1.conv, col2.conv, col3.conv))
    }
  }

}

class RowShape4[R1, R2, R3, R4](
      shape: Tuple4[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4]]
    ) extends RowShape[Tuple4[R1, R2, R3, R4]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4) => Seq(col1, col2, col3, col4)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4) => 
        new RowConv4(Tuple4(col1.conv, col2.conv, col3.conv, col4.conv))
    }
  }

}

class RowShape5[R1, R2, R3, R4, R5](
      shape: Tuple5[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5]]
    ) extends RowShape[Tuple5[R1, R2, R3, R4, R5]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5) => Seq(col1, col2, col3, col4, col5)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5) => 
        new RowConv5(Tuple5(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv))
    }
  }

}

class RowShape6[R1, R2, R3, R4, R5, R6](
      shape: Tuple6[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6]]
    ) extends RowShape[Tuple6[R1, R2, R3, R4, R5, R6]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6) => Seq(col1, col2, col3, col4, col5, col6)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6) => 
        new RowConv6(Tuple6(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv))
    }
  }

}

class RowShape7[R1, R2, R3, R4, R5, R6, R7](
      shape: Tuple7[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7]]
    ) extends RowShape[Tuple7[R1, R2, R3, R4, R5, R6, R7]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7) => Seq(col1, col2, col3, col4, col5, col6, col7)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7) => 
        new RowConv7(Tuple7(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv))
    }
  }

}

class RowShape8[R1, R2, R3, R4, R5, R6, R7, R8](
      shape: Tuple8[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8]]
    ) extends RowShape[Tuple8[R1, R2, R3, R4, R5, R6, R7, R8]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8) => Seq(col1, col2, col3, col4, col5, col6, col7, col8)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8) => 
        new RowConv8(Tuple8(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv))
    }
  }

}

class RowShape9[R1, R2, R3, R4, R5, R6, R7, R8, R9](
      shape: Tuple9[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9]]
    ) extends RowShape[Tuple9[R1, R2, R3, R4, R5, R6, R7, R8, R9]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9) => 
        new RowConv9(Tuple9(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv))
    }
  }

}

class RowShape10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10](
      shape: Tuple10[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10]]
    ) extends RowShape[Tuple10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) => 
        new RowConv10(Tuple10(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv))
    }
  }

}

class RowShape11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11](
      shape: Tuple11[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11]]
    ) extends RowShape[Tuple11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) => 
        new RowConv11(Tuple11(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv))
    }
  }

}

class RowShape12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12](
      shape: Tuple12[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12]]
    ) extends RowShape[Tuple12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) => 
        new RowConv12(Tuple12(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv))
    }
  }

}

class RowShape13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13](
      shape: Tuple13[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13]]
    ) extends RowShape[Tuple13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) => 
        new RowConv13(Tuple13(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv))
    }
  }

}

class RowShape14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14](
      shape: Tuple14[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14]]
    ) extends RowShape[Tuple14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) => 
        new RowConv14(Tuple14(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv))
    }
  }

}

class RowShape15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15](
      shape: Tuple15[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15]]
    ) extends RowShape[Tuple15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) => 
        new RowConv15(Tuple15(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv))
    }
  }

}

class RowShape16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16](
      shape: Tuple16[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16]]
    ) extends RowShape[Tuple16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) => 
        new RowConv16(Tuple16(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv))
    }
  }

}

class RowShape17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17](
      shape: Tuple17[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17]]
    ) extends RowShape[Tuple17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) => 
        new RowConv17(Tuple17(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv))
    }
  }

}

class RowShape18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18](
      shape: Tuple18[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18]]
    ) extends RowShape[Tuple18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) => 
        new RowConv18(Tuple18(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv))
    }
  }

}

class RowShape19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19](
      shape: Tuple19[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19]]
    ) extends RowShape[Tuple19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) => 
        new RowConv19(Tuple19(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv))
    }
  }

}

class RowShape20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20](
      shape: Tuple20[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20]]
    ) extends RowShape[Tuple20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) => 
        new RowConv20(Tuple20(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv))
    }
  }

}

class RowShape21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21](
      shape: Tuple21[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20], TypeCol[R21]]
    ) extends RowShape[Tuple21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) => 
        new RowConv21(Tuple21(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv, col21.conv))
    }
  }

}

class RowShape22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22](
      shape: Tuple22[TypeCol[R1], TypeCol[R2], TypeCol[R3], TypeCol[R4], TypeCol[R5], TypeCol[R6], TypeCol[R7], TypeCol[R8], TypeCol[R9], TypeCol[R10], TypeCol[R11], TypeCol[R12], TypeCol[R13], TypeCol[R14], TypeCol[R15], TypeCol[R16], TypeCol[R17], TypeCol[R18], TypeCol[R19], TypeCol[R20], TypeCol[R21], TypeCol[R22]]
    ) extends RowShape[Tuple22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22]] {

  val cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) => Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) => 
        new RowConv22(Tuple22(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv, col21.conv, col22.conv))
    }
  }

}
