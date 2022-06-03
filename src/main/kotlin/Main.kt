const val MIR_COMISSION = 0.75
const val MASTERCARD_COMISSION = 0.6
const val MASTERCARD_COMISSION_ADD = 2_000.0
const val MASTERCARD_PROMOTION_MIN = 30_000.0
const val MASTERCARD_PROMOTION_MAX = 7_500_000.0

enum class CardType {
    VISA,
    MIR,
    MASTERCARD,
    MAESTRO,
    VK_PAY
}

internal fun calculateComission(
    transfer: Int,
    cardType: CardType = CardType.VK_PAY,
    transferAmount: Int = 0
): Int {

    val trans = transfer.toDouble()

    val comission: Double = when (cardType) {
        CardType.VK_PAY -> 0.0
        CardType.MIR, CardType.VISA -> {
            val mirComission = trans * (MIR_COMISSION / 100)
            if (mirComission <= 3500) 3500.0 else mirComission
        }

        CardType.MASTERCARD, CardType.MAESTRO -> {
            val mastercardComission = (trans * MASTERCARD_COMISSION) + MASTERCARD_COMISSION_ADD
            if (trans + transferAmount < MASTERCARD_PROMOTION_MIN
                && trans + transferAmount > MASTERCARD_PROMOTION_MAX
            ) mastercardComission else 0.0
        }
    }
    return comission.toInt()
}

fun main() {
    val transfer = 500_000
    val cardType = CardType.MIR
    val transferAmount = 100_000
    val comission = calculateComission(transfer = transfer, cardType = cardType, transferAmount = transferAmount)

    println("Сумма перевода ${transfer / 100} руб.")
    println("Комиссия ${comission / 100} руб. ${comission % 100} коп.")
}