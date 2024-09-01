package me.kotlinMod.echoing_tools.toolClasses

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class SculkPickaxeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : PickaxeItem(material, attackDamage, attackSpeed, settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient || hand == Hand.OFF_HAND || user.itemCooldownManager.isCoolingDown(this)) return super.use(world, user, hand)
        user.addStatusEffect(StatusEffectInstance(StatusEffects.HASTE, 350, 1))
        user.itemCooldownManager.set(this, 600)
        return super.use(world, user, hand)
    }

}