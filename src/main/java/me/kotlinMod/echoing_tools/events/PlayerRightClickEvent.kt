package me.kotlinMod.echoing_tools.events

import me.kotlinMod.echoing_tools.modItems.ToolBuilder
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.AxeItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import me.kotlinMod.echoing_tools.EchoingTools.ModID.shovelAbility
import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.furySurge
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.damage.DamageSources
import net.minecraft.entity.effect.InstantStatusEffect
import net.minecraft.text.Text


class PlayerRightClickEvent : UseItemCallback {
    override fun interact(player: PlayerEntity?, world: World?, hand: Hand?): TypedActionResult<ItemStack> {
        if (world != null && !world.isClient && player != null && hand != null) {
            if (hand == Hand.MAIN_HAND) {
                val item = player.inventory.mainHandStack.item
                if (item is PickaxeItem && item.material == ToolBuilder.NETH_SCULK_PICK) {
                    pickaxeAbility(player, item)
                }
                else if (item is AxeItem && item.material == ToolBuilder.NETH_SCULK_AXE) {
                    axeAbility(player, item)
                }
                else if (item is ShovelItem && item.material == ToolBuilder.NETH_SCULK_SHOVEL) {
                    shovelAbility(player, item)
                }
            }
        }
        return TypedActionResult(ActionResult.PASS, null)
    }
    private fun pickaxeAbility(player: PlayerEntity, item: Item) {
        if (player.itemCooldownManager.isCoolingDown(item)) return
        val haste = StatusEffectInstance(StatusEffects.HASTE, 350, 1)
        player.addStatusEffect(haste)
        player.itemCooldownManager.set(item, 600)
    }

    private fun axeAbility(player: PlayerEntity, item: Item) {
        if (player.itemCooldownManager.isCoolingDown(item)) return
        if (MinecraftClient.getInstance().gameVersion.contains("1.20.1"))
            player.damage(DamageSources(player.world.registryManager).magic(), 2f)
        val furySurgeInstance = StatusEffectInstance(furySurge, 400, 0, false, true, true)
        player.addStatusEffect(furySurgeInstance, player)
        player.itemCooldownManager.set(item, 600)
    }
    private fun shovelAbility(player: PlayerEntity, item: Item) {
        if (player.itemCooldownManager.isCoolingDown(item)) return
        Thread {
            val playerUUID = player.uuid
            shovelAbility.put(playerUUID, 0u)
            Thread.sleep(30000)
            if (shovelAbility.containsKey(playerUUID)) {
                if (shovelAbility[playerUUID]!! > 50u)
                    player.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, ((shovelAbility[playerUUID]!! * 5u /* <- this will be a fourth of the blocks because this is in ticks and 20 / 4 = 5 */ - 10u).toInt()), 1))
                else
                    player.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED, ((shovelAbility[playerUUID]!! * 5u /* <- this will be a fourth of the blocks because this is in ticks and 20 / 4 = 5 */ - 10u).toInt()), 0))
                shovelAbility.remove(playerUUID)
            }
        }.start()
        player.itemCooldownManager.set(item, 800)
    }

}