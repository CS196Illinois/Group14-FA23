package com.example.cs124h_dining_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs124h_dining_app.R;

public class ThanksActivity extends AppCompatActivity {
    Button homeBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks);

        homeBack = (Button) findViewById(R.id.homeBack);
        homeBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                returnHome();
            }
        });
    }
    public void returnHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}