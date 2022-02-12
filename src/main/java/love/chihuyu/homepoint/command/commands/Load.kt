package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender

object Load: Command("loadhome") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {

    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}