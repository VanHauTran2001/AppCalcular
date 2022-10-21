package com.example.appcalcular

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appcalcular.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        actionNumber()
        actionOperators()
        actionBack()
        actionAnswer()
    }

    private fun actionAnswer() {
        binding!!.actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding!!.placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding!!.answer.text = longResult.toString()
                } else
                    binding!!.answer.text = result.toString()

            } catch (e: Exception) {
                Log.d("EXCEPTION", "Message: ${e.message}")
            }

        }
    }

    private fun actionBack() {
        binding!!.delete.setOnClickListener {
            val expression = binding!!.placeholder.text.toString()
            if (expression.isNotEmpty()) {
                binding!!.placeholder.text = expression.substring(0, expression.length - 1)
            }
        }
    }

    private fun actionOperators() {
        binding!!.clear.setOnClickListener { appendVal("", true) }
        binding!!.actionDivide.setOnClickListener { appendVal(" / ", false) }
        binding!!.actionMultiply.setOnClickListener { appendVal(" * ", false) }
        binding!!.actionMinus.setOnClickListener { appendVal(" - ", false) }
        binding!!.actionAdd.setOnClickListener { appendVal(" + ", false) }
    }

    private fun actionNumber() {
        binding!!.num0.setOnClickListener { appendVal("0", false) }
        binding!!.num1.setOnClickListener { appendVal("1", false) }
        binding!!.num2.setOnClickListener { appendVal("2", false) }
        binding!!.num3.setOnClickListener { appendVal("3", false) }
        binding!!.num4.setOnClickListener { appendVal("4", false) }
        binding!!.num5.setOnClickListener { appendVal("5", false) }
        binding!!.num6.setOnClickListener { appendVal("6", false) }
        binding!!.num7.setOnClickListener { appendVal("7", false) }
        binding!!.num8.setOnClickListener { appendVal("8", false) }
        binding!!.num9.setOnClickListener { appendVal("9", false) }
        binding!!.numDot.setOnClickListener { appendVal(".", false) }
    }
    fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            binding!!.placeholder.text = ""
            binding!!.answer.text = ""
        } else {
            binding!!.placeholder.append(string)
        }
    }

}