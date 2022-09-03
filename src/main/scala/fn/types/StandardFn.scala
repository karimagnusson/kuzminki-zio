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

package kuzminki.fn.types

import kuzminki.column._
import kuzminki.render.NoArgs


trait StringFn extends StringCol with FnNoArgs
trait StringArgsFn extends StringCol with FnArgs

trait BooleanFn extends BooleanCol with FnNoArgs
trait BooleanArgsFn extends BooleanCol with FnArgs

trait ShortFn extends ShortCol with FnNoArgs
trait ShortArgsFn extends ShortCol with FnArgs

trait IntFn extends IntCol with FnNoArgs
trait IntArgsFn extends IntCol with FnArgs

trait LongFn extends LongCol with FnNoArgs
trait LongArgsFn extends LongCol with FnArgs

trait FloatFn extends FloatCol with FnNoArgs
trait FloatArgsFn extends FloatCol with FnArgs

trait DoubleFn extends DoubleCol with FnNoArgs
trait DoubleArgsFn extends DoubleCol with FnArgs

trait BigDecimalFn extends BigDecimalCol with FnNoArgs
trait BigDecimalArgsFn extends BigDecimalCol with FnArgs

trait TimeFn extends TimeCol with FnNoArgs
trait TimeArgsFn extends TimeCol with FnArgs

trait DateFn extends DateCol with FnNoArgs
trait DateArgsFn extends DateCol with FnArgs

trait TimestampFn extends TimestampCol with FnNoArgs
trait TimestampArgsFn extends TimestampCol with FnArgs

trait JsonbFn extends JsonbCol with FnNoArgs
trait JsonbArgsFn extends JsonbCol with FnArgs

trait UUIDFn extends UUIDCol with FnNoArgs
trait UUIDArgsFn extends UUIDCol with FnArgs

trait StringSeqFn extends StringSeqCol with FnNoArgs
trait StringSeqArgsFn extends StringSeqCol with FnArgs

trait BooleanSeqFn extends BooleanSeqCol with FnNoArgs
trait BooleanSeqArgsFn extends BooleanSeqCol with FnArgs

trait ShortSeqFn extends ShortSeqCol with FnNoArgs
trait ShortSeqArgsFn extends ShortSeqCol with FnArgs

trait IntSeqFn extends IntSeqCol with FnNoArgs
trait IntSeqArgsFn extends IntSeqCol with FnArgs

trait LongSeqFn extends LongSeqCol with FnNoArgs
trait LongSeqArgsFn extends LongSeqCol with FnArgs

trait FloatSeqFn extends FloatSeqCol with FnNoArgs
trait FloatSeqArgsFn extends FloatSeqCol with FnArgs

trait DoubleSeqFn extends DoubleSeqCol with FnNoArgs
trait DoubleSeqArgsFn extends DoubleSeqCol with FnArgs

trait BigDecimalSeqFn extends BigDecimalSeqCol with FnNoArgs
trait BigDecimalSeqArgsFn extends BigDecimalSeqCol with FnArgs

trait TimeSeqFn extends TimeSeqCol with FnNoArgs
trait TimeSeqArgsFn extends TimeSeqCol with FnArgs

trait DateSeqFn extends DateSeqCol with FnNoArgs
trait DateSeqArgsFn extends DateSeqCol with FnArgs

trait TimestampSeqFn extends TimestampSeqCol with FnNoArgs
trait TimestampSeqArgsFn extends TimestampSeqCol with FnArgs

