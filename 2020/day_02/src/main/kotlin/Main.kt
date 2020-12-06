import java.io.File

fun main() {

    val validCount = File("src/main/resources/input.txt")
            .readLines()
            .map(::decodePassword)
            .filter(Password::isValid)
            .count()

    println(validCount)
}

private fun decodePassword(encoded: String): Password {
    val min = encoded.substring(0, encoded.indexOf('-')).toInt()
    val max = encoded.substring(encoded.indexOf('-') + 1, encoded.indexOf(' ')).toInt()
    val char = encoded.substring(encoded.indexOf(' ') + 1, encoded.indexOf(':')).first()
    val password = encoded.substring(encoded.indexOf(": ") + 2)
    return Password(password, OneLetterPolicy(min, max, char))
}

class Password(val text: String, val policy: OneLetterPolicy) {

    fun isValid(): Boolean = policy.isValid(text)
}

class OneLetterPolicy(val min: Int, val max: Int, val char: Char) {

    fun isValid(password: String): Boolean {
        return password.toCharArray().filter(predicate = { it == char }).count() in min..max
    }
}