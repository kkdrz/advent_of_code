import java.io.File

fun main() {
    val instructions = File("src/main/resources/input.txt")
            .readLines()
            .map { decodeInstruction(it) }

    Executor(instructions).execute()
}

class Executor(private val instructions: List<Instruction>) {
    var acc = 0
    private var runLog = mutableMapOf<Int, Int>().let {
        for (i in 0..instructions.size) {
            it[i] = 0
        }
        it
    }

    fun execute() {
        execute(0)
    }

    private fun execute(instructionId: Int) {
        val instruction = instructions[instructionId]

        runLog[instructionId] = runLog[instructionId]!! + 1

        if (runLog[instructionId]!! > 1) {
            println(acc)
            return
        }

        when (instruction.type) {
            "nop" -> execute(instructionId + 1)
            "acc" -> {
                acc += instruction.value
                execute(instructionId + 1)
            }
            "jmp" -> execute(instructionId + instruction.value)
        }
    }
}

fun decodeInstruction(code: String) = Instruction(code.substring(0, 3), code.substring(4).toInt())

data class Instruction(val type: String, val value: Int)
