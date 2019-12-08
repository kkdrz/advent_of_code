import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.streams.toList

internal class InstructionsInterpreterTest {

    private val instructionsInterpreter = InstructionsInterpreter()

    @Test
    fun getWiresFromFile() {

        val expectedWire1 = WireBuilder(Point(0, 0))
            .right(10)
            .left(1)
            .up(3)
            .down(2)
            .left(1)
            .left(1)
            .create()

        val expectedWire2 = WireBuilder(Point(0, 0))
            .left(5)
            .right(1)
            .up(2)
            .down(3)
            .left(4)
            .left(5)
            .create()

        val wires = instructionsInterpreter.getWiresFromFile(Paths.get("./src/test/resources/input.txt")).toList()

        assertEquals(2, wires.size)
        assertTrue(wires.contains(expectedWire1))
        assertTrue(wires.contains(expectedWire2))
    }
}