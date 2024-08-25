package me.kotlinMod.echoing_tools.mixin;

import me.kotlinMod.echoing_tools.modItems.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkSensorBlock.class)
public class SensorActivateCallback {

    @Inject(at = @At("HEAD"), method = "setActive", cancellable = true)
    public void setActive(@Nullable Entity sourceEntity, World world, BlockPos pos, BlockState state, int power, int frequency, CallbackInfo ci) {
        if (sourceEntity instanceof LivingEntity && ((LivingEntity) sourceEntity).hasStatusEffect(ModItems.Companion.getStealth()))
            ci.cancel();
    }
}
