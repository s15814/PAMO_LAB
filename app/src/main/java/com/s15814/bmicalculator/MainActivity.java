package com.s15814.bmicalculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText heightInput, weightInput;
    TextView result;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        heightInput = (EditText) findViewById(R.id.insertHeight);
        weightInput = (EditText) findViewById(R.id.insertWeight);
        result = (TextView) findViewById(R.id.resultView);
        calculate = (Button) findViewById(R.id.calculateButton);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "If you want to learn more about BMI, visit\nhttps://www.cdc.gov/healthyweight/assessing/bmi/adult_bmi/index.html", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
