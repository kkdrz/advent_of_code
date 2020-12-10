import java.io.File

fun main() {
    val bags = File("src/main/resources/input.txt")
            .readLines()
            .map { decodeBag(it) }

    val searchedBagColor = "shiny gold"

    val count = getParentBags(bags, searchedBagColor).count()

    println(count)
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