import distancecalculator.ManhattanDistanceCalculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import wire.Point

internal class ManhattanDistanceCalculatorTest {

    val calc = ManhattanDistanceCalculator()

    @Test
    fun should_calculate_manhattan_distance() {

        assertEquals(12, calc.calculate(Point(6, 6)))
    }

}