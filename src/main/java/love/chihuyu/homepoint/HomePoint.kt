package love.chihuyu.homepoint

import love.chihuyu.homepoint.commands.*
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

        listOf(
            CommandBackHome.main,
            CommandDelHome.main,
            CommandDelHome.forceremove,
            CommandHome.main,
            CommandHome.withSpecifyHome,
            CommandListHome.main,
            CommandLoadHome.main,
            CommandRenameHome.main,
            CommandSaveHome.main,
            CommandSetHome.main
        ).forEach { it.register() }

        logger.info("plugin has loaded.")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        PointDatas.save()
        logger.info("plugin has unloaded")
    }
}