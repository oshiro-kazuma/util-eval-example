// src/main/scala/Main.scala
import config.MyConfig
import com.twitter.util.Eval
import play.api.libs.json.{Json, JsValue}

object Main extends App {
  // config を読み込む
  val config = Eval[MyConfig](new java.io.File("config/OrenoConfig.scala"))

  // ユーザーデータを解決する
  def userDataResolve(settingId: Int, user: JsValue): JsValue = {
    import DataExample.masterResolver
    Json.toJson((config.paramSettings(settingId) map (p => p.key -> p.method(masterResolver, user))).toMap)
  }

  println(userDataResolve(1, DataExample.user))
}

// サンプルデータです
object DataExample {
  val master = Map(
    "character" -> Map(
      "1" -> Json.parse("""{"name": "ビッグ・キャッスル", "image_url": "http://xxx", "attribute_id": "2"}"""),
      "2" -> Json.parse("""{"name": "スモール・キャッスル", "image_url": "http://xxx", "attribute_id": "1"}""")
    ),
    "attribute" -> Map(
      "1" -> Json.parse("""{"name": "火", "image_url": "http://xxx"}"""),
      "2" -> Json.parse("""{"name": "水", "image_url": "http://xxx"}"""),
      "3" -> Json.parse("""{"name": "風", "image_url": "http://xxx"}"""),
      "4" -> Json.parse("""{"name": "地", "image_url": "http://xxx"}""")
    )
  )
  val masterResolver = (name: String) => (id: JsValue) => master.get(name).get(id.as[String])

  val user = Json.parse("""{"user_id": 1, "user_name": "大城", "character_id": "1"}""")
}
