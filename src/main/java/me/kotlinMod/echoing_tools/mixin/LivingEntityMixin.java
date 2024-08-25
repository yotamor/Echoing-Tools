package me.kotlinMod.echoing_tools.mixin;

import me.kotlinMod.echoing_tools.events.EffectAddCallBack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import static me.kotlinMod.echoing_tools.modItems.ModItems.Companion;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    LivingEntity entity = (LivingEntity) (Object) this;

    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true)
    private void addStatusEffect(final StatusEffectInstance effect, final @Nullable Entity source, final CallbackInfoReturnable<StatusEffectInstance> callbackInfoReturnable) {
        ActionResult result = EffectAddCallBack.EVENT.invoker().effectAdd(entity, effect, source, callbackInfoReturnable);
        if(result == ActionResult.FAIL)
            callbackInfoReturnable.cancel();
    }
    @Inject(method = "heal", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V"))
    private void healMixin(float amount, CallbackInfo ci) {
        if (entity.hasStatusEffect(Companion.getSculkiness())) {
            entity.setHealth(entity.getHealth() + (amount / 3));
        }
    }
}
