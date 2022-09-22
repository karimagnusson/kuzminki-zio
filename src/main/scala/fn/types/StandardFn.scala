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


trait StringFn extends StringCol with FnColArgs
trait StringArgsFn extends StringCol with FnArgs

trait BooleanFn extends BooleanCol with FnColArgs
trait BooleanArgsFn extends BooleanCol with FnArgs

trait ShortFn extends ShortCol with FnColArgs
trait ShortArgsFn extends ShortCol with FnArgs

trait IntFn extends IntCol with FnColArgs
trait IntArgsFn extends IntCol with FnArgs

trait LongFn extends LongCol with FnColArgs
trait LongArgsFn extends LongCol with FnArgs

trait FloatFn extends FloatCol with FnColArgs
trait FloatArgsFn extends FloatCol with FnArgs

trait DoubleFn extends DoubleCol with FnColArgs
trait DoubleArgsFn extends DoubleCol with FnArgs

trait BigDecimalFn extends BigDecimalCol with FnColArgs
trait BigDecimalArgsFn extends BigDecimalCol with FnArgs

trait TimeFn extends TimeCol with FnColArgs
trait TimeArgsFn extends TimeCol with FnArgs

trait DateFn extends DateCol with FnColArgs
trait DateArgsFn extends DateCol with FnArgs

trait TimestampFn extends TimestampCol with FnColArgs
trait TimestampArgsFn extends TimestampCol with FnArgs

trait JsonbFn extends JsonbCol with FnColArgs
trait JsonbArgsFn extends JsonbCol with FnArgs

trait UUIDFn extends UUIDCol with FnColArgs
trait UUIDArgsFn extends UUIDCol with FnArgs

trait StringSeqFn extends StringSeqCol with FnColArgs
trait StringSeqArgsFn extends StringSeqCol with FnArgs

trait BooleanSeqFn extends BooleanSeqCol with FnColArgs
trait BooleanSeqArgsFn extends BooleanSeqCol with FnArgs

trait ShortSeqFn extends ShortSeqCol with FnColArgs
trait ShortSeqArgsFn extends ShortSeqCol with FnArgs

trait IntSeqFn extends IntSeqCol with FnColArgs
trait IntSeqArgsFn extends IntSeqCol with FnArgs

trait LongSeqFn extends LongSeqCol with FnColArgs
trait LongSeqArgsFn extends LongSeqCol with FnArgs

trait FloatSeqFn extends FloatSeqCol with FnColArgs
trait FloatSeqArgsFn extends FloatSeqCol with FnArgs

trait DoubleSeqFn extends DoubleSeqCol with FnColArgs
trait DoubleSeqArgsFn extends DoubleSeqCol with FnArgs

trait BigDecimalSeqFn extends BigDecimalSeqCol with FnColArgs
trait BigDecimalSeqArgsFn extends BigDecimalSeqCol with FnArgs

trait TimeSeqFn extends TimeSeqCol with FnColArgs
trait TimeSeqArgsFn extends TimeSeqCol with FnArgs

trait DateSeqFn extends DateSeqCol with FnColArgs
trait DateSeqArgsFn extends DateSeqCol with FnArgs

trait TimestampSeqFn extends TimestampSeqCol with FnColArgs
trait TimestampSeqArgsFn extends TimestampSeqCol with FnArgs



