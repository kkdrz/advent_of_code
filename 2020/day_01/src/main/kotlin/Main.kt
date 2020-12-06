import java.io.File

fun main() {

    val expenses = File("src/main/resources/input.txt").readLines().map { it.toInt() }

    expenses.forEach { i ->
        expenses.forEach { j ->
            if (i + j == 2020) {
                println(i * j)
                return
            }
        }
    }
}