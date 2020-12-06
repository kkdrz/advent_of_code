import java.util.regex.Pattern

fun isSixDigitNumber(number: Int) = number.toString().length == 6

fun containsTwoSameAdjacentDigits(number: Int) = number.toString().zipWithNext().any { it.first == it.second }

fun digitsDoesNotDecrease(number: Int) =
    number.toString().zipWithNext { d1, d2 ->
        Pair(
            d1.toInt(),
            d2.toInt()
        )
    }.all { it.first <= it.second }

fun containsTwoSameAdjacentDigitsWhichAreNotPartOfBiggerGroup(number: Int): Boolean {

    val matcher = Pattern.compile("(\\d)\\1+").matcher(number.toString())

    val matches = mutableListOf<String>()

    while (matcher.find()) {
        matches.add(matcher.group())
    }

    val digitsRepeatedTwoTimes = matches.filter { it.length == 2 }.map { it[0] }.toList()
    val digitsRepeatedMoreThanTwoTimes = matches.filter { it.length > 2 }.map { it[0] }.toList()

    return digitsRepeatedTwoTimes.any { digit -> !digitsRepeatedMoreThanTwoTimes.contains(digit) }
}