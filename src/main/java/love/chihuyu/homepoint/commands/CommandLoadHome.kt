package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.executors.CommandExecutor
import love.chihuyu.homepoint.PointDatas

object CommandLoadHome {

    val main = CommandAPICommand("loadhome")
        .withPermission("homepoint.loadhome")
        .withPermission(CommandPermission.OP)
        .executes(
            CommandExecutor { sender, args ->
                PointDatas.load()
                sender.sendMessage("§7Data was loaded successfully.")
            }
        )
}
