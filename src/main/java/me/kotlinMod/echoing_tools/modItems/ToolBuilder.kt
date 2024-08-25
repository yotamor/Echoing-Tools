package me.kotlinMod.echoing_tools.modItems

import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient

enum class ToolBuilder(private val durability: Int, private val miningSpeedMultiplier: Float, private val attackDamage: Float, private val miningLevel: Int, private val enchantability: Int, private val repairItems: Ingredient) : ToolMaterial {

    NETH_SCULK_HOE(2480, 9f, 0f, 0, 15, Ingredient.ofItems(ItemConvertible { Items.ECHO_SHARD }, { Items.NETHERITE_INGOT })),
    NETH_SCULK_SHOVEL(2480, 9f, 0f, 0, 15, Ingredient.ofItems(ItemConvertible { Items.ECHO_SHARD }, { Items.NETHERITE_INGOT })),
    NETH_SCULK_PICK(2480, 9f, 6f, MiningLevels.NETHERITE, 15, Ingredient.ofItems(ItemConvertible { Items.ECHO_SHARD }, { Items.NETHERITE_INGOT })),
    NETH_SCULK_AXE(2480, 9f, 0f, 0, 15, Ingredient.ofItems(ItemConvertible { Items.ECHO_SHARD }, { Items.NETHERITE_INGOT })),
    NETH_SCULK_SWORD(2480, 0f, 0f, 0,15, Ingredient.ofItems(ItemConvertible { Items.ECHO_SHARD }, { Items.NETHERITE_INGOT }));

    override fun getDurability(): Int {
        return durability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return miningSpeedMultiplier
    }

    override fun getAttackDamage(): Float {
        return attackDamage
    }

    override fun getMiningLevel(): Int {
        return miningLevel
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairItems
    }
}