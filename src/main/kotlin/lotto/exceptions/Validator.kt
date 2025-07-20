package lotto.exceptions

import lotto.domain.LottoNumber
import lotto.domain.LottoNumber.Companion.MAX_RANGE_NUMBER
import lotto.domain.LottoNumber.Companion.MIN_RANGE_NUMBER
import lotto.domain.LottoTicket

object Validator {
    fun number(num: Int) {
        require(num in MIN_RANGE_NUMBER..MAX_RANGE_NUMBER) { ExceptionMessage.INVALID_INPUT }
    }

    fun amount(amount: Int) {
        require(amount >= LottoTicket.PRICE_OF_TICKET) { ExceptionMessage.TOO_SMALL }
        require(amount % LottoTicket.PRICE_OF_TICKET == 0) { ExceptionMessage.NOT_DIVISIBLE }
    }

    fun purchase(
        amount: Int,
        size: Int,
    ) {
        val ableToPurchase = amount / LottoTicket.PRICE_OF_TICKET
        require(ableToPurchase > 0) { ExceptionMessage.TOO_SMALL }
        require(size > 0) { ExceptionMessage.TOO_SMALL }
        require(size <= ableToPurchase) { ExceptionMessage.TOO_BIG }
    }

    fun numbers(numbers: Set<Int>) {
        require(numbers.size == LottoTicket.SUFFICIENT_SIZE) { ExceptionMessage.NOT_SUFFICIENT_SIZE }
        numbers.forEach { LottoNumber.from(it) }
    }

    fun bonusNumber(
        bonusNumber: Int,
        winningNumbers: Set<Int>,
    ) {
        require(bonusNumber !in winningNumbers) { ExceptionMessage.DUPLICATE + ", $bonusNumber" }
    }
}
