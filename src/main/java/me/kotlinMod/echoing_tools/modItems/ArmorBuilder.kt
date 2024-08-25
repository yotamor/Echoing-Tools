package me.kotlinMod.echoing_tools.modItems

import me.kotlinMod.echoing_tools.EchoingTools
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

enum class ArmorBuilder(private val pieceName: String, private val durabilityMultiplayer: Byte, private val protections: IntArray, private val enchantability: Int, private val equipSound: SoundEvent, private val repairItems: Ingredient, private val armorToughness: Float, private val knockbackResistance: Float) : ArmorMaterial {

    NETH_SCULK("netherite_sculk", 45, intArrayOf(3, 8, 6, 3), 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, Ingredient.ofItems(ItemConvertible { Items.ECHO_SHARD }, { Items.NETHERITE_INGOT }), 3f, 0.1f);

    private val BASE_DURABILITY = intArrayOf(11, 16, 15, 13)

    override fun getDurability(type: ArmorItem.Type?): Int {
        return BASE_DURABILITY[type!!.ordinal] * durabilityMultiplayer
    }

    override fun getProtection(type: ArmorItem.Type?): Int {
        return protections[type!!.ordinal]
    }

    override fun getEnchantability(): Int {
        return enchantability
    }

    override fun getEquipSound(): SoundEvent {
        return equipSound
    }

    override fun getRepairIngredient(): Ingredient {
       return repairItems
    }

    override fun getName(): String {
        return "${EchoingTools.ModID.modID}:$pieceName"
    }

    override fun getToughness(): Float {
        return armorToughness
    }

    override fun getKnockbackResistance(): Float {
        return knockbackResistance
    }
}