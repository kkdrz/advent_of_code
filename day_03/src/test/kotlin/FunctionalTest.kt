import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import wire.Point
import java.nio.file.Paths


internal class FunctionalTest {

    @Test
    fun should_return_correct_answer() {

        val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/test/resources/functional_test.txt"))
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

}