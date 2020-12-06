import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ComputerTest {

    private val computer = Computer()

    @Test
    fun should_read_program() {
        assertEquals(listOf(1,0,0,3,1,1,2,3), computer.readProgramFromFile("test_input.txt"))
    }

    @Test
    fun should_execute_program_with_addition() {
        val initialProgram = "1,0,0,0,99";
        val expectedResult = "2,0,0,0,99"

        val actualResult = computer.compute(initialProgram);

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun should_execute_program_with_multiplication() {
        val initialProgram = "2,3,0,3,99";
        val expectedResult = "2,3,0,6,99"

        val actualResult = computer.compute(initialProgram);

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun should_execute_program_with_addition_and_multiplication() {
        val initialProgram = "1,1,1,4,99,5,6,0,99";
        val expectedResult = "30,1,1,4,2,5,6,0,99"

        val actualResult = computer.compute(initialProgram);

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun should_execute_program() {
        val initialProgram = "2,4,4,5,99,0";
        val expectedResult = "2,4,4,5,99,9801"

        val actualResult = computer.compute(initialProgram);

        assertEquals(expectedResult, actualResult)
    }

}