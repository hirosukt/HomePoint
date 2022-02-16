package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender

object Load : Command("loadhome") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        var err: String? = null
        if (!sender.hasPermission("homepoint.command.loadhome")) {
            err = "You don't have permission."
        }
        if (err != null) {
            sender.sendMessage("§c$err")
            return
        }

        PointDatas.load()
        sender.sendMessage("§7Data was loaded successfully.")
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}
