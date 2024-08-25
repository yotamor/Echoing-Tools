package me.kotlinMod.echoing_tools.modItems

import me.kotlinMod.echoing_tools.EchoingTools.ModID.modID
import me.kotlinMod.echoing_tools.effects.SculkinessEffect
import me.kotlinMod.echoing_tools.effects.GenericStatusEffect
import me.kotlinMod.echoing_tools.enchantments.SculkinessEnchantment
import me.kotlinMod.echoing_tools.mixin.PotionRecipes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.*
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.world.World
import java.util.*


class ModItems {

    companion object {

        // temp vals
        private val factory: EntityType.EntityFactory<SculkArrowEntity> = EntityType.EntityFactory { _: EntityType<SculkArrowEntity>, world: World ->
            // Use the desired constructor explicitly inside the lambda expression
            SculkArrowEntity(world)
        }

        private val emptyUpgradeTemplateBaseTextures: List<Identifier> = listOf(
            Identifier("minecraft:item/empty_armor_slot_helmet"),
            Identifier("minecraft:item/empty_armor_slot_chestplate"),
            Identifier("minecraft:item/empty_armor_slot_leggings"),
            Identifier("minecraft:item/empty_armor_slot_boots"),
            Identifier("minecraft:item/empty_slot_sword"),
            Identifier("minecraft:item/empty_slot_hoe"),
            Identifier("minecraft:item/empty_slot_shovel"),
            Identifier("minecraft:item/empty_slot_axe"),
            Identifier("minecraft:item/empty_slot_pickaxe")
        )

        private val emptyUpgradeTemplateAdditionTextures: List<Identifier> =
            listOf(Identifier("minecraft:item/empty_slot_ingot"))

        //blocks
        val ancientMushroom: Block = registerBlock("ancient_mushroom", AncientMushroom(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).noCollision().breakInstantly().sounds(BlockSoundGroup.SCULK).pistonBehavior(PistonBehavior.DESTROY)))


        //features
        /*
        val feature: RegistryKey<PlacedFeature> = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(modID, "sculk_shrine_feature"))
        */

        //enchantments
        val spread: Enchantment = registerEnchant("spread")


        // items
        val SCULK_INGOT: Item = registerItem("echoing_ingot", Item(FabricItemSettings().rarity(Rarity.RARE)))
        val wardenTear: Item = registerItem("warden_tear", Item(FabricItemSettings().rarity(Rarity.RARE)))
        val upgradeTemplateCore: Item = registerItem("upgrade_template_core", Item(FabricItemSettings().rarity(Rarity.RARE).fireproof()))
        val hollowSculkTemplate: Item = registerItem("hollow_sculk_upgrade_smithing_template", Item(FabricItemSettings()))
        var sculkTemplate: Item = registerItem(
            "sculk_upgrade_smithing_template",
            SmithingTemplateItem(
                Text.translatable("smithing_template.echoing_tools.sculk_upgrade.applies_to").formatted(Formatting.BLUE),
                Text.translatable("item.echoing_tools.echoing_ingot").formatted(Formatting.BLUE),
                Text.translatable("smithing_template.echoing_tools.sculk_upgrade.netherite_upgrade").formatted(Formatting.GRAY),
                Text.translatable("smithing_template.echoing_tools.sculk_upgrade.equipment_slot"),
                Text.translatable("smithing_template.echoing_tools.sculk_upgrade.ingot_slot"),
                emptyUpgradeTemplateBaseTextures,
                emptyUpgradeTemplateAdditionTextures
        ))

        //armor
        val nethSculkHelmet: Item = registerItem("sculk_netherite_helmet", ArmorItem(ArmorBuilder.NETH_SCULK, ArmorItem.Type.HELMET, FabricItemSettings().fireproof()))
        val nethSculkChestplate: Item = registerItem("sculk_netherite_chestplate", ArmorItem(ArmorBuilder.NETH_SCULK, ArmorItem.Type.CHESTPLATE, FabricItemSettings().fireproof()))
        val nethSculkLeggings: Item = registerItem("sculk_netherite_leggings", ArmorItem(ArmorBuilder.NETH_SCULK, ArmorItem.Type.LEGGINGS, FabricItemSettings().fireproof()))
        val nethSculkBoots: Item = registerItem("sculk_netherite_boots", ArmorItem(ArmorBuilder.NETH_SCULK, ArmorItem.Type.BOOTS, FabricItemSettings().fireproof()))


