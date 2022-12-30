[![Twitter Follow](https://img.shields.io/twitter/follow/kuzminki_lib?label=follow&style=flat&logo=twitter&color=brightgreen)](https://twitter.com/kuzminki_lib)
[![license](https://img.shields.io/github/license/rdbc-io/rdbc.svg?style=flat-square)](https://github.com/rdbc-io/rdbc/blob/master/LICENSE)
[![Gitter](https://img.shields.io/gitter/room/gitterHQ/gitter.svg?style=flat-square)](https://gitter.im/kuzminki/kuzminki-zio)
# kuzminki-zio

Kuzminki is feature-rich query builder and access library for PostgreSQL written in Scala.

It is available with integration for ZIO 1 and ZIO 2 and a version that relies only on Scala ExecutionContext.The API follows the logic of SQL statements. As a result the code is easy to read and memorise while the resulting SQL statement is predictable. It is as type-checked as possible and does not use any wild-card types resulting in confusing errors.

Features:
- Cached queries, meaning that the SQL string is built only once, for improved performance and re-usability.
- Streaming from and to the database
- Extensive support for JSONB fields
- Support for Array fields
- Rich support for timestamp fields
- Support for sub-queries
- Support for transactions

This library is also available for ZIO 2 [kuzminki-zio-2](https://github.com/karimagnusson/kuzminki-zio-2)  

See full documentation at [https://kuzminki.io/](https://kuzminki.io/)

Take a look at [kuzminki-zhttp-demo](https://github.com/karimagnusson/kuzminki-zhttp-demo) for a example of a REST API using this library and [zio-http](https://github.com/dream11/zio-http)

Release 0.9.4-RC4 adds the following featurees:
- Select row as JSON string
- Debug, print query
- Insert, update null values
- Timestamp, Date, Time methods

Attention! There are some changes to the API in this version. They affect cached queries.

#### Sbt
```sbt
// compiled for Scala 2.13.8 and ZIO 1.0.12
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.4-RC4"
```

#### Example
```scala
import zio._
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
      .values(("Joe", 35))
      .run
    
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
      .run
    
    _ <- ZIO.foreach(clients) {
      case (id, username, age) =>
        putStrLn(s"$id $username $age")
    }
  } yield ()

  val dbLayer = Kuzminki.layer(DbConfig.forDb("company"))

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    job.provideCustomLayer(dbLayer).exitCode
  }
}
```








