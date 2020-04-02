package com.s15814.healthApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.text.DecimalFormat;

public class BmiCalculator extends Fragment {

    EditText heightInput, weightInput;
    TextView result;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bmi_calculator, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        heightInput = (EditText) view.findViewById(R.id.insertHeight);
        weightInput = (EditText) view.findViewById(R.id.insertHeight);
        result = (TextView) view.findViewById(R.id.bmiCalculatorResultView);

        view.findViewById(R.id.bmiCalculatorCalculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        view.findViewById(R.id.bmiCalculatorBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(BmiCalculator.this)
                        .navigate(R.id.action_FirstFragment_to_mainMenu);
            }
        });
    }

    private void calculateBMI() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();

        DecimalFormat df = new DecimalFormat("##.##");

        if (!heightStr.isEmpty() && !weightStr.isEmpty() && !heightStr.equals("0")) {
            double heightValue = Double.parseDouble(heightStr) / 100.00;
            double weightValue = Double.parseDouble(weightStr);

            double bmiValue = weightValue / Math.pow(heightValue, 2);
            double formattedBmi = Double.parseDouble(df.format(bmiValue));

            displayResult(formattedBmi);
        } else {
            result.setText(R.string.incorrectValueText);
        }
    }

    private void displayResult(double bmiValue) {
        String bmiResult;

        if (bmiValue < 18.50) {
            bmiResult = "Underweight";
        } else if (bmiValue < 24.90) {
            bmiResult = "Healthy";
        } else if (bmiValue < 29.90) {
            bmiResult = "Overweight";
        } else {
            bmiResult = "Obese";
        }

        bmiResult = bmiValue + "\nWeight status: " + bmiResult;
        if (bmiValue > 0.00) {
            result.setText(bmiResult);
        } else {
            result.setText(R.string.invalidInputText);
        }
    }

}
