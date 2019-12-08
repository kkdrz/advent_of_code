package wire

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StraightPathTest {

    @Test
    fun should_return_interception_point() {

        val path1 = StraightPath(Point(0, 0), Point(1, 0))
        val path2 = StraightPath(Point(1, -1), Point(1, 2))

        assertEquals(Point(1, 0), path1.getInterceptionPoint(path2))
    }


    @Test
    fun should_not_allow_creation_of_diagonal_path() {
        assertThrows<IllegalArgumentException> {
            StraightPath(Point(1, 1), Point(2, 2))
        }
    }
}