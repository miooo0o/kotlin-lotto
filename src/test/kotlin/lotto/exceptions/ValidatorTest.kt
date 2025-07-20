package lotto.exceptions

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {
    @ValueSource(ints = [1, 2, 3, 43, 44, 45])
    @ParameterizedTest
    fun `valid when numbers are within range`(number: Int) {
        assertDoesNotThrow {
            Validator.number(number)
        }
    }

    @ValueSource(ints = [-1, 0, 46, 47, 100])
    @ParameterizedTest
    fun `invalid when numbers are out of range or duplicated`(number: Int) {
        assertThrows<IllegalArgumentException> {
            Validator.number(number)
        }
    }

    @ValueSource(ints = [0, -1, 1001, 1111, 1201])
    @ParameterizedTest
    fun `invalid when amount is less than ticket price or not divisible`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            Validator.amount(amount)
        }
    }

    @ValueSource(ints = [1000, 2000, 10000, 15000])
    @ParameterizedTest
    fun `valid when purchase amount is sufficient and divisible`(amount: Int) {
        assertDoesNotThrow {
            Validator.amount(amount)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1000, 1",
        "5000, 5",
        "2000, 2",
    )
    fun `valid purchase test`(
        amount: Int,
        size: Int,
    ) {
        assertThrows<IllegalArgumentException> {
            Validator.purchase(amount, size)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "500, 1",
        "1000, -1",
        "1000, 0",
        "1000, 2",
        "0, 1",
        "2000, 3",
    )
    fun `invalid purchase test`(
        amount: Int,
        size: Int,
    ) {
        assertThrows<IllegalArgumentException> {
            Validator.purchase(amount, size)
        }
    }
}
