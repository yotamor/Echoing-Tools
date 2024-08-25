package me.kotlinMod.echoing_tools.events

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.wardenTear
import net.minecraft.entity.effect.StatusEffects

class EntityEffectEvent : EffectAddCallBack {
    override fun effectAdd(livingEntity: LivingEntity, statusEffectInstance: StatusEffectInstance, source: Entity?, callbackInfoReturnable: CallbackInfoReturnable<StatusEffectInstance>): ActionResult {
        return if (livingEntity is PlayerEntity && statusEffectInstance.effectType == StatusEffects.DARKNESS && livingEntity.inventory.contains(ItemStack(wardenTear)))
            ActionResult.FAIL
        else ActionResult.SUCCESS
    }
}