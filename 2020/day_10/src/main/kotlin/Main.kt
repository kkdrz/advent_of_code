import java.io.File
import java.util.function.BiFunction

fun main() {
    val data = File("src/main/resources/input.txt").readLines().map { it.toInt() }.toMutableList()
    data.add(0)

    part1(data.toMutableList())

    val count = mutableMapOf<Int, Long>(0 to 1)

    data.sorted().forEach {
        for (i in 1..3) {
            count.merge(it + i, count[it]!!) { t, u -> t + u }
        }
    }

    println("Part 2: ${count[count.keys.maxOrNull()!!]}")
}


private fun part1(data: MutableList<Int>) {
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