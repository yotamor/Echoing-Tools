package me.kotlinMod.echoing_tools.mixin;

import me.kotlinMod.echoing_tools.modItems.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Unique
    PlayerEntity player = (PlayerEntity) (Object) this;

    @Inject(at = @At("HEAD"), method = "addExperience")
    private void onExperienceAdd(int experience, CallbackInfo ci) {
        if (experience > 0 && player.hasStatusEffect(ModItems.Companion.getSculkiness())) {
            float playerHealth = player.getHealth();
            float playerMaxHealth = player.getMaxHealth();

            float xpToAdd = ((float) experience) / 5;
            if (xpToAdd > playerMaxHealth - playerHealth)
                player.setHealth(playerMaxHealth);
            else
                player.setHealth(playerHealth + xpToAdd);
        }
    }

}
