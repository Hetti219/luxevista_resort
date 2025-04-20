package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLoggedInEmail;
    private Button buttonProfile;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLoggedInEmail = findViewById(R.id.textViewLoggedInEmail);
        buttonProfile = findViewById(R.id.buttonProfile);
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        // Get the logged-in email from SharedPreferences
        String loggedInEmail = sharedPreferences.getString("email", "No email found");
        textViewLoggedInEmail.setText("Logged in as: " + loggedInEmail);

        // Set click listener for the View Profile button
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for the View Rooms button
        Button buttonViewRooms = findViewById(R.id.buttonViewRooms);
        buttonViewRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomListActivity.class);
                startActivity(intent);
            }
        });
    }
}