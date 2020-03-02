package com.cespaul.scalc

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorUnitTest {
    @Test
    fun checkCalculator() {
        val s = "(13+37)*(8-(3*2))"
        val n = ExpressionParser()
        val expression: List<String> = n.parse(s)
        val calculator = Calculator()
        assertEquals(calculator.calc(expression), 100.0, 0.00001)
    }
}
