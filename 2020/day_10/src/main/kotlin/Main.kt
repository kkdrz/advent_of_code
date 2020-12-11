import java.io.File

fun main() {
    val data = File("src/main/resources/input.txt").readLines().map { it.toInt() }.toMutableList()

    val chain = mutableListOf<Int>()
    val differences = mutableMapOf<Int, Int>()
    var currentAdapter = 0
    while (data.isNotEmpty()) {
        val nextAdapter = findWeakestAdapterForInputOf(currentAdapter, data)

        differences.merge(nextAdapter - currentAdapter, 1) { t, u -> t + u }

        chain.add(nextAdapter)
        currentAdapter = nextAdapter
    }
    differences[3] = differences[3]!! + 1 //built-in adapter
    println("Part 1: ${differences[1]!! * differences[3]!!}")

}

fun findWeakestAdapterForInputOf(jolts: Int, data: MutableList<Int>): Int {
    val adapter = data.filter { it - jolts < 4 }.minOrNull()
    data.remove(adapter)
    return adapter!!
}