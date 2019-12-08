import wire.Point
import wire.Wire
import wire.WireBuilder
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

class InstructionsInterpreter {

    fun getWiresFromFile(file: Path) = Files.lines(file).map { getWire(Point(0, 0), it) }.toList()

    private fun getWire(startingPoint: Point, instructions: String): Wire {

        val wireBuilder = WireBuilder(startingPoint)

        instructions
            .split(",")
            .forEach {
                val direction = it[0]
                val distance = it.substring(1).toInt()
                when (direction) {
                    'U' -> wireBuilder.up(distance)
                    'D' -> wireBuilder.down(distance)
                    'L' -> wireBuilder.left(distance)
                    'R' -> wireBuilder.right(distance)
                    else -> throw IllegalStateException("Unknown direction: $direction")
                }
            }

        return wireBuilder.create()
    }

}