package me.kotlinMod.echoing_tools.events

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import me.kotlinMod.echoing_tools.EchoingTools.ModID.shovelAbility

class PlayerMinesEvent : PlayerBlockBreakEvents.After {
    override fun afterBlockBreak(world: World, player: PlayerEntity, pos: BlockPos?, state: BlockState?, blockEntity: BlockEntity?) {
        val playerUUID = player.uuid
        if (world.isClient || !shovelAbility.contains(playerUUID) || shovelAbility[playerUUID]!! >= UByte.MAX_VALUE - 1u) return
        shovelAbility[playerUUID] = (shovelAbility[playerUUID]!! + 1u).toUByte()
    }
}