package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Home: Command("home") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        var err: String? = null
        if (sender !is Player) {
            err = "Please do command in world."
        } else if (!sender.hasPermission("homepoint.command.home")) {
            err = "You don't have permission."
        } else if (args.isEmpty()) {
            err = "Please input name."
        }
        if (err != null) {
            sender.sendMessage("§c$err")
            return
        }
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}