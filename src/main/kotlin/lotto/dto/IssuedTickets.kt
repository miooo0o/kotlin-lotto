package lotto.dto

import lotto.domain.LottoTicket

data class IssuedTickets(val tickets: List<LottoTicket>) {
    operator fun plus(ticket: LottoTicket): IssuedTickets {
        return IssuedTickets(
            tickets.toMutableList()
                .also { it.add(ticket) }.toList(),
        )
    }

    operator fun plus(other: IssuedTickets): IssuedTickets {
        return IssuedTickets(
            this.tickets + other.tickets,
        )
    }
}
