import java.io.File
import kotlin.math.abs

fun main() {
    val data = File("src/main/resources/input.txt")
            .readLines()
            .map { it[0] to it.substring(1).toInt() }

    val ship = Ship()

    data.forEach { command ->
        when (command.first) {
            'N' -> ship.goNorth(command.second)
            'E' -> ship.goEast(command.second)
            'W' -> ship.goWest(command.second)
            'S' -> ship.goSouth(command.second)
            'L' -> ship.rotateLeft(command.second)
            'R' -> ship.rotateRight(command.second)
            'F' -> ship.goForward(command.second)
        }
    }

    println(abs(ship.x) + abs(ship.y))
}

class Ship {
    var x = 0
    var y = 0

    var direction: Int = 0
    val directions: List<Char> = listOf('E', 'N', 'W', 'S')

    fun goNorth(units: Int) {
        y -= units
    }

    fun goSouth(units: Int) {
        y += units
    }

    fun goEast(units: Int) {
        x += units
    }

    fun goWest(units: Int) {
        x -= units
    }

    fun goForward(units: Int) {
        when (directions[direction]) {
            'E' -> x += units
            'W' -> x -= units
            'N' -> y -= units
            'S' -> y += units
        }
    }

    fun rotateLeft(degrees: Int) {
        direction = (direction + (degrees / 90)) % directions.size
    }

    fun rotateRight(degrees: Int) {
        rotateLeft(360 - degrees)
    }


}