package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object DelHome : Command("delhome") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        var err: String? = null
        if (sender !is Player) {
            err = "Please do command in world."
        } else if (!sender.hasPermission("homepoint.command.delhome")) {
            err = "You don't have permission."
        } else if (args.isEmpty()) {
            err = "Please input name."
        }
        if (err != null) {
            sender.sendMessage("§c$err")
            return
        }

        val player = PointDatas.points[(sender as Player).uniqueId]

        if (player == null) {
            sender.sendMessage("§cYou aren't have homes.")
        } else {
            if (!player.any { it.getPointName() == args[0] }) {
                sender.sendMessage("§cHome not found.")
                return
            }
            PointDatas.points[sender.uniqueId]?.removeIf { it.getPointName() == args[0] }
        }

        PointDatas.save()
        sender.sendMessage("§7Home removed successfully.")
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        return PointDatas.points[(sender as Player).uniqueId]?.map { it.getPointName() }
    }
}
