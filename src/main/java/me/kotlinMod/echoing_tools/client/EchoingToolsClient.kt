package me.kotlinMod.echoing_tools.client

import me.kotlinMod.echoing_tools.modItems.ModItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.EntityType
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.text.Text
import net.minecraft.world.World
import kotlin.math.cos
import kotlin.math.sin

class EchoingToolsClient : ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    override fun onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModItems.sculkArrowEntity) { context ->
            SculkArrowRenderer(context)
        }



        // EntityModelLayerRegistry.registerModelLayer(EntityModelLayer(Identifier(modID, "sculk_arrow_model"), "main"), SculkArrowModel.GetTexturedModelData::getTexturedModelData)
    }

}