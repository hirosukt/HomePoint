package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

object Home : Command("home") {
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

        val target = PointDatas.points[(sender as Player).uniqueId]?.map { it.getPointName() }?.firstOrNull { it == args[0] }

        if (target == null) {
            sender.sendMessage("§cHome not found.")
            return
        }

        val entities = mutableListOf<Entity>()

        entities.add(sender)

        if (sender.vehicle != null) {
            entities.add(sender.vehicle!!)
            sender.vehicle!!.passengers.forEach { entities.add(it) }
        }

        entities.forEach { entity ->
            PointDatas.points[sender.uniqueId]?.firstOrNull { it.getPointName() == args[0] }?.getLocation()?.let {
                entity.teleport(
                    it
                )
            }
        }
        sender.sendMessage("§7Teleported to §n${args[0]}.")
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        return PointDatas.points[(sender as Player).uniqueId]?.map { it.getPointName() }
    }
}
