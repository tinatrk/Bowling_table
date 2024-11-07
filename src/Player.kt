import kotlin.random.Random

class Player(val name: String) {
    private val frames: MutableList<Frame> = mutableListOf()

    fun makeMove(){

        val firstThrow: Int = getThrow(MAX_COUNT_PINS)
        val secondThrow: Int = getThrow(MAX_COUNT_PINS - firstThrow)

        val frame = Frame(firstThrow, secondThrow)
        if (firstThrow == MAX_COUNT_PINS) {
            frame.isStrike = true
        } else if (firstThrow + secondThrow == MAX_COUNT_PINS){
            frame.isSpare = true
        }

        addPointsForPrevStrikeOrSpare(frame)

        frame.total += getTotal()

        frames.add(frame)
    }

    fun makeFinalMove(){

        makeMove()
        val lastFrame = frames[frames.size-1]
        lastFrame.isFinal = true

        if (lastFrame.isStrike){
            lastFrame.bonusFirstThrow = bonusThrow().toString()
            lastFrame.bonusSecondThrow = bonusThrow().toString()
            frames.removeAt(frames.size - 1)
            frames.removeAt(frames.size - 1)
        } else if (lastFrame.isSpare ){
            lastFrame.bonusFirstThrow = bonusThrow().toString()
            frames.removeAt(frames.size - 1)
        }

    }

    private fun bonusThrow(): Int{
        val bonusThrow: Int = getThrow(MAX_COUNT_PINS)
        val frame = Frame(bonusThrow, 0)
        frame.isStrike = true

        addPointsForPrevStrikeOrSpare(frame)

        frame.total += getTotal()

        frames.add(frame)

        return bonusThrow
    }

    private fun getThrow(maxValue: Int): Int{
        return Random.nextInt(MIN_COUNT_PINS, maxValue + 1)
    }

    fun getTotal(): Int {
        return if (frames.size !=  0) frames[frames.size-1].total else 0
    }

    private fun addPointsForPrevStrikeOrSpare(curFrame: Frame){

        if (frames.size > 0){
            val prevFrame = frames[frames.size - 1]
            if (prevFrame.isStrike){
                if (curFrame.isStrike)
                    prevFrame.total += curFrame.firstThrow
                else prevFrame.total += curFrame.firstThrow + curFrame.secondThrow

                addPointsForPrevPrevStrike(curFrame, prevFrame)

            } else if (prevFrame.isSpare)
                prevFrame.total += curFrame.firstThrow
        }
    }

    private fun addPointsForPrevPrevStrike(curFrame: Frame, prevFrame: Frame){
        if (frames.size > 1){
            val prevPrevFrame = frames[frames.size - 2]
            if (prevPrevFrame.isStrike) {
                prevPrevFrame.total += curFrame.firstThrow
                prevFrame.total += curFrame.firstThrow
            }
        }
    }

    fun printName(){
        println("$name    ")
    }

    fun printFrames(){
        frames.forEach {it.printFrame()}
    }

    private companion object {
        const val MAX_COUNT_PINS = 10
        const val MIN_COUNT_PINS = 0
    }
}


