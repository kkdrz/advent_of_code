import Config.Companion.DEFAULT_STARTING_POINT
import wire.Point

class ClosestIntersectionPointFinder {

    fun getClosestIntersectionPoint(points: List<Point>, calculator: DistanceCalculator) =
        points
            .associateWith { calculator.calculate(DEFAULT_STARTING_POINT, it) }
            .minWith(Comparator { o1, o2 -> o1.value.compareTo(o2.value) })
}