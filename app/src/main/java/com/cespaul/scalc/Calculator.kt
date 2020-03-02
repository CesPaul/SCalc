package com.cespaul.scalc

import java.util.*

class Calculator {
    fun calc(postfix: List<String>): Double {
        val stack: Deque<Double> = ArrayDeque<Double>()
        for (x in postfix) {
            when (x) {
                "+" -> stack.push(stack.pop() + stack.pop())
                "-" -> {
                    val b: Double = stack.pop()
                    val a: Double = stack.pop()
                    stack.push(a - b)
                }
                "*" -> stack.push(stack.pop() * stack.pop())
                "/" -> {
                    val b: Double = stack.pop()
                    val a: Double = stack.pop()
                    stack.push(a / b)
                }
                "u-" -> stack.push(-stack.pop())
                else -> stack.push(x.toDouble())
            }
        }
        return stack.pop()
    }
}