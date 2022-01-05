[![license](https://img.shields.io/github/license/rdbc-io/rdbc.svg?style=flat-square)](https://github.com/rdbc-io/rdbc/blob/master/LICENSE)
[![Gitter](https://img.shields.io/gitter/room/gitterHQ/gitter.svg?style=flat-square)](https://gitter.im/kuzminki/kuzminki-zio)
# kuzminki-zio

Kuzminki-zio is query builder and access library for PostgreSQL and [ZIO](https://zio.dev/) written in Scala.

The project is currently under development and not fully tested. It currently works with ZIO 1.x.

If you have any questions about the project feel free to post on Gitter or contact me directly on telegram @karimagnusson.

See full documentation at [https://kuzminki.io/](https://kuzminki.io/)

#### Sbt
```sbt
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.1"
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
    _ <- db.exec {
      sql
        .insert(client)
        .cols2(t => (t.username, t.age))
        .render(("Joe", 35))
    }
    _ <- db.exec {
      sql
        .update(client)
        .setOne(_.age ==> 24)
        .whereOne(_.id === 4)
        .render
    }
    _ <- db.exec(sql.delete(client).whereOne(_.id === 7).render)
    clients <- db.query {
      sql
        .select(client)
        .cols3(_.all)
        .whereOne(_.age > 25)
        .limit(5)
        .render
    }
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





