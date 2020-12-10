import java.io.File

fun main() {
    val seats = File("src/main/resources/input.txt")
            .readLines()
            .map { Seat(it) }

    seats.forEach { println("Row: ${it.row}, Column: ${it.column}, Id: ${it.id()}") }

    println(seats.map { it.id() }.maxOrNull())
}

class Seat(encoded: String) {

    val row: Int
    val column: Int

    init {
        row = decodeRow(encoded.substring(0, 7))
        column = decodeColumn(encoded.substring(7))
    }

    fun id(): Int = row * 8 + column

    private fun decodeRow(code: String): Int {
        var from = 0
        var to = 127

        code.forEach {
            when (it) {
                'F' -> to -= (to - from) / 2 + 1
                'B' -> from += (to - from) / 2 + 1
            }
        }

        return from
    }

    private fun decodeColumn(code: String): Int {
        var from = 0
        var to = 7

        code.forEach {
            when (it) {
                'L' -> to -= (to - from) / 2 + 1
                'R' -> from += (to - from) / 2 + 1
            }
        }

        return from
    }


}