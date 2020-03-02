package com.cespaul.scalc

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class ParserUnitTest {
    @Test
    fun checkParser() {
        val s = "(13+37)*(8-(3*2))"
        val n = ExpressionParser()
        val expression: List<String> = n.parse(s)
        assertArrayEquals(
            expression.toTypedArray(),
            arrayOf("13", "37", "+", "8", "3", "2", "*", "-", "*")
        )
    }
}
