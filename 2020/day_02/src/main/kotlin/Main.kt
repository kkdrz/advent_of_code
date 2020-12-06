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
    return Password(password, ExactlyOnePositionPolicy(min, max, char))
}


class Password(val text: String, val policy: Policy) {

    fun isValid(): Boolean = policy.isValid(text)
}

interface Policy {
    fun isValid(password: String): Boolean
}

class OneLetterPolicy(val min: Int, val max: Int, val char: Char) : Policy {

    override fun isValid(password: String): Boolean {
        return password.toCharArray().filter { it == char }.count() in min..max
    }
}

class ExactlyOnePositionPolicy(val first: Int, val second: Int, val char: Char) : Policy {

    override fun isValid(password: String): Boolean {
        return (password[first - 1] == char).xor(password[second - 1] == char)
    }

}