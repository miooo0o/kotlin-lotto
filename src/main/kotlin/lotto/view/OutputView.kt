package lotto.view

import lotto.domain.LottoTicket
import lotto.domain.Rank
import lotto.dto.PurchaseResult
import lotto.dto.RankedTickets

object OutputView {
    object Print {
        fun purchaseResult(purchaseResult: PurchaseResult) {
            val pluralized = pluralizeTicket(purchaseResult.userPurchase.maxPurchasable)
            println(
                "Purchased ${purchaseResult.userPurchase.manualTicketsCount} manual " +
                    "and ${purchaseResult.userPurchase.randomTicketsCount} automatic $pluralized.",
            )
            purchaseResult.totalTickets.tickets.forEach { println("[${it.toText()}]") }
        }

        internal fun LottoTicket.toText(): String = toRawSet().sortedBy { it }.joinToString(", ") { it.toString() }

        fun winningStatistics(rankedTickets: RankedTickets) {
            Prompt.winningStatisticsTitle()
            Rank.entries.filter { it != Rank.MISS }.reversed().forEach {
                eachRank(it, rankedTickets.ranked)
            }
            totalRate(rankedTickets)
        }

        private fun eachRank(
            entry: Rank,
            results: List<Rank>,
        ) {
            val matchCount = results.count { it == entry }
            val hasBonus = if (entry.requiresBonus) " + Bonus Ball" else ""
            val winningMoney = "%,d".format(entry.winningMoney)
            val pluralizedTicket = pluralizeTicket(matchCount)
            println(
                "${entry.countOfMatch} Matches" +
                    "$hasBonus ($winningMoney ${LottoTicket.CURRENCY}) - $matchCount $pluralizedTicket",
            )
        }

        private fun totalRate(rankedTickets: RankedTickets) {
            Prompt.totalReturnRate()
            println("%.2f".format(rankedTickets.totalRate))
        }

        private fun pluralizeTicket(size: Int): String {
            return if (size == 1 || size == 0) "ticket" else "tickets"
        }
    }

    object Prompt {
        fun amount() = println(PURCHASE_AMOUNT_PROMPT)

        fun numberOfManual() = println(NUMBER_OF_MANUAL_PROMPT)

        fun manualNumbers() = println(MANUAL_TICKET_PROMPT)

        fun winningNumbers() = println(WINNING_NUMBERS_PROMPT)

        fun bonusNumber() = println(BONUS_NUMBER_PROMPT)

        fun winningStatisticsTitle() = println(TITLE_OF_RESULT_PROMPT)

        fun totalReturnRate() = print(TOTAL_RETURN_PROMPT)

        fun error(exception: Exception) {
            val msg = exception.message ?: "Unexpected error occurred."
            println("ERROR: $msg")
        }

        private const val PURCHASE_AMOUNT_PROMPT = "Please enter the purchase amount."
        private const val NUMBER_OF_MANUAL_PROMPT = "Enter the number of manual tickets to purchase."
        private const val MANUAL_TICKET_PROMPT = "Enter the numbers for manual tickets."
        private const val WINNING_NUMBERS_PROMPT = "Please enter last week’s winning numbers."
        private const val BONUS_NUMBER_PROMPT = "Please enter the bonus number."
        private const val TITLE_OF_RESULT_PROMPT = "Winning Statistics\n------------------"
        private const val TOTAL_RETURN_PROMPT = "Total return rate is "
    }
}
