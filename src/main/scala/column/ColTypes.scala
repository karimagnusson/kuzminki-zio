package kuzminki.column

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import java.util.UUID
import kuzminki.conv._
import kuzminki.filter._
import kuzminki.api.Jsonb


trait StringCol extends TypeCol[String] {
  val conv = StringConv
}

trait BooleanCol extends TypeCol[Boolean] {
  val conv = BooleanConv
}

trait ShortCol extends TypeCol[Short] {
  val conv = ShortConv
}

trait IntCol extends TypeCol[Int] {
  val conv = IntConv
}

trait LongCol extends TypeCol[Long] {
  val conv = LongConv
}

trait FloatCol extends TypeCol[Float] {
  val conv = FloatConv
}

trait DoubleCol extends TypeCol[Double] {
  val conv = DoubleConv
}

trait BigDecimalCol extends TypeCol[BigDecimal] {
  val conv = BigDecimalConv
}

trait TimeCol extends TypeCol[Time] {
  val conv = TimeConv
}

trait DateCol extends TypeCol[Date] {
  val conv = DateConv
}

trait TimestampCol extends TypeCol[Timestamp] {
  val conv = TimestampConv
}

trait JsonbCol extends TypeCol[Jsonb] {
  val conv = JsonbConv
}

trait UUIDCol extends TypeCol[UUID] {
  val conv = UUIDConv
}

trait StringSeqCol extends TypeCol[Seq[String]] {
  val conv = StringSeqConv
}

trait BooleanSeqCol extends TypeCol[Seq[Boolean]] {
  val conv = BooleanSeqConv
}

trait ShortSeqCol extends TypeCol[Seq[Short]] {
  val conv = ShortSeqConv
}

trait IntSeqCol extends TypeCol[Seq[Int]] {
  val conv = IntSeqConv
}

trait LongSeqCol extends TypeCol[Seq[Long]] {
  val conv = LongSeqConv
}

trait FloatSeqCol extends TypeCol[Seq[Float]] {
  val conv = FloatSeqConv
}

trait DoubleSeqCol extends TypeCol[Seq[Double]] {
  val conv = DoubleSeqConv
}

trait BigDecimalSeqCol extends TypeCol[Seq[BigDecimal]] {
  val conv = BigDecimalSeqConv
}

trait TimeSeqCol extends TypeCol[Seq[Time]] {
  val conv = TimeSeqConv
}

trait DateSeqCol extends TypeCol[Seq[Date]] {
  val conv = DateSeqConv
}

trait TimestampSeqCol extends TypeCol[Seq[Timestamp]] {
  val conv = TimestampSeqConv
}

