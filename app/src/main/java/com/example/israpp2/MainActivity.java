package com.example.israpp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonISR;
    private Button buttonAllen;
    private Button buttonPar;
    private Button buttonIke;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAllen = (Button) findViewById(R.id.buttonAllen);
        buttonPar = (Button) findViewById(R.id.buttonPar);
        buttonIke = (Button) findViewById(R.id.buttonIke);
        buttonISR = (Button) findViewById(R.id.buttonISR);
        buttonIke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openActivity2ike();
            }
        });
        buttonPar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openActivity2par();
            }
        });
        buttonAllen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openActivity2allen();
            }
        });
        buttonISR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                openActivity2();
            }
        });
    }
        public void openActivity2() {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        public void openActivity2par() {
            Intent intent = new Intent(this, MainActivity2par.class);
            startActivity(intent);
        }
        public void openActivity2ike() {
            Intent intent = new Intent(this, MainActivity2ike.class);
            startActivity(intent);
        }
        public void openActivity2allen() {
            Intent intent = new Intent(this, MainActivity2allen.class);
            startActivity(intent);
        }
}