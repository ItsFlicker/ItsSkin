package io.github.itsflicker.skin

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class Skin(
    val id: String,
    val source: String,
    val customModelData: Int
) {

    fun build(): ItemStack {
        val item = CustomStack.getInstance(source)!!.itemStack
        val meta = item.itemMeta!!
        meta["type", PersistentDataType.STRING] = id
        item.itemMeta = meta
        return item
    }

}