package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.homepoint.PointDatas

object CommandListHome {

    val main = CommandAPICommand("listhome")
        .withPermission("homepoint.listhome")
        .withPermission(CommandPermission.NONE)
        .withAliases("lh")
        .executesPlayer(
            PlayerCommandExecutor { sender, args ->
                PointDatas.load()
                val player = PointDatas.points[sender.uniqueId]?.joinToString("\n") {
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
        )
}
