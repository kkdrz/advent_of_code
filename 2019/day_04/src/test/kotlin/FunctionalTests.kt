import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FunctionalTests {

    @Test
    fun part1() {
        val passwords = listOf(111111, 223450, 123789)

        val filtered = passwords
            .filter { isSixDigitNumber(it) }
            .filter { containsTwoSameAdjacentDigits(it) }
            .filter { digitsDoesNotDecrease(it) }

        assertEquals(1, filtered.size)
        assertTrue(filtered.contains(111111))
    }
}