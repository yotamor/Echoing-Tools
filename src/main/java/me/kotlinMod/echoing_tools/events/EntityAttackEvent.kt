package me.kotlinMod.echoing_tools.events

import me.kotlinMod.echoing_tools.modItems.ToolBuilder
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.SwordItem
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import me.kotlinMod.echoing_tools.EchoingTools.ModID.timesSinceSwordAbility
import me.kotlinMod.echoing_tools.modItems.ModItems
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class EntityAttackEvent : AttackEntityCallback {

    override fun interact(player: PlayerEntity, world: World, hand: Hand, entity: Entity, hitResult: EntityHitResult?): ActionResult {
        val mainHandItem: Item = player.mainHandStack.item
        if (entity !is LivingEntity || !entity.isAlive || player.isSpectator || player.itemCooldownManager.isCoolingDown(mainHandItem) || world.isClient) return ActionResult.PASS


        if (player.hasStatusEffect(ModItems.sculkiness) && !player.getStatusEffect(ModItems.sculkiness)!!.isInfinite) {
                player.setStatusEffect(StatusEffectInstance(ModItems.sculkiness, player.getStatusEffect(ModItems.sculkiness)!!.duration - 50, 0), null)
        }

        if (mainHandItem !is SwordItem) {
            return ActionResult.PASS
        }

        if (timesSinceSwordAbility.containsKey(entity.uuid)) {
            if (timesSinceSwordAbility[entity.uuid]!! >= 3u)
                ability(entity, player, world, mainHandItem)
            else {
                timesSinceSwordAbility[entity.uuid] = (timesSinceSwordAbility[entity.uuid]!! + 1u).toUByte()
            }
        } else {
            if (mainHandItem.material == ToolBuilder.NETH_SCULK_SWORD)
                timesSinceSwordAbility[entity.uuid] = 1u
        }

        return ActionResult.PASS
    }

    private fun ability(livingEntity: LivingEntity, player: PlayerEntity, world: World, item: SwordItem) {
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
            livingEntity.damage(DamageSources(world.registryManager).sonicBoom(player), (3f + item.attackDamage - 8))
            world.playSound(null, livingEntity.blockPos, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.HOSTILE, 3f, 1f)
            timesSinceSwordAbility[livingEntity.uuid] = 0u
        }
    }
}