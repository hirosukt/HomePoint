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

        PointDatas.load()

        val player = PointDatas.points[(sender as Player).uniqueId]?.joinToString("\n") {
            "§6${it.getPointName()} §7-> (${it.getLocation().world?.name}) ${
                "%,.1f".format(it.getLocation().x)
            }, ${
                "%,.1f".format(it.getLocation().y)
            }, ${
                "%,.1f".format(it.getLocation().z)
            }"
        }
        sender.sendMessage("§7Heres are your home list.")
        sender.sendMessage(player)
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}
