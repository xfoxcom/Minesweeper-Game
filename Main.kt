package minesweeper

import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field? ")
    val mines = readln().toInt()
    val listOfPosition = mutableListOf<String>()
    val list = mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    )
    val hided = mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    )

    while (listOfPosition.distinct().size != mines) {
        listOfPosition.add("" + Random.nextInt(0, 8) + Random.nextInt(0, 8))
    }

    for (i in listOfPosition) {
        if (i.toInt() in 0..8) list[0][i.toInt()] = "X" else list[i.toInt()/10][i.toInt()%10] = "X"
    }

    scan(list)

    for (i in 0..8) {
        for (j in 0..8) {
            if (list[i][j].matches("[0-9]+".toRegex())) hided[i][j] = list[i][j]
        }
    }

    showField(hided)

    while (!winCondition(list, hided)) {
        print("Set/delete mines marks (x and y coordinates): ")
        val (a, b) = readln().split("\\s+".toRegex())
        if (hided[b.toInt()-1][a.toInt()-1].matches("[0-9]+".toRegex())) println("There is a number here!")
        if (hided[b.toInt()-1][a.toInt()-1].matches("\\*".toRegex())) {
            hided[b.toInt()-1][a.toInt()-1] = "."
            showField(hided)
        } else
        if (hided[b.toInt()-1][a.toInt()-1].matches("\\.".toRegex())) {
            hided[b.toInt()-1][a.toInt()-1] = "*"
            showField(hided)
        }

    }
    println("Congratulations! You found all the mines!")
}

fun scan (line: MutableList<MutableList<String>>) {
   for (i in 0..8) {
       for (j in 0..8) {
           var count = 0
           if (line[i][j] != "X") {
               try { if (line[i-1][j-1] == "X") count++  } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i-1][j] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i-1][j+1] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i][j+1] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i][j-1] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i+1][j-1] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i+1][j] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               try { if (line[i+1][j+1] == "X") count++ } catch (e: IndexOutOfBoundsException) { e.message }
               if (count > 0) line[i][j] = "$count"
           }
       }
   }
}
fun showField (list: MutableList<MutableList<String>>) {
    var i = 1
    println(" │123456789│")
    println("—|—————————|")
    for (strings in list) {
        print("${i++}|")
        for (string in strings) {
            print(string)
        }
        print("|")
        println()
    }
    println("—|—————————|")
}
fun winCondition (openField: MutableList<MutableList<String>>, hiddenField: MutableList<MutableList<String>>): Boolean {
    val list = mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    )
    for (i in 0..8) {
        for (j in 0..8) {
            if (openField[i][j] == "X") list[i][j] = "*" else list[i][j] = openField[i][j]
        }
    }
    return list == hiddenField
}

