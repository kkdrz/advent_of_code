import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FiltersTest {

    @Test
    fun should_filter_out_not_six_digit_numbers() {
        val input = listOf(122, -11, 1223456789, 122456)
        val expected = listOf(122456)

        val actual = input.filter { isSixDigitNumber(it) }

        assertEquals(expected, actual)
    }


    @Test
    fun should_filter_out_when_adjacent_digits_not_same() {
        val input = listOf(123456, 456789, 345678, 112233)
        val expected = listOf(112233)

        val actual = input.filter { containsTwoSameAdjacentDigits(it) }

        assertEquals(expected, actual)
    }

    @Test
    fun should_filter_out_when_digits_decrease() {
        val input = listOf(6554321, 124465, 654332, 112233)
        val expected = listOf(112233)

        val actual = input.filter { digitsDoesNotDecrease(it) }

        assertEquals(expected, actual)
    }

    @Test
    fun should_filter_out_when_more_than_two_same_adjacent_digits() {
        val input = listOf(111211, 111111, 111122, 122456, 112233, 123444)
        val expected = listOf(111122, 122456, 112233)

        val actual = input.filter { containsTwoSameAdjacentDigitsWhichAreNotPartOfBiggerGroup(it) }

        assertEquals(expected, actual)
    }
}