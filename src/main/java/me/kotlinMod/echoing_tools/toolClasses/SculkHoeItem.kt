package me.kotlinMod.echoing_tools.toolClasses

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.ToolMaterial
import net.minecraft.util.ActionResult

class SculkHoeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : HoeItem(material, attackDamage, attackSpeed, settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient) return super.useOnBlock(context)
        val block: BlockState = context.world.getBlockState(context.blockPos)
        val isValidBlock = when (block.block) {
            Blocks.STONE -> true
            Blocks.DIORITE -> true
            Blocks.GRANITE -> true
            Blocks.ANDESITE -> true
            Blocks.DEEPSLATE -> true
            else -> false
        }
        if (isValidBlock) {
            context.stack.damage(5, context.player, null)
            context.world.setBlockState(context.blockPos, Blocks.SCULK.defaultState)
        }
        return super.useOnBlock(context)
    }
}