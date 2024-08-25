package me.kotlinMod.echoing_tools.worldGen

import me.kotlinMod.echoing_tools.EchoingTools.ModID.modID
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.placementmodifier.*

class AncientMushroomPlacedFeature {
    companion object {
        val ancientMushroomPlacedKey: RegistryKey<PlacedFeature> = registerPlacedKey("ancient_mushroom_placed_feature")
        val ancientMushroomFeatureKey: RegistryKey<ConfiguredFeature<*, *>> = registerConfiguredKey("ancient_mushroom_configured_feature")

        private fun registerConfiguredKey(name: String): RegistryKey<ConfiguredFeature<*, *>> {
            return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(modID, name))
        }

        private fun registerPlacedKey(name: String): RegistryKey<PlacedFeature> {
            return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(modID, name))
        }


        private fun registerPlaced(context: Registerable<PlacedFeature>, key: RegistryKey<PlacedFeature>, configuration: RegistryEntry<ConfiguredFeature<*, *>>, modifiers: List<PlacementModifier>) {
            context.register(key, PlacedFeature(configuration, modifiers))
        }

        fun bootstrap(placedContext: Registerable<PlacedFeature>) {
            registerPlaced(placedContext, ancientMushroomPlacedKey, placedContext.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ancientMushroomFeatureKey),
                listOf(
                    RarityFilterPlacementModifier.of(7),
                    SquarePlacementModifier.of(),
                    //HeightRangePlacementModifier.uniform(),
                    BiomePlacementModifier.of(),
                    CountMultilayerPlacementModifier.of(3),
                    CountPlacementModifier.of(3),
                //BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Vec3i(0, -1, 0), Blocks.SCULK))))
            ))
        }
    }
}