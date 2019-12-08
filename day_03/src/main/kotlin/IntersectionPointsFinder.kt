import Config.Companion.DEFAULT_STARTING_POINT
import wire.Point
import wire.Wire

class IntersectionPointsFinder {

    fun getIntersectionPoints(wires: List<Wire>): List<Point> =
        wires.flatMap { wire ->
            wires
                .minus(wire)
                .flatMap { wire.getInterceptionPoints(it) }
        }.distinct()
            .minus(DEFAULT_STARTING_POINT)
}