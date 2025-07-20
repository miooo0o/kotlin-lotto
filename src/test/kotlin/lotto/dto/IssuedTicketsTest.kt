package lotto.dto

import lotto.controller.TicketIssuer
import lotto.domain.LottoTicket
import lotto.view.OutputView.Print.toText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class IssuedTicketsTest {
    @Test
    fun `should print issued tickets in expected format`() {
        val issuedTickets =
            TicketIssuer.with(
                listOf(
                    setOf(1, 2, 3, 4, 5, 6),
                ),
            )
        assertThat(issuedTickets.tickets.map { it.toText() }).isEqualTo(listOf("1, 2, 3, 4, 5, 6"))
    }

    @Test
    fun `should print issued tickets in correct format2`() {
        val issuedTickets =
            TicketIssuer.with(
                listOf(
                    setOf(1, 2, 3, 4, 5, 6),
                    setOf(7, 8, 9, 10, 11, 12),
                ),
            )
        assertThat(issuedTickets.tickets.map { it.toText() })
            .isEqualTo(
                listOf("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"),
            )
    }

    @Test
    fun `should combine issued tickets correctly`() {
        val issuedTickets1 =
            TicketIssuer.with(
                listOf(
                    setOf(1, 2, 3, 4, 5, 6),
                    setOf(7, 8, 9, 10, 11, 12),
                ),
            )

        val issuedTickets2 =
            TicketIssuer.with(
                listOf(
                    setOf(1, 2, 3, 4, 5, 6),
                    setOf(7, 8, 9, 10, 11, 12),
                ),
            )
        val totalIssuedTickets = issuedTickets1 + issuedTickets2
        assertThat(totalIssuedTickets.tickets.map { it.toText() })
            .isEqualTo(
                listOf(
                    "1, 2, 3, 4, 5, 6",
                    "7, 8, 9, 10, 11, 12",
                    "1, 2, 3, 4, 5, 6",
                    "7, 8, 9, 10, 11, 12",
                ),
            )
    }

    @Test
    fun `should combine issued tickets and a lotto ticket correctly`() {
        val issuedTickets =
            TicketIssuer.with(
                listOf(
                    setOf(1, 2, 3, 4, 5, 6),
                    setOf(7, 8, 9, 10, 11, 12),
                ),
            )
        val aLottoTicket = LottoTicket.from(setOf(13, 14, 15, 16, 17, 18))
        val totalIssuedTickets = issuedTickets + aLottoTicket
        assertTrue(totalIssuedTickets.tickets.last() == aLottoTicket)

        assertThat(totalIssuedTickets.tickets.map { it.toText() })
            .isEqualTo(
                listOf(
                    "1, 2, 3, 4, 5, 6",
                    "7, 8, 9, 10, 11, 12",
                    "13, 14, 15, 16, 17, 18",
                ),
            )
    }
}
