package com.example.israpp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonISR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonISR = (Button) findViewById(R.id.buttonISR);
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
}