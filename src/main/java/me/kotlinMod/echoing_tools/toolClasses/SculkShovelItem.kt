package me.kotlinMod.echoing_tools.toolClasses

import me.kotlinMod.echoing_tools.EchoingTools.ModID.shovelAbility
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ShovelItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class SculkShovelItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings) : ShovelItem(material, attackDamage, attackSpeed, settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (hand != Hand.MAIN_HAND || world.isClient || user.itemCooldownManager.isCoolingDown(this)) return super.use(world, user, hand)

        Thread {
            val playerUUID = user.uuid
            shovelAbility[playerUUID] = 0u
            Thread.sleep(30000)
            if (shovelAbility.containsKey(playerUUID)) {
                if (shovelAbility[playerUUID]!! > 50u)
                    user.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, ((shovelAbility[playerUUID]!! * 5u /* <- this will be a fourth of the blocks because this is in ticks and 20 / 4 = 5 */ - 10u).toInt()), 1))
                else
                    user.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, ((shovelAbility[playerUUID]!! * 5u /* <- this will be a fourth of the blocks because this is in ticks and 20 / 4 = 5 */ - 10u).toInt()), 0))
                shovelAbility.remove(playerUUID)
            }
        }.start()
        user.itemCooldownManager.set(this, 800)
        return super.use(world, user, hand)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        if (world.isClient || miner !is PlayerEntity || !miner.itemCooldownManager.isCoolingDown(this)) return super.postMine(stack, world, state, pos, miner)
        val playerUUID = miner.uuid
        if (world.isClient || !shovelAbility.contains(playerUUID) || shovelAbility[playerUUID]!! >= UByte.MAX_VALUE - 1u) return super.postMine(stack, world, state, pos, miner)
        shovelAbility[playerUUID] = (shovelAbility[playerUUID]!! + 1u).toUByte()
        return super.postMine(stack, world, state, pos, miner)
    }

}