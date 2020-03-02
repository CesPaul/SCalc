package com.cespaul.scalc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculate.setOnClickListener {
            val s: String = expression.text.toString()
            val n = ExpressionParser()
            lateinit var expression: List<String>
            try {
                expression = n.parse(s)
                val calculator = Calculator()
                result.text = calculator.calc(expression).toString()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
