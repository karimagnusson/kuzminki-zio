[![license](https://img.shields.io/github/license/rdbc-io/rdbc.svg?style=flat-square)](https://github.com/rdbc-io/rdbc/blob/master/LICENSE)
[![Gitter](https://img.shields.io/gitter/room/gitterHQ/gitter.svg?style=flat-square)](https://gitter.im/kuzminki/kuzminki-zio)
# kuzminki-zio

Kuzminki-zio is query builder and access library for PostgreSQL and [ZIO](https://zio.dev/) written in Scala.

If you have any questions about the project feel free to post on Gitter or contact me directly on telegram @karimagnusson.

This library is also available for ZIO 2 [kuzminki-zio-2](https://github.com/karimagnusson/kuzminki-zio-2)

See full documentation at [https://kuzminki.io/](https://kuzminki.io/)

#### Sbt
```sbt
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.2"
```

#### Example
```scala
import zio._
import zio.console._
import zio.blocking._
import kuzminki.api._

object ExampleApp extends zio.App {

  class Client extends Model("client") {
    val id = column[Int]("id")
    val username = column[String]("username")
    val age = column[Int]("age")
    def all = (id, username, age)
  }

  val client = Model.get[Client]

  val job = for {
    _ <- sql
      .insert(client)
      .cols2(t => (t.username, t.age))
      .run(("Joe", 35))
    
    _ <- sql
      .update(client)
      .set(_.age ==> 24)
      .where(_.id === 4)
      .run
    
    _ <- sql.delete(client).where(_.id === 7).run
    
    clients <- sql
      .select(client)
      .cols3(_.all)
      .where(_.age > 25)
      .limit(5)
      .run
    
    _ <- ZIO.foreach(clients) {
      case (id, username, age) =>
        putStrLn(s"$id $username $age")
    }
  } yield ()

  val dbConfig = DDbConfig.forDb("company").getConfig
  val dbLayer = Kuzminki.layer(dbConfig)

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    job.provideCustomLayer(dbLayer).exitCode
  }
}
```

#### Support for array fields and JSON results
The following features are not yet in a released version. Use git clone.

#### Array field
```scala
class Demo extends Model("demo") {
  val id = column[Int]("id")
  val numbers = column[Seq[Int]]("numbers")
}

val demo = Model.get[Demo]

for {

  id <- sql
    .insert(demo)
    .cols1(_.numbers)
    .returning1(_.id)
    .runHead(List(1, 2, 3))

  _ <- sql
    .update(demo)
    .set(_.numbers.append(4)) // append, prepend, remove
    .where(_.id === id)
    .run

  numbers <- sql
    .select(demo)
    .cols1(_.numbers)
    .where(_.numbers.has(2))  // has, overlap
    .run

} yield numbers // Vector(1, 2, 3, 4)
```

#### Return JSON
You can get the result as JSON, using your favorite JSON library. It comes in handy if you are, for example, writing a REST web service.
```scala
// See PlayJsonLoader example below
implicit val loadJson: Seq[Tuple2[String, Any]] => JsValue = data => PlayJsonLoader.load(data)

for {
  clients <- sql
    .select(client)
    .colsNamed(t => Seq(
      t.id,           // Column name is used as a key.
      t.username,     // If you want a different key:
      t.age           // ("client_id"  -> t.id)
    ))
    .all
    .runAs[JsValue]   // Vector[JsValue]
    .map(JsArray(_))  // JsValue
  } yield clients
```

#### Example JSON loader
Write something along these lines to use the JSON library of your choosing.
```scala
import play.api.libs.json._

object PlayJsonLoader {

  val toJsValue: Any => JsValue = {
    case v: String      => JsString(v)
    case v: Boolean     => JsBoolean(v)
    case v: Short       => JsNumber(v)
    case v: Int         => JsNumber(v)
    case v: Long        => JsNumber(v)
    case v: Float       => JsNumber(v)
    case v: Double      => JsNumber(v)
    case v: BigDecimal  => JsNumber(v)
    case v: Time        => Json.toJson(v)
    case v: Date        => Json.toJson(v)
    case v: Timestamp   => Json.toJson(v)
    case v: Option[_]   => v.map(toJsValue).getOrElse(JsNull)
    case v: Seq[_]      => JsArray(v.map(toJsValue))
    case _              => throw new Exception("Cannot convert to JsValue")
  }

  def load(data: Seq[Tuple2[String, Any]]): JsValue = {
    JsObject(data.map(p => (p._1, toJsValue(p._2))))
  }
}
```



