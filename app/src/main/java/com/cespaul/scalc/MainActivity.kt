package com.cespaul.scalc

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Фильтрация ввода и применение фильтра.
        val filter: InputFilter = object : InputFilter {
            private val REGEX = "^[0-9.+/*()-]+$"
            override fun filter(
                source: CharSequence,
                start: Int,
                end: Int,
                dest: Spanned,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                return if (source.isEmpty() || source.toString().matches(REGEX.toRegex())) null else ""
            }
        }
        expression.filters = arrayOf(filter)

        calculate.setOnClickListener {
            val s: String = expression.text.toString()
            val n = ExpressionParser()
            lateinit var expression: List<String>
            try {
                expression = n.parse(s)
                val calculator = Calculator()
                result.text = calculator.calc(expression).toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Некорректное выражение", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
