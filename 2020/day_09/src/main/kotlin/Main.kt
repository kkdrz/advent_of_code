import java.io.File

fun main() {
    val data = File("src/main/resources/input.txt").readLines().map { it.toLong() }

    val preambleLength = 25

    val errorValue = findErrorValue(data, preambleLength)
    println("Part 1: $errorValue")
    for (i in 0..data.size) {

        var currentSum: Long = data[i]
        var j = i
        while (currentSum < errorValue) {
            currentSum += data[++j]
        }

        if (currentSum == errorValue) {
            val max = data.slice(i..j).maxOrNull()
            val min = data.slice(i..j).minOrNull()
            println("Max: $max")
            println("Min: $min")
            println("Part 2: ${max!!.plus(min!!)}")
            return
        }
    }

}

private fun findErrorValue(data: List<Long>, preambleLength: Int): Long {
    data.forEachIndexed { index, it ->
        if (index >= preambleLength) {
            val isCorrect = isSumOfNumbersBetweenIndexes(data, index - preambleLength, index - 1, it)
            if (!isCorrect) return it
        }
    }
    return 0
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



