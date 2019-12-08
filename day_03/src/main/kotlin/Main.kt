import java.nio.file.Paths

fun main() {

    val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/main/resources/input.txt"))
    val intersectionPoints = IntersectionPointsFinder().getIntersectionPoints(wires)
    val closestInterceptionPoint =
        ClosestIntersectionPointFinder().getClosestIntersectionPoint(intersectionPoints, ManhattanDistanceCalculator())

    println(closestInterceptionPoint)
}


