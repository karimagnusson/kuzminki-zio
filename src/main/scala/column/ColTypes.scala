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

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp


trait StringCol extends StringColValue
                   with UniversalFilters[String]
                   with StringFilters {

  val self = this
  def asOpt = StringOptCol(this)
}


trait BooleanCol extends BooleanColValue
                    with UniversalFilters[Boolean] {

  val self = this
  def asOpt = BooleanOptCol(this)
}


trait ShortCol extends ShortColValue
                  with UniversalFilters[Short]
                  with ComparativeFilters[Short]
                  with AggregationSubqueryFilters[Short] {

  val self = this
  def asOpt = ShortOptCol(this)
}


trait IntCol extends IntColValue
                with SelfRef[Int]
                with UniversalFilters[Int]
                with ComparativeFilters[Int]
                with AggregationSubqueryFilters[Int] {

  val self = this
  def asOpt = IntOptCol(this)
}


trait LongCol extends LongColValue
                 with UniversalFilters[Long]
                 with ComparativeFilters[Long]
                 with AggregationSubqueryFilters[Long] {

  val self = this
  def asOpt = LongOptCol(this)
}


trait FloatCol extends FloatColValue
                  with UniversalFilters[Float]
                  with ComparativeFilters[Float]
                  with AggregationSubqueryFilters[Float] {

  val self = this
  def asOpt = FloatOptCol(this)
}


trait DoubleCol extends DoubleColValue
                   with UniversalFilters[Double]
                   with ComparativeFilters[Double]
                   with AggregationSubqueryFilters[Double] {

  val self = this
  def asOpt = DoubleOptCol(this)
}


trait BigDecimalCol extends BigDecimalColValue
                       with UniversalFilters[BigDecimal]
                       with ComparativeFilters[BigDecimal]
                       with AggregationSubqueryFilters[BigDecimal] {

  val self = this
  def asOpt = BigDecimalOptCol(this)
}


trait TimeCol extends TimeColValue
                 with UniversalFilters[Time]
                 with ComparativeFilters[Time] {

  val self = this
  def asOpt = TimeOptCol(this)
}


trait DateCol extends DateColValue
                 with UniversalFilters[Date]
                 with ComparativeFilters[Date] {

  val self = this
  def asOpt = DateOptCol(this)
}


trait TimestampCol extends TimestampColValue
                      with UniversalFilters[Timestamp]
                      with ComparativeFilters[Timestamp] {

  val self = this
  def asOpt = TimestampOptCol(this)
}
































