package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ListHome : Command("listhome") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        var err: String? = null
        if (sender !is Player) {
            err = "Please do command in world."
        } else if (!sender.hasPermission("homepoint.command.listhome")) {
            err = "You don't have permission."
        }
        if (err != null) {
            sender.sendMessage("§c$err")
            return
        }

        val player =
            PointDatas.points[(sender as Player).uniqueId]?.joinToString("\n") {
                "§6${it.pointName} §7-> ${
                    "%,.1f".format(
                        it.location.x
                    )
                }, ${"%,.1f".format(it.location.y)}, ${"%,.1f".format(it.location.z)}"
            }
        sender.sendMessage("§7Here are your home list.")
        sender.sendMessage(player)
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}