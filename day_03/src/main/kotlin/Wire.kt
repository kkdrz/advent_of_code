data class Wire(val paths: List<StraightPath>) {

}

class WireBuilder(private val startingPoint: Point) {
    private val paths: MutableList<StraightPath> = mutableListOf()

    fun left(distance: Int): WireBuilder {
        val lastPoint: Point = getLastPoint()
        paths.add(StraightPath(lastPoint, Point(lastPoint.x - distance, lastPoint.y)))
        return this
    }

    fun right(distance: Int): WireBuilder {
        val lastPoint: Point = getLastPoint()
        paths.add(StraightPath(lastPoint, Point(lastPoint.x + distance, lastPoint.y)))
        return this
    }

    fun up(distance: Int): WireBuilder {
        val lastPoint: Point = getLastPoint()
        paths.add(StraightPath(lastPoint, Point(lastPoint.x, lastPoint.y + distance)))
        return this
    }

    fun down(distance: Int): WireBuilder {
        val lastPoint: Point = getLastPoint()
        paths.add(StraightPath(lastPoint, Point(lastPoint.x, lastPoint.y - distance)))
        return this
    }

    fun create(): Wire = Wire(paths)

    private fun getLastPoint() = if (paths.size > 0) paths.last().to else startingPoint
}

data class StraightPath(val from: Point, val to: Point) {

}

data class Point(val x: Int, val y: Int) {

}