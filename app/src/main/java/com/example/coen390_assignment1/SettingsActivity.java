package com.example.coen390_assignment1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.content.SharedPreferences;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        EditText counter1Input;
        EditText counter2Input;
        EditText counter3Input;
        EditText maximumCountsInput;
        Button saveButton;
        // Finding all Counters buttons in HTML file
        counter1Input = findViewById(R.id.editTextCounter1);
        counter2Input = findViewById(R.id.editTextCounter2);
        counter3Input = findViewById(R.id.editTextCounter3);
        maximumCountsInput = findViewById(R.id.editTextMaximumCounts);
        saveButton = findViewById(R.id.buttonSave);
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String savedCounter1Name = preferences.getString("counter1Name", "");
        String savedCounter2Name = preferences.getString("counter2Name", "");
        String savedCounter3Name = preferences.getString("counter3Name", "");
        int savedMaximumCounts = preferences.getInt("maximumCounts", 0);
        counter1Input.setText(savedCounter1Name);
        counter2Input.setText(savedCounter2Name);
        counter3Input.setText(savedCounter3Name);
        maximumCountsInput.setText(String.valueOf(savedMaximumCounts));

        // SAVE Listener
        saveButton.setOnClickListener(v -> {

            String counter1Name = counter1Input.getText().toString();
            String counter2Name = counter2Input.getText().toString();
            String counter3Name = counter3Input.getText().toString();
            String maximumCountsText = maximumCountsInput.getText().toString();
            // Checking for empty fields
            if (counter1Name.isEmpty()){
                counter1Input.setError("Counter 1 name is required");
                return;
            }
            if (counter2Name.isEmpty()){
                counter2Input.setError("Counter 2 name is required");
                return;
            }
            if (counter3Name.isEmpty()){
                counter3Input.setError("Counter 3 name is required");
                return;
            }

            if (maximumCountsText.isEmpty()){
                maximumCountsInput.setError("Maximum counts is required");
                return;
            }
            // Checking for Max 20 characters
            if (counter1Name.length() > 20) {
                counter1Input.setError("Maximum 20 characters");
                return;
            }

            if (counter2Name.length() > 20) {
                counter2Input.setError("Maximum 20 characters");
                return;
            }

            if (counter3Name.length() > 20) {
                counter3Input.setError("Maximum 20 characters");
                return;
            }

            // Checking for only letters and spaces
            if (!counter1Name.matches("[a-zA-Z ]+")){
                counter1Input.setError("Only letters and spaces are allowed");
                return;
            }

            if (!counter2Name.matches("[a-zA-Z ]+")) {
                counter2Input.setError("Only letters and spaces are allowed");
                return;
            }

            if (!counter3Name.matches("[a-zA-Z ]+")){
                counter3Input.setError("Only letters and spaces are allowed");
                return;
            }

            int maximumCounts = Integer.parseInt(maximumCountsText);

            if(maximumCounts < 5 || maximumCounts > 200){
                maximumCountsInput.setError("Maximum counts must be between 5 and 200");
                return;
            }

            Settings settings = new Settings(counter1Name, counter2Name, counter3Name, maximumCounts);
            // Saving data to SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("counter1Name", settings.getCounter1Name());
            editor.putString("counter2Name", settings.getCounter2Name());
            editor.putString("counter3Name", settings.getCounter3Name());
            editor.putInt("maximumCounts", settings.getMaximumCounts());
            editor.apply();
            finish();





        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}



