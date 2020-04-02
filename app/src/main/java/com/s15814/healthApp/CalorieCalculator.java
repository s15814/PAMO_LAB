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

public class CalorieCalculator extends Fragment {

    EditText heightInput, weightInput, ageInput;
    TextView result;

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
        result.setText("Result");

    }
}
