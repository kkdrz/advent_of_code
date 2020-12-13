import java.io.File

fun main() {
    val data = File("src/main/resources/input.txt")
            .readLines()

    val myTimestamp = data[0].toLong()

    // (id, waitingTime)
    val nearestBus = data[1].split(",")
            .filter { it != "x" }
            .map { it.toInt() to it.toInt() - (myTimestamp % it.toInt()) }
            .minByOrNull { it.second }!!

    println(nearestBus.first * nearestBus.second)

}
