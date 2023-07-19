package com.example.basiccalculator

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basiccalculator.databinding.ActivityMainBinding
import com.example.basiccalculator.ui.theme.BasicCalculatorTheme
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private var num1: String = "0"
    private var num2: String = "0"
    private var result = 0.0
    private var resultScreen: String = ""
    private var inputScreen: String = ""
    private var opr: String = ""
    private val numBuilder = StringBuilder()
    private val num1Builder = StringBuilder()
    private val oprArray = listOf("/", "x", "-", "+")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCalculatorTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {}
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        digitClick()
        pointClick()
        operatorClick()
        clearClick()
        equalClick()
    }

    private fun calculate() {
        if (oprArray.any { it in inputScreen }) {
            num2 = inputScreen.split("/", "x", "-", "+").last().trim()
        }
        if (opr == "/") result = num1.toDouble() / num2.toDouble()
        if (opr == "x") result = num1.toDouble() * num2.toDouble()
        if (opr == "-") result = num1.toDouble() - num2.toDouble()
        if (opr == "+") result = num1.toDouble() + num2.toDouble()
        result = String.format("%.8f",result).toDouble()
        resultScreen = result.toString()
    }

    private fun digitClick() {
        binding.btNum0.setOnClickListener {
            inputScreen = numBuilder.append("0").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum1.setOnClickListener {
            inputScreen = numBuilder.append("1").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum2.setOnClickListener {
            inputScreen = numBuilder.append("2").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum3.setOnClickListener {
            inputScreen = numBuilder.append("3").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum4.setOnClickListener {
            inputScreen = numBuilder.append("4").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum5.setOnClickListener {
            inputScreen = numBuilder.append("5").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum6.setOnClickListener {
            inputScreen = numBuilder.append("6").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum7.setOnClickListener {
            inputScreen = numBuilder.append("7").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum8.setOnClickListener {
            inputScreen = numBuilder.append("8").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
        binding.btNum9.setOnClickListener {
            inputScreen = numBuilder.append("9").toString()
            binding.inputTv.text = inputScreen
            calculate()
            resultScreenEvent()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun resultScreenEvent() {
        binding.resultTv.text = "= $inputScreen"
        binding.inputTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
        binding.resultTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
        binding.resultTv.typeface = Typeface.DEFAULT
        binding.resultTv.setTextIsSelectable(false)
        if (opr != "") {
            binding.resultTv.text = "= $resultScreen"
        }
    }

    private fun operatorClick() {
        binding.btOprDiv.setOnClickListener {
            opr = "/"
            operatorRule()
        }
        binding.btOprMult.setOnClickListener {
            opr = "x"
            operatorRule()
        }
        binding.btOprMin.setOnClickListener {
            opr = "-"
            operatorRule()
        }
        binding.btOprPlus.setOnClickListener {
            opr = "+"
            operatorRule()

        }
    }

    private fun operatorRule() {
        if (inputScreen == "") {
            inputScreen = numBuilder.append("0", opr).toString()
            binding.inputTv.text = inputScreen
        } else if (inputScreen.last() == '.' ||
            oprArray.any { it == inputScreen.last().toString() }
        ) {
            Toast.makeText(this, "lho", Toast.LENGTH_SHORT).show()
            inputScreen = numBuilder.deleteCharAt(numBuilder.length - 1).toString()
            binding.inputTv.text = inputScreen
            inputScreen = numBuilder.append(opr).toString()
            binding.inputTv.text = inputScreen
        } else {
            if (num2 == "0") num1 =
                inputScreen.split("/", "x", "-", "+").first().trim()
            if (num2 != "0") num1 = resultScreen
            inputScreen = numBuilder.append(opr).toString()
            binding.inputTv.text = inputScreen
        }

    }

    @SuppressLint("SetTextI18n")
    private fun pointClick() {
        binding.btNumPoint.setOnClickListener {
            if (inputScreen == "" ||
                oprArray.any { it == inputScreen.last().toString() }
            ) {
                inputScreen = numBuilder.append("0", ".").toString()
                binding.inputTv.text = inputScreen
                binding.resultTv.text = "= $result"
                Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()

            } else if (inputScreen.last() == '.') {
                Toast.makeText(this, "already in decimal", Toast.LENGTH_SHORT).show()
                inputScreen = numBuilder.deleteCharAt(numBuilder.length - 1).toString()
            } else {
                inputScreen = numBuilder.append(".").toString()
                binding.inputTv.text = inputScreen
                if (inputScreen.contains('.') &&
                    oprArray.any { it in inputScreen }
                ) binding.resultTv.text = "= $resultScreen"
                else binding.resultTv.text = "= $inputScreen"
            }
        }
    }

    private fun equalClick() {
        binding.btOprEqual.setOnClickListener {
            binding.inputTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            binding.resultTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60F)
            binding.resultTv.typeface = Typeface.DEFAULT_BOLD
            binding.resultTv.setTextIsSelectable(true)
            inputScreen = numBuilder.clear().toString()
            resultScreen = num1Builder.clear().toString()
            num1 = "0"
            num2 = "0"
            result= 0.0
            opr = ""
        }
    }

    private fun clearClick() {
        binding.btClear.setOnClickListener {
            inputScreen = numBuilder.clear().toString()
            resultScreen = num1Builder.clear().toString()
            num1 = "0"
            num2 = "0"
            result = 0.0
            opr = ""
            binding.inputTv.text = inputScreen
            binding.resultTv.text = ""
            binding.inputTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
            binding.resultTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
            binding.resultTv.typeface = Typeface.DEFAULT
            binding.resultTv.setTextIsSelectable(false)
        }
    }

}