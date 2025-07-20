package lotto.domain

import lotto.exceptions.ExceptionMessage

@JvmInline
value class LottoTicket(val lottoNumbers: Set<LottoNumber>) {
    init {
        require(lottoNumbers.size == SUFFICIENT_SIZE) { ExceptionMessage.NOT_SUFFICIENT_SIZE }
    }

    fun toRawSet(): Set<Int> {
        return lottoNumbers.map { it.value }.toSet()
    }

    fun doesNotContains(lottoNumber: LottoNumber): Boolean {
        return lottoNumber.value !in toRawSet()
    }

    fun getRank(winningCombination: WinningCombination): Rank {
        val countOfMatch = lottoNumbers.count { it in winningCombination.winningTicket.lottoNumbers }
        val matchBonus = lottoNumbers.any { it == winningCombination.bonusNumber }
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    companion object {
        const val SUFFICIENT_SIZE = 6
        const val PRICE_OF_TICKET = 1000
        const val CURRENCY = "KRW"

        fun from(requests: Set<Int>): LottoTicket {
            return LottoTicket(
                requests.map {
                    LottoNumber.from(it)
                }.toCollection(linkedSetOf()),
            )
        }
    }
}
