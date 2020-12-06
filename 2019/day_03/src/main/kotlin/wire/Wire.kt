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

    val steps: Int = when(lineType) {
        VERTICAL -> Math.abs(from.y - to.y)
        HORIZONTAL -> Math.abs(from.x - to.x)
    }

    fun getInterceptionPoints(paths: List<StraightPath>): List<Point> =
        paths.mapNotNull { getInterceptionPoint(it) }

    fun getInterceptionPoint(otherPath: StraightPath): Point? =
        when (lineType) {
            otherPath.lineType -> null
            VERTICAL ->
                if (this.from.x.between(otherPath.from.x, otherPath.to.x)
                    && otherPath.from.y.between(this.from.y, this.to.y)
                )
                    Point(from.x, otherPath.from.y)
                else null
            HORIZONTAL -> if (this.from.y.between(
                    otherPath.from.y,
                    otherPath.to.y
                ) && otherPath.from.x.between(this.from.x, this.to.x)
            ) Point(
                otherPath.from.x,
                from.y
            ) else null
        }

    private fun Int.between(from: Int, to: Int): Boolean = this in from toward to

    private infix fun Int.toward(to: Int): IntProgression {
        val step = if (this > to) -1 else 1
        return IntProgression.fromClosedRange(this, to, step)
    }

    fun includes(point: Point): Boolean = when (lineType) {
        VERTICAL -> point.x == this.from.x && point.y.between(this.from.y, this.to.y)
        HORIZONTAL -> point.y == this.from.y && point.x.between(this.from.x, this.to.x)
    }

    fun getStepsForPoint(point: Point): Int {
        if (!includes(point)) {
            throw java.lang.IllegalArgumentException("Path does not contain point $point")
        }

        return when(lineType) {
            VERTICAL -> Math.abs(point.y - this.from.y)
            HORIZONTAL -> Math.abs(point.x - this.from.x)
        }
    }
}


enum class LineType {
    HORIZONTAL, VERTICAL
}

data class Point(val x: Int, val y: Int) {

}