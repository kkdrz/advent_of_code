import java.nio.file.Path
import java.util.*
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val scanner = Scanner(Path.of("src/main/resources/input.txt"))
    scanner.useDelimiter(Pattern.compile("\r\n\r\n"))

    var validPassports = 0
    while (scanner.hasNext()) {
        val encodedPassport = scanner.next().replace("\r\n", " ")

        val isValid = Passport(encodedPassport).isValid(PassportValidatorImpl())

        if (isValid) {
            validPassports++
        }
    }

    println(validPassports)
}

data class Passport(val encodedPassport: String) {

    val keys = mutableMapOf<String, String>()

    init {

        encodedPassport.split(Pattern.compile("[\n ]")).forEach {
            val kvSplit = it.split(":")
            keys[kvSplit[0]] = kvSplit[1]
        }

    }

    fun isValid(validator: PassportValidator): Boolean {
        return validator.isValid(this);
    }

}

interface PassportValidator {

    fun isValid(passport: Passport): Boolean

}

class PassportValidatorImpl : PassportValidator {

    override fun isValid(passport: Passport): Boolean {

        val byr = passport.keys["byr"]
        if (byr == null || !digitsBetween(byr, 4, 1920, 2002)) {
            return false
        }

        val iyr = passport.keys["iyr"]
        if (iyr == null || !digitsBetween(iyr, 4, 2010, 2020)) {
            return false
        }

        val eyr = passport.keys["eyr"]
        if (eyr == null || !digitsBetween(eyr, 4, 2020, 2030)) {
            return false
        }

        val hgt = passport.keys["hgt"] ?: return false


        if (hgt.endsWith("cm")) {
            if (!digitsBetween(hgt.substring(0, hgt.indexOf("cm")), 3, 150, 193)) {
                return false
            }
        } else if (hgt.endsWith("in")) {
            if(!digitsBetween(hgt.substring(0, hgt.indexOf("in")), 2, 59, 76)) {
                return false
            }
        } else {
            return false
        }

        val hcl = passport.keys["hcl"]

        if (hcl == null || !hcl.matches("#[0-9a-f]{6}".toRegex())) {
            return false
        }

        val ecl = passport.keys["ecl"]

        if (ecl == null || !arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").any { ecl == it }) {
            return false
        }

        val pid = passport.keys["pid"]

        if (pid == null || !pid.matches("[0-9]{9}".toRegex())) {
            return false
        }

        return true
    }

    private fun digitsBetween(value: String?, noDigits: Int, min: Int, max: Int): Boolean {
        return value != null
                && "\\d{$noDigits}".toRegex().matches(value)
                && value.toInt() in min..max
    }

}
