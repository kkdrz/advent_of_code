import java.nio.file.Path
import java.util.*
import java.util.regex.Pattern

fun main() {
    val scanner = Scanner(Path.of("src/main/resources/input.txt"))
    scanner.useDelimiter(Pattern.compile("\r\n\r\n"))

    var sum = 0

    while (scanner.hasNext()) {
        val groupDeclaration = scanner.next()

        sum += groupDeclaration.split("\r\n")
                .map { it.toSet() }
                .reduce{ acc, next -> acc.intersect(next) }
                .count()
    }

    println(sum)
}
