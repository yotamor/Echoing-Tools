package me.kotlinMod.echoing_tools.client

import me.kotlinMod.echoing_tools.EchoingTools.ModID.modID
import me.kotlinMod.echoing_tools.modItems.SculkArrowEntity
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.entity.*
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class SculkArrowRenderer(context: EntityRendererFactory.Context?) : ProjectileEntityRenderer<SculkArrowEntity>(context) {
    override fun getTexture(entity: SculkArrowEntity): Identifier {
        return Identifier(modID, "textures/entity/projectiles/sculk_arrow.png")
    }
}