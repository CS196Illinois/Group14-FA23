package com.example.israpp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button checkIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        checkIn = (Button) findViewById(R.id.checkIn);
        checkIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivity3();
            }
        });
    }
    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}