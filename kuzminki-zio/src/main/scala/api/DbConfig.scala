/*
* Copyright 2021 Kári Magnússon
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package kuzminki.api

import java.util.Properties


object DbConfig {
  def forDb(db: String) = new DbConfig(db)
}

class DbConfig(db: String) {

  val props = new Properties()

  var poolSize = 10
  var minPoolSize = 3

  private var host = "localhost"

  private var port = "5432"

  def url = s"jdbc:postgresql://$host:$port/$db"

  def withMaxPoolSize(value: Int) = {
    poolSize = value
    this
  }

  def withMinPoolSize(value: Int) = {
    minPoolSize = value
    this
  }

  def withHost(value: String) = {
    host = value
    this
  }

  def withPort(value: String) = {
    port = value
    this
  }

  def withUser(value: String) = {
    props.setProperty("user", value)
    this
  }

  def withPassword(value: String) = {
    props.setProperty("password", value)
    this
  }

  def withOptions(options: Map[String, String]) = {
    options.foreach {
      case (key, value) =>
        props.setProperty(key, value)
    }
    this
  }
}

















