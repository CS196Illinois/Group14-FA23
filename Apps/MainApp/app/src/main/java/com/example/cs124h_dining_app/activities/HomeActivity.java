package com.example.cs124h_dining_app.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs124h_dining_app.R;
import com.example.cs124h_dining_app.Time2Eat;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button buttonISR;
    private Button buttonAllen;
    private Button buttonPar;
    private Button buttonIke;
    private TextView username;
    private Time2Eat appState;

    private ActivityResultLauncher<Intent> loginLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == AppCompatActivity.RESULT_OK) {
                        username.setText(appState.getUser().getEmail());
                    }
                }
            }
    );
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        appState = (Time2Eat) this.getApplication();
        username = (TextView) findViewById(R.id.username);
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
            loginLaunch.launch(intent);
        });

        loginLaunch.launch(new Intent(getApplicationContext(), LoginActivity.class));
    }
    public void openDining(String  id) {
        Intent intent = new Intent(this, DiningActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}