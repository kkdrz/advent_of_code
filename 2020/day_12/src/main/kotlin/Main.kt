import java.io.File
import kotlin.math.abs

fun main() {
    val data = File("src/main/resources/input.txt")
            .readLines()
            .map { it[0] to it.substring(1).toInt() }

    val ship1 = Part1Ship()
    execShip(data, ship1)
    println(abs(ship1.x) + abs(ship1.y))

    val ship2 = Part2Ship()
    execShip(data, ship2)
    println(abs(ship2.x) + abs(ship2.y))

}

private fun execShip(data: List<Pair<Char, Int>>, ship: Ship) {
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
}

interface Ship {
    fun goNorth(units: Int)
    fun goSouth(units: Int)
    fun goEast(units: Int)
    fun goWest(units: Int)
    fun goForward(units: Int)
    fun rotateLeft(degrees: Int)
    fun rotateRight(degrees: Int)
}

class Part2Ship : Ship {

    var x = 0
    var y = 0

    var waypointX = 10
    var waypointY = -1

    override fun goNorth(units: Int) {
        waypointY -= units
    }

    override fun goSouth(units: Int) {
        waypointY += units
    }

    override fun goEast(units: Int) {
        waypointX += units
    }

    override fun goWest(units: Int) {
        waypointX -= units
    }

    override fun goForward(units: Int) {
        x += units * waypointX
        y += units * waypointY
    }

    override fun rotateLeft(degrees: Int) {
        for (i in 0 until degrees / 90) {
            waypointY = -waypointX.also { waypointX = waypointY }
        }
    }

    override fun rotateRight(degrees: Int) {
        rotateLeft(360 - degrees)
    }

}

open class Part1Ship : Ship {
    var x = 0
    var y = 0

    var direction: Int = 0
    val directions: List<Char> = listOf('E', 'N', 'W', 'S')

    override fun goNorth(units: Int) {
        y -= units
    }

    override fun goSouth(units: Int) {
        y += units
    }

    override fun goEast(units: Int) {
        x += units
    }

    override fun goWest(units: Int) {
        x -= units
    }

    override fun goForward(units: Int) {
        when (directions[direction]) {
            'E' -> x += units
            'W' -> x -= units
            'N' -> y -= units
            'S' -> y += units
        }
    }

    override fun rotateLeft(degrees: Int) {
        direction = (direction + (degrees / 90)) % directions.size
    }

    override fun rotateRight(degrees: Int) {
        rotateLeft(360 - degrees)
    }


}