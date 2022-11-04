interface Game {

    fun start()

    fun play()

    class Start : Game {

        override fun start() {

            print("How many mines do you want on the field? ")

            val mines = readln().toInt()

            Minefield.Battleground.setMines(mines)

            Minefield.Battleground.scan()

            Minefield.Battleground.show()
        }

        override fun play() {

            while (!Minefield.Battleground.winCondition()) {

                print("Set/unset mine marks or claim a cell as free: ")

                val (a, b, c) = readln().split("\\s+".toRegex())

                when (c) {

                    "free" -> if (Minefield.Battleground.get_battleground()[b.toInt() - 1][a.toInt() - 1].matches("[0-9]+".toRegex())) {
                        Minefield.Battleground.get_battlegroundFogged()[b.toInt() - 1][a.toInt() - 1] =
                            Minefield.Battleground.get_battleground()[b.toInt() - 1][a.toInt() - 1]
                        Minefield.Battleground.show()
                    } else if (Minefield.Battleground.get_battleground()[b.toInt() - 1][a.toInt() - 1].matches("\\.".toRegex())) {
                        Minefield.Battleground.reveal(b.toInt() - 1, a.toInt() - 1)
                        Minefield.Battleground.reveal(b.toInt() - 1, a.toInt() - 1)
                        Minefield.Battleground.show()
                    } else if (Minefield.Battleground.get_battleground()[b.toInt() - 1][a.toInt() - 1].matches("X".toRegex())) {
                        for (i in 0..8) {
                            for (j in 0..8) {
                                if (Minefield.Battleground.get_battleground()[i][j].matches("X".toRegex()))
                                    Minefield.Battleground.get_battlegroundFogged()[i][j] =
                                        Minefield.Battleground.get_battleground()[i][j]
                            }
                        }
                        Minefield.Battleground.show()
                        print("You stepped on a mine and failed!")
                        return
                    }
                    "mine" -> if (Minefield.Battleground.get_battlegroundFogged()[b.toInt() - 1][a.toInt() - 1].matches(
                            "\\*".toRegex()
                        )
                    ) {
                        Minefield.Battleground.get_battlegroundFogged()[b.toInt() - 1][a.toInt() - 1] = "."
                        Minefield.Battleground.show()
                        if (Minefield.Battleground.winCondition()) {
                            println("Congratulations! You found all the mines!")
                        }
                    } else if (Minefield.Battleground.get_battlegroundFogged()[b.toInt() - 1][a.toInt() - 1].matches("\\.".toRegex())) {
                        Minefield.Battleground.get_battlegroundFogged()[b.toInt() - 1][a.toInt() - 1] = "*"
                        Minefield.Battleground.show()
                        if (Minefield.Battleground.winCondition()) {
                            println("Congratulations! You found all the mines!")
                        }
                    }
                }
            }
        }

    }

}