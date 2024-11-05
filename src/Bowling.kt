class Bowling() {
    private val players: MutableList<Player> = mutableListOf()
    private var winner: String = ""

    fun addPlayer(name: String){
        players.add(Player(name))
    }

    fun getCountPlayers(): Int {
        return players.size
    }

    fun start(){
        for (round in 0..COUNT_ROUNDS-2){
            makeRound()
        }
        makeFinalRound()

        printResult()
        getWinner()
        println("\nПобедитель: $winner")
    }

    private fun makeRound(){
        players.forEach{it.makeMove()}
    }

    private fun makeFinalRound(){
        players.forEach{it.makeFinalMove()}
    }

    private fun getWinner(){
        players.sortBy { it.getTotal() }
        winner = players[players.size - 1].name
    }

    private fun printResult(){
        players.forEach{
            it.printName()
            it.printFrames()
            println(" ")
        }
    }

    private companion object{
        const val COUNT_ROUNDS = 10
    }
}