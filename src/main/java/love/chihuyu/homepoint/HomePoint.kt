package love.chihuyu.homepoint

import love.chihuyu.homepoint.command.commands.ListHome
import love.chihuyu.homepoint.command.commands.Load
import love.chihuyu.homepoint.command.commands.Save
import love.chihuyu.homepoint.command.commands.SetHome
import love.chihuyu.homepoint.point.Point
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin

class HomePoint : JavaPlugin() {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        saveResource("config.yml", false)
        PointDatas.load()
        ConfigurationSerialization.registerClass(Point::class.java)

        SetHome.register()
        Save.register()
        Load.register()
        ListHome.register()

        logger.info("plugin has loaded.")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        PointDatas.save()
        logger.info("plugin has unloaded")
    }
}