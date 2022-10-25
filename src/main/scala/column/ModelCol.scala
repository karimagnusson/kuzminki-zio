package kuzminki.column

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import java.util.UUID
import kuzminki.filter._
import kuzminki.render.{Prefix, NoArgs}


sealed trait ModelCol extends NoArgs {          
  val info: ColInfo
  def name = info.name
  def render(prefix: Prefix) = prefix.pick(info)
}

case class StringModelCol(info: ColInfo) extends StringCol with ModelCol

case class BooleanModelCol(info: ColInfo) extends BooleanCol with ModelCol

case class ShortModelCol(info: ColInfo) extends ShortCol with ModelCol

case class IntModelCol(info: ColInfo) extends IntCol with ModelCol

case class LongModelCol(info: ColInfo) extends LongCol with ModelCol

case class FloatModelCol(info: ColInfo) extends FloatCol with ModelCol

case class DoubleModelCol(info: ColInfo) extends DoubleCol with ModelCol

case class BigDecimalModelCol(info: ColInfo) extends BigDecimalCol with ModelCol

case class TimeModelCol(info: ColInfo) extends TimeCol with ModelCol

case class DateModelCol(info: ColInfo) extends DateCol with ModelCol

case class TimestampModelCol(info: ColInfo) extends TimestampCol with ModelCol

case class JsonbModelCol(info: ColInfo) extends JsonbCol with ModelCol

case class UUIDModelCol(info: ColInfo) extends UUIDCol with ModelCol

case class StringSeqModelCol(info: ColInfo) extends StringSeqCol with ModelCol

case class BooleanSeqModelCol(info: ColInfo) extends BooleanSeqCol with ModelCol

case class ShortSeqModelCol(info: ColInfo) extends ShortSeqCol with ModelCol

case class IntSeqModelCol(info: ColInfo) extends IntSeqCol with ModelCol

case class LongSeqModelCol(info: ColInfo) extends LongSeqCol with ModelCol

case class FloatSeqModelCol(info: ColInfo) extends FloatSeqCol with ModelCol

case class DoubleSeqModelCol(info: ColInfo) extends DoubleSeqCol with ModelCol

case class BigDecimalSeqModelCol(info: ColInfo) extends BigDecimalSeqCol with ModelCol

case class TimeSeqModelCol(info: ColInfo) extends TimeSeqCol with ModelCol

case class DateSeqModelCol(info: ColInfo) extends DateSeqCol with ModelCol

case class TimestampSeqModelCol(info: ColInfo) extends TimestampSeqCol with ModelCol

case class JsonbSeqModelCol(info: ColInfo) extends JsonbSeqCol with ModelCol












