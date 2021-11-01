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

package kuzminki.column

import kuzminki.render.UnderlyingRenderAndArgs


case class StringOptCol(underlying: AnyCol) extends StringOptColValue
                                               with UnderlyingRenderAndArgs


case class BooleanOptCol(underlying: AnyCol) extends BooleanOptColValue
                                                with UnderlyingRenderAndArgs


case class ShortOptCol(underlying: AnyCol) extends ShortOptColValue
                                             with UnderlyingRenderAndArgs


case class IntOptCol(underlying: AnyCol) extends IntOptColValue
                                            with UnderlyingRenderAndArgs


case class LongOptCol(underlying: AnyCol) extends LongOptColValue
                                             with UnderlyingRenderAndArgs


case class FloatOptCol(underlying: AnyCol) extends FloatOptColValue
                                              with UnderlyingRenderAndArgs


case class DoubleOptCol(underlying: AnyCol) extends DoubleOptColValue
                                               with UnderlyingRenderAndArgs


case class BigDecimalOptCol(underlying: AnyCol) extends BigDecimalOptColValue
                                                   with UnderlyingRenderAndArgs


case class TimeOptCol(underlying: AnyCol) extends TimeOptColValue
                                             with UnderlyingRenderAndArgs


case class DateOptCol(underlying: AnyCol) extends DateOptColValue
                                             with UnderlyingRenderAndArgs


case class TimestampOptCol(underlying: AnyCol) extends TimestampOptColValue
                                                  with UnderlyingRenderAndArgs







