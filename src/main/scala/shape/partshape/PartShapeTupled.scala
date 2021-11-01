package kuzminki.shape


class PartShape2[P1, P2](
      shape: Tuple2[CachePart[P1], CachePart[P2]]
    ) extends PartShape[Tuple2[P1, P2]] {

  def parts = {
    shape match {
      case (cond1, cond2) =>
        Seq(cond1, cond2)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2) =>
        new ParamConv2(cond1.conv, cond2.conv)
    }
  }
}

class PartShape3[P1, P2, P3](
      shape: Tuple3[CachePart[P1], CachePart[P2], CachePart[P3]]
    ) extends PartShape[Tuple3[P1, P2, P3]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3) =>
        Seq(cond1, cond2, cond3)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3) =>
        new ParamConv3(cond1.conv, cond2.conv, cond3.conv)
    }
  }
}

class PartShape4[P1, P2, P3, P4](
      shape: Tuple4[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4]]
    ) extends PartShape[Tuple4[P1, P2, P3, P4]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4) =>
        Seq(cond1, cond2, cond3, cond4)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4) =>
        new ParamConv4(cond1.conv, cond2.conv, cond3.conv, cond4.conv)
    }
  }
}

class PartShape5[P1, P2, P3, P4, P5](
      shape: Tuple5[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5]]
    ) extends PartShape[Tuple5[P1, P2, P3, P4, P5]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5) =>
        Seq(cond1, cond2, cond3, cond4, cond5)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5) =>
        new ParamConv5(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv)
    }
  }
}

class PartShape6[P1, P2, P3, P4, P5, P6](
      shape: Tuple6[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6]]
    ) extends PartShape[Tuple6[P1, P2, P3, P4, P5, P6]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6) =>
        new ParamConv6(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv)
    }
  }
}

class PartShape7[P1, P2, P3, P4, P5, P6, P7](
      shape: Tuple7[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7]]
    ) extends PartShape[Tuple7[P1, P2, P3, P4, P5, P6, P7]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7) =>
        new ParamConv7(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv)
    }
  }
}

class PartShape8[P1, P2, P3, P4, P5, P6, P7, P8](
      shape: Tuple8[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8]]
    ) extends PartShape[Tuple8[P1, P2, P3, P4, P5, P6, P7, P8]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8) =>
        new ParamConv8(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv)
    }
  }
}

class PartShape9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
      shape: Tuple9[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9]]
    ) extends PartShape[Tuple9[P1, P2, P3, P4, P5, P6, P7, P8, P9]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9) =>
        new ParamConv9(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv)
    }
  }
}

class PartShape10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
      shape: Tuple10[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10]]
    ) extends PartShape[Tuple10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10) =>
        new ParamConv10(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv)
    }
  }
}

class PartShape11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
      shape: Tuple11[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11]]
    ) extends PartShape[Tuple11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11) =>
        new ParamConv11(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv)
    }
  }
}

class PartShape12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
      shape: Tuple12[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12]]
    ) extends PartShape[Tuple12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12) =>
        new ParamConv12(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv)
    }
  }
}

class PartShape13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
      shape: Tuple13[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13]]
    ) extends PartShape[Tuple13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13) =>
        new ParamConv13(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv)
    }
  }
}

class PartShape14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
      shape: Tuple14[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14]]
    ) extends PartShape[Tuple14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14) =>
        new ParamConv14(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv)
    }
  }
}

class PartShape15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
      shape: Tuple15[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15]]
    ) extends PartShape[Tuple15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15) =>
        new ParamConv15(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv)
    }
  }
}

class PartShape16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
      shape: Tuple16[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16]]
    ) extends PartShape[Tuple16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16) =>
        new ParamConv16(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv)
    }
  }
}

class PartShape17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
      shape: Tuple17[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17]]
    ) extends PartShape[Tuple17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17) =>
        new ParamConv17(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv)
    }
  }
}

class PartShape18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
      shape: Tuple18[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18]]
    ) extends PartShape[Tuple18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18) =>
        new ParamConv18(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv)
    }
  }
}

class PartShape19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
      shape: Tuple19[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19]]
    ) extends PartShape[Tuple19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19) =>
        new ParamConv19(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv)
    }
  }
}

class PartShape20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
      shape: Tuple20[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20]]
    ) extends PartShape[Tuple20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20) =>
        new ParamConv20(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv, cond20.conv)
    }
  }
}

class PartShape21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
      shape: Tuple21[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21]]
    ) extends PartShape[Tuple21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21) =>
        new ParamConv21(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv, cond20.conv, cond21.conv)
    }
  }
}

class PartShape22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
      shape: Tuple22[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21], CachePart[P22]]
    ) extends PartShape[Tuple22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22]] {

  def parts = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21, cond22) =>
        Seq(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21, cond22)
    }
  }

  def conv = {
    shape match {
      case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21, cond22) =>
        new ParamConv22(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv, cond20.conv, cond21.conv, cond22.conv)
    }
  }
}
