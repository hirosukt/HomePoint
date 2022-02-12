package love.chihuyu.homepoint.command.commands

import love.chihuyu.homepoint.PointDatas
import love.chihuyu.homepoint.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

object BackHome: Command("backhome") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        var err: String? = null
        if (sender !is Player) {
            err = "Please do command in world."
        } else if (!sender.hasPermission("homepoint.command.backhome")) {
            err = "You don't have permission."
        }
        if (err != null) {
            sender.sendMessage("§c$err")
            return
        }

        val entities = mutableListOf<Entity>()

        entities.add(sender as Player)

        if (sender.vehicle != null) {
            entities.add(sender.vehicle!!)
            sender.vehicle!!.passengers.forEach { entities.add(it) }
        }

        val location = PointDatas.tempPoints[sender.uniqueId]

        if (location == null) {
            sender.sendMessage("§cTemp location not found.")
            return
        }

        entities.forEach { entity ->
            entity.teleport(location)
        }

        sender.sendMessage("§7Teleported back to before location.")
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}