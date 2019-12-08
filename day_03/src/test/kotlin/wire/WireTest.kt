package wire

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WireTest {

    @Test
    fun should_find_interception_points() {
        val wire1 = WireBuilder(Point(0, 0))
            .right(10)
            .up(3)
            .left(1)
            .left(1)
            .create()

        val wire2 = WireBuilder(Point(0, 0))
            .up(1)
            .right(2)
            .down(2)
            .right(1)
            .up(2)
            .right(1)
            .down(2)
            .create()

        val expectedInterceptionPoints: List<Point> = listOf(
            Point(0,0),
            Point(2,0),
            Point(3,0),
            Point(4,0)
        )

        val actualInterceptionPoints = wire1.getInterceptionPoints(wire2)

        assertEquals(expectedInterceptionPoints, actualInterceptionPoints)
    }


    @Test
    fun should_find_same_points() {
        val wire1 = WireBuilder(Point(0, 0))
            .right(10)
            .up(3)
            .left(1)
            .left(1)
            .create()

        val wire2 = WireBuilder(Point(0, 0))
            .up(1)
            .right(2)
            .down(2)
            .right(1)
            .up(2)
            .right(1)
            .down(2)
            .create()

        val actualInterceptionPoints1 = wire1.getInterceptionPoints(wire2).toSet()
        val actualInterceptionPoints2 = wire2.getInterceptionPoints(wire1).toSet()

        assertEquals(actualInterceptionPoints1, actualInterceptionPoints2)
    }
}