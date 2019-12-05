fun main() {
    val computer = Computer()

    val program = computer.readProgramFromFile("input.txt").toMutableList();

    program[1] = 12
    program[2] = 2

    println(computer.compute(program)[0])
}