package lotto.domain

import lotto.view.OutputView.Print.toText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTicketTest {
    @Test
    fun `throw when lotto ticket has insufficient numbers`() {
        assertThrows<IllegalArgumentException> {
            LottoTicket(hashSetOf())
        }
    }

    @Test
    fun `does not throw when lotto ticket has sufficient numbers`() {
        assertDoesNotThrow {
            val setOfLottoNumber =
                hashSetOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                )
            LottoTicket(setOfLottoNumber)
        }
    }

    @Test
    fun `throw when lotto ticket has sufficient numbers, but numbers not in range`() {
        assertThrows<IllegalArgumentException> {
            val setOfLottoNumber =
                hashSetOf(
                    LottoNumber.from(0),
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                )
            LottoTicket(setOfLottoNumber)
        }
    }

    @Test
    fun `should format LottoTicket numbers as string`() {
        val setOfLottoNumber =
            hashSetOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            )
        assertThat(
            LottoTicket(setOfLottoNumber).toText(),
        ).isEqualTo("1, 2, 3, 4, 5, 6")
    }

    @Test
    fun `should get Correct Rank`() {
        val winningCombination =
            WinningCombination(
                LottoTicket.from(setOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(7),
            )
        val rankFirstLottoTicket = LottoTicket.from(setOf(1, 2, 3, 4, 5, 6))
        assertThat(rankFirstLottoTicket.getRank(winningCombination)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `throw exception message, when lotto ticket has not enough or duplicate numbers`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                LottoTicket(
                    hashSetOf(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                    ),
                )
            }
        assertThat(exception.message).isEqualTo("lotto ticket has not sufficient size")
    }
}
