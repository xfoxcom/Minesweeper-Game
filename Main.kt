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

    while (listOfPosition.distinct().size != mines) {
        listOfPosition.add("" + Random.nextInt(0, 8) + Random.nextInt(0, 8))
    }

    for (i in listOfPosition) {
        if (i.toInt() in 0..8) list[0][i.toInt()] = "X" else list[i.toInt()/10][i.toInt()%10] = "X"
    }

    scan(list)

    for (strings in list) {
        for (string in strings) {
            print(string)
        }
        println()
    }
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

