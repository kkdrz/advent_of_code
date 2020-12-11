import java.io.File

fun main() {
    val instructions = File("src/main/resources/input.txt")
            .readLines()
            .map { decodeInstruction(it) }

    val executor1 = Executor(instructions)
    executor1.execute()
    println("Part 1: ${executor1.acc}")

    val nopOrJmp = mutableListOf<Int>()
    instructions.forEachIndexed { index, instruction -> if (instruction.type == "nop" || instruction.type == "jmp") nopOrJmp.add(index) }

    nopOrJmp.forEach { i ->
        val instructionsModified = instructions.toMutableList()
        instructionsModified[i] = Instruction(if (instructionsModified[i].type == "jmp") "nop" else "jmp", instructionsModified[i].value)

        val executor = Executor(instructionsModified)
        if (executor.execute()) {
            println("Part 2: ${executor.acc}")
        }
    }
}

class Executor(private val instructions: List<Instruction>) {
    var acc = 0
    private var runLog = mutableMapOf<Int, Int>().let {
        for (i in 0..instructions.size) {
            it[i] = 0
        }
        it
    }

    fun execute(): Boolean = execute(0)

    private fun execute(instructionId: Int): Boolean {

        if (instructionId >= instructions.size) {
            return true
        }

        val instruction = instructions[instructionId]
        runLog[instructionId] = runLog[instructionId]!! + 1

        if (runLog[instructionId]!! > 1) {
            return false
        }

        when (instruction.type) {
            "nop" -> return execute(instructionId + 1)
            "acc" -> {
                acc += instruction.value
                return execute(instructionId + 1)
            }
            "jmp" -> return execute(instructionId + instruction.value)
        }

        println("Smth went wrong")
        return false
    }
}

fun decodeInstruction(code: String) = Instruction(code.substring(0, 3), code.substring(4).toInt())

data class Instruction(val type: String, val value: Int)
