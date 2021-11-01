package kuzminki.shape

import kuzminki.column.TypeCol
import kuzminki.shape._


class ParamShape2[P1, P2](
      shape: Tuple2[TypeCol[P1], TypeCol[P2]]
    ) extends ParamShape[Tuple2[P1, P2]] {

  def size = 2

  def cols = {
    shape match {
      case (col1, col2) =>
        Seq(col1, col2)
    }
  }

  def conv = {
    shape match {
      case (col1, col2) =>
        new ParamConv2(col1.conv, col2.conv)
    }
  }
}

class ParamShape3[P1, P2, P3](
      shape: Tuple3[TypeCol[P1], TypeCol[P2], TypeCol[P3]]
    ) extends ParamShape[Tuple3[P1, P2, P3]] {

  def size = 3

  def cols = {
    shape match {
      case (col1, col2, col3) =>
        Seq(col1, col2, col3)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3) =>
        new ParamConv3(col1.conv, col2.conv, col3.conv)
    }
  }
}

class ParamShape4[P1, P2, P3, P4](
      shape: Tuple4[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4]]
    ) extends ParamShape[Tuple4[P1, P2, P3, P4]] {

  def size = 4

  def cols = {
    shape match {
      case (col1, col2, col3, col4) =>
        Seq(col1, col2, col3, col4)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4) =>
        new ParamConv4(col1.conv, col2.conv, col3.conv, col4.conv)
    }
  }
}

class ParamShape5[P1, P2, P3, P4, P5](
      shape: Tuple5[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5]]
    ) extends ParamShape[Tuple5[P1, P2, P3, P4, P5]] {

  def size = 5

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5) =>
        Seq(col1, col2, col3, col4, col5)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5) =>
        new ParamConv5(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv)
    }
  }
}

class ParamShape6[P1, P2, P3, P4, P5, P6](
      shape: Tuple6[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6]]
    ) extends ParamShape[Tuple6[P1, P2, P3, P4, P5, P6]] {

  def size = 6

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6) =>
        Seq(col1, col2, col3, col4, col5, col6)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6) =>
        new ParamConv6(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv)
    }
  }
}

class ParamShape7[P1, P2, P3, P4, P5, P6, P7](
      shape: Tuple7[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7]]
    ) extends ParamShape[Tuple7[P1, P2, P3, P4, P5, P6, P7]] {

  def size = 7

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7) =>
        Seq(col1, col2, col3, col4, col5, col6, col7)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7) =>
        new ParamConv7(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv)
    }
  }
}

class ParamShape8[P1, P2, P3, P4, P5, P6, P7, P8](
      shape: Tuple8[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8]]
    ) extends ParamShape[Tuple8[P1, P2, P3, P4, P5, P6, P7, P8]] {

  def size = 8

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8) =>
        new ParamConv8(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv)
    }
  }
}

class ParamShape9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
      shape: Tuple9[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9]]
    ) extends ParamShape[Tuple9[P1, P2, P3, P4, P5, P6, P7, P8, P9]] {

  def size = 9

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9) =>
        new ParamConv9(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv)
    }
  }
}

class ParamShape10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
      shape: Tuple10[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10]]
    ) extends ParamShape[Tuple10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10]] {

  def size = 10

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) =>
        new ParamConv10(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv)
    }
  }
}

class ParamShape11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
      shape: Tuple11[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11]]
    ) extends ParamShape[Tuple11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11]] {

  def size = 11

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) =>
        new ParamConv11(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv)
    }
  }
}

class ParamShape12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
      shape: Tuple12[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12]]
    ) extends ParamShape[Tuple12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12]] {

  def size = 12

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) =>
        new ParamConv12(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv)
    }
  }
}

class ParamShape13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
      shape: Tuple13[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13]]
    ) extends ParamShape[Tuple13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13]] {

  def size = 13

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) =>
        new ParamConv13(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv)
    }
  }
}

class ParamShape14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
      shape: Tuple14[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14]]
    ) extends ParamShape[Tuple14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14]] {

  def size = 14

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) =>
        new ParamConv14(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv)
    }
  }
}

class ParamShape15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
      shape: Tuple15[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15]]
    ) extends ParamShape[Tuple15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15]] {

  def size = 15

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) =>
        new ParamConv15(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv)
    }
  }
}

class ParamShape16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
      shape: Tuple16[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16]]
    ) extends ParamShape[Tuple16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16]] {

  def size = 16

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) =>
        new ParamConv16(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv)
    }
  }
}

class ParamShape17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
      shape: Tuple17[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17]]
    ) extends ParamShape[Tuple17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17]] {

  def size = 17

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) =>
        new ParamConv17(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv)
    }
  }
}

class ParamShape18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
      shape: Tuple18[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18]]
    ) extends ParamShape[Tuple18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18]] {

  def size = 18

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) =>
        new ParamConv18(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv)
    }
  }
}

class ParamShape19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
      shape: Tuple19[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19]]
    ) extends ParamShape[Tuple19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19]] {

  def size = 19

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) =>
        new ParamConv19(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv)
    }
  }
}

class ParamShape20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
      shape: Tuple20[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19], TypeCol[P20]]
    ) extends ParamShape[Tuple20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20]] {

  def size = 20

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) =>
        new ParamConv20(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv)
    }
  }
}

class ParamShape21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
      shape: Tuple21[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19], TypeCol[P20], TypeCol[P21]]
    ) extends ParamShape[Tuple21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21]] {

  def size = 21

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) =>
        new ParamConv21(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv, col21.conv)
    }
  }
}

class ParamShape22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
      shape: Tuple22[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19], TypeCol[P20], TypeCol[P21], TypeCol[P22]]
    ) extends ParamShape[Tuple22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22]] {

  def size = 22

  def cols = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) =>
        Seq(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22)
    }
  }

  def conv = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) =>
        new ParamConv22(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv, col21.conv, col22.conv)
    }
  }
}
