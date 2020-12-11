import java.io.File

fun main() {
    val data = File("src/main/resources/input.txt").readLines().map { it.toLong() }

    val preambleLength = 25

    data.forEachIndexed { index, it ->
        if (index >= preambleLength) {
            val isCorrect = isSumOfNumbersBetweenIndexes(data, index - preambleLength, index - 1, it)
            if (!isCorrect) println("Part 1 error: $it")
        }
    }

}

fun isSumOfNumbersBetweenIndexes(data: List<Long>, startIndex: Int, endIndex: Int, number: Long): Boolean {

    for (i in startIndex..endIndex) {
        for (j in startIndex..endIndex) {
            if (i != j && data[i] + data[j] == number) {
                return true
            }
        }
    }
    return false
}



