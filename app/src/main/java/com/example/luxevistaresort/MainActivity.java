package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLoggedInEmail;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLoggedInEmail = findViewById(R.id.textViewLoggedInEmail);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        String loggedInEmail = sharedPreferences.getString("loggedInEmail", "No email found");
        textViewLoggedInEmail.setText("Logged in as: " + loggedInEmail);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.navigation_rooms) {
                intent = new Intent(MainActivity.this, RoomListActivity.class);
            } else if (item.getItemId() == R.id.navigation_services) {
                intent = new Intent(MainActivity.this, ServiceListActivity.class);
            } else if (item.getItemId() == R.id.navigation_bookings) {
                intent = new Intent(MainActivity.this, MyBookingsActivity.class);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(MainActivity.this, ProfileActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}