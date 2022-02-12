package love.chihuyu.homepoint.point

import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable

class Point(): ConfigurationSerializable {

    private lateinit var pointName: String
    private lateinit var location: Location

    constructor(pointName: String, location: Location) : this() {
        this.pointName = pointName
        this.location = location
    }

    constructor(map: MutableMap<String, Any>) : this() {
        this.pointName = map["pointName"].toString()
        this.location = map["location"] as Location
    }

    override fun serialize(): Map<String, Any> {
        val map = hashMapOf<String, Any>()

        map["pointName"] = pointName
        map["location"] = location

        return map
    }

    fun deserialize(pointName: String, location: Location): Point {
        return Point(pointName, location)
    }

    fun getPointName(): String {
        return pointName
    }

    fun getLocation(): Location {
        return location
    }
}