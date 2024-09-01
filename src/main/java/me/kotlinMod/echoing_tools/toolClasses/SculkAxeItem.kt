package me.kotlinMod.echoing_tools.toolClasses

import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.furySurge
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class SculkAxeItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings) : AxeItem(material, attackDamage, attackSpeed, settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient || user.itemCooldownManager.isCoolingDown(this) || user.mainHandStack.item != this) return super.use(world, user, hand)

        user.damage(DamageSources(user.world.registryManager).magic(), 2f)
        val furySurgeInstance = StatusEffectInstance(furySurge, 400, 0, false, true, true)
        user.addStatusEffect(furySurgeInstance, user)
        user.itemCooldownManager.set(this, 600)
        return super.use(world, user, hand)
    }
}