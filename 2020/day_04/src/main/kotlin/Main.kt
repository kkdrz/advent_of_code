import java.nio.file.Path
import java.util.*
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val scanner = Scanner(Path.of("src/main/resources/input.txt"))
    scanner.useDelimiter(Pattern.compile("\r\n\r\n"))

    var validPassports = 0
    while (scanner.hasNext()) {
        val currentPassport = scanner.next()

        val isValid = arrayOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt").all { currentPassport.contains("$it:") }

        if (isValid) {
            validPassports++
        }
    }

    println(validPassports)
}