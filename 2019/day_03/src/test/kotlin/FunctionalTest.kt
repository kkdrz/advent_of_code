import distancecalculator.ManhattanDistanceCalculator
import distancecalculator.StepsDistanceCalculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import wire.Point
import java.nio.file.Paths


internal class FunctionalTest {

    @Test
    fun part1() {

        val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/test/resources/functional_test_part_1.txt"))
        val intersectionPoints = IntersectionPointsFinder().getIntersectionPoints(wires)
        val closestInterceptionPoint =
            ClosestIntersectionPointFinder().getClosestIntersectionPoint(
                intersectionPoints,
                ManhattanDistanceCalculator()
            )

        Assertions.assertEquals(2, intersectionPoints.size)
        Assertions.assertTrue(intersectionPoints.contains(Point(3, 3)))
        Assertions.assertTrue(intersectionPoints.contains(Point(6, 5)))
        Assertions.assertEquals(Point(3, 3), closestInterceptionPoint!!.key)
        Assertions.assertEquals(6, closestInterceptionPoint.value)

    }

    @Test
    fun part2_1() {

        val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/test/resources/functional_test_part_2_1.txt"))
        val intersectionPoints = IntersectionPointsFinder().getIntersectionPoints(wires)
        val closestInterceptionPoint =
            ClosestIntersectionPointFinder().getClosestIntersectionPoint(
                intersectionPoints,
                StepsDistanceCalculator(wires)
            )

        Assertions.assertEquals(610, closestInterceptionPoint!!.value)

    }

    @Test
    fun part2_2() {

        val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/test/resources/functional_test_part_2_2.txt"))
        val intersectionPoints = IntersectionPointsFinder().getIntersectionPoints(wires)
        val closestInterceptionPoint =
            ClosestIntersectionPointFinder().getClosestIntersectionPoint(
                intersectionPoints,
                StepsDistanceCalculator(wires)
            )

        Assertions.assertEquals(410, closestInterceptionPoint!!.value)

    }

}