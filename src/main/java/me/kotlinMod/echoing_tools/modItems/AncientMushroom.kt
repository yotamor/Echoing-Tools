package me.kotlinMod.echoing_tools.modItems

import me.kotlinMod.echoing_tools.modItems.ModItems.Companion.ancientMushroom
import net.minecraft.block.*
import net.minecraft.entity.AreaEffectCloudEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ItemStack
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Colors
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import net.minecraft.world.explosion.Explosion

class AncientMushroom(settings: Settings) : Block(settings) {
    override fun getOutlineShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape {
        return createCuboidShape(5.0,0.0,5.0, 11.0, 7.0, 11.0)
    }

    override fun asBlock(): Block {
        return ancientMushroom
    }

    override fun onBroken(world: WorldAccess, pos: BlockPos, state: BlockState) {
        dropStacks(state, world, pos, null)
    }

    override fun getStateForNeighborUpdate(state: BlockState, direction: Direction, neighborState: BlockState, world: WorldAccess, pos: BlockPos, neighborPos: BlockPos): BlockState {
        return if (state.canPlaceAt(world, pos)) state
        else Blocks.AIR.defaultState
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return world.getBaseLightLevel(pos, 0) <= 10 && world.getBlockState(pos.down()).block is SculkBlock
    }

    override fun onDestroyedByExplosion(world: World, pos: BlockPos, explosion: Explosion) {
        val areaEffectCloud: AreaEffectCloudEntity = AreaEffectCloudEntity(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        areaEffectCloud.duration = 120
        areaEffectCloud.color = Colors.BLACK
        areaEffectCloud.radius = 2.5f
        areaEffectCloud.addEffect(StatusEffectInstance(StatusEffects.DARKNESS, 80, 0, false, true, true))
        world.spawnEntity(areaEffectCloud)
    }

    override fun onSteppedOn(world: World?, pos: BlockPos?, state: BlockState?, entity: Entity) {
        if (entity is LivingEntity)
            entity.addStatusEffect(StatusEffectInstance(StatusEffects.DARKNESS, 50, 0, false, true, true))
    }

    override fun onPlaced(world: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
        if (placer == null) return
        world.playSound(null, pos, SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.BLOCKS)
    }

    override fun canMobSpawnInside(state: BlockState?): Boolean {
        return false
    }

    override fun getPickStack(world: BlockView?, pos: BlockPos?, state: BlockState?): ItemStack {
        return ancientMushroom.asItem().defaultStack
    }

    override fun getSoundGroup(state: BlockState?): BlockSoundGroup {
        return BlockSoundGroup.SCULK
    }


    /*
    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return floor.block is SculkBlock
    }

    override fun getMaxAge(): Int {
        return 3
    }

    override fun getSeedsItem(): ItemConvertible {
        return asItem()
    }

    override fun onEntityCollision(state: BlockState?, world: World?, pos: BlockPos?, entity: Entity) {
        if (entity is LivingEntity)
            entity.addStatusEffect(StatusEffectInstance(StatusEffects.DARKNESS, 50, 0))
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return world.getBaseLightLevel(pos, 0) <= 7 && canPlantOnTop(world.getBlockState(pos.down()), world, pos)
    }
    */
}