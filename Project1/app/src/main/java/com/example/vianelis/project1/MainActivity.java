package com.example.vianelis.project1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calculate = findViewById(R.id.Calculate);

        calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        EditText height = findViewById(R.id.HeightInput);
        EditText width = findViewById(R.id.WidthInput);

        calculateArea(height.getText().toString(), width.getText().toString());
        calculatePerimeter(height.getText().toString(), width.getText().toString());
    }

    private void calculateArea(String h, String w) {
        TextView area = findViewById(R.id.CalcArea);
        double area1 = Double.parseDouble(h)* Double.parseDouble(w);
        area.setText(String.valueOf(String.format("%.2f", (area1))));
    }
    private void calculatePerimeter (String h, String w) {
        TextView perimeter = findViewById(R.id.CalcPerimeter);
        double perimeter1 = (Double.parseDouble(h) * 2) + (Double.parseDouble(w) * 2);
        perimeter.setText(String.valueOf(String.format("%.2f", (perimeter1))));
    }

}