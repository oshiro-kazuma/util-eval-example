// src/main/scala/config/MyConfig.scala
package config

import com.twitter.util.Eval
import play.api.libs.json.JsValue

trait MyConfig {

  case class Param(key: String, code: String) {
    type T = ((String) => (JsValue) => JsValue, JsValue) => JsValue
    val method: T = {
      Eval(s"""(master: (String) => (play.api.libs.json.JsValue) => play.api.libs.json.JsValue, user: play.api.libs.json.JsValue) => $code""")
    }
  }

  val paramSettings: Map[Int, Seq[Param]]
}
