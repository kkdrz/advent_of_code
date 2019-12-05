fun main() {
    val computer = Computer()
    part1(computer);
    part2(computer);


}

fun part1(computer: Computer) {

    val program = computer.readProgramFromFile("input.txt").toMutableList();

    program[1] = 12
    program[2] = 2

    println(computer.compute(program)[0])
}

fun part2(computer: Computer) {

    val program = computer.readProgramFromFile("input.txt");

    for (noun in 0 until 99) {
        for (verb in 0 until 99) {
            val mutableProgram = program.toMutableList();
            mutableProgram[1] = noun
            mutableProgram[2] = verb
            if (computer.compute(mutableProgram)[0] == 19690720) {
                println("noun = $noun")
                println("verb = $verb")
                println("answer = ${100 * noun + verb}")
                return;
            }
        }
    }

}