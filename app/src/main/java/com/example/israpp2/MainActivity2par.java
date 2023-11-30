package com.example.israpp2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2par extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button checkIn;
    private String text;
    private Spinner spinner;
    static Station entry;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2par);
        MainActivity0.locationChosen = "PAR";
        spinner = (Spinner) findViewById(R.id.spinner); //anything with "spinner refers to the dropdown stations
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stationsPar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        checkIn = (Button) findViewById(R.id.checkIn);
        checkIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivity3();
            }
        });
    }

    //the next 2 methods are for the dropdown station options in order to select them and make them visible to the viewer
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        entry = new Station();
        text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
        entry.setName(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}