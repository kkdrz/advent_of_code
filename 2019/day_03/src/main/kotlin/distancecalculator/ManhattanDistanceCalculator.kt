package distancecalculator

import Config.Companion.DEFAULT_STARTING_POINT
import wire.Point

class ManhattanDistanceCalculator: DistanceCalculator {

    override fun calculate(point: Point) = Math.abs(DEFAULT_STARTING_POINT.x - point.x) + Math.abs(DEFAULT_STARTING_POINT.y - point.y)

}