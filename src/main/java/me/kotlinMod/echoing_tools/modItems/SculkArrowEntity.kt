package me.kotlinMod.echoing_tools.modItems

import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.sculkArrow
import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.sculkArrowEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class SculkArrowEntity : PersistentProjectileEntity {
    constructor(world: World, shooter: LivingEntity) : super(sculkArrowEntity, shooter, world)
    constructor(world: World, x: Double, y: Double, z: Double) : super(sculkArrowEntity, x, y, z, world)
    constructor(world: World) : super(sculkArrowEntity, world)

    override fun tick() {
        if (this.inGround) {
            super.tick()
            return
        }
        val velocity: Vec3d = this.velocity
        world.addParticle(ParticleTypes.SONIC_BOOM, this.x, this.y, this.z, velocity.x, velocity.y, velocity.z)
        super.tick()
    }

    override fun onHit(target: LivingEntity) {
        super.onHit(target)
        target.damage(DamageSources(target.world.registryManager).sonicBoom(target), 2f)
    }

    override fun asItemStack(): ItemStack {
        return sculkArrow.defaultStack
    }

}