package com.s15814.healthApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.text.DecimalFormat;

public class CalorieCalculator extends Fragment {

    EditText heightInput, weightInput, ageInput;
    TextView result;
    RadioGroup buttonGroup;
    RadioButton buttonMale, buttonFemale;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calorie_calculator, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        heightInput = (EditText) view.findViewById(R.id.calorieCalculatorHeightInput);
        weightInput = (EditText) view.findViewById(R.id.calorieCalculatorWeightInput);
        ageInput = (EditText) view.findViewById(R.id.calorieCalculatorAgeInput);
        result = (TextView) view.findViewById(R.id.calorieCalculatorResultText);
        buttonGroup = (RadioGroup) view.findViewById(R.id.calorieCalculatorGenderGroup);
        buttonMale = (RadioButton) view.findViewById(R.id.calorieCalculatorMaleRadioButton);
        buttonFemale = (RadioButton) view.findViewById(R.id.calorieCalculatorFemaleRadioButton);


        view.findViewById(R.id.calorieCalculatorCalculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalories();
            }
        });

        view.findViewById(R.id.calorieCalculatorBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CalorieCalculator.this)
                        .navigate(R.id.action_SecondFragment_to_mainMenu);
            }
        });
    }

    private void calculateCalories() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();
        String ageStr = ageInput.getText().toString();

        DecimalFormat df = new DecimalFormat("##.##");

        int selectedButton = this.buttonGroup.indexOfChild(
                getView().findViewById(this.buttonGroup.getCheckedRadioButtonId())
        );

        if (!heightStr.isEmpty() && !weightStr.isEmpty() && !ageStr.isEmpty()) {
            if (selectedButton == 1) {
                double recommendedCalories = 655.1 + (9.563 * Double.parseDouble(weightStr)) + (1.85 * Double.parseDouble(heightStr)) - (4.676 * Double.parseDouble(ageStr));
                result.setText("Recommended calories: " + Double.parseDouble(df.format(recommendedCalories)));
            } else {
                double recommendedCalories = 66.5 + (13.75 * Double.parseDouble(weightStr)) + (5.003 * Double.parseDouble(heightStr)) - (6.775 * Double.parseDouble(ageStr));
                result.setText("Recommended calories: " + Double.parseDouble(df.format(recommendedCalories)));
            }
        } else {
            result.setText("Please enter all the values.");
        }
    }
}
