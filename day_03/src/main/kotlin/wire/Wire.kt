package wire

import wire.LineType.HORIZONTAL
import wire.LineType.VERTICAL

data class Wire(val paths: List<StraightPath>) {

    fun getInterceptionPoints(otherWire: Wire) =
        if (otherWire == this) listOf()
        else {
            paths.map { it.getInterceptionPoints(otherWire.paths) }
                .filter { it.isNotEmpty() }
                .flatten()
        }


}

data class StraightPath(val from: Point, val to: Point) {

    private val lineType =
        when {
            from.x == to.x -> VERTICAL
            from.y == to.y -> HORIZONTAL
            else -> throw IllegalArgumentException(
                "Only vertical and horizontal paths supported."
            )
        }

    fun getInterceptionPoints(paths: List<StraightPath>): List<Point> =
        paths.mapNotNull { getInterceptionPoint(it) }

    fun getInterceptionPoint(otherPath: StraightPath): Point? =
        when (lineType) {
            otherPath.lineType -> null
            VERTICAL -> if (from.x in otherPath.from.x toward otherPath.to.x && otherPath.from.y in from.y toward to.y) Point(
                from.x,
                otherPath.from.y
            ) else null
            HORIZONTAL -> if (from.y in otherPath.from.y toward otherPath.to.y && otherPath.from.x in from.x toward to.x) Point(
                otherPath.from.x,
                from.y
            ) else null
        }

    private infix fun Int.toward(to: Int): IntProgression {
        val step = if (this > to) -1 else 1
        return IntProgression.fromClosedRange(this, to, step)
    }
}


enum class LineType {
    HORIZONTAL, VERTICAL
}

data class Point(val x: Int, val y: Int) {

}