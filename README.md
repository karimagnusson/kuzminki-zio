[![license](https://img.shields.io/github/license/rdbc-io/rdbc.svg?style=flat-square)](https://github.com/rdbc-io/rdbc/blob/master/LICENSE)
[![Gitter](https://img.shields.io/gitter/room/gitterHQ/gitter.svg?style=flat-square)](https://gitter.im/kuzminki/kuzminki-zio)
# kuzminki-zio

Kuzminki is query builder and access library for PostgreSQL written in Scala.  
This version is for ZIO 1.

Kuzminki is written for those who like SQL. Queries are written with the same logic you write SQL statements. As a result the code is easy to read and memorise while the resulting SQL statement is predictable.

This library is also available for ZIO 2 [kuzminki-zio-2](https://github.com/karimagnusson/kuzminki-zio-2)  

See full documentation at [https://kuzminki.io/](https://kuzminki.io/)

You can take a look at [kuzminki-zhttp-demo](https://github.com/karimagnusson/kuzminki-zhttp-demo) for a example of a REST API using this library and [zio-http](https://github.com/dream11/zio-http)

Release 0.9.4-RC3 adds the following features:
- Support for jsonb field
- Support for uuid field
- Streaming from and to the database
- Support for transactions
- Ability to use postgres functions
- Improved ability to cache statements


Attention! There are some changes to the API in this version. They affect INSERT, UPDATE and DELETE.

#### Sbt
```sbt
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.4-RC3"
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

#### In the latest push:

#### Print the query

```scala

sql
  .select(client)
  .cols3(_.all)
  .where(_.age > 25)
  .limit(5)
  .printSqlWithArgs
  .run
// SELECT "id", "username", "age" FROM "client" WHERE "age" > 25 LIMIT 5

sql
  .update(client)
  .set(_.age ==> 24)
  .where(_.id === 4)
  .printSql
  .run
// UPDATE "client" SET "age" = ? WHERE id = ?

```

#### INSERT / UPDATE null values

```scala

sql
  .insert(client)
  .cols2(t => (t.username, t.age.asOpt))
  .values(("Joe", None))
  .run
    
sql
  .update(client)
  .set(_.age.asOpt ==> None)
  .where(_.id === 4)
  .run

```

#### Cached queries have a new syntax

```scala

sql
  .insert(client)
  .cols2(t => (t.username, t.age))
  .cache
    
sql
  .update(client)
  .pickSet1(_.age.use ==> Arg)
  .pickWhere1(_.id.use === Arg)
  .cache
    
sql.delete(client).pickWhere1(_.id.use === Arg).cache
    
sql
  .select(client)
  .cols3(_.all)
  .all
  .limit(5)
  .pickWhere1(_.age.use > Arg)
  .cache

```

#### Timestamp / Date / Time methods

```scala

class Demo extends Model("demo") {
  val id = column[Int]("id")
  val eventTime = column[Time]("event_time")
  val eventDate = column[Date]("event_date")
  val updatedAt = column[Timestamp]("updated_at")
}

val demo = Model.get[Demo]

sql
  .update(demo)
  .set(t => Seq(
    t.eventTime += Fn.interval(hours = 3, minutes = 10),
    t.eventDate += Fn.interval(years = 1, days = 2),
    t.updatedAt += Fn.interval(months = 4, hours = 5)
  ))
  .where(_.id === 25)
  .run

sql
  .select(demo)
  .cols3(t => (
    t.eventTime.format("MM:HH"),
    t.eventDate.format("DD Mon YYYY"),
    t.updatedAt.format("DD Mon YYYY MM:HH")
  ))
  .where(_.id === 25)
  .runHead

sql
  .select(demo)
  .cols4(t => (
    t.id,
    t.eventDate.month,
    t.updatedAt.week
    t.updatedAt + Fn.interval(days = 10)
  ))
  .where(t => Seq(
    t.eventDate.year === 2022,
    t.eventDate.quarter === 2
  ))
  .run

```

See: [https://www.postgresql.org/docs/current/functions-datetime.html](https://www.postgresql.org/docs/current/functions-datetime.html)

| Method        | Types           | Arg
| ------------- | --------------- | ----------- |
| +             | All             | PGInterval  |
| -             | All             | PGInterval  |
| age           | Timestamp       |             |
| epochSecs     | Timestamp       |             |
| epochMillis   | Timestamp       |             |
| century       | Timestamp Date  |             |
| decade        | Timestamp Date  |             |
| year          | Timestamp Date  |             |
| quarter       | Timestamp Date  |             |
| month         | Timestamp Date  |             |
| week          | Timestamp Date  |             |
| day           | Timestamp Date  |             |
| dow           | Timestamp Date  |             |
| doy           | Timestamp Date  |             |
| isoDow        | Timestamp Date  |             |
| isoYear       | Timestamp Date  |             |
| hour          | Timestamp Time  |             |
| minute        | Timestamp Time  |             |
| second        | Timestamp Time  |             |
| microseconds  | Timestamp Time  |             |
| milliseconds  | Timestamp Time  |             |
| asDate        | Timestamp       |             |
| asTime        | Timestamp       |             |
| asTimestamp   | Date            |             |
| asString      | All             |             |
| format        | All             | String      |
| setNow        | All             |             |
| +=            | All             | PGInterval  |
| -=            | All             | PGInterval  |











