const val ONE_MINUTE: Int = 60
const val ONE_HOUR: Int = 60 * 60
const val ONE_DAY = 60 * 60 * 24

internal fun agoToText(time: Int): String {

    fun generateStringHour(inTime: Int): String {
        val hour = inTime / ONE_HOUR
        val typeTime = when (hour % 10) {
            1 -> "час"
            2, 3, 4 -> "часа"
            else -> "часов"
        }
        return "был(а) в сети $hour $typeTime назад"
    }

    fun generateStringMin(inTime: Int): String {
        val minute = inTime / ONE_MINUTE
        val typeTime = when (minute % 10) {
            1 -> "минуту"
            2, 3, 4 -> "минуты"
            else -> "минут"
        }
        return "был(а) в сети $minute $typeTime назад"
    }

    val stringTime: String = when {
        time in 0..ONE_MINUTE -> "был(а) в сети только что"
        time in ONE_MINUTE + 1..ONE_HOUR - 1 -> generateStringMin(time)
        time in ONE_HOUR..ONE_DAY -> generateStringHour(time)
        time in ONE_DAY..(2 * ONE_DAY) -> "был(а) в сети сегодня"
        time in (2 * ONE_DAY + 1)..(3 * ONE_DAY) -> "был(а) в сети вчера"
        time > 3 * ONE_DAY -> "был(а) в сети давно"
        else -> "был(а) в сети давно"
    }
    return stringTime
}

fun main() {
    val inputeSecond = 3599
    val text = agoToText(inputeSecond)
    println(text)
}