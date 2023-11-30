package com.example.israpp2;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
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
    ListOfStations list = new ListOfStations();

   // private ArrayList<Station> stationList = new ArrayList<>();
    private ArrayList<Integer> numList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Anything with waitBar or waitTime refers to the "progress bar" and text that show the wait time
        waitTime = (TextView) findViewById(R.id.waitTime);
        stationRating = (RatingBar) findViewById(R.id.stationRating);
        waitBar = (SeekBar) findViewById(R.id.waitBar);
        messageReview = (EditText) findViewById(R.id.messageReview);


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
                Station stationCopy = new Station();
                stationCopy.setLocation(MainActivity0.locationChosen);
                if (stationCopy.getLocation().equals("ISR")) {
                    stationCopy = MainActivity2.entry;
                    loadData();
                    stationCopy.setTime(wait);
                    stationCopy.setQuality(stationRating.getRating());
                    stationCopy.setDescription(String.valueOf(messageReview.getText()));
                    // entry.setDescription();
                    list.add(stationCopy);
                    numList.add(1);
                    averageTime = list.calculateAvgTime();
                    double averageRating = list.calculateAvgRating();
                    openActivity4();
                    count++;
                    System.out.println(list.getStationList());
                    System.out.println(averageTime);
                    System.out.println(averageRating);
                    System.out.println(list.getReviewMessages());
                    saveData();
                } else if (stationCopy.getLocation().equals("Ike")) {
                    stationCopy = MainActivity2ike.entry;
                    loadData();
                    stationCopy.setTime(wait);
                    stationCopy.setQuality(stationRating.getRating());
                    stationCopy.setDescription(String.valueOf(messageReview.getText()));
                    // entry.setDescription();
                    numList.add(1);
                    openActivity4();
                    count++;
                    System.out.println(list.getStationList());
                    System.out.println(averageTime);
                    saveData();
                } else if (stationCopy.getLocation().equals("Allen")) {
                    stationCopy = MainActivity2allen.entry;
                    loadData();
                    stationCopy.setTime(wait);
                    stationCopy.setQuality(stationRating.getRating());
                    stationCopy.setDescription(String.valueOf(messageReview.getText()));
                    // entry.setDescription();
                    list.add(stationCopy);
                    numList.add(1);
                    averageTime = list.calculateAvgTime();
                    double averageRating = list.calculateAvgRating();
                    openActivity4();
                    count++;
                    System.out.println(list.getStationList());
                    System.out.println(averageTime);
                    System.out.println(averageRating);
                    System.out.println(list.getReviewMessages());
                    saveData();
                } else {
                    stationCopy = MainActivity2par.entry;
                    loadData();
                    stationCopy.setTime(wait);
                    stationCopy.setQuality(stationRating.getRating());
                    stationCopy.setDescription(String.valueOf(messageReview.getText()));
                    // entry.setDescription();
                    list.add(stationCopy);
                    numList.add(1);
                    averageTime = list.calculateAvgTime();
                    double averageRating = list.calculateAvgRating();
                    openActivity4();
                    count++;
                    System.out.println(list.getStationList());
                    System.out.println(averageTime);
                    System.out.println(averageRating);
                    System.out.println(list.getReviewMessages());
                    saveData();
                }


            }
        });
    }

    public void openActivity4() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ListOfStations>(){}.getType();
        list = gson.fromJson(json, type);
        if (list == null) {
            list = new ListOfStations();
        }

    }


}