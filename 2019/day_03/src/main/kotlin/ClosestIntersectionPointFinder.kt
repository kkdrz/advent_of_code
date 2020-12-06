import distancecalculator.DistanceCalculator
import wire.Point

class ClosestIntersectionPointFinder {

    fun getClosestIntersectionPoint(points: List<Point>, calculator: DistanceCalculator) =
        points
            .associateWith { calculator.calculate(it) }
            .minWith(Comparator { o1, o2 -> o1.value.compareTo(o2.value) })
}