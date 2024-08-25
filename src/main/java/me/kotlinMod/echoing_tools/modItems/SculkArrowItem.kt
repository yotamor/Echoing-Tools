package me.kotlinMod.echoing_tools.modItems

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class SculkArrowItem(settings: Settings) : ArrowItem(settings) {
    override fun createArrow(world: World, stack: ItemStack, shooter: LivingEntity?): PersistentProjectileEntity {
        if (shooter != null) {
            world.playSound(null, shooter.blockPos, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 0.3f, 1f)
            return SculkArrowEntity(world, shooter)
        }
        return SculkArrowEntity(world)

    }
}