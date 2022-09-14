package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.homepoint.PointDatas
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

object CommandBackHome {

    val main = CommandAPICommand("backhome")
        .withAliases("bh")
        .withPermission("homepoint.backhome")
        .withPermission(CommandPermission.NONE)
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val entities = mutableListOf<Entity>()

            entities.add(sender as Player)

            if (sender.vehicle != null) {
                entities.add(sender.vehicle!!)
                sender.vehicle!!.passengers.forEach { entities.add(it) }
            }

            val location = PointDatas.tempPoints[sender.uniqueId]

            if (location == null) {
                sender.sendMessage("§cTemp location not found.")
                return@PlayerCommandExecutor
            }

            entities.forEach { entity ->
                entity.teleport(location)
            }

            sender.sendMessage("§7Teleported back to before location.")
        })
}