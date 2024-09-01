package me.kotlinMod.echoing_tools.events

import me.kotlinMod.echoing_tools.modItems.ModItems
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.fabricmc.fabric.api.loot.v2.LootTableSource
import net.minecraft.item.Items
import net.minecraft.loot.LootManager
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.EnchantRandomlyLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier

class LootTableEvent : LootTableEvents.Modify {

    private val ancientCityChest: Identifier = Identifier.of("minecraft", "chests/ancient_city")!!

    override fun modifyLootTable(resourceManager: ResourceManager, lootManager: LootManager, id: Identifier, tableBuilder: LootTable.Builder, source: LootTableSource) {
        if (id == ancientCityChest) {
            val templateLootPool: LootPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1f))
                .conditionally(RandomChanceLootCondition.builder(0.22f))
                .with(ItemEntry.builder(ModItems.hollowSculkTemplate))
                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create().add(ModItems.spread)))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 2f)))
                .build()


            tableBuilder.pool(templateLootPool)
        }
    }
}