package me.kotlinMod.echoing_tools.client


import me.kotlinMod.echoing_tools.modItems.SculkArrowEntity
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.util.math.MatrixStack


lateinit var base: ModelPart
class SculkArrowModel(modelPart: ModelPart) : EntityModel<SculkArrowEntity>() {

    object GetTexturedModelData {
        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            modelPartData.addChild(
                EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(
                    -6f, 12f,
                    -6f, 12f, 12f, 12f
                ), ModelTransform.pivot(0f, 0f, 0f)
            )
            return TexturedModelData.of(modelData, 64, 64)
        }
    }

    override fun setAngles(entity: SculkArrowEntity?, limbAngle: Float, limbDistance: Float, animationProgress: Float, headYaw: Float, headPitch: Float) {
        TODO("Not yet implemented")
    }

    override fun render(matrices: MatrixStack?, vertices: VertexConsumer?, light: Int, overlay: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        TODO("Not yet implemented")
    }



}