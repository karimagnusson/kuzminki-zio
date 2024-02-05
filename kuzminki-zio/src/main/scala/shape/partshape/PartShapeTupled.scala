/*
* Copyright 2021 Kári Magnússon
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package kuzminki.shape

import scala.annotation.nowarn


@nowarn
class PartShape2[P1, P2](
    shape: Tuple2[CachePart[P1], CachePart[P2]]
  ) extends PartShape[Tuple2[P1, P2]] {

  val parts = shape match {
    case (cond1, cond2) =>
      Vector(cond1, cond2)
  }

  val conv = shape match {
    case (cond1, cond2) =>
      new ParamConv2(cond1.conv, cond2.conv)
  }
}

@nowarn
class PartShape3[P1, P2, P3](
    shape: Tuple3[CachePart[P1], CachePart[P2], CachePart[P3]]
  ) extends PartShape[Tuple3[P1, P2, P3]] {

  val parts = shape match {
    case (cond1, cond2, cond3) =>
      Vector(cond1, cond2, cond3)
  }

  val conv = shape match {
    case (cond1, cond2, cond3) =>
      new ParamConv3(cond1.conv, cond2.conv, cond3.conv)
  }
}

@nowarn
class PartShape4[P1, P2, P3, P4](
    shape: Tuple4[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4]]
  ) extends PartShape[Tuple4[P1, P2, P3, P4]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4) =>
      Vector(cond1, cond2, cond3, cond4)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4) =>
      new ParamConv4(cond1.conv, cond2.conv, cond3.conv, cond4.conv)
  }
}

@nowarn
class PartShape5[P1, P2, P3, P4, P5](
    shape: Tuple5[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5]]
  ) extends PartShape[Tuple5[P1, P2, P3, P4, P5]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5) =>
      Vector(cond1, cond2, cond3, cond4, cond5)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5) =>
      new ParamConv5(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv)
  }
}

@nowarn
class PartShape6[P1, P2, P3, P4, P5, P6](
    shape: Tuple6[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6]]
  ) extends PartShape[Tuple6[P1, P2, P3, P4, P5, P6]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6) =>
      new ParamConv6(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv)
  }
}

@nowarn
class PartShape7[P1, P2, P3, P4, P5, P6, P7](
    shape: Tuple7[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7]]
  ) extends PartShape[Tuple7[P1, P2, P3, P4, P5, P6, P7]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7) =>
      new ParamConv7(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv)
  }
}

@nowarn
class PartShape8[P1, P2, P3, P4, P5, P6, P7, P8](
    shape: Tuple8[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8]]
  ) extends PartShape[Tuple8[P1, P2, P3, P4, P5, P6, P7, P8]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8) =>
      new ParamConv8(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv)
  }
}

@nowarn
class PartShape9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    shape: Tuple9[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9]]
  ) extends PartShape[Tuple9[P1, P2, P3, P4, P5, P6, P7, P8, P9]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9) =>
      new ParamConv9(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv)
  }
}

@nowarn
class PartShape10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    shape: Tuple10[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10]]
  ) extends PartShape[Tuple10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10) =>
      new ParamConv10(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv)
  }
}

@nowarn
class PartShape11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    shape: Tuple11[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11]]
  ) extends PartShape[Tuple11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11) =>
      new ParamConv11(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv)
  }
}

@nowarn
class PartShape12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    shape: Tuple12[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12]]
  ) extends PartShape[Tuple12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12) =>
      new ParamConv12(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv)
  }
}

@nowarn
class PartShape13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    shape: Tuple13[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13]]
  ) extends PartShape[Tuple13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13) =>
      new ParamConv13(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv)
  }
}

@nowarn
class PartShape14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    shape: Tuple14[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14]]
  ) extends PartShape[Tuple14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14) =>
      new ParamConv14(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv)
  }
}

@nowarn
class PartShape15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    shape: Tuple15[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15]]
  ) extends PartShape[Tuple15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15) =>
      new ParamConv15(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv)
  }
}

@nowarn
class PartShape16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    shape: Tuple16[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16]]
  ) extends PartShape[Tuple16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16) =>
      new ParamConv16(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv)
  }
}

@nowarn
class PartShape17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    shape: Tuple17[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17]]
  ) extends PartShape[Tuple17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17) =>
      new ParamConv17(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv)
  }
}

@nowarn
class PartShape18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    shape: Tuple18[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18]]
  ) extends PartShape[Tuple18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18) =>
      new ParamConv18(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv)
  }
}

@nowarn
class PartShape19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    shape: Tuple19[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19]]
  ) extends PartShape[Tuple19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19) =>
      new ParamConv19(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv)
  }
}

@nowarn
class PartShape20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    shape: Tuple20[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20]]
  ) extends PartShape[Tuple20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20) =>
      new ParamConv20(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv, cond20.conv)
  }
}

@nowarn
class PartShape21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    shape: Tuple21[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21]]
  ) extends PartShape[Tuple21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21) =>
      new ParamConv21(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv, cond20.conv, cond21.conv)
  }
}

@nowarn
class PartShape22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    shape: Tuple22[CachePart[P1], CachePart[P2], CachePart[P3], CachePart[P4], CachePart[P5], CachePart[P6], CachePart[P7], CachePart[P8], CachePart[P9], CachePart[P10], CachePart[P11], CachePart[P12], CachePart[P13], CachePart[P14], CachePart[P15], CachePart[P16], CachePart[P17], CachePart[P18], CachePart[P19], CachePart[P20], CachePart[P21], CachePart[P22]]
  ) extends PartShape[Tuple22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22]] {

  val parts = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21, cond22) =>
      Vector(cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21, cond22)
  }

  val conv = shape match {
    case (cond1, cond2, cond3, cond4, cond5, cond6, cond7, cond8, cond9, cond10, cond11, cond12, cond13, cond14, cond15, cond16, cond17, cond18, cond19, cond20, cond21, cond22) =>
      new ParamConv22(cond1.conv, cond2.conv, cond3.conv, cond4.conv, cond5.conv, cond6.conv, cond7.conv, cond8.conv, cond9.conv, cond10.conv, cond11.conv, cond12.conv, cond13.conv, cond14.conv, cond15.conv, cond16.conv, cond17.conv, cond18.conv, cond19.conv, cond20.conv, cond21.conv, cond22.conv)
  }
}
