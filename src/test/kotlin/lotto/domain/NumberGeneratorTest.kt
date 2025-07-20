package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class NumberGeneratorTest {
    @Test
    fun `should create numbers within range`() {
        val result = NumberGenerator.createNumbers(1, 10)

        assertThat(result).hasSize(10)
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    }

    @Test
    fun `should create numbers for single value range`() {
        val result = NumberGenerator.createNumbers(5, 5)

        assertThat(result).hasSize(1)
        assertThat(result).containsExactly(5)
    }

    @Test
    fun `does not throw if have SUFFICIENT_SIZE numbers`() {
        assertDoesNotThrow {
            val input = listOf(1, 2, 3, 4, 5, 6)
            NumberGenerator.sufficientSizeNumbers(input)
        }
    }
}
