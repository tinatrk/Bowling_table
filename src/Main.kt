fun main() {
    val START = "старт"
    val game = Bowling()
    println("Боулинг")

    while(true){
        println("Введите имя игрока или '$START', чтобы начать игру")
        val userInput = readln()
        if (userInput != "") {
            if (userInput == START) {
                if (game.getCountPlayers() < 2)
                    println("Для игры необходимо хотя бы два игрока")
                else {
                    game.start()
                    println("\nСпасибо за игру")
                    break
                }
            } else {
                game.addPlayer(userInput)
                println("Игрок '$userInput' добавлен")
            }
        }
        else println("Некорректный ввод")
    }
}
