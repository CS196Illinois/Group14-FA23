package com.example.israpp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity0 extends AppCompatActivity {
    static ListOfStations fusion48 = new ListOfStations();
    static ListOfStations sapporito = new ListOfStations();
    static ListOfStations latitude = new ListOfStations();
    static ListOfStations grillworks = new ListOfStations();
    static ListOfStations solutionsKitchen = new ListOfStations();
    static ListOfStations grainsGreens = new ListOfStations();
    static ListOfStations riseDine = new ListOfStations();
    static ListOfStations cafeCrumb = new ListOfStations();
    /* public static ListOfStations getList() {
        return list;
    }  */
    Button buttonHome;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        buttonHome = (Button) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openActivity1();
            }
        });

    }

    public void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}