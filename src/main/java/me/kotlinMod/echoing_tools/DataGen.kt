package me.kotlinMod.echoing_tools

import me.kotlinMod.echoing_tools.datagen.WorldGen
import me.kotlinMod.echoing_tools.worldGen.AncientMushroomConfiguredFeature
import me.kotlinMod.echoing_tools.worldGen.AncientMushroomPlacedFeature
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys

class DataGen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        // :: just does { a, b -> T(a, b) }
        pack.addProvider(::WorldGen)
    }

    override fun buildRegistry(registryBuilder: RegistryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, AncientMushroomPlacedFeature::bootstrap)
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, AncientMushroomConfiguredFeature::bootstrap)
    }
}