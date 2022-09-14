package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.point.Point
import org.bukkit.entity.Player

object CommandSetHome {

    val main = CommandAPICommand("sethome")
        .withPermission("homepoint.sethome")
        .withPermission(CommandPermission.NONE)
        .withAliases("sh")
        .withArguments(StringArgument("homeName"))
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val player = PointDatas.points[(sender as Player).uniqueId]

            if (player == null) {
                PointDatas.points[sender.uniqueId] = mutableListOf(Point(args[0] as String, sender.location))
            } else {
                if (player.any { it.getPointName() == args[0] as String }) {
                    sender.sendMessage("§cThe name already set.")
                    return@PlayerCommandExecutor
                }
                PointDatas.points[sender.uniqueId]?.add(Point(args[0] as String, sender.location))
            }

            PointDatas.tempPoints[sender.uniqueId] = sender.location
            PointDatas.save()
            sender.sendMessage("§7Home saved successfully.")
        })
}