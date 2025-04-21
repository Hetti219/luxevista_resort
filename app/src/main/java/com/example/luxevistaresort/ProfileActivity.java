package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewProfileEmail;
    private SharedPreferences sharedPreferences;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewProfileEmail = findViewById(R.id.textViewProfileEmail);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

        String loggedInEmail = sharedPreferences.getString("email", "No email found");
        textViewProfileEmail.setText(loggedInEmail);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.navigation_rooms) {
                intent = new Intent(ProfileActivity.this, RoomListActivity.class);
            } else if (item.getItemId() == R.id.navigation_services) {
                intent = new Intent(ProfileActivity.this, ServiceListActivity.class);
            } else if (item.getItemId() == R.id.navigation_bookings) {
                intent = new Intent(ProfileActivity.this, MyBookingsActivity.class);
            } else if (item.getItemId() == R.id.navigation_profile) {
                // Do nothing, already on this screen
                return true;
            }

            if (intent != null) {
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }
}