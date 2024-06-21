package io.github.itsflicker.skin

import org.bukkit.entity.Player
import taboolib.common.platform.command.*
import taboolib.expansion.createHelper
import taboolib.platform.util.giveItem

@CommandHeader("itsskin", permission = "itsskin.command")
object Command {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(optional = true)
    val get = subCommand {
        dynamic("type") {
            suggest {
                ItsSkin.skins.get().keys.toList()
            }
            execute<Player> { sender, _, argument ->
                val skin = ItsSkin.skins.get()[argument]!!
                sender.giveItem(skin.build())
            }
        }
    }

}