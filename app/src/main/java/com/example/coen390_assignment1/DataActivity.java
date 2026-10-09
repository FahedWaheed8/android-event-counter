package com.example.coen390_assignment1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import java.util.ArrayList;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    private Switch switchCounter1;
    private Switch switchCounter2;
    private Switch switchCounter3;
    private ListView listViewEvents;
    private TextView textViewDataTotal;

    private String counter1Name;
    private String counter2Name;
    private String counter3Name;

    private int counter1Count;
    private int counter2Count;
    private int counter3Count;
    private int totalCount;
    private String eventHistory;
    private boolean showNumbers = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);

        switchCounter1 = findViewById(R.id.switchCounter1);
        switchCounter2 = findViewById(R.id.switchCounter2);
        switchCounter3 = findViewById(R.id.switchCounter3);
        listViewEvents = findViewById(R.id.listViewEvents);
        textViewDataTotal = findViewById(R.id.textViewDataTotal);


        SharedPreferences preferences =
                getSharedPreferences("AppSettings", MODE_PRIVATE);

        counter1Name = preferences.getString("counter1Name", "Event 1");
        counter2Name = preferences.getString("counter2Name", "Event 2");
        counter3Name = preferences.getString("counter3Name", "Event 3");

        counter1Count = preferences.getInt("counter1Count", 0);
        counter2Count = preferences.getInt("counter2Count", 0);
        counter3Count = preferences.getInt("counter3Count", 0);
        totalCount = preferences.getInt("totalCount", 0);
        textViewDataTotal.setText("Total Count: " + totalCount);

        eventHistory = preferences.getString("eventHistory", "");

        switchCounter1.setText(counter1Name + ": " + counter1Count);
        switchCounter2.setText(counter2Name + ": " + counter2Count);
        switchCounter3.setText(counter3Name + ": " + counter3Count);

        switchCounter1.setChecked(true);
        switchCounter2.setChecked(true);
        switchCounter3.setChecked(true);

        updateList();

        switchCounter1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateList();
        });

        switchCounter2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateList();
        });

        switchCounter3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateList();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private void updateList() {

        ArrayList<String> data = new ArrayList<>();

        if (!eventHistory.isEmpty()) {

            String[] events = eventHistory.split(",");

            for (String event : events) {

                if (event.equals("1") && switchCounter1.isChecked()) {
                    data.add(showNumbers ? "1" : counter1Name);
                }

                if (event.equals("2") && switchCounter2.isChecked()) {
                    data.add(showNumbers ? "2" : counter2Name);
                }

                if (event.equals("3") && switchCounter3.isChecked()) {
                    data.add(showNumbers ? "3" : counter3Name);data.add(showNumbers ? "3" : counter3Name);
                }
            }
        }



        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        data
                );

        listViewEvents.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.data_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        if (item.getItemId() == R.id.menuToggle) {

            showNumbers = !showNumbers;

            if (showNumbers) {
                item.setTitle("Show Names");
            } else {
                item.setTitle("Show Numbers");
            }

            updateList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}