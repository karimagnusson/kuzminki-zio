import java.sql.Time
import java.sql.Date
import java.sql.Timestamp
import java.util.Properties
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.{Statement, PreparedStatement}
import java.sql.ResultSet
import zio._
import zio.console._
import zio.blocking._
import scala.util.{Either, Left, Right}
import kuzminki.api._


object models {

  class BinancePrice extends Model("binance_price") {
    val id = column[Long]("id")
    val symbol = column[String]("symbol")
    val priceUsd = column[Double]("price_usd")
    val priceBtc = column[Double]("price_btc")
    val change = column[Double]("change")
    val updatedAt = column[Timestamp]("updated_at")
    val rounded = column[Timestamp]("rounded")
  }

  Model.register[BinancePrice]
}

object SelectTest extends zio.App {

  import models._

  val binancePrice = Model.get[BinancePrice]

  def printThread = s"[${Thread.currentThread().getName}]"

  def getPrice(db: Kuzminki, symbol: String) = {
    db.query {
      qb
        .select(binancePrice)
        .cols3(t => (
          t.symbol,
          t.priceUsd,
          t.rounded
        ))
        .whereOne(_.symbol === symbol)
        .orderByOne(_.id.desc)
        .limit(3)
        .build
    }
  }

  val priceEth = {
    qb
      .select(binancePrice)
      .cols3(t => (
        t.symbol,
        t.priceUsd,
        t.rounded
      ))
      .whereOne(_.symbol === "ETH")
      .orderByOne(_.id.desc)
      .limit(3)
      .build
  }

  def priceString: Tuple3[String, Double, Timestamp] => String = {
    case (symbol, price, rounded) =>
      s"$symbol: $price [$rounded]"
  }

  def printPrice(db: Kuzminki, symbol: String) = {
    for {
      prices <- getPrice(db, symbol)
      _ <- ZIO.foreach(prices)(price => putStrLn(priceString(price)))
    } yield ()
  }

  def printManyPrices(db: Kuzminki, symbols: List[String]) = {
    ZIO.foreachPar(symbols)(symbol => printPrice(db, symbol))
  }

  val job = for {
    _ <- putStrLn("Start")
    db <- Kuzminki.async("cointracker")
    _ <- putStrLn("Connected")
    //_ <- printManyPrices(db, List("BTC", "ETH", "DCR", "DOT"))
    _ <- printPrice(db, "BTC")
    _ <- db.query(priceEth)
    _ <- db.close()
    _ <- putStrLn("Closed")
  } yield ()

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    job.exitCode
  }
}