import wire.Point

class ManhattanDistanceCalculator: DistanceCalculator{

    override fun calculate(a: Point, b: Point) = Math.abs(a.x - b.x) + Math.abs(a.y - b.y)

}