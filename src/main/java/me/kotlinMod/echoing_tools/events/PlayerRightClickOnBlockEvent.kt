package me.kotlinMod.echoing_tools.events

import me.kotlinMod.echoing_tools.modItems.ToolBuilder
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.HoeItem
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World

class PlayerRightClickOnBlockEvent : UseBlockCallback {
    override fun interact(player: PlayerEntity?, world: World?, hand: Hand?, hitResult: BlockHitResult?): ActionResult {
        if (player != null && hand != null && world != null && !world.isClient && hitResult != null) {
            if (hand == Hand.MAIN_HAND) {
                val item = player.mainHandStack.item
                if (item is HoeItem) {
                    if (item.material == ToolBuilder.NETH_SCULK_HOE) {
                        val isValidBlock: Boolean = when (world.getBlockState(hitResult.blockPos).block) {
                            Blocks.STONE -> true
                            Blocks.DIORITE -> true
                            Blocks.GRANITE -> true
                            Blocks.ANDESITE -> true
                            Blocks.DEEPSLATE -> true
                            else -> false
                        }
                        if (isValidBlock) {
                            player.mainHandStack.damage(5, player, null)
                            world.setBlockState(hitResult.blockPos, Blocks.SCULK.defaultState)
                        }
                    }
                }
            }
        }
        return ActionResult.PASS
    }
}