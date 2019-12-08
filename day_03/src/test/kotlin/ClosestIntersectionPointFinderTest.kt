import distancecalculator.ManhattanDistanceCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import wire.Point

internal class ClosestIntersectionPointFinderTest {


    @Test
    fun should_find_closest_point() {

        val points = listOf(
            Point(123, 144),
            Point(-12, 44),
            Point(0, 10000),
            Point(10000, 0),
            Point(5, 5),
            Point(12, -45),
            Point(44, 23),
            Point(12, -1)
        )

        val closestPoint =
            ClosestIntersectionPointFinder().getClosestIntersectionPoint(points,
                ManhattanDistanceCalculator()
            )

        assertEquals(Point(5, 5), closestPoint!!.key)
        assertEquals(10, closestPoint.value)
    }
}