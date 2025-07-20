package lotto.dto

import lotto.domain.LottoTicket

data class UserPurchase(
    val amount: Int,
    val manualTicketsCount: Int,
) {
    val maxPurchasable: Int = getMaxPurchasable(amount)
    val randomTicketsCount: Int = getRandomTicketsCount(maxPurchasable, manualTicketsCount)

    private fun getMaxPurchasable(amount: Int) = amount / LottoTicket.PRICE_OF_TICKET

    private fun getRandomTicketsCount(
        total: Int,
        manualNumber: Int,
    ): Int {
        require(total >= manualNumber) { "total must be >= $manualNumber" }
        return total - manualNumber
    }
}
