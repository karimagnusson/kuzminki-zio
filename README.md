[![license](https://img.shields.io/github/license/rdbc-io/rdbc.svg?style=flat-square)](https://github.com/rdbc-io/rdbc/blob/master/LICENSE)
[![Gitter](https://img.shields.io/gitter/room/gitterHQ/gitter.svg?style=flat-square)](https://gitter.im/kuzminki/kuzminki-zio)
# kuzminki-zio

Kuzminki is query builder and access library for PostgreSQL written in Scala.  
This version is for ZIO 1.

Kuzminki is written for those who like SQL. Queries are written with the same logic you write SQL statements. As a result the code is easy to read and memorise while the resulting SQL statement is predictable.

This library is also available for ZIO 2 [kuzminki-zio-2](https://github.com/karimagnusson/kuzminki-zio-2)  
And For Akka [kuzminki-akka](https://github.com/karimagnusson/kuzminki-akka)

See full documentation at [https://kuzminki.io/](https://kuzminki.io/)

You can take a look at [kuzminki-zhttp-demo](https://github.com/karimagnusson/kuzminki-zhttp-demo) for a example of a REST API using this library and [zio-http](https://github.com/dream11/zio-http)

#### Sbt
```sbt
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.4-RC1"
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

#### Streaming
Streaming is available in the latest push, not in version 0.9.4-RC1.

Streaming from the database.
```scala
sql
  .select(client)
  .cols3(_.all)
  .all
  .orderBy(_.id.asc)
  .stream
  .map(makeLine)
  .run(fileSink(Paths.get("clints.txt")))
```

Streaming into the database. The same logic can be used for UPDATE and DELETE.
```scala
val insertStm = sql
  .insert(client)
  .cols2(t => (t.username, t.age))
  .cache

// insert one at a time.
readFileIntoStream("clints.txt")
  .map(makeTupleFromLine)
  .run(insertStm.asSink)

// insert in chunks of 100 using transaction.
readFileIntoStream("clints.txt")
  .map(makeTupleFromLine)
  .transduce(testStm.collect(100))
  .run(insertStm.asChunkSink)
```

#### Transaction
Transaction is available in the latest push, not in version 0.9.4-RC1.

Do INSERT, UPDATE and DELETE in one transaction.
```scala
sql.transaction(
  sql.insert(client).cols2(t => (t.username, t.age)).render(("Joe", 25)),
  sql.update(client).set(_.age ==> 31).where(_.id === 45),
  sql.delete(client).where(_.id === 83)
).run
```

Insert many rows in one transaction. The same logic can be used for UPDATE and DELETE.
```scala
val clientList: List[Tuple2[String, Int]] = //...

insertStm.execList(clientList)
```




















