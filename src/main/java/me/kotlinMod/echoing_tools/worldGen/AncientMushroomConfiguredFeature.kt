package me.kotlinMod.echoing_tools.worldGen

import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.ancientMushroom
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.stateprovider.BlockStateProvider

class AncientMushroomConfiguredFeature {
    companion object {

        private val ancientMushroomFeatureKey = AncientMushroomPlacedFeature.ancientMushroomFeatureKey

        fun bootstrap(configuredContext: Registerable<ConfiguredFeature<*, *>>) {
            registerConfigured(configuredContext, ancientMushroomFeatureKey, Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    BlockStateProvider.of(ancientMushroom)
                )
            )
        }

        private fun <FC : FeatureConfig, F : Feature<FC>> registerConfigured(context: Registerable<ConfiguredFeature<*, *>>, key: RegistryKey<ConfiguredFeature<*, *>>, feature: F, config: FC) {
            context.register(key, ConfiguredFeature(feature, config))
        }

    }
}