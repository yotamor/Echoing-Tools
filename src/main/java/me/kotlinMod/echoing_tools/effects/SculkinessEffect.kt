package me.kotlinMod.echoing_tools.effects

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity

class SculkinessEffect(category: StatusEffectCategory, color: Int) : StatusEffect(category, color) {

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
            if (entity !is PlayerEntity)
                entity.damage(entity.damageSources.magic(), 2f)
        }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        val i = 25 shr amplifier
        return if (i > 0) {
            duration % i == 0
        } else {
            true
        }
    }
}