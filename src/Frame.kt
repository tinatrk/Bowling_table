class Frame(val firstThrow: Int, val secondThrow: Int) {
    var total: Int = firstThrow + secondThrow
    var isStrike: Boolean = false
    var isSpare: Boolean = false
    var bonusFirstThrow: String = ""
    var bonusSecondThrow: String = ""
    var isFinal: Boolean = false

    fun printFrame(){
        val symbols = getThrowsForPrint(firstThrow, secondThrow)

        if (!isFinal) {
            print("| ${symbols.first} | ${symbols.second} | $total |   ")
        }
        else{
            print("| ${symbols.first} | ${symbols.second} | " +
                    "${getFinalSymbol(bonusFirstThrow)} | ${getFinalSymbol(bonusSecondThrow)} | $total")
        }
    }

    private fun getThrowsForPrint(first: Int, second: Int): Pair<String, String>{

        if (first == MAX_COUNT_PINS) return Pair("X", "")

        if (first + second == MAX_COUNT_PINS) return Pair(getSymbol(first), "/")

        return Pair(getSymbol(first), getSymbol(second))
    }

    private fun getSymbol(value: Int): String{
        return if (value == MIN_COUNT_PINS) "-" else value.toString()
    }

    private fun getFinalSymbol(value: String): String{
        if (value == MAX_COUNT_PINS.toString()) return "X"
        if (value == MIN_COUNT_PINS.toString()) return "-"
        return value
    }

    private companion object{
        const val MAX_COUNT_PINS = 10
        const val MIN_COUNT_PINS = 0
    }
}