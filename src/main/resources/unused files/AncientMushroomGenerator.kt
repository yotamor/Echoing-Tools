//package me.kotlinMod.echoing_tools.modItems
//
//import me.kotlinMod.echoing_tools.EchoingTools.ModID.modID
//import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.ancientMushroom
//import net.minecraft.registry.Registerable
//import net.minecraft.registry.RegistryKey
//import net.minecraft.registry.RegistryKeys
//import net.minecraft.registry.entry.RegistryEntry
//import net.minecraft.util.Identifier
//import net.minecraft.world.gen.blockpredicate.BlockPredicate
//import net.minecraft.world.gen.feature.*
//import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier
//import net.minecraft.world.gen.placementmodifier.PlacementModifier
//import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier
//import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier
//import net.minecraft.world.gen.stateprovider.BlockStateProvider
//
//
//class AncientMushroomPlaced {
//    companion object {
//        val ancientMushroomPlacedKey: RegistryKey<PlacedFeature> = registerPlacedKey("ancient_mushroom_placed_feature")
//        val ancientMushroomFeatureKey: RegistryKey<ConfiguredFeature<*, *>> = registerConfiguredKey("ancient_mushroom_feature")
//
//        private fun registerConfiguredKey(name: String): RegistryKey<ConfiguredFeature<*, *>> {
//            return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(modID, name))
//        }
//
//        private fun registerPlacedKey(name: String): RegistryKey<PlacedFeature> {
//            return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(modID, name))
//        }
//
//
//        private fun registerPlaced(context: Registerable<PlacedFeature>, key: RegistryKey<PlacedFeature>, configuration: RegistryEntry<ConfiguredFeature<*, *>>, modifiers: List<PlacementModifier>) {
//            context.register(key, PlacedFeature(configuration, modifiers))
//        }
//
//        fun bootstrap(placedContext: Registerable<PlacedFeature>) {
//            registerPlaced(placedContext, ancientMushroomPlacedKey, placedContext.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ancientMushroomFeatureKey), listOf(RarityFilterPlacementModifier.of(30), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()))
//        }
//    }
//
//
//}
//
//class AncientMushroomConFeature {
//    companion object {
//
//        private val ancientMushroomFeatureKey = AncientMushroomPlaced.ancientMushroomFeatureKey
//        private val ancientMushroomConfiguredKey = AncientMushroomPlaced.ancientMushroomPlacedKey
//
//        fun bootstrap(configuredContext: Registerable<ConfiguredFeature<*, *>>) {
//            registerConfigured(configuredContext, ancientMushroomFeatureKey, Feature.FLOWER,
//                RandomPatchFeatureConfig(
//                    40,
//                    3,
//                    0,
//                    PlacedFeatures.createEntry(
//                        Feature.SIMPLE_BLOCK,
//                        SimpleBlockFeatureConfig(BlockStateProvider.of(ancientMushroom)),
//                        BlockPredicate.IS_AIR
//                    )
//                ))
//        }
//
//        private fun <FC : FeatureConfig, F : Feature<FC>> registerConfigured(context: Registerable<ConfiguredFeature<*, *>>, key: RegistryKey<ConfiguredFeature<*, *>>, feature: F, config: FC) {
//            context.register(key, ConfiguredFeature(feature, config))
//        }
//
//    }
//}