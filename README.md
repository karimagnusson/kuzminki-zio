---

> **Warning**
> This project has moved to Slinq

Kuzminki has been renamed and restructured as https://github.com/karimagnusson/slinq.

All future development will continue in the new repository:
- GitHub: https://github.com/karimagnusson/slinq
- Documentation: https://slinq.kotturinn.com/

**What changed?**

- Renamed from Kuzminki to Slinq
- Consolidated all versions (ZIO 2 and EC) into a single repository
- Updated package names from kuzminki.* to slinq.pg.*
- Scala 3 only (dropped Scala 2.13 and ZIO 1 support)

**Migration**

Update your imports:
```scala
// Old
import kuzminki.api._

// New
import slinq.pg.zio.api.*
import slinq.pg.zio.api.given
```

Thank you for using Kuzminki. See you at Slinq!

---

# Kuzminki

A PostgreSQL query builder for Scala/ZIO that mirrors SQL structure directly in code.

```scala
// available for Scala 2.13 and Scala 3
libraryDependencies += "io.github.karimagnusson" %% "kuzminki-zio" % "0.9.5"
```

## Why Kuzminki?

Most query builders abstract SQL behind collection-like APIs. Kuzminki takes the opposite approach: your Scala code reads like the SQL it generates. This makes complex queries readable and the API intuitive - you already know SQL.

```scala
sql
  .select(client)
  .cols3(_.all)
  .where(_.age > 25)
  .orderBy(_.username.asc)
  .limit(5)
  .run
```

## Features

- Native ZIO integration as a layer
- Full JSONB support - query, update, and return rows as JSON
- Array field operations
- Subqueries in WHERE clauses and SELECT columns
- Streaming to and from the database
- Statement caching for JDBC-level performance
- Transactions for bulk and mixed operations
- Type-safe throughout - no wildcard types with unclear errors

## Postgres by design

Kuzminki focuses exclusively on PostgreSQL rather than targeting lowest-common-denominator SQL. This allows deep support for Postgres-specific features like JSONB and arrays. Works with Postgres-compatible databases like CockroachDB.

## Example

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

## Resources

- [Full documentation](https://kuzminki.kotturinn.com/)
- [ZIO 2 version](https://github.com/karimagnusson/kuzminki-zio-2)

Please report bugs if you find them and feel free to DM me on Twitter if you have any questions.
