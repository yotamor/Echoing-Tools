package me.kotlinMod.echoing_tools.toolClasses

import me.kotlinMod.echoing_tools.EchoingTools.ModID.timesSinceSwordAbility
import me.kotlinMod.echoing_tools.modItems.ModItems
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

class SculkSwordItem(toolMaterial: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : SwordItem(toolMaterial, attackDamage, attackSpeed, settings) {

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        if (stack.item != this || attacker !is PlayerEntity || attacker.world.isClient) return super.postHit(stack, target, attacker)
        if (timesSinceSwordAbility.containsKey(target.uuid)) {
            if (timesSinceSwordAbility[target.uuid]!! >= 3u)
                ability(target, attacker, stack)
            else {
                timesSinceSwordAbility[target.uuid] = (timesSinceSwordAbility[target.uuid]!! + 1u).toUByte()
            }
        } else {
                timesSinceSwordAbility[target.uuid] = 1u
        }
        return super.postHit(stack, target, attacker)
    }

    private fun ability(livingEntity: LivingEntity, player: PlayerEntity, item: ItemStack) {
        val mainHandItemStack: ItemStack = player.mainHandStack
        val playerTimesSinceSwordAbility: UByte = timesSinceSwordAbility[livingEntity.uuid]!!

        if (EnchantmentHelper.getLevel(ModItems.spread, mainHandItemStack) > 0) {

            if (playerTimesSinceSwordAbility >= 6u) {
                livingEntity.addStatusEffect(
                    StatusEffectInstance(ModItems.sculkiness, 240, 0, true, true, true),
                    player)

                timesSinceSwordAbility[livingEntity.uuid] = 0u

            } else {
                timesSinceSwordAbility[livingEntity.uuid] = (playerTimesSinceSwordAbility + 1u).toUByte()
            }
        }

        else {
            livingEntity.damage(DamageSources(player.world.registryManager).sonicBoom(player), (3f + item.damage - 8))
            player.world.playSound(null, livingEntity.blockPos, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.HOSTILE, 3f, 1f)
            timesSinceSwordAbility[livingEntity.uuid] = 0u
        }
    }

}
