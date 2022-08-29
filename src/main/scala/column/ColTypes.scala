package kuzminki.column

import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import kuzminki.conv._
import kuzminki.filter._


trait StringCol extends TypeCol[String] {
  def typeName = "String"
  val conv = StringConv
}

trait BooleanCol extends TypeCol[Boolean] {
  def typeName = "Boolean"
  val conv = BooleanConv
}

trait ShortCol extends TypeCol[Short] {
  def typeName = "Short"
  val conv = ShortConv
}

trait IntCol extends TypeCol[Int] {
  def typeName = "Int"
  val conv = IntConv
}

trait LongCol extends TypeCol[Long] {
  def typeName = "Long"
  val conv = LongConv
}

trait FloatCol extends TypeCol[Float] {
  def typeName = "Float"
  val conv = FloatConv
}

trait DoubleCol extends TypeCol[Double] {
  def typeName = "Double"
  val conv = DoubleConv
}

trait BigDecimalCol extends TypeCol[BigDecimal] {
  def typeName = "BigDecimal"
  val conv = BigDecimalConv
}

trait TimeCol extends TypeCol[Time] {
  def typeName = "Time"
  val conv = TimeConv
}

trait DateCol extends TypeCol[Date] {
  def typeName = "Date"
  val conv = DateConv
}

trait TimestampCol extends TypeCol[Timestamp] {
  def typeName = "Timestamp"
  val conv = TimestampConv
}

trait StringSeqCol extends TypeCol[Seq[String]] {
  def typeName = "StringSeq"
  val conv = StringSeqConv
}

trait BooleanSeqCol extends TypeCol[Seq[Boolean]] {
  def typeName = "BooleanSeq"
  val conv = BooleanSeqConv
}

trait ShortSeqCol extends TypeCol[Seq[Short]] {
  def typeName = "ShortSeq"
  val conv = ShortSeqConv
}

trait IntSeqCol extends TypeCol[Seq[Int]] {
  def typeName = "IntSeq"
  val conv = IntSeqConv
}

trait LongSeqCol extends TypeCol[Seq[Long]] {
  def typeName = "LongSeq"
  val conv = LongSeqConv
}

trait FloatSeqCol extends TypeCol[Seq[Float]] {
  def typeName = "FloatSeq"
  val conv = FloatSeqConv
}

trait DoubleSeqCol extends TypeCol[Seq[Double]] {
  def typeName = "DoubleSeq"
  val conv = DoubleSeqConv
}

trait BigDecimalSeqCol extends TypeCol[Seq[BigDecimal]] {
  def typeName = "BigDecimalSeq"
  val conv = BigDecimalSeqConv
}

trait TimeSeqCol extends TypeCol[Seq[Time]] {
  def typeName = "TimeSeq"
  val conv = TimeSeqConv
}

trait DateSeqCol extends TypeCol[Seq[Date]] {
  def typeName = "DateSeq"
  val conv = DateSeqConv
}

trait TimestampSeqCol extends TypeCol[Seq[Timestamp]] {
  def typeName = "TimestampSeq"
  val conv = TimestampSeqConv
}

