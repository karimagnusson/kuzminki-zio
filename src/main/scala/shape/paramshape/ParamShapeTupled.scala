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

import kuzminki.column.TypeCol
import kuzminki.shape._


class ParamShape2[P1, P2](
    shape: Tuple2[TypeCol[P1], TypeCol[P2]]
  ) extends ParamShape[Tuple2[P1, P2]] {

  val size = 2

  val cols = shape match {
    case (col1, col2) =>
      Vector(col1, col2)
  }

  val conv = shape match {
    case (col1, col2) =>
      new ParamConv2(col1.conv, col2.conv)
  }
}

class ParamShape3[P1, P2, P3](
    shape: Tuple3[TypeCol[P1], TypeCol[P2], TypeCol[P3]]
  ) extends ParamShape[Tuple3[P1, P2, P3]] {

  val size = 3

  val cols = shape match {
    case (col1, col2, col3) =>
      Vector(col1, col2, col3)
  }

  val conv = shape match {
    case (col1, col2, col3) =>
      new ParamConv3(col1.conv, col2.conv, col3.conv)
  }
}

class ParamShape4[P1, P2, P3, P4](
    shape: Tuple4[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4]]
  ) extends ParamShape[Tuple4[P1, P2, P3, P4]] {

  val size = 4

  val cols = shape match {
    case (col1, col2, col3, col4) =>
      Vector(col1, col2, col3, col4)
  }

  val conv = shape match {
    case (col1, col2, col3, col4) =>
      new ParamConv4(col1.conv, col2.conv, col3.conv, col4.conv)
  }
}

class ParamShape5[P1, P2, P3, P4, P5](
    shape: Tuple5[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5]]
  ) extends ParamShape[Tuple5[P1, P2, P3, P4, P5]] {

  val size = 5

  val cols = shape match {
    case (col1, col2, col3, col4, col5) =>
      Vector(col1, col2, col3, col4, col5)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5) =>
      new ParamConv5(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv)
  }
}

class ParamShape6[P1, P2, P3, P4, P5, P6](
    shape: Tuple6[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6]]
  ) extends ParamShape[Tuple6[P1, P2, P3, P4, P5, P6]] {

  val size = 6

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6) =>
      Vector(col1, col2, col3, col4, col5, col6)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6) =>
      new ParamConv6(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv)
  }
}

class ParamShape7[P1, P2, P3, P4, P5, P6, P7](
    shape: Tuple7[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7]]
  ) extends ParamShape[Tuple7[P1, P2, P3, P4, P5, P6, P7]] {

  val size = 7

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7) =>
      Vector(col1, col2, col3, col4, col5, col6, col7)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7) =>
      new ParamConv7(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv)
  }
}

class ParamShape8[P1, P2, P3, P4, P5, P6, P7, P8](
    shape: Tuple8[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8]]
  ) extends ParamShape[Tuple8[P1, P2, P3, P4, P5, P6, P7, P8]] {

  val size = 8

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8) =>
      new ParamConv8(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv)
  }
}

class ParamShape9[P1, P2, P3, P4, P5, P6, P7, P8, P9](
    shape: Tuple9[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9]]
  ) extends ParamShape[Tuple9[P1, P2, P3, P4, P5, P6, P7, P8, P9]] {

  val size = 9

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9) =>
      new ParamConv9(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv)
  }
}

class ParamShape10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10](
    shape: Tuple10[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10]]
  ) extends ParamShape[Tuple10[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10]] {

  val size = 10

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) =>
      new ParamConv10(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv)
  }
}

class ParamShape11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11](
    shape: Tuple11[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11]]
  ) extends ParamShape[Tuple11[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11]] {

  val size = 11

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) =>
      new ParamConv11(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv)
  }
}

class ParamShape12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12](
    shape: Tuple12[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12]]
  ) extends ParamShape[Tuple12[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12]] {

  val size = 12

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) =>
      new ParamConv12(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv)
  }
}

class ParamShape13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13](
    shape: Tuple13[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13]]
  ) extends ParamShape[Tuple13[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13]] {

  val size = 13

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) =>
      new ParamConv13(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv)
  }
}

class ParamShape14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14](
    shape: Tuple14[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14]]
  ) extends ParamShape[Tuple14[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14]] {

  val size = 14

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) =>
      new ParamConv14(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv)
  }
}

class ParamShape15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15](
    shape: Tuple15[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15]]
  ) extends ParamShape[Tuple15[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15]] {

  val size = 15

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) =>
      new ParamConv15(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv)
  }
}

class ParamShape16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16](
    shape: Tuple16[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16]]
  ) extends ParamShape[Tuple16[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16]] {

  val size = 16

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) =>
      new ParamConv16(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv)
  }
}

class ParamShape17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17](
    shape: Tuple17[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17]]
  ) extends ParamShape[Tuple17[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17]] {

  val size = 17

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) =>
      new ParamConv17(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv)
  }
}

class ParamShape18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18](
    shape: Tuple18[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18]]
  ) extends ParamShape[Tuple18[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18]] {

  val size = 18

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) =>
      new ParamConv18(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv)
  }
}

class ParamShape19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19](
    shape: Tuple19[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19]]
  ) extends ParamShape[Tuple19[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19]] {

  val size = 19

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) =>
      new ParamConv19(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv)
  }
}

class ParamShape20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20](
    shape: Tuple20[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19], TypeCol[P20]]
  ) extends ParamShape[Tuple20[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20]] {

  val size = 20

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) =>
      new ParamConv20(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv)
  }
}

class ParamShape21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21](
    shape: Tuple21[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19], TypeCol[P20], TypeCol[P21]]
  ) extends ParamShape[Tuple21[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21]] {

  val size = 21

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) =>
      new ParamConv21(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv, col21.conv)
  }
}

class ParamShape22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22](
    shape: Tuple22[TypeCol[P1], TypeCol[P2], TypeCol[P3], TypeCol[P4], TypeCol[P5], TypeCol[P6], TypeCol[P7], TypeCol[P8], TypeCol[P9], TypeCol[P10], TypeCol[P11], TypeCol[P12], TypeCol[P13], TypeCol[P14], TypeCol[P15], TypeCol[P16], TypeCol[P17], TypeCol[P18], TypeCol[P19], TypeCol[P20], TypeCol[P21], TypeCol[P22]]
  ) extends ParamShape[Tuple22[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22]] {

  val size = 22

  val cols = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) =>
      Vector(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22)
  }

  val conv = shape match {
    case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) =>
      new ParamConv22(col1.conv, col2.conv, col3.conv, col4.conv, col5.conv, col6.conv, col7.conv, col8.conv, col9.conv, col10.conv, col11.conv, col12.conv, col13.conv, col14.conv, col15.conv, col16.conv, col17.conv, col18.conv, col19.conv, col20.conv, col21.conv, col22.conv)
  }
}
