package me.kotlinMod.echoing_tools.mixin.client;

import me.kotlinMod.echoing_tools.EchoingTools;
import me.kotlinMod.echoing_tools.modItems.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Redirect(method = "drawHeart", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void getHeartTexture(DrawContext instance, Identifier texture, int x, int y, int u, int v, int width, int height) {
        boolean hardcore = v != 0;
        boolean half = u % 2 != 0;
        final PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.hasStatusEffect(ModItems.Companion.getSculkiness()) && u > 25 /* not empty */) {
            if (!half) {
                if (hardcore)
                    instance.drawTexture(Identifier.of(EchoingTools.ModID.modID, "textures/gui/sprites/hud/heart/sculk_full_hardcore.png"), x, y, 0, 0, 9, 9, 9, 9);
                else
                    instance.drawTexture(Identifier.of(EchoingTools.ModID.modID, "textures/gui/sprites/hud/heart/sculk_full.png"), x, y, 0, 0, 9, 9, 9, 9);
                
            }
            else {
                if (hardcore)
                    instance.drawTexture(Identifier.of(EchoingTools.ModID.modID, "textures/gui/sprites/hud/heart/sculk_half_hardcore.png"), x, y, 0, 0, 9, 9, 9, 9);
                else
                    instance.drawTexture(Identifier.of(EchoingTools.ModID.modID, "textures/gui/sprites/hud/heart/sculk_half.png"), x, y, 0, 0, 9, 9, 9, 9);
            }
        }
        else
            instance.drawTexture(texture, x, y, u, v, width, height);
    }
}
