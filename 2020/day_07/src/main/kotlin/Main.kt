import java.io.File

fun main() {
    val bags = File("src/main/resources/input.txt")
            .readLines()
            .map { decodeBag(it) }

    val searchedBagColor = "shiny gold"

    val count = getParentBags(bags, searchedBagColor).count()
    println("Part 1: $count")

    println("Part 2: ${getNumberOfInternalBags(bags, getBagByColor(bags, searchedBagColor))}")

}

fun getBagByColor(bags: List<Bag>, color: String): Bag = bags.first { it.color == color }

fun getNumberOfInternalBags(bags: List<Bag>, bag: Bag): Int {
    return bag.content.map { it.value }.sum() + bag.content.entries.map {
        getNumberOfInternalBags(bags, getBagByColor(bags, it.key)) * it.value
    }.sum()
}

private fun getParentBags(bags: List<Bag>, searchedBagColor: String): List<Bag> {
    val parentBags = bags.filter { it.contains(searchedBagColor) }

    if (parentBags.isEmpty()) {
        return emptyList()
    }

    return parentBags + parentBags.flatMap { getParentBags(bags, it.color) }.distinct()
}

fun decodeBag(description: String): Bag {

    val bagColor = description.substring(0, description.indexOf(" bags contain"))

    val content = if (description.contains("no other bags"))
        emptyMap()
    else
        description.substring(description.indexOf("bags contain") + 13)
                .replace("(bags?|\\.)".toRegex(), "")
                .split(", ")
                .map { excerpt -> excerpt.substring(excerpt.indexOf(" ") + 1, excerpt.length - 1).trim() to excerpt.substring(0, excerpt.indexOf(" ")).trim().toInt() }
                .toMap()

    return Bag(bagColor, content)
}

data class Bag(val color: String, val content: Map<String, Int>) {

    fun contains(color: String): Boolean = content.contains(color)

}