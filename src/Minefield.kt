import kotlin.random.Random

interface Minefield {

    fun show()

    fun scan()

    fun setMines(mines: Int)

    fun winCondition(): Boolean

    fun reveal(b: Int, a: Int)

    object Battleground : Minefield {

        private val battleground = mutableListOf(
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

        private val battlegroundFogged = mutableListOf(
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

        override fun show() {
            var i = 1
            println(" │123456789│")
            println("—|—————————|")
            for (strings in battlegroundFogged) {
                print("${i++}|")
                for (string in strings) {
                    print(string)
                }
                print("|")
                println()
            }
            println("—|—————————|")
        }

        override fun scan() {
            for (i in 0..8) {
                for (j in 0..8) {
                    var count = 0
                    if (battleground[i][j] != "X") {
                        try {
                            if (battleground[i - 1][j - 1] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i - 1][j] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i - 1][j + 1] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i][j + 1] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i][j - 1] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i + 1][j - 1] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i + 1][j] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        try {
                            if (battleground[i + 1][j + 1] == "X") count++
                        } catch (e: IndexOutOfBoundsException) {
                            e.message
                        }
                        if (count > 0) battleground[i][j] = "$count"
                    }
                }
            }
        }

        override fun setMines(mines: Int) {

            val random = mutableListOf<Int>()

            while (random.distinct().size != mines) {
                random.add(Random.nextInt(0, 89))
                random.removeAll(mutableListOf(9, 19, 29, 39, 49, 59, 69, 79))
            }

            for (i in random) {
                if (i in 0..8) battleground[0][i] = "X" else battleground[i / 10][i % 10] = "X"
            }

        }

        override fun winCondition(): Boolean {
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
            val listH = mutableListOf(
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
                    if (battleground[i][j] == "X") list[i][j] = "*"
                }
            }
            for (i in 0..8) {
                for (j in 0..8) {
                    if (battlegroundFogged[i][j] == "*") listH[i][j] = "*"
                }
            }
            return list == listH
        }

        override fun reveal(b: Int, a: Int) {

            if (b < 0 || b > 8 || a < 0 || a > 8) return

            if (battleground[b][a].matches("\\d".toRegex())) {
                battlegroundFogged[b][a] = battleground[b][a]
                return
            }
            if (battlegroundFogged[b][a] != "." || battleground[b][a] == "X") return

            battlegroundFogged[b][a] = "/"
            replaceStar()

            reveal(b - 1, a)
            reveal(b, a + 1)
            reveal(b, a - 1)
            reveal(b + 1, a)

            reveal(b - 1, a - 1)
            reveal(b + 1, a + 1)
            reveal(b - 1, a + 1)
            reveal(b + 1, a - 1)
        }

        fun get_battleground(): MutableList<MutableList<String>> {
            return battleground
        }

        fun get_battlegroundFogged(): MutableList<MutableList<String>> {
            return battlegroundFogged
        }

        fun replaceStar() {
            for (i in 0..8) {
                for (j in 0..8) {
                    try {
                        if (battlegroundFogged[i][j] == "*" && checkIf(
                                i,
                                j
                            ) && !battleground[i][j].matches("\\d".toRegex())
                        )
                            battlegroundFogged[i][j] = "/"
                    } catch (e: IndexOutOfBoundsException) {
                        e.message
                    }
                }
            }
        }

        fun checkIf(i: Int, j: Int): Boolean {
            try {
                if (battlegroundFogged[i - 1][j - 1] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i - 1][j] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i - 1][j + 1] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i][j + 1] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i][j - 1] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i + 1][j - 1] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i + 1][j] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            try {
                if (battlegroundFogged[i + 1][j + 1] == "/") return true
            } catch (e: IndexOutOfBoundsException) {
                e.message
            }
            return false
        }

    }
}