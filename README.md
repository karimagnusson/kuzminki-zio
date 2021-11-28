[![license](https://img.shields.io/github/license/rdbc-io/rdbc.svg?style=flat-square)](https://github.com/rdbc-io/rdbc/blob/master/LICENSE)
[![Gitter](https://img.shields.io/gitter/room/gitterHQ/gitter.svg?style=flat-square)](https://gitter.im/kuzminki/kuzminki-zio)
# kuzminki-zio

Kuzminki-zio is query builder and access library for PostgreSQL and [ZIO](https://zio.dev/) written in Scala.

The project is currently under development and not fully tested. It currently works with ZIO 1.x.

If you have any questions about the project feel free to post on Gitter or contact me directly on telegram @karimagnusson.

#### Sbt
```sbt
libraryDependencies += "io.github.karimagnusson" % "kuzminki-zio" % "0.9.0"
```

#### Example
```scala
import kuzminki.api._

class Client extends Model("client") {
  val id = column[Int]("id")
  val username = column[String]("username")
  val age = column[Int]("age")
  def all = (id, username, age)
}

val client = Model.get[Client]

for {
  db <- Kuzminki.forConfig(DbConfig.forDb("company").getConfig)
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
  _ <- db.exec { sql.delete(client).whereOne(_.id === 7).render }
  clients <- db.query {
      sql
        .select(client)
        .cols3(_.all)
        .whereOne(_.age > 25)
        .render
    }
} yield clients // Seq[Tuple3[Int, String, Int]]
```

#### Connecting to the database
```scala
import kuzminki.api._

val config = DbConfig
  .forDb("<db-name>")
  .withPoolSize(10) // default = 10
  .withHost("<host>") // default = localhost
  .withPort("<port>") // default = 5432
  .withUser("<user>")
  .withPassword("<password>")
  .withOptions(Map(...))
  .getConfig

for { 
  db <- Kuzminki.forConfig(config)
  ...
} yield ...
```

#### Defining a model
```scala
import kuzminki.api._
import java.sql.Timestamp

class User extends Model("user_profile") {
  val id = column[Int]("id")
  val username = column[String]("username")
  val email = column[String]("email")
  val name = column[String]("name")
  val age = column[Int]("age")
  val gender = column[String]("gender")
  val country = column[String]("country")
  val city = column[String]("city")
  val discount = column[Int]("discount")
  val isActive = column[Boolean]("is_active")
  val created = column[Timestamp]("created")
}

Model.register[User]

class Customer extends Model("customer") {
  val id = column[Int]("id")
  val userId = column[Int]("user_id")
  val spending = column[Int]("spending")
}

Model.register[Customer]

class Newsletter extends Model("newsletter") {
  val email = column[String]("email")
  val isSubscribed = column[Boolean]("is_subscribed")
}

Model.register[Newsletter]
```

#### Create an instance of a model
```scala
// Create an instance of the model for later use and make sure there is only one instance of the model.
Model.register[User]

// Get an existing instance of the model. If it does not exist, it is created.
val user = Model.get[User]
```

#### Select query
```scala
import kuzminki.api._

val user = Model.get[User]

for {
  db <- Kuzminki.forConfig(DbConfig.forDb("company").getConfig)
  users <- db.query {
    sql
      .select(user)
      .cols2(t => (
        t.id,
        t.username
      ))
      .where(t => Seq(
        t.gender === "f",
        t.age > 25
      ))
      .orderByOne(_.age.desc)
      .limit(10)
      .render
  }
} yield users // Seq[Tuple2[Int, String]]
```
Statement:
```sql
SELECT
  "id",
  "username"
FROM "user_profile"
WHERE "gender" = 'f'
AND "age" > 25
ORDER BY "age" DESC
LIMIT 10
```

#### Conditions
```scala
.whereOne(_.id > 100)

.where(t => Seq(
    t.gender === "f",
    t.age > 25
))
```
AND / OR
```scala
import kuzminki.fn._

.where(t => Seq(
  t.age > 25,
  Or(
    t.country === "RU",
    t.country === "FR"
  )
))
// WHERE "age" > 25 AND ("country" == 'RU' OR "country" == 'FR')

.whereOne(t => Or(
  And(
    t.country === "RU",
    t.city === "Moscow"
  ),
  And(
    t.country === "FR",
    t.city === "Paris"
  )
))
// WHERE ("country" == 'RU' AND "city" == 'Moscow') OR ("country" == 'FR' AND "city" == 'Paris')
```
Optional conditions
```scala
.whereOpt(_.id > Some(100))

.whereOpts(t => Seq(
  t.gender === None,
  t.age > Some(25)
))
// WHERE "age" > 25

.whereOpts(t => Seq(
  t.age > Some(25),
  Or.opts(
    t.country === Some("RU"),
    t.country === Some("FR")
  )
))
// WHERE "age" > 25 AND ("country" == 'RU' OR "country" == 'FR')
```

#### Join
```scala
sql
  .select(user, customer)
  .cols3(t => (
    t.a.id,
    t.a.username,
    t.b.spending
  ))
  .joinOn(_.id, _.userId)
  .where(t => Seq(
    t.a.age > 25,
    t.b.spending > 1000
  ))
  .orderByOne(_.b.spending.desc)
  .limit(10)
  .render
  // returns Tuple3[Int, String, Int]
```
```sql
SELECT
  "a"."id",
  "a"."username",
  "b"."spending"
FROM "user_profile" "a"
INNER JOIN "customer" "b"
ON "a"."id" = "b"."user_id"
WHERE "a"."age" > 25
AND "b"."spending" > 1000
ORDER BY "b"."spending" DESC
LIMIT 10
```

#### Nested query
```scala
val newsletter = Model.get[Newsletter]

sql
  .select(user)
  .cols2(_.basic)
  .whereOne(_.email.in(
    sql
      .select(newsletter)
      .cols1(_.email)
      .whereOne(_.isSubscribed === true)
      .asSubquery
  ))
  .render
```
```sql
SELECT
  "id",
  "username"
FROM "user_profile"
WHERE "email" = ANY(
  SELECT "email"
  FROM "newsletter"
  WHERE "is_subscribed" = true
)
```

#### Cache
Cached query is built only once and the execution has the same performance a raw SQL query.
```scala
val stm = sql
  .select(user)
  .cols1(_.username)
  .all
  .orderByOne(_.age.asc)
  .cacheWhere2(t => (
    t.country.cacheEq,
    t.age.cacheGt
  ))

db.query {
  stm.render(("CN", 25))
}
```
```sql
SELECT "username"
FROM "user_profile"
WHERE "country" = 'CN'
AND "age" > 25
ORDER BY "age" ASC
```
Cached and uncached WHERE conditions.
```scala
val stm = sql
  .select(user)
  .cols1(_.username)
  .whereOne(_.age > 25)
  .orderByOne(_.age.asc)
  .cacheWhere1(_.country.cacheEq)

db.query {
  stm.render("CN")
}
```
```sql
SELECT "username"
FROM "user_profile"
WHERE "age" > 25
AND "country" = 'CN'
ORDER BY "age" ASC
```

### Insert

#### Basic
```scala
db.exec {
  sql
    .insert(user)
    .cols2(t => (
      t.username,
      t.email
    ))
    .render(("bob", "bob@mail.com"))
}
```
Cache the statement
```scala
val stm = sql
  .insert(user)
  .cols2(t => (
    t.username,
    t.email
  ))
  .cache

db.exec {
  stm.render(("bob", "bob@mail.com"))
}
```
If you need to insert columns that exceed the limits of a tuple, larger than 22.
```scala
sql
  .insert(user)
  .data(t => Seq(
    t.username ==> "bob",
    t.email ==> "bob@mail.com"
  ))
  .render
```
```sql
INSERT INTO "user_profile" ("username", "email") VALUES ('bob', 'bob@mail.com')
```

#### Insert returning
```scala
val stm = sql
  .insert(user)
  .cols2(t => (
    t.username,
    t.email
  ))
  .returning3(t => (
    t.id,
    t.username,
    t.email
  ))
  .render(("bob", "bob@mail.com"))

db.query(stm)
```
```sql
INSERT INTO "user_profile"
("username", "email")
VALUES ('bob', 'bob@mail.com')
RETURNING
  "id",
  "username",
  "email"
```

#### Insert on conflict do nothing
```scala
sql
  .insert(user)
  .cols2(t => (
    t.username,
    t.email
  ))
  .onConflictDoNothing
  .render(("bob", "bob@mail.com"))
```
```sql
INSERT INTO "user_profile"
("username", "email")
VALUES ('bob', 'bob@mail.com')
ON CONFLICT DO NOTHING
```

#### Upsert
```scala
val stm = sql
  .insert(user)
  .cols2(t => (
    t.username,
    t.email
  ))
  .onConflictOnColumn(_.username)
  .doUpdateOne(_.email)
  .cache

db.exec {
  stm.render(("bob", "bob@hotmail.com"))
}
```
```sql
INSERT INTO "user_profile"
("username", "email")
VALUES ('bob', 'bob@mail.com')
ON CONFLICT ("username")
DO UPDATE SET "email" = 'bob@mail.com'
```

### Update
```scala
sql
  .update(user)
  .setOne(_.country ==> "JP")
  .whereOne(_.id === 103)
  .render
```
```sql
UPDATE "user" SET "country" = 'JP' WHERE id = 103
```

#### Update returning
```scala
sql
  .update(user)
  .set(t => Seq(
    t.country ==> "IS",
    t.city ==> "Reykjavik"
  ))
  .whereOne(_.id === 31)
  .returning4(t => (
    t.id,
    t.email,
    t.country,
    t.city
  ))
  .render
```
```sql
UPDATE "user_profile"
SET country = 'IS',
       city = 'Reykjavik'
WHERE "id" = 31
RETURNING
  "id",
  "email",
  "country",
  "city"
```

#### Cache update
```scala
val stm = sql
  .update(user)
  .cacheSet2(t => (
    t.isActive.cacheAssign,
    t.discount.cacheIncrement
  ))
  .cacheWhere1(_.id.cacheEq)

db.exec {
  stm.render((true, 10), 27)
}
```
```sql
UPDATE "user_profile"
SET "is_active" = true,
     "discount" = "discount" + 10
WHERE "id" = 27
```

### Delete
```scala
sql
  .delete(user)
  .whereOne(_.id === 103)
  .render
```
```sql
DELETE FROM "user_profile" WHERE "id" = 103
```

#### Count
```scala
sql
  .count(user)
  .whereOne(_.country === "IT")
  .render
```
```sql
SELECT count(*) FROM "user_profile" WHERE "country" = 'IT'
```
#### Avg Sum Max Min
```scala
import kuzminki.api._
import kuzminki.fn._

sql
  .select(user)
  .cols3(t => (
    Avg.int(t.age),
    Max.int(t.age),
    Min.int(t.age)
  ))
  .whereOne(_.country === "US")
  .render
```
```sql
SELECT
  avg("age"), 2),
  max("age"),
  min("age")
FROM "user_profile"
WHERE "country" = 'US'
```

#### Data types

Postgres                  | Scala
--------------------------|-----------------------------
varchar / text            | String
bool                      | Boolean
int2                      | Short
int4                      | Int
int8                      | Long
float4                    | Float
float8                    | Double
numeric                   | BigDecimal
date                      | java.sql.Date
time                      | java.sql.Time
timestamp                 | java.sql.Timestamp


#### Condition operators

|Operator    | Alternative    | Cache             | Column type
|------------|----------------|-------------------|-------------------
|===         | matches        | cacheEq           | Any
|!==         | not            | cacheNot          | Any
|>           | gt             | cacheGt           | Numbers and time
|<           | lt             | cacheLt           | Numbers and time
|>=          | gte            | cacheGte          | Numbers and time
|<=          | lte            | cacheLte          | Numbers and time
|~           | reMatch        | cacheReMatch      | String
|~*          | reIMatch       | cacheReIMatch     | String
|!~          | reNotMatch     | cacheReNotMatch   | String
|!~*         | reNotIMatch    | cacheReNotIMatch  | String
|            | like           | cacheLike         | String
|            | startsWith     | cacheStartsWith   | String
|            | endsWith       | cacheEndsWith     | String
|            | similarTo      | cacheSimilarTo    | String
|            | isNull         |                   |
|            | isNotNull      |                   |
|            | in             |                   | Any
|            | notIn          |                   | Any


#### Update operators

| Operator   | Cache
|------------|-----------------
| ==>        | cacheAssign
| +=         | cacheIncrement
| -=         | cacheDecrement














