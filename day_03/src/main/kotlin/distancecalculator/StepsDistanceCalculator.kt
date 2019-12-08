package distancecalculator

import wire.Point
import wire.Wire
import java.lang.IllegalStateException

class StepsDistanceCalculator(val wires: List<Wire>) : DistanceCalculator {

    override fun calculate(point: Point): Int =
        wires.map { getDistanceInWire(it, point) }.sum()


    private fun getDistanceInWire(wire: Wire, point: Point): Int {
        val paths = wire.paths
        val it = paths.iterator()
        var steps = 0;
        while (it.hasNext()) {
            val currentPath = it.next()

            if (currentPath.includes(point)) {
                steps+= currentPath.getStepsForPoint(point)
                return steps;
            } else {
                steps += currentPath.steps
            }
        }
        throw IllegalStateException("Wire doesn't contain point $point")
    }

}