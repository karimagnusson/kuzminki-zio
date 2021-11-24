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


case class DbConfig(url: String, props: Properties, poolSize: Int)

object DbConfig {
  def forDb(dbName: String) = new DbConfigBuilder(dbName)
}

class DbConfigBuilder(dbName: String) {

  private var poolSize = 10

  private var hostOpt: Option[String] = None

  private var portOpt: Option[String] = None

  private val props = new Properties()

  private def getUrl = {
    val host = hostOpt.getOrElse("localhost")
    val port = portOpt.getOrElse("5432")
    s"jdbc:postgresql://$host:$port/$dbName"
  }

  def withPoolSize(value: Int) = {
    poolSize = value
    this
  }

  def withHost(value: String) = {
    hostOpt = Some(value)
    this
  }

  def withPort(value: String) = {
    portOpt = Some(value)
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

  def getConfig = DbConfig(getUrl, props, poolSize)
}



















