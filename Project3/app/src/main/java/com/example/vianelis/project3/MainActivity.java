package com.example.vianelis.project3;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //variable declarations
    Spinner UnitsSpinnerSelect;
    ArrayAdapter UnitSpinnerList;
    EditText UnitInput;
    TextView UnitOutput;
    TextView Unit1;
    TextView Unit2;
    public int positionSelected = 0;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnitsSpinnerSelect = findViewById(R.id.Units_List);
        Unit1 = findViewById(R.id.Unit1);
        Unit2 = findViewById(R.id.Unit2);
        submit = findViewById(R.id.Calculate);


        ArrayList<String> unitsList = new ArrayList<>();

        unitsList.add(getResources().getString(R.string.mikm));
        unitsList.add(getResources().getString(R.string.kmmi));
        unitsList.add(getResources().getString(R.string.incm));
        unitsList.add(getResources().getString(R.string.cmin));

        //adapter to fill the select list with UnitsList array values
        UnitSpinnerList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, unitsList);
        UnitsSpinnerSelect.setAdapter(UnitSpinnerList);

        //select list spinner listener
        UnitsSpinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionSelected = i;

                switch (positionSelected) {
                    case 0:
                        Unit1.setText(String.valueOf("Miles"));
                        Unit2.setText(String.valueOf("Kilometers"));
                        break;
                    case 1:
                        Unit1.setText(String.valueOf("Kilometers"));
                        Unit2.setText(String.valueOf("Miles"));
                        break;
                    case 2:
                        Unit1.setText(String.valueOf("Inches"));
                        Unit2.setText(String.valueOf("Centimeters"));
                        break;
                    case 3:
                        Unit1.setText(String.valueOf("Centimeters"));
                        Unit2.setText(String.valueOf("Inches"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //convert the value entered into appropriate unit on click of submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                UnitInput = findViewById(R.id.UnitInput);
                UnitOutput = findViewById(R.id.UnitOutput);

                if(!UnitInput.getText().toString().equalsIgnoreCase(""))
                {
                    switch (positionSelected) {
                        //convert mi to km
                        case 0:
                            double mi = Double.parseDouble(UnitInput.getText().toString()) * 1.6093;
                            UnitOutput.setText(String.valueOf(String.format("%.2f", (mi))));
                            break;
                        //convert km to mi
                        case 1:
                            double km = Double.parseDouble(UnitInput.getText().toString()) * 0.6214;
                            UnitOutput.setText(String.valueOf(String.format("%.2f", (km))));
                            break;
                        //convert in to cm
                        case 2:
                            double in = Double.parseDouble(UnitInput.getText().toString()) * 2.54;
                            UnitOutput.setText(String.valueOf(String.format("%.2f", (in))));
                            break;
                        //convert cm to in
                        case 3:
                            double cm = Double.parseDouble(UnitInput.getText().toString()) * 0.3937;
                            UnitOutput.setText(String.valueOf(String.format("%.2f", (cm))));
                            break;
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter value to convert",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}