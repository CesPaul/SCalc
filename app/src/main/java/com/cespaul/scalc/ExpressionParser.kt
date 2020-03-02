package com.cespaul.scalc

import java.util.*
import kotlin.collections.ArrayList

class ExpressionParser {
    private val operators = "+-*/"
    private val delimiters = "() $operators"

    private fun isDelimiter(token: String): Boolean {
        if (token.length != 1) return false
        for (element in delimiters) {
            if (token[0] == element) return true
        }
        return false
    }

    private fun isOperator(token: String): Boolean {
        if (token == "u-") return true
        for (element in operators) {
            if (token[0] == element) return true
        }
        return false
    }

    private fun priority(token: String): Int {
        return when (token) {
            "(" -> 1
            "+" -> 2
            "-" -> 2
            "*" -> 3
            "/" -> 3
            else -> 4
        }
    }

    fun parse(infix: String): List<String> {
        if (infix.isEmpty()) {
            throw Exception("Выражение не должно быть пустым!")
        }
        val postfix: MutableList<String> = ArrayList()
        val stack: Deque<String> = ArrayDeque<String>()
        val tokenizer = StringTokenizer(infix, delimiters, true)
        var prev = ""
        var curr: String
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken()
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                throw Exception("Некорректное выражение.")
            }
            if (curr == " ") continue
            if (isDelimiter(curr)) {
                when (curr) {
                    "(" -> stack.push(curr)
                    ")" -> {
                        while (stack.peek() != "(") {
                            postfix.add(stack.pop())
                            if (stack.isEmpty()) {
                                throw Exception("Скобки не согласованы.")
                            }
                        }
                        stack.pop()
                    }
                    else -> {
                        // унарный минус
                        if (curr == "-" && (prev == "" || isDelimiter(prev) && prev != ")")) {
                            curr = "u-"
                        } else {
                            while (!stack.isEmpty() && priority(curr) <= priority(stack.peek())) {
                                postfix.add(stack.pop())
                            }
                        }
                        stack.push(curr)
                    }
                }
            } else {
                postfix.add(curr)
            }
            prev = curr
        }
        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop()) else {
                throw Exception("Скобки не согласованы.")
            }
        }
        return postfix
    }
}