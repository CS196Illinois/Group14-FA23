package com.example.cs124h_dining_app.activities;

import static com.example.cs124h_dining_app.util.DiningHallStuffKt.hallName;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cs124h_dining_app.R;
import com.example.cs124h_dining_app.data.DiningMenuRepository;
import com.example.cs124h_dining_app.models.UserStats;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    // public static final int TIME = 0;
    private Button submitButton;
    private RatingBar stationRating;

    private TextView waitTime;
    private SeekBar waitBar;
    private static int count = 0;
    private int wait;
    private int averageTime;
    private EditText messageReview;

    private String id;
    private  String station;

    private ArrayList<Integer> numList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);
        //Anything with waitBar or waitTime refers to the "progress bar" and text that show the wait time
        waitTime = (TextView) findViewById(R.id.waitTime);
        stationRating = (RatingBar) findViewById(R.id.stationRating);
        waitBar = (SeekBar) findViewById(R.id.waitBar);
        messageReview = (EditText) findViewById(R.id.messageReview);
        id = this.getIntent().getStringExtra("id");
        station = this.getIntent().getStringExtra("station");

        waitBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                waitTime.setText("" + (i * 6 / 10) + " Minutes");
                int userTime = i * 6 / 10;
                wait = userTime;
                // this is where we put the code to store the info on the wait time
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {
                submitUserInput();
            }
        });
    }

    public void submitUserInput() {
        Intent intent = new Intent(this, ThanksActivity.class);
        UserStats data = new UserStats(hallName(id), station, wait, stationRating.getRating(), messageReview.getText().toString());
        DiningMenuRepository.submitStats(data, this);
        startActivity(intent);
        finish();
    }
}