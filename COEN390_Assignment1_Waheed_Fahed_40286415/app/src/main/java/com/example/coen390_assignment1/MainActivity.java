package com.example.coen390_assignment1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button buttonSettings; // Button variable for SETTINGS BUTTON
    private Button buttonEvent1; // Button variable for EVENT A BUTTON
    private Button buttonEvent2; // Button variable for EVENT B BUTTON
    private Button buttonEvent3; // Button variable for EVENT C BUTTON
    private Button buttonShowCounts; // Button variable for SHOW MY COUNTS BUTTON
    private int totalCount = 0; // Total Count variable
    private int counter1Count = 0;
    private int counter2Count = 0;
    private int counter3Count = 0;
    private int maximumCounts = 200;
    private String eventHistory = "";
    private TextView textViewTotalCount; // TextView variable for TOTAL COUNT


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonEvent1 = findViewById(R.id.buttonEvent1);
        buttonEvent2 = findViewById(R.id.buttonEvent2);
        buttonEvent3 = findViewById(R.id.buttonEvent3);

        buttonEvent1.setOnClickListener(v -> {
            if (totalCount < maximumCounts) {
                counter1Count++;
                totalCount++;

                eventHistory += "1,";

                saveCounts();
                textViewTotalCount.setText("Total Count: " + totalCount);
            }
        });

        buttonEvent2.setOnClickListener(v -> {
            if (totalCount < maximumCounts) {
                counter2Count++;
                totalCount++;

                eventHistory += "2,";

                saveCounts();
                textViewTotalCount.setText("Total Count: " + totalCount);
            }
        });

        buttonEvent3.setOnClickListener(v -> {
            if (totalCount < maximumCounts) {
                counter3Count++;
                totalCount++;

                eventHistory += "3,";

                saveCounts();
                textViewTotalCount.setText("Total Count: " + totalCount);
            }
        });

        textViewTotalCount = findViewById(R.id.textViewTotalCount); // Finding TOTAL COUNT button in HTML file
        loadCounts();


        buttonSettings = findViewById(R.id.buttonSettings); // Finding SETTINGS button in HTML file
                buttonSettings.setOnClickListener(v -> { // Listens for a click for SETTINGS button
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class); // An action Request
                    startActivity(intent);
                });

        buttonShowCounts = findViewById(R.id.buttonShowCounts);
            buttonShowCounts.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCounterNames();
    }

    private void loadCounterNames(){
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE); //Access saved settings

        String counter1Name = preferences.getString("counter1Name", "Event 1"); // Get the saved Counter 1 name
        String counter2Name = preferences.getString("counter2Name", "Event 2"); // Get the saved Counter 2 name
        String counter3Name = preferences.getString("counter3Name", "Event 3"); // Get the saved Counter 3 name
        maximumCounts = preferences.getInt("maximumCounts", 200); // Get the saved maximum counts

        buttonEvent1.setText(counter1Name); // Display that name on the button
        buttonEvent2.setText(counter2Name); // Display that name on the button
        buttonEvent3.setText(counter3Name); // Display that name on the button


    }
    private void saveCounts() {
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("counter1Count", counter1Count);
        editor.putInt("counter2Count", counter2Count);
        editor.putInt("counter3Count", counter3Count);
        editor.putInt("totalCount", totalCount);
        editor.putString("eventHistory", eventHistory);


        editor.apply();
    }

    private void loadCounts() {
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        counter1Count = preferences.getInt("counter1Count", 0);
        counter2Count = preferences.getInt("counter2Count", 0);
        counter3Count = preferences.getInt("counter3Count", 0);
        totalCount = preferences.getInt("totalCount", 0);
        eventHistory = preferences.getString("eventHistory", "");

        textViewTotalCount.setText("Total Count: " + totalCount);
    }

}