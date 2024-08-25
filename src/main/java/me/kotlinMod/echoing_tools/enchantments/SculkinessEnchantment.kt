package me.kotlinMod.echoing_tools.enchantments

import me.kotlinMod.echoing_tools.modItems.ModItems
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack

class SculkinessEnchantment(rarity: Rarity, target: EnchantmentTarget, slotTypes: Array<out EquipmentSlot>) : Enchantment(rarity, target, slotTypes) {

    override fun isAcceptableItem(stack: ItemStack): Boolean {
        return stack.item == ModItems.nethSculkSword
    }

    override fun isTreasure(): Boolean {
        return true
    }

}