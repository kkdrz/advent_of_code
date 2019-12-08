package distancecalculator

import wire.Point

interface DistanceCalculator {

    fun calculate( point: Point): Int

}