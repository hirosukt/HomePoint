package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.point.Point
import org.bukkit.entity.Player

object CommandRenameHome {

    val main = CommandAPICommand("renamehome")
        .withPermission("homepoint.renamehome")
        .withPermission(CommandPermission.NONE)
        .withAliases("rh")
        .withArguments(
            TextArgument("homeName").replaceSuggestions(
                ArgumentSuggestions.strings { info ->
                    PointDatas.points[(info.sender as Player).uniqueId]?.map { it.getPointName() }?.toTypedArray()
                }
            ),
            StringArgument("newName")
        )
        .executesPlayer(
            PlayerCommandExecutor { sender, args ->
                val player = PointDatas.points[sender.uniqueId]
                val target = args[0] as String
                val name = args[1] as String

                if (player == null) {
                    sender.sendMessage("§cYou aren't have homes.")
                } else {
                    if (!player.any { it.getPointName() == target }) {
                        sender.sendMessage("§cHome not found.")
                        return@PlayerCommandExecutor
                    }
                    val data = player.first { it.getPointName() == target }
                    player.removeIf { it.getPointName() == target }
                    player.add(Point(name, data.getLocation()))
                    sender.sendMessage("§7Home renamed $target to $name.")
                }
            }
        )
}
