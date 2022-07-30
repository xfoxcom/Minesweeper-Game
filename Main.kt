package minesweeper
import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field? ")
    val mines = readln().toInt()
    val listOfPosition = mutableListOf<Int>()

    while (listOfPosition.distinct().size != mines) {
        listOfPosition.add(Random.nextInt(0, 81))
    }
    val fieldEmpty = StringBuilder(".................................................................................")
    for (c in fieldEmpty.indices) {
        for (i in listOfPosition) {
            if (c == i) fieldEmpty[c] = 'X'
        }
    }
    for (e in 9..81 step 10) {
        fieldEmpty.insert(e, "\n")
    }
    print(fieldEmpty)
}

