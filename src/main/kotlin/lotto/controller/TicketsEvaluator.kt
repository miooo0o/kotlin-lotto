package lotto.controller

import lotto.domain.Rank
import lotto.domain.WinningCombination
import lotto.dto.IssuedTickets

object TicketsEvaluator {
    fun runWith(
        issuedTickets: IssuedTickets,
        winningCombination: WinningCombination,
    ): List<Rank> {
        return issuedTickets.tickets.map {
            it.getRank(winningCombination)
        }
    }
}
