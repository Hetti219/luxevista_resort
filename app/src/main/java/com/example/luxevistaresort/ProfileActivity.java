package com.example.luxevistaresort;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewProfileFullName;
    private TextView textViewProfileEmail;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewProfileFullName = findViewById(R.id.textViewProfileFullName);
        textViewProfileEmail = findViewById(R.id.textViewProfileEmail);
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        // Get the user's full name from SharedPreferences
        String userFullName = sharedPreferences.getString("full_name", "No name found");
        textViewProfileFullName.setText("Name: " + userFullName);

        // Get the user's email from SharedPreferences
        String userEmail = sharedPreferences.getString("email", "No email found");
        textViewProfileEmail.setText("Email: " + userEmail);
    }
}