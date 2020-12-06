import java.lang.IllegalStateException

class Computer {

    fun readProgramFromFile(fileName: String): List<Int> =
        {}.javaClass.getResource(fileName)
            .readText()
            .split(",")
            .map { it.toInt() }

    fun compute(program: String): String = compute(program.split(",").map { it.toInt() }.toMutableList()).joinToString(",")

    fun compute(program: MutableList<Int>): MutableList<Int> =
        computeOpCode(program, 0);

    private fun computeOpCode(program: MutableList<Int>, position: Int): MutableList<Int> {
        when (program[position]) {
            1 -> program[program[position + 3]] = program[program[position + 1]] + program[program[position + 2]]
            2 -> program[program[position + 3]] = program[program[position + 1]] * program[program[position + 2]]
            99 -> return program;
            else -> throw IllegalStateException("Command " + program[position] + " unknown");
        }

        return computeOpCode(program, position + 4);
    }
}