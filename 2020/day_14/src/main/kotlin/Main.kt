import java.io.File
import java.lang.Long.toBinaryString

fun main() {
    val data = File("src/main/resources/input.txt")
            .readLines()

    val executor = Executor()

    data.forEach { line ->
        if (line.startsWith("mask")) {
            executor.mask = line.substring(7)
        } else {
            executor.set(
                    line.substring(4, line.indexOf(']')).toInt(),
                    line.substring(line.indexOf("= ") + 2).toLong()
            )
        }
    }

    println(executor.memory.values.sum())

}


class Executor {

    var mask: String = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    var memory = mutableMapOf<Int, Long>()

    fun set(index: Int, value: Long) {
        memory[index] = applyMask(value)
    }

    private fun applyMask(value: Long): Long {
        val binary: CharArray = toBinaryString(value).let {
            if (it.length < mask.length)
                ("0".repeat(mask.length - it.length) + it).toCharArray()
            else it.toCharArray()
        }

        mask.forEachIndexed { index, char -> if (char != 'X') binary[index] = char }

        return java.lang.Long.parseUnsignedLong(String(binary), 2)
    }
}