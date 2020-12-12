import java.io.File
import kotlin.math.min

fun main() {
    val data = File("src/main/resources/input.txt").readLines()

    run(data, ::getAdjacent, 4)
    run(data, ::getFirstSeen, 5)

}

private fun run(data: List<String>, seatsToWatch: (List<String>, Int, Int) -> List<Char>, minOccupiedToEmpty: Int) {
    var prevState = data
    var currState = getNewState(prevState, seatsToWatch, minOccupiedToEmpty)

    while (prevState != currState) {
        prevState = currState
        currState = getNewState(currState, seatsToWatch, minOccupiedToEmpty)
    }

    var occupiedCount = 0
    prevState.forEach { row -> row.forEach { seat -> if (seat == '#') occupiedCount++ } }

    println(occupiedCount)
}

private fun getNewState(data: List<String>, seatsToWatch: (List<String>, Int, Int) -> List<Char>, minOccupiedToEmpty: Int): MutableList<String> {
    val newState = data.toMutableList()

    for (y in data.indices) {
        for (x in data[y].indices) {
            if (data[y][x] == '.') continue
            val adjacent = seatsToWatch(data, y, x)

            val adjacentOccupied = adjacent.filter { isOccupied(it) }.count() >= minOccupiedToEmpty

            if (adjacentOccupied) {
                newState[y] = StringBuilder(newState[y]).also { it.setCharAt(x, 'L') }.toString()
            }

            val adjacentFree = adjacent.all { !isOccupied(it) }

            if (adjacentFree) {
                newState[y] = StringBuilder(newState[y]).also { it.setCharAt(x, '#') }.toString()
            }
        }
    }
    return newState
}

fun getFirstSeen(data: List<String>, y: Int, x: Int): List<Char> {
    val firstSeen = mutableListOf<Char>()

    //right
    for (i in x + 1 until data[y].length) {
        if (data[y][i] != '.') {
            firstSeen.add(data[y][i])
            break
        }
    }

    //left
    for (i in x - 1 downTo 0) {
        if (data[y][i] != '.') {
            firstSeen.add(data[y][i])
            break
        }
    }

    //down
    for (i in y + 1 until data.size) {
        if (data[i][x] != '.') {
            firstSeen.add(data[i][x])
            break
        }
    }

    //up
    for (i in y - 1 downTo 0) {
        if (data[i][x] != '.') {
            firstSeen.add(data[i][x])
            break
        }
    }

    //diagonal 1
    var i = 1
    while (x + i < data[y].length && y + i < data.size) {
        if (data[y + i][x + i] != '.') {
            firstSeen.add(data[y + i][x + i])
            break
        }
        i++
    }

    i = 1
    while (x - i > -1 && y - i > -1) {
        if (data[y - i][x - i] != '.') {
            firstSeen.add(data[y - i][x - i])
            break
        }
        i++
    }

    //diagonal 2
    i = 1
    while (x + i < data[y].length && y - i > -1) {
        if (data[y - i][x + i] != '.') {
            firstSeen.add(data[y - i][x + i])
            break
        }
        i++
    }

    i = 1
    while (x - i > -1 && y + i < data.size) {
        if (data[y + i][x - i] != '.') {
            firstSeen.add(data[y + i][x - i])
            break
        }
        i++
    }

    return firstSeen
}

private fun getAdjacent(data: List<String>, y: Int, x: Int): List<Char> {
    val adjacent = mutableListOf<Char>()

    data.getOrNull(y)?.getOrNull(x - 1)?.let { adjacent.add(it) }
    data.getOrNull(y)?.getOrNull(x + 1)?.let { adjacent.add(it) }
    data.getOrNull(y - 1)?.getOrNull(x)?.let { adjacent.add(it) }
    data.getOrNull(y + 1)?.getOrNull(x)?.let { adjacent.add(it) }
    data.getOrNull(y - 1)?.getOrNull(x - 1)?.let { adjacent.add(it) }
    data.getOrNull(y + 1)?.getOrNull(x - 1)?.let { adjacent.add(it) }
    data.getOrNull(y - 1)?.getOrNull(x + 1)?.let { adjacent.add(it) }
    data.getOrNull(y + 1)?.getOrNull(x + 1)?.let { adjacent.add(it) }

    return adjacent
}

fun isOccupied(it: Char): Boolean = it == '#'

