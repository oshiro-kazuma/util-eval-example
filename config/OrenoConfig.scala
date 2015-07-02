// config/OrenoConfig.scala
import config.MyConfig

new MyConfig {
  val paramSettings = Map(
    1 -> Seq(
      Param(
        key = "character_name",
        code = """ master("character")(user \ "character_id") \ "name" """),
      Param(
        key = "character_image_url",
        code = """ master("character")(user \ "character_id") \ "image_url" """),
      Param(
        key = "character_attribute",
        code = """ master("attribute")( (master("character")(user \ "character_id")) \ "attribute_id" ) \ "name" """)),
    2 -> Seq(/* todo */)
  )
}
