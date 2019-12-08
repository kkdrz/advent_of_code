import java.nio.file.Paths

fun main() {

    val instructionsInterpreter = InstructionsInterpreter()
    instructionsInterpreter.getWiresFromFile(Paths.get("./src/main/resources/input.txt"))
}


