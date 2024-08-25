package me.kotlinMod.echoing_tools.modItems

import com.google.common.collect.Multimap
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ItemStack
import net.minecraft.item.ArmorItem
import java.util.*

class ArmorAttribute : ModifyItemAttributeModifiersCallback {

    override fun modifyAttributeModifiers(stack: ItemStack, slot: EquipmentSlot, attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>) {
        val stackItem = stack.item
        if (stackItem is ArmorItem && stackItem.slotType == slot && stackItem.material == ArmorBuilder.NETH_SCULK) {
            attributeModifiers.put(
                EntityAttributes.GENERIC_MAX_HEALTH,
                EntityAttributeModifier(UUID.nameUUIDFromBytes(stackItem.type.name.toByteArray() /* encodes the armor && armor piece of the armor to bytes then generate an uuid out of it */), "sculk armor health bonus", 1.0, EntityAttributeModifier.Operation.ADDITION)
            )
        }
    }
}