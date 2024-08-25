package me.kotlinMod.echoing_tools.mixin;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BrewingRecipeRegistry.class)
public interface PotionRecipes {
    @Invoker("registerPotionRecipe")
    static void registerPotionRecipe(Potion input, Item item, Potion output) {
        throw new AssertionError();
    }
}
