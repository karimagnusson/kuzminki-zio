| Discord | Twitter |
| --- | --- |
| [![badge-discord](https://img.shields.io/discord/629491597070827530?logo=discord)](https://discord.com/channels/629491597070827530/1063139826636963931) | [![Twitter Follow](https://img.shields.io/twitter/follow/kuzminki_lib?label=follow&style=flat&logo=twitter&color=brightgreen)](https://twitter.com/kuzminki_lib) |

# kuzminki-zio

#### Sbt
```sbt
// compiled for Scala 2.13.8 and ZIO 1.0.17
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.4-RC5"
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
    
  } yield clients

  val dbLayer = Kuzminki.layer(DbConfig.forDb("company"))

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    job.provideCustomLayer(dbLayer).exitCode
  }
}
```

#### About Kuzminki
Kuzminki is feature-rich query builder and access library for PostgreSQL written in Scala. The approach of this library is to write SQL statements in Scala with the same structure as SQL is written. Rather than having the API natural to Scala logic, for example similar to how you work with collections, this approach seeks to make the Scala code as similar to the resulting SQL statement as possible. The goal of this approach is to make it easy to read the code and understand the resulting SQL statement. It should also make the API intuitive and easy to memorise. With this approach, it becomes practical to write quite complicated queries while still being able to understand what they do. For example subqueries and queries where column values are modified. With a different approach, the user would have to learn the libraries own unique logic. But since the logic of SQL is already known, such complexity becomes practical. With a feature-rich API, the user can find solutions and avoid writing raw SQL statements that are not checked by the compiler. The goal of this project is to offer a productive and readable API to work with Postgresql and take advantage of its many features.

#### Features
Kuzminki supports jsonb and array fields. When doing insert, update or delete, it offers one or more column values to be returned. It offers many options for searching and returning datetime column values. It supports subqueries, both as a condition in a search and collect values to be returned to the client. Transactions are supported, both as a way to do bulk-operations and to do multiple different operations. Data can be streamed to and from the database. Rows can be delivered to the client as, tuples, case classes or vectors. Data for insert can be a tuple or a case class. Types are checked as much as possible and wild-card types that result in unclear errors are not used. Kuzminki comes with native support for ZIO as a layer.

#### JSON
Postgresql comes with a jsonb field to store and query data in JSON format. Being able to take advantage of the jsonb field opens up great flexibility in organizing your data. You can work with structured and unstructured data to get the best of both worlds. Kuzminki offers extensive support for querying and updating jsonb fields. Also, Kuzminki offers the ability to return rows as JSON strings. This can be useful when, for example, you need to service JSON directly to the client you can do so without having to transform the data. You can organise how your object is formed from the table columns. For example if you need some columns to be in a nested object. If you need to create a big object from multiple tables, you can do so with a single query using subqueries. Take a look at the [zio-http demo project](https://github.com/karimagnusson/kuzminki-zhttp-demo) for examples of these features.

#### Performance
Statements can be cached for better performance and reusability. This means that the SQL string is created only once. Execution time should be the same as running a raw statement with JDBC. All statements can be cached except for SELECT statements with optional WHERE arguments.

#### Only Postgres
Kuzminki supports only Postgresql. It could be adapted for use with other databases if there is interest in that. But given that it has support for many postgres specific features, support for another database would require it’s own project rather than a size fits all approach. Therefore, at the moment the goal is to deliver a good library for Postgres. That being said, there are other Postgres compatible databases that work with Kuzminki. For example CockroachDB. For those looking to scale up, it might be a good choice.

This library is also available for ZIO 2 [kuzminki-zio-2](https://github.com/karimagnusson/kuzminki-zio-2)  

See full documentation at [https://kuzminki.io/](https://kuzminki.io/)

Take a look at [kuzminki-zhttp-demo](https://github.com/karimagnusson/kuzminki-zhttp-demo) for a example of a REST API using this library and [zio-http](https://github.com/dream11/zio-http)

#### In the latest push
Improved connection pool  
GROUP BY and HAVING

```scala
sql
  .select(user)
  .cols2(t => (
    t.gender,
    Agg.avg(t.age)
  ))
  .where(_.age > 0)
  .groupBy(_.gender)
  .having(_.gender !== "")
  .orderBy(t => Agg.avg(t.age).desc)
  .run
```
If you wish to cache the query:
```scala
val stm = sql
  .select(user)
  .cols2(t => (
    t.gender,
    Agg.avg(t.age)
  ))
  .all
  .groupBy(_.gender)
  .having(_.gender !== "")
  .orderBy(t => Agg.avg(t.age).desc)
  .pickWhere1(_.gender.use > Arg)
  .cache

stm.run(0)
```

#### In the latest version, 0.9.4-RC5

Changes:  
Improved exceptions. Queries return typed exception SQLException.  
Improved custom functions.  
Added Pages.

#### Custom functions
```scala
import kuzminki.fn.StringFn

case class FullName(
  title: String,
  first: TypeCol[String],
  second: TypeCol[String]
) extends StringFn {
  val name = "full_name"
  val template = s"concat_ws(' ', '$title', %s, %s)"
  val cols = Vector(first, second)
}

sql
  .select(user)
  .cols2(t => (
    t.id,
    FullName("Mr", t.firstName, t.lastName)
  ))
  .where(_.id === 10)
  .runHead

```
If you need to have the driver fill in arguments:
```scala
case class FullNameParam(
  title: String,
  first: TypeCol[String],
  second: TypeCol[String]
) extends StringParamsFn {
  val name = "full_name"
  val template = s"concat_ws(' ', ?, %s, %s)"
  val cols = Vector(first, second)
  val params = Vector(title)
}
```

#### Pages
```scala
val pages = sql
  .select(user)
  .cols3(t => (
    t.id,
    t.firstName,
    t.lastName)
  ))
  .orderBy(_.id.asc)
  .asPages(10) // 10 rows

val job = for {
  next  <- pages.next
  page3 <- pages.page(3)
} yield (next, page3)
```








