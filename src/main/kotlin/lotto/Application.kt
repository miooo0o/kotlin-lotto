package lotto

import lotto.controller.LottoWinningCreator
import lotto.controller.PurchasesController
import lotto.dto.RankedTickets
import lotto.view.OutputView
import kotlin.system.exitProcess

fun main() {
    try {
        val purchaseResult = PurchasesController.getPurchaseResult()
        OutputView.Print.purchaseResult(purchaseResult)

        val winningCombination = LottoWinningCreator.fromUserInput()
        val rankedTickets = RankedTickets(purchaseResult, winningCombination)
        OutputView.Print.winningStatistics(rankedTickets)
    } catch (exception: Exception) {
        OutputView.Prompt.error(exception)
        exitProcess(1)
    }
}
