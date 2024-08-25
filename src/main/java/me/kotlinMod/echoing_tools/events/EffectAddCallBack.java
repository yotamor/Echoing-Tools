package me.kotlinMod.echoing_tools.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public interface EffectAddCallBack {

    Event<EffectAddCallBack> EVENT = EventFactory.createArrayBacked(EffectAddCallBack.class,
            (listeners) -> (livingEntity, statusEffectInstance, source, callbackInfoReturnable) -> {
                for (EffectAddCallBack listener : listeners) {
                    ActionResult result = listener.effectAdd(livingEntity, statusEffectInstance, source, callbackInfoReturnable);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult effectAdd(LivingEntity livingEntity, StatusEffectInstance statusEffectInstance, @Nullable Entity source, CallbackInfoReturnable<StatusEffectInstance> callbackInfoReturnable);
}
