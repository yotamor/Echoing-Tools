package me.kotlinMod.echoing_tools.events

import me.kotlinMod.echoing_tools.EchoingTools
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld

class EntityUnloadEvent : ServerEntityEvents.Unload {
    override fun onUnload(entity: Entity, world: ServerWorld) {
        EchoingTools.ModID.timesSinceSwordAbility.remove(entity.uuid)
    }
}