package me.kotlinMod.echoing_tools.datagen

import me.kotlinMod.echoing_tools.EchoingTools.ModID.logger
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

class RecipeGen(dataGenerator: FabricDataOutput) : FabricLanguageProvider(dataGenerator) {

    override fun getName(): String {
        return "Recipe Gen"
    }

    override fun generateTranslations(translationBuilder: TranslationBuilder) {


        try {
            translationBuilder.add(dataOutput.modContainer.findPath("assets/echoing_tools/lang/en_us.json").get())
        }
        catch (e: RuntimeException) {
            logger.warn("Error while loading language file")
        }
    }
}