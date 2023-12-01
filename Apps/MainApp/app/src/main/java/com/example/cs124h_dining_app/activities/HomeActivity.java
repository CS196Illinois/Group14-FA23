package com.example.cs124h_dining_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs124h_dining_app.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button buttonISR;
    private Button buttonAllen;
    private Button buttonPar;
    private Button buttonIke;

    private FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        buttonAllen = (Button) findViewById(R.id.buttonAllen);
        buttonPar = (Button) findViewById(R.id.buttonPar);
        buttonIke = (Button) findViewById(R.id.buttonIke);
        buttonISR = (Button) findViewById(R.id.buttonISR);
        buttonIke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openDining("1");
            }
        });
        buttonPar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openDining("2");
            }
        });
        buttonAllen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openDining("5");
            }
        });
        buttonISR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openDining("3");
            }
        });

        findViewById(R.id.buttonLogout).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
    public void openDining(String  id) {
        Intent intent = new Intent(this, DiningActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}