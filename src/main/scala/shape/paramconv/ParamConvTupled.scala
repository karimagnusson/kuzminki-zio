package kuzminki.shape

import kuzminki.conv.ValConv


class ParamConv2[P1, P2](
      shape: Tuple2[ValConv[P1], ValConv[P2]]
    ) extends ParamConv[Tuple2[P1, P2]] {

  def fromShape(params: Tuple2[P1, P2]) = {
    params match {
      case (par1, par2) =>
        Vector(par1, par2)
    }
  }
}

class ParamConv3[P1, P2, P3](
      shape: Tuple3[ValConv[P1], ValConv[P2], ValConv[P3]]
    ) extends ParamConv[Tuple3[P1, P2, P3]] {

  def fromShape(params: Tuple3[P1, P2, P3]) = {
    params match {
      case (par1, par2, par3) =>
        Vector(par1, par2, par3)
    }
  }
}

class ParamConv4[P1, P2, P3, P4](
      shape: Tuple4[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4]]
    ) extends ParamConv[Tuple4[P1, P2, P3, P4]] {

  def fromShape(params: Tuple4[P1, P2, P3, P4]) = {
    params match {
      case (par1, par2, par3, par4) =>
        Vector(par1, par2, par3, par4)
    }
  }
}

class ParamConv5[P1, P2, P3, P4, P5](
      shape: Tuple5[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5]]
    ) extends ParamConv[Tuple5[P1, P2, P3, P4, P5]] {

  def fromShape(params: Tuple5[P1, P2, P3, P4, P5]) = {
    params match {
      case (par1, par2, par3, par4, par5) =>
        Vector(par1, par2, par3, par4, par5)
    }
  }
}

class ParamConv6[P1, P2, P3, P4, P5, P6](
      shape: Tuple6[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6]]
    ) extends ParamConv[Tuple6[P1, P2, P3, P4, P5, P6]] {

  def fromShape(params: Tuple6[P1, P2, P3, P4, P5, P6]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6) =>
        Vector(par1, par2, par3, par4, par5, par6)
    }
  }
}

class ParamConv7[P1, P2, P3, P4, P5, P6, P7](
      shape: Tuple7[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7]]
    ) extends ParamConv[Tuple7[P1, P2, P3, P4, P5, P6, P7]] {

  def fromShape(params: Tuple7[P1, P2, P3, P4, P5, P6, P7]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7) =>
        Vector(par1, par2, par3, par4, par5, par6, par7)
    }
  }
}

class ParamConv8[P1, P2, P3, P4, P5, P6, P7, P8](
      shape: Tuple8[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8]]
    ) extends ParamConv[Tuple8[P1, P2, P3, P4, P5, P6, P7, P8]] {

  def fromShape(params: Tuple8[P1, P2, P3, P4, P5, P6, P7, P8]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8)
    }
  }
}

class ParamConv9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
      shape: Tuple9[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9]]
    ) extends ParamConv[Tuple9[P1, P2, P3, P4, P5, P6, P7, P8, P9]] {

  def fromShape(params: Tuple9[P1, P2, P3, P4, P5, P6, P7, P8, P9]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9)
    }
  }
}

class ParamConv10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
      shape: Tuple10[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10]]
    ) extends ParamConv[Tuple10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10]] {

  def fromShape(params: Tuple10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10)
    }
  }
}

class ParamConv11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
      shape: Tuple11[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11]]
    ) extends ParamConv[Tuple11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11]] {

  def fromShape(params: Tuple11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11)
    }
  }
}

class ParamConv12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
      shape: Tuple12[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12]]
    ) extends ParamConv[Tuple12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12]] {

  def fromShape(params: Tuple12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12)
    }
  }
}

class ParamConv13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
      shape: Tuple13[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13]]
    ) extends ParamConv[Tuple13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13]] {

  def fromShape(params: Tuple13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13)
    }
  }
}

class ParamConv14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
      shape: Tuple14[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14]]
    ) extends ParamConv[Tuple14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14]] {

  def fromShape(params: Tuple14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14)
    }
  }
}

class ParamConv15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
      shape: Tuple15[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15]]
    ) extends ParamConv[Tuple15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15]] {

  def fromShape(params: Tuple15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15)
    }
  }
}

class ParamConv16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
      shape: Tuple16[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16]]
    ) extends ParamConv[Tuple16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16]] {

  def fromShape(params: Tuple16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16)
    }
  }
}

class ParamConv17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
      shape: Tuple17[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16], ValConv[P17]]
    ) extends ParamConv[Tuple17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17]] {

  def fromShape(params: Tuple17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17)
    }
  }
}

class ParamConv18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
      shape: Tuple18[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16], ValConv[P17], ValConv[P18]]
    ) extends ParamConv[Tuple18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18]] {

  def fromShape(params: Tuple18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18)
    }
  }
}

class ParamConv19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
      shape: Tuple19[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16], ValConv[P17], ValConv[P18], ValConv[P19]]
    ) extends ParamConv[Tuple19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19]] {

  def fromShape(params: Tuple19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19)
    }
  }
}

class ParamConv20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
      shape: Tuple20[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16], ValConv[P17], ValConv[P18], ValConv[P19], ValConv[P20]]
    ) extends ParamConv[Tuple20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20]] {

  def fromShape(params: Tuple20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19, par20) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19, par20)
    }
  }
}

class ParamConv21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
      shape: Tuple21[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16], ValConv[P17], ValConv[P18], ValConv[P19], ValConv[P20], ValConv[P21]]
    ) extends ParamConv[Tuple21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21]] {

  def fromShape(params: Tuple21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19, par20, par21) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19, par20, par21)
    }
  }
}

class ParamConv22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
      shape: Tuple22[ValConv[P1], ValConv[P2], ValConv[P3], ValConv[P4], ValConv[P5], ValConv[P6], ValConv[P7], ValConv[P8], ValConv[P9], ValConv[P10], ValConv[P11], ValConv[P12], ValConv[P13], ValConv[P14], ValConv[P15], ValConv[P16], ValConv[P17], ValConv[P18], ValConv[P19], ValConv[P20], ValConv[P21], ValConv[P22]]
    ) extends ParamConv[Tuple22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22]] {

  def fromShape(params: Tuple22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22]) = {
    params match {
      case (par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19, par20, par21, par22) =>
        Vector(par1, par2, par3, par4, par5, par6, par7, par8, par9, par10, par11, par12, par13, par14, par15, par16, par17, par18, par19, par20, par21, par22)
    }
  }
}
