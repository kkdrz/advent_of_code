import java.io.File
import java.util.*

fun main() {
    val data = File("src/main/resources/input.txt")
            .readLines()

    part1(data)

    part2(data)
}

private fun part2(data: List<String>) {
    // [(minutes, busId),...]
    val buses: Queue<Pair<Int, Long>> = LinkedList(
            data[1].split(",")
                    .mapIndexed { index, busId -> index to busId }
                    .filter { it.second != "x" }
                    .map { it.first to it.second.toLong() }
    )


    val processedBusses = mutableListOf<Pair<Int, Long>>(buses.poll())
    var timestamp = processedBusses[0].second

    val stepFunction: (Int) -> Long = { n -> timestamp + processedBusses.map { it.second }.reduce { acc, busId -> acc * busId } * n }

    var n = 0
    while (buses.isNotEmpty()) {
        val testedTimestamp = stepFunction(n++)
        val bus = buses.peek()
        if ((testedTimestamp + bus.first) % bus.second == 0L) {
            processedBusses.add(buses.poll())
            timestamp = testedTimestamp
            n = 0
        }
    }

    println("Part 2: $timestamp")
}

private fun part1(data: List<String>) {
    val myTimestamp = data[0].toLong()

    // (id, waitingTime)
    val nearestBus = data[1].split(",")
            .filter { it != "x" }
            .map { it.toInt() to it.toInt() - (myTimestamp % it.toInt()) }
            .minByOrNull { it.second }!!

    println("Part 1: ${nearestBus.first * nearestBus.second}")
}
