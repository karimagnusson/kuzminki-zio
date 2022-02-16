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





