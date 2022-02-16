package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.command.Command
import love.chihuyu.homepoint.point.Point
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetHome : Command("sethome") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        var err: String? = null
        if (sender !is Player) {
            err = "Please do command in world."
        } else if (!sender.hasPermission("homepoint.command.sethome")) {
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
            PointDatas.points[sender.uniqueId] = mutableListOf(Point(args[0], sender.location))
        } else {
            if (player.any { it.getPointName() == args[0] }) {
                sender.sendMessage("§cThe name already set.")
                return
            }
            PointDatas.points[sender.uniqueId]?.add(Point(args[0], sender.location))
        }

        PointDatas.tempPoints[sender.uniqueId] = sender.location
        PointDatas.save()
        sender.sendMessage("§7Home saved successfully.")
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}
