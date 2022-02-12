package love.chihuyu.homepoint

import love.chihuyu.homepoint.command.commands.*
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
        ConfigurationSerialization.registerClass(Point::class.java)
        PointDatas.points = mutableMapOf()
        PointDatas.tempPoints = mutableMapOf()
        PointDatas.load()

        SetHome.register()
        Save.register()
        Load.register()
        ListHome.register()
        Home.register()
        DelHome.register()
        BackHome.register()

        logger.info("plugin has loaded.")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        PointDatas.save()
        logger.info("plugin has unloaded")
    }
}