package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.homepoint.PointDatas
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

object CommandHome {

    val main = CommandAPICommand("home")
        .withPermission("homepoint.home")
        .withPermission(CommandPermission.NONE)
        .executesPlayer(
            PlayerCommandExecutor { sender, args ->
                val target = PointDatas.points[(sender as Player).uniqueId]?.map { it.getPointName() }?.firstOrNull { it == "home" }

                if (target == null) {
                    sender.sendMessage("§cHome not found.")
                    return@PlayerCommandExecutor
                }

                val entities = mutableListOf<Entity>()

                entities.add(sender)

                if (sender.vehicle != null) {
                    entities.add(sender.vehicle!!)
                    sender.vehicle!!.passengers.forEach { entities.add(it) }
                }

                entities.forEach { entity ->
                    PointDatas.points[sender.uniqueId]?.firstOrNull { it.getPointName() == "home" }?.getLocation()?.let {
                        entity.teleport(
                            it
                        )
                    }
                }
                sender.sendMessage("§7Teleported to §n$target.")
            }
        )

    val withSpecifyHome = CommandAPICommand("home")
        .withPermission("homepoint.home")
        .withPermission(CommandPermission.NONE)
        .withArguments(
            TextArgument("homeName").replaceSuggestions(
                ArgumentSuggestions.strings { info ->
                    PointDatas.points[(info.sender as Player).uniqueId]?.map { it.getPointName() }?.toTypedArray()
                }
            )
        )
        .executesPlayer(
            PlayerCommandExecutor { sender, args ->
                val targetName = args[0] as String
                val target = PointDatas.points[(sender as Player).uniqueId]?.map { it.getPointName() }?.firstOrNull { it == targetName }

                if (target == null) {
                    sender.sendMessage("§cHome not found.")
                    return@PlayerCommandExecutor
                }

                val entities = mutableListOf<Entity>()

                entities.add(sender)

                if (sender.vehicle != null) {
                    entities.add(sender.vehicle!!)
                    sender.vehicle!!.passengers.forEach { entities.add(it) }
                }

                entities.forEach { entity ->
                    PointDatas.points[sender.uniqueId]?.firstOrNull { it.getPointName() == targetName }?.getLocation()?.let {
                        entity.teleport(
                            it
                        )
                    }
                }
                sender.sendMessage("§7Teleported to §n$target.")
            }
        )
}
