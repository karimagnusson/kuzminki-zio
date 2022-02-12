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

import java.sql.ResultSet
import kuzminki.conv.ValConv


class RowConv2[R1, R2](
    shape: Tuple2[ValConv[R1], ValConv[R2]]
  ) extends RowConv[Tuple2[R1, R2]] {

  def fromRow(rs: ResultSet): Tuple2[R1, R2] = {
    shape match {
      case (col1, col2) =>
        (col1.get(rs, 1), col2.get(rs, 2))
    }
  }
}

class RowConv3[R1, R2, R3](
    shape: Tuple3[ValConv[R1], ValConv[R2], ValConv[R3]]
  ) extends RowConv[Tuple3[R1, R2, R3]] {

  def fromRow(rs: ResultSet): Tuple3[R1, R2, R3] = {
    shape match {
      case (col1, col2, col3) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3))
    }
  }
}

class RowConv4[R1, R2, R3, R4](
    shape: Tuple4[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4]]
  ) extends RowConv[Tuple4[R1, R2, R3, R4]] {

  def fromRow(rs: ResultSet): Tuple4[R1, R2, R3, R4] = {
    shape match {
      case (col1, col2, col3, col4) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4))
    }
  }
}

class RowConv5[R1, R2, R3, R4, R5](
    shape: Tuple5[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5]]
  ) extends RowConv[Tuple5[R1, R2, R3, R4, R5]] {

  def fromRow(rs: ResultSet): Tuple5[R1, R2, R3, R4, R5] = {
    shape match {
      case (col1, col2, col3, col4, col5) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5))
    }
  }
}

class RowConv6[R1, R2, R3, R4, R5, R6](
    shape: Tuple6[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6]]
  ) extends RowConv[Tuple6[R1, R2, R3, R4, R5, R6]] {

  def fromRow(rs: ResultSet): Tuple6[R1, R2, R3, R4, R5, R6] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6))
    }
  }
}

class RowConv7[R1, R2, R3, R4, R5, R6, R7](
    shape: Tuple7[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7]]
  ) extends RowConv[Tuple7[R1, R2, R3, R4, R5, R6, R7]] {

  def fromRow(rs: ResultSet): Tuple7[R1, R2, R3, R4, R5, R6, R7] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7))
    }
  }
}

class RowConv8[R1, R2, R3, R4, R5, R6, R7, R8](
    shape: Tuple8[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8]]
  ) extends RowConv[Tuple8[R1, R2, R3, R4, R5, R6, R7, R8]] {

  def fromRow(rs: ResultSet): Tuple8[R1, R2, R3, R4, R5, R6, R7, R8] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8))
    }
  }
}

class RowConv9[R1, R2, R3, R4, R5, R6, R7, R8, R9](
    shape: Tuple9[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9]]
  ) extends RowConv[Tuple9[R1, R2, R3, R4, R5, R6, R7, R8, R9]] {

  def fromRow(rs: ResultSet): Tuple9[R1, R2, R3, R4, R5, R6, R7, R8, R9] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9))
    }
  }
}

class RowConv10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10](
    shape: Tuple10[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10]]
  ) extends RowConv[Tuple10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10]] {

  def fromRow(rs: ResultSet): Tuple10[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10))
    }
  }
}

class RowConv11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11](
    shape: Tuple11[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11]]
  ) extends RowConv[Tuple11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11]] {

  def fromRow(rs: ResultSet): Tuple11[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11))
    }
  }
}

class RowConv12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12](
    shape: Tuple12[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12]]
  ) extends RowConv[Tuple12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12]] {

  def fromRow(rs: ResultSet): Tuple12[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12))
    }
  }
}

class RowConv13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13](
    shape: Tuple13[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13]]
  ) extends RowConv[Tuple13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13]] {

  def fromRow(rs: ResultSet): Tuple13[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13))
    }
  }
}

class RowConv14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14](
    shape: Tuple14[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14]]
  ) extends RowConv[Tuple14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14]] {

  def fromRow(rs: ResultSet): Tuple14[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14))
    }
  }
}

class RowConv15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15](
    shape: Tuple15[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15]]
  ) extends RowConv[Tuple15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15]] {

  def fromRow(rs: ResultSet): Tuple15[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15))
    }
  }
}

class RowConv16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16](
    shape: Tuple16[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16]]
  ) extends RowConv[Tuple16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16]] {

  def fromRow(rs: ResultSet): Tuple16[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16))
    }
  }
}

class RowConv17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17](
    shape: Tuple17[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16], ValConv[R17]]
  ) extends RowConv[Tuple17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17]] {

  def fromRow(rs: ResultSet): Tuple17[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16), col17.get(rs, 17))
    }
  }
}

class RowConv18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18](
    shape: Tuple18[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16], ValConv[R17], ValConv[R18]]
  ) extends RowConv[Tuple18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18]] {

  def fromRow(rs: ResultSet): Tuple18[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16), col17.get(rs, 17), col18.get(rs, 18))
    }
  }
}

class RowConv19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19](
    shape: Tuple19[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16], ValConv[R17], ValConv[R18], ValConv[R19]]
  ) extends RowConv[Tuple19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19]] {

  def fromRow(rs: ResultSet): Tuple19[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16), col17.get(rs, 17), col18.get(rs, 18), col19.get(rs, 19))
    }
  }
}

class RowConv20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20](
    shape: Tuple20[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16], ValConv[R17], ValConv[R18], ValConv[R19], ValConv[R20]]
  ) extends RowConv[Tuple20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20]] {

  def fromRow(rs: ResultSet): Tuple20[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16), col17.get(rs, 17), col18.get(rs, 18), col19.get(rs, 19), col20.get(rs, 20))
    }
  }
}

class RowConv21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21](
    shape: Tuple21[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16], ValConv[R17], ValConv[R18], ValConv[R19], ValConv[R20], ValConv[R21]]
  ) extends RowConv[Tuple21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21]] {

  def fromRow(rs: ResultSet): Tuple21[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16), col17.get(rs, 17), col18.get(rs, 18), col19.get(rs, 19), col20.get(rs, 20), col21.get(rs, 21))
    }
  }
}

class RowConv22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22](
    shape: Tuple22[ValConv[R1], ValConv[R2], ValConv[R3], ValConv[R4], ValConv[R5], ValConv[R6], ValConv[R7], ValConv[R8], ValConv[R9], ValConv[R10], ValConv[R11], ValConv[R12], ValConv[R13], ValConv[R14], ValConv[R15], ValConv[R16], ValConv[R17], ValConv[R18], ValConv[R19], ValConv[R20], ValConv[R21], ValConv[R22]]
  ) extends RowConv[Tuple22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22]] {

  def fromRow(rs: ResultSet): Tuple22[R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19, R20, R21, R22] = {
    shape match {
      case (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20, col21, col22) =>
        (col1.get(rs, 1), col2.get(rs, 2), col3.get(rs, 3), col4.get(rs, 4), col5.get(rs, 5), col6.get(rs, 6), col7.get(rs, 7), col8.get(rs, 8), col9.get(rs, 9), col10.get(rs, 10), col11.get(rs, 11), col12.get(rs, 12), col13.get(rs, 13), col14.get(rs, 14), col15.get(rs, 15), col16.get(rs, 16), col17.get(rs, 17), col18.get(rs, 18), col19.get(rs, 19), col20.get(rs, 20), col21.get(rs, 21), col22.get(rs, 22))
    }
  }
}
