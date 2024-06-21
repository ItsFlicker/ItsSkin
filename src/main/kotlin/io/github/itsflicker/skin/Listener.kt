package io.github.itsflicker.skin

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.giveItem
import taboolib.platform.util.isAir

object Listener {

    @SubscribeEvent
    fun onClick(e: InventoryClickEvent) {
        if (e.view.topInventory !is CraftingInventory) return
        if (!e.isLeftClick || e.isShiftClick) return
        val player = e.whoClicked as Player
        val clicked = e.currentItem
        val cursor = e.cursor
        if (clicked.isAir() || cursor.isAir()) return
        val meta = clicked.itemMeta ?: return
        val type = cursor.itemMeta?.get("type", PersistentDataType.STRING) ?: return
        val previous = meta["skin", PersistentDataType.STRING]
        if (previous == null) {
            if (meta.hasCustomModelData()) return
            e.isCancelled = true
            val skin = ItsSkin.skins.get()[type] ?: return
            val empty = ItsSkin.skins.get()["empty"] ?: return
            cursor.consume()
            player.giveItem(empty.build())
            meta.setCustomModelData(skin.customModelData)
            meta["skin", PersistentDataType.STRING] = type
            clicked.itemMeta = meta
        } else {
            if (type != "empty") return
            e.isCancelled = true
            val skin = ItsSkin.skins.get()[previous] ?: return
            player.giveItem(skin.build())
            cursor.consume()
            meta.setCustomModelData(null)
            meta.remove("skin")
            clicked.itemMeta = meta
        }
    }

    private fun ItemStack.consume() {
        if (amount > 1) amount -= 1
        else type = Material.AIR
    }

}