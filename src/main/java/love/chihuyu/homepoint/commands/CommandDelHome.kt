package love.chihuyu.homepoint.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.*
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.homepoint.PointDatas
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Content
import net.md_5.bungee.api.chat.hover.content.Entity
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.Player

object CommandDelHome {

    val main = CommandAPICommand("delhome")
        .withAliases("dh")
        .withPermission("homepoint.delhome")
        .withPermission(CommandPermission.NONE)
        .withArguments(TextArgument("homeName").replaceSuggestions(ArgumentSuggestions.strings { info ->
            PointDatas.points[(info.sender as Player).uniqueId]?.map { it.getPointName() }?.toTypedArray()
        }))
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            sender.spigot().sendMessage(TextComponent("Home can't restore. Are you sure? ${ChatColor.GREEN}${ChatColor.BOLD}[Confirm]").apply {
                this.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/forcedelhome ${args[0] as String}")
                this.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("Click to remove home"))
            })
        })

    val forceremove = CommandAPICommand("forcedelhome")
        .withArguments(StringArgument("homeName"))
        .withPermission("homepoint.forcedelhome")
        .withPermission(CommandPermission.NONE)
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val player = PointDatas.points[sender.uniqueId]

            if (player == null) {
                sender.sendMessage("§cYou aren't have homes.")
            } else {
                if (!player.any { it.getPointName() == args[0] as String }) {
                    sender.sendMessage("§cHome not found.")
                    return@PlayerCommandExecutor
                }
                PointDatas.points[sender.uniqueId]?.removeIf { it.getPointName() == args[0] as String }
                PointDatas.save()
                sender.sendMessage("§7Home removed successfully.")
            }
        })
}