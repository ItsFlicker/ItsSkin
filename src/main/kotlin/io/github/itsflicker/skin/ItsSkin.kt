package io.github.itsflicker.skin

import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.ConfigNodeTransfer
import taboolib.module.configuration.Configuration

@ConfigNode(bind = "config.yml")
object ItsSkin : Plugin() {

    @Config
    lateinit var conf: Configuration
        private set

    @ConfigNode
    val skins = ConfigNodeTransfer<List<Map<String, *>>, Map<String, Skin>> {
        associate {
            val id = it["id"].toString()
            val source = it["source"].toString()
            val cmd = it["custom-model-data"].toString().toInt()
            id to Skin(id, source, cmd)
        }
    }

}