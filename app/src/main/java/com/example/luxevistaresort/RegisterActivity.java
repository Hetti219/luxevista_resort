package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegisterFullName;
    private EditText editTextRegisterEmail;
    private EditText editTextRegisterPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister;
    private TextView textViewLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        editTextRegisterFullName = findViewById(R.id.editTextRegisterFullName);
        editTextRegisterEmail = findViewById(R.id.editTextRegisterEmail);
        editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewLogin = findViewById(R.id.textViewLogin);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        // Set click listener for the login TextView
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editTextRegisterFullName.getText().toString().trim();
                String email = editTextRegisterEmail.getText().toString().trim();
                String password = editTextRegisterPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                // Perform input validation for Full Name
                if (TextUtils.isEmpty(fullName)) {
                    editTextRegisterFullName.setError("Full Name is required");
                    return;
                }

                // Perform input validation for Email
                if (TextUtils.isEmpty(email)) {
                    editTextRegisterEmail.setError("Email is required");
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextRegisterEmail.setError("Enter a valid email address");
                    return;
                }

                // Perform input validation for Password
                if (TextUtils.isEmpty(password)) {
                    editTextRegisterPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    editTextRegisterPassword.setError("Password must be at least 6 characters long");
                    return;
                }

                // Perform input validation for Confirm Password
                if (TextUtils.isEmpty(confirmPassword)) {
                    editTextConfirmPassword.setError("Confirm Password is required");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    editTextConfirmPassword.setError("Passwords do not match");
                    return;
                }

                // If all validations pass, simulate registration
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("full_name", fullName); // Store the full name
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                // Navigate to the login screen
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}