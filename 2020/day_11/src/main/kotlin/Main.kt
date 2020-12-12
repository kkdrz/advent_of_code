import java.io.File

fun main() {
    val data = File("src/main/resources/input.txt").readLines()

    var prevState = data
    var currState = getNewState(prevState)

    while (prevState != currState) {
        prevState = currState
        currState = getNewState(currState)
    }

    var occupiedCount = 0
    prevState.forEach { row -> row.forEach { seat -> if (seat == '#') occupiedCount++ } }

    println(occupiedCount)
}

private fun getNewState(data: List<String>): MutableList<String> {
    val newState = data.toMutableList()

    for (y in data.indices) {
        for (x in data[y].indices) {
            if (data[y][x] == '.') continue
            val adjacent = getAdjacent(data, y, x)

            val adjacentOccupied = adjacent.filter { isOccupied(it) }.count() > 3

            if (adjacentOccupied) {
                newState[y] = StringBuilder(newState[y]).also { it.setCharAt(x, 'L') }.toString()
            }

            val adjacentFree = adjacent.all { isFree(it) }

            if (adjacentFree) {
                newState[y] = StringBuilder(newState[y]).also { it.setCharAt(x, '#') }.toString()
            }
        }
    }
    return newState
}

fun isFree(it: Char): Boolean = it != '#'

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

