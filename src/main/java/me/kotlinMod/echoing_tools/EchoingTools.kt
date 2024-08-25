package me.kotlinMod.echoing_tools


import me.kotlinMod.echoing_tools.events.*
import me.kotlinMod.echoing_tools.modItems.*
import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.sculkArrow
import me.kotlinMod.echoing_tools.worldGen.AncientMushroomPlacedFeature
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.ProjectileDispenserBehavior
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Position
import net.minecraft.world.World
import net.minecraft.world.biome.BiomeKeys
import net.minecraft.world.gen.GenerationStep
import org.slf4j.LoggerFactory
import java.util.*


class EchoingTools : ModInitializer {


    object ModID {
        const val modID: String = "echoing_tools"
        var shovelAbility: MutableMap<UUID, UByte> = emptyMap<UUID, UByte>().toMutableMap()
        var timesSinceSwordAbility: MutableMap<UUID, UByte> = emptyMap<UUID, UByte>().toMutableMap()
        val logger = LoggerFactory.getLogger(modID)!!
    }

    override fun onInitialize() {
        ModItems.registerGroup()
        registerDispenserBehaviour()
        generateAncientMushrooms()

        EffectAddCallBack.EVENT.register(EntityEffectEvent())
        PlayerBlockBreakEvents.AFTER.register(PlayerMinesEvent())
        AttackEntityCallback.EVENT.register { player: PlayerEntity, world: World, hand: Hand, entity: Entity, hitResult: EntityHitResult? -> EntityAttackEvent().interact(player, world, hand, entity, hitResult) }
        UseItemCallback.EVENT.register(PlayerRightClickEvent())
        UseBlockCallback.EVENT.register(PlayerRightClickOnBlockEvent())
        ModifyItemAttributeModifiersCallback.EVENT.register(ArmorAttribute())
        ServerEntityEvents.ENTITY_UNLOAD.register(EntityUnloadEvent())
        LootTableEvents.MODIFY.register(LootTableEvent())
    }

    private fun registerDispenserBehaviour(): SculkArrowEntity? {
        DispenserBlock.registerBehavior(sculkArrow, object : ProjectileDispenserBehavior() {
            override fun createProjectile(world: World, position: Position, stack: ItemStack): ProjectileEntity {
                world.playSound(null, BlockPos(position.x.toInt(), position.y.toInt(), position.z.toInt()), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.HOSTILE, 0.2f, 1f)
                val arrowEntity = SculkArrowEntity(world, position.x, position.y, position.z)
                arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED
                return arrowEntity
            }
        })
        return null
    }

    private fun generateAncientMushrooms() {
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK), GenerationStep.Feature.VEGETAL_DECORATION, AncientMushroomPlacedFeature.ancientMushroomPlacedKey
        )
    }
}