        // tools and weapons
        val nethSculkPickaxe: Item = registerItem("sculk_netherite_pickaxe", PickaxeItem(ToolBuilder.NETH_SCULK_PICK, -1, -2.8f, FabricItemSettings().fireproof()))
        val nethSculkShovel: Item = registerItem("sculk_netherite_shovel", ShovelItem(ToolBuilder.NETH_SCULK_SHOVEL, 5.5f, -3f, FabricItemSettings().fireproof()))
        val nethSculkHoe: Item = registerItem("sculk_netherite_hoe", HoeItem(ToolBuilder.NETH_SCULK_HOE, 0, 0f, FabricItemSettings().fireproof()))
        val nethSculkAxe: Item = registerItem("sculk_netherite_axe", AxeItem(ToolBuilder.NETH_SCULK_AXE, 9f, -3f, FabricItemSettings().fireproof()))
        val nethSculkSword: Item = registerItem("sculk_netherite_sword", SwordItem(ToolBuilder.NETH_SCULK_SWORD, 7, -2.4f, FabricItemSettings().fireproof()))
        val sculkArrow: Item = registerItem("sculk_arrow", SculkArrowItem(FabricItemSettings()))

        //effect
        val furySurge: StatusEffect =
            registerStatusEffect("fury_surge", GenericStatusEffect(StatusEffectCategory.NEUTRAL, 0xDF1D1D))
                .addAttributeModifier(
                    EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    UUID.nameUUIDFromBytes("fury_surge_effect_strength".toByteArray()).toString(),
                    3.0,
                    Operation.ADDITION
                )
                .addAttributeModifier(
                    EntityAttributes.GENERIC_MAX_HEALTH,
                    UUID.nameUUIDFromBytes("fury_surge_effect_health".toByteArray()).toString(),
                    -0.25,
                    Operation.MULTIPLY_BASE
                )
        val stealth: StatusEffect = registerStatusEffect("stealth", GenericStatusEffect(StatusEffectCategory.BENEFICIAL,4194432))
        val sculkiness: StatusEffect = registerStatusEffect("sculkiness", SculkinessEffect(StatusEffectCategory.NEUTRAL, 465664))

        // potions
        val stealthPotion: Potion = registerPotion("stealth", stealth, 600, 0, Potions.INVISIBILITY, ancientMushroom.asItem())
        val darknessPotion: Potion = registerPotion("darkness", StatusEffects.DARKNESS, 300, 0, Potions.NIGHT_VISION, ancientMushroom.asItem())


        //entities
        val sculkArrowEntity: EntityType<SculkArrowEntity> = Registry.register(Registries.ENTITY_TYPE, Identifier(modID, "sculk_arrow"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
            .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build())


        private fun registerItem(name: String, item: Item): Item {
            return Registry.register(Registries.ITEM, Identifier(modID, name), item)
        }
        private fun registerBlock(name: String, block: Block): Block {
            Registry.register(Registries.ITEM, Identifier(modID, name), BlockItem(block, FabricItemSettings()))

            return Registry.register(Registries.BLOCK, Identifier(modID, name), block)
        }
        private fun registerStatusEffect(name: String, effect: StatusEffect): StatusEffect {
            return Registry.register(Registries.STATUS_EFFECT, Identifier(modID, name), effect)
        }
        private fun registerPotion(name: String, effect: StatusEffect, durationTicks: Int, amplifier: Int, from: Potion, ingredient: Item): Potion {
            val potion: Potion = Registry.register(Registries.POTION, Identifier(modID, name), Potion(StatusEffectInstance(effect, durationTicks, amplifier, false, true, true)))
            PotionRecipes.registerPotionRecipe(from, ingredient, potion)
            return potion
        }
        private fun registerEnchant(name: String): Enchantment {
            return Registry.register(Registries.ENCHANTMENT, Identifier(modID, name), SculkinessEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)))
        }

        fun registerGroup(): ItemGroup {
            val group = FabricItemGroup.builder()
                .icon { ItemStack(SCULK_INGOT) }
                .displayName(Text.translatable("group.echoing_tools.echoing_tools_creative_tab"))
                .entries { _: ItemGroup.DisplayContext, entries ->
                    // items
                    entries.add(SCULK_INGOT)
                    entries.add(sculkTemplate)
                    entries.add(hollowSculkTemplate)
                    entries.add(upgradeTemplateCore)
                    entries.add(wardenTear)
                    entries.add(sculkArrow)

                    // armor
                    entries.add(nethSculkHelmet)
                    entries.add(nethSculkChestplate)
                    entries.add(nethSculkLeggings)
                    entries.add(nethSculkBoots)

                    // tools and weapons
                    entries.add(nethSculkPickaxe)
                    entries.add(nethSculkShovel)
                    entries.add(nethSculkHoe)
                    entries.add(nethSculkAxe)
                    entries.add(nethSculkSword)

                    //blocks
                    entries.add(ancientMushroom.asItem())
                }
                .build()
            return Registry.register(Registries.ITEM_GROUP, Identifier(modID, "echoing_tools_creative_tab"), group)
        }
    }
}