import wire.Point

interface DistanceCalculator {

    fun calculate(a: Point, b: Point): Int

}