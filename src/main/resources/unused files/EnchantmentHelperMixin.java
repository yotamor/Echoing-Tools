package me.kotlinMod.echoing_tools.mixin;

import me.kotlinMod.echoing_tools.modItems.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Unique
    EnchantmentHelper enchantmentHelperInstance = (EnchantmentHelper) (Object) this;

    @Unique
    private static Enchantment currentEnchantment;

    @Inject(at = @At("HEAD"), method = "getEnchantmentId")
    private static void getEnchantment(Enchantment enchantment, CallbackInfoReturnable<Identifier> cir) {
        currentEnchantment = enchantment;
    }

    @Redirect(at = @At("HEAD"), method = "getPossibleEntries")
    private static boolean isAcceptableItem(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        if (stack.getItem() != ModItems.Companion.getNethSculkSword())

    }
6
}
