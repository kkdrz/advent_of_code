import distancecalculator.ManhattanDistanceCalculator
import distancecalculator.StepsDistanceCalculator
import java.nio.file.Paths

fun main() {

    part1()
    part2()
}

private fun part1() {
    val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/main/resources/input.txt"))
    val intersectionPoints = IntersectionPointsFinder().getIntersectionPoints(wires)
    val closestInterceptionPoint =
        ClosestIntersectionPointFinder().getClosestIntersectionPoint(intersectionPoints,
            ManhattanDistanceCalculator()
        )

    println(closestInterceptionPoint)
}

private fun part2() {
    val wires = InstructionsInterpreter().getWiresFromFile(Paths.get("./src/main/resources/input.txt"))
    val intersectionPoints = IntersectionPointsFinder().getIntersectionPoints(wires)

    val closestInterceptionPoint =
        ClosestIntersectionPointFinder().getClosestIntersectionPoint(intersectionPoints,
            StepsDistanceCalculator(wires)
        )

    println(closestInterceptionPoint)
}


