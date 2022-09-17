package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.executors.CommandExecutor
import love.chihuyu.homepoint.PointDatas

object CommandSaveHome {

    val main = CommandAPICommand("savehome")
        .withPermission("homepoint.savehome")
        .withPermission(CommandPermission.OP)
        .executes(
            CommandExecutor { sender, args ->
                PointDatas.save()
                sender.sendMessage("§7Data was saved successfully.")
            }
        )
}
