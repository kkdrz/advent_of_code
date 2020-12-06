import java.io.File

fun main() {

    val map = File("src/main/resources/input.txt")
        .readLines().toMutableList()

    val maxY = map.size - 1
    val maxX = map[0].length - 1

    val slopeRight = 3
    val slopeDown = 1

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

    println(trees)
}
