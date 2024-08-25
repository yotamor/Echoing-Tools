package me.kotlinMod.echoing_tools.effects

import me.kotlinMod.echoing_tools.EchoingTools.ModID.modID
import net.minecraft.util.Identifier

enum class HeartTypesEnum(private val fullTexture: Identifier, private val fullBlinkingTexture: Identifier, private val halfTexture: Identifier,
                          private val halfBlinkingTexture: Identifier, private val hardcoreFullTexture: Identifier,
                          private val hardcoreFullBlinkingTexture: Identifier, private val hardcoreHalfTexture: Identifier, private val hardcoreHalfBlinkingTexture: Identifier) {

    SCULKINESS(
        Identifier(modID,"hud/heart/sculk_full"),
        //change
        Identifier(modID,"hud/heart/sculk_full"),
        Identifier(modID, "hud/heart/sculk_half"),
        //change
        Identifier(modID,"hud/heart/sculk_half"),
        Identifier(modID,"hud/heart/sculk_full_hardcore"),
        //change
        Identifier(modID,"hud/heart/sculk_full_hardcore"),
        Identifier(modID,"hud/heart/sculk_half_hardcore"),
        //change
        Identifier(modID,"hud/heart/sculk_half_hardcore")
    );

    fun getTexture(half: Boolean, blinking: Boolean, hardcore: Boolean): Identifier {
        return if (half)
            if (hardcore)
                hardcoreHalfTexture
            else
                halfTexture
        else
            if (hardcore)
                hardcoreFullTexture
            else
                fullTexture
    }

}