import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import wire.Point

internal class ManhattanDistanceCalculatorTest {

    val calc = ManhattanDistanceCalculator()

    @Test
    fun should_calculate_manhattan_distance() {

        assertEquals(12, calc.calculate(Point(0,0), Point(6,6)))
    }

}