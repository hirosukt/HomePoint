package love.chihuyu.homepoint.point

import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable

class Point(val pointName: String, private val location: Location): ConfigurationSerializable {

    override fun serialize(): MutableMap<String, Any> {
        val map = hashMapOf<String, Any>()

        map["pointName"] = pointName
        map["location"] = location

        return map
    }
}