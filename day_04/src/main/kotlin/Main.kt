fun main() {
    val range = 382345..843167

    val part1_result = range
        .filter { isSixDigitNumber(it) }
        .filter { containsTwoSameAdjacentDigits(it) }
        .filter { digitsDoesNotDecrease(it) }
        .count()

    val part2_result = range
        .filter { isSixDigitNumber(it) }
        .filter { digitsDoesNotDecrease(it) }
        .filter { containsTwoSameAdjacentDigitsWhichAreNotPartOfBiggerGroup(it) }
        .count()

    println(part1_result)
    println(part2_result)
}


