package com.s15814.healthApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import java.text.DecimalFormat
import kotlin.math.pow

class BmiCalculator : Fragment() {
    private var heightInput: EditText? = null
    private var weightInput: EditText? = null
    private var result: TextView? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bmi_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heightInput = view.findViewById<View>(R.id.insertHeight) as EditText
        weightInput = view.findViewById<View>(R.id.insertHeight) as EditText
        result = view.findViewById<View>(R.id.bmiCalculatorResultView) as TextView
        view.findViewById<View>(R.id.bmiCalculatorCalculateButton).setOnClickListener { calculateBMI() }
        view.findViewById<View>(R.id.bmiCalculatorBackButton).setOnClickListener {
            NavHostFragment.findNavController(this@BmiCalculator)
                    .navigate(R.id.action_FirstFragment_to_mainMenu)
        }
    }

    private fun calculateBMI() {
        val heightStr = heightInput!!.text.toString()
        val weightStr = weightInput!!.text.toString()
        val df = DecimalFormat("##.##")
        if (heightStr.isNotEmpty() && weightStr.isNotEmpty() && heightStr != "0") {
            val heightValue = heightStr.toDouble() / 100.00
            val weightValue = weightStr.toDouble()
            val bmiValue = weightValue / heightValue.pow(2.0)
            val formattedBmi = df.format(bmiValue).toDouble()
            displayResult(formattedBmi)
        } else {
            result!!.setText(R.string.incorrectValueText)
        }
    }

    private fun displayResult(bmiValue: Double) {
        var bmiResult: String = when {
            bmiValue < 18.50 -> {
                "Underweight"
            }
            bmiValue < 24.90 -> {
                "Healthy"
            }
            bmiValue < 29.90 -> {
                "Overweight"
            }
            else -> {
                "Obese"
            }
        }
        bmiResult = "$bmiValue\nWeight status: $bmiResult"
        if (bmiValue > 0.00) {
            result!!.text = bmiResult
        } else {
            result!!.setText(R.string.invalidInputText)
        }
    }
}