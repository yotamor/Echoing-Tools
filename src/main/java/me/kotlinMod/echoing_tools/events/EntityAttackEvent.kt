package me.kotlinMod.echoing_tools.events

import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import me.kotlinMod.echoing_tools.modItems.ModItems
import net.minecraft.item.Item

class EntityAttackEvent : AttackEntityCallback {

    override fun interact(player: PlayerEntity, world: World, hand: Hand, entity: Entity, hitResult: EntityHitResult?): ActionResult {
        val mainHandItem: Item = player.mainHandStack.item
        if (entity !is LivingEntity || !entity.isAlive || player.isSpectator || player.itemCooldownManager.isCoolingDown(mainHandItem) || world.isClient) return ActionResult.PASS


        if (player.hasStatusEffect(ModItems.sculkiness) && !player.getStatusEffect(ModItems.sculkiness)!!.isInfinite) {
                player.setStatusEffect(StatusEffectInstance(ModItems.sculkiness, player.getStatusEffect(ModItems.sculkiness)!!.duration - 50, 0), null)
        }



        return ActionResult.PASS
    }


}