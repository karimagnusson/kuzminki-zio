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

package kuzminki.function.types

import kuzminki.function.ColFunction
import kuzminki.column._


trait StringFunction extends ColFunction with StringCol

trait BigDecimalFunction extends ColFunction with BigDecimalCol

trait ShortFunction extends ColFunction with ShortCol

trait IntFunction extends ColFunction with IntCol

trait LongFunction extends ColFunction with LongCol

trait FloatFunction extends ColFunction with FloatCol

trait DoubleFunction extends ColFunction with DoubleCol

trait TimeFunction extends ColFunction with TimeCol

trait DateFunction extends ColFunction with DateCol

trait TimestampFunction extends ColFunction with TimestampCol

