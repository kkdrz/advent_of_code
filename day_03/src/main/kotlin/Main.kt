import java.io.File

fun main() {

    val map = File("src/main/resources/input.txt")
        .readLines().toMutableList()

    val result = arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        .map { getNumberOfTrees(mutableListOf<String>().apply { addAll(map) }, it.second, it.first) }
        .map { it.toLong() }
        .reduce { acc, n -> acc * n }

    println(result)
}

private fun getNumberOfTrees(
    map: MutableList<String>,
    slopeDown: Int,
    slopeRight: Int
): Int {
    val maxY = map.size - 1
    val maxX = map[0].length - 1

    var x = 0
    var y = 0
    var trees = 0

    while (y + slopeDown <= maxY) {
        x += slopeRight
        y += slopeDown

        if (x > maxX) {
            x -= maxX + 1
        }

        if (map[y][x] == '#') trees++

    }

    return trees
}
