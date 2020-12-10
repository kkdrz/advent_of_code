import java.nio.file.Path
import java.util.*
import java.util.regex.Pattern

fun main() {
    val scanner = Scanner(Path.of("src/main/resources/input.txt"))
    scanner.useDelimiter(Pattern.compile("\r\n\r\n"))

    var sum = 0

    while (scanner.hasNext()) {
        val declaration = scanner.next().replace("\r\n", "")
        sum += declaration.toCharArray().distinct().count()
    }

    println(sum)
}
