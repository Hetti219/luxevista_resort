package com.example.luxevistaresort;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class ServiceDetailsActivity extends AppCompatActivity {

    private ImageView imageViewServiceDetails;
    private TextView textViewServiceNameDetails;
    private TextView textViewServicePriceDetails;
    private TextView textViewServiceAvailabilityDetails;
    private TextView textViewServiceDurationDetails;
    private TextView textViewServiceDescriptionDetails;
    private Button buttonBookService;
    private SharedPreferences bookingPreferences;
    private TextView textViewSelectedDate;
    private Button buttonPickDate;
    private TextView textViewSelectedTime;
    private Button buttonPickTime;
    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        // Initialize views
        imageViewServiceDetails = findViewById(R.id.imageViewServiceDetails);
        textViewServiceNameDetails = findViewById(R.id.textViewServiceNameDetails);
        textViewServicePriceDetails = findViewById(R.id.textViewServicePriceDetails);
        textViewServiceAvailabilityDetails = findViewById(R.id.textViewServiceAvailabilityDetails);
        textViewServiceDurationDetails = findViewById(R.id.textViewServiceDurationDetails);
        textViewServiceDescriptionDetails = findViewById(R.id.textViewServiceDescriptionDetails);
        buttonBookService = findViewById(R.id.buttonBookService);
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        textViewSelectedTime = findViewById(R.id.textViewSelectedTime);
        buttonPickTime = findViewById(R.id.buttonPickTime);

        // Initialize SharedPreferences for bookings (for this example)
        bookingPreferences = getSharedPreferences("service_bookings", MODE_PRIVATE);

        // Get the service index passed from ServiceListActivity
        int serviceIndex = getIntent().getIntExtra("SERVICE_INDEX", -1);

        // Fetch the selected service from the list
        if (serviceIndex >= 0 && serviceIndex < ServiceListActivity.serviceList.size()) {
            Service selectedService = ServiceListActivity.serviceList.get(serviceIndex);

            // Populate the views with the service details
            imageViewServiceDetails.setImageResource(selectedService.getImageResourceId());
            textViewServiceNameDetails.setText(selectedService.getName());
            textViewServicePriceDetails.setText("$" + String.format("%.2f", selectedService.getPrice()));
            textViewServiceAvailabilityDetails.setText("Availability: " + selectedService.getAvailability());
            textViewServiceDurationDetails.setText("Duration: " + selectedService.getDuration());
            textViewServiceDescriptionDetails.setText(selectedService.getDescription());

            // Set OnClickListener for the Pick Date button
            buttonPickDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get current date
                    final Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);

                    // Create and show the DatePickerDialog
                    android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(ServiceDetailsActivity.this,
                            (view, year, monthOfYear, dayOfMonth) -> {
                                // Update the selected date TextView
                                ServiceDetailsActivity.this.year = year;
                                ServiceDetailsActivity.this.month = monthOfYear;
                                ServiceDetailsActivity.this.day = dayOfMonth;
                                textViewSelectedDate.setText(String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                            }, year, month, day);
                    datePickerDialog.show();
                }
            });

            // Set OnClickListener for the Pick Time button
            buttonPickTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get current time
                    final Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);

                    // Create and show the TimePickerDialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(ServiceDetailsActivity.this,
                            (view, hourOfDay, minute) -> {
                                // Update the selected time TextView
                                ServiceDetailsActivity.this.hour = hourOfDay;
                                ServiceDetailsActivity.this.minute = minute;
                                textViewSelectedTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
                            }, hour, minute, android.text.format.DateFormat.is24HourFormat(ServiceDetailsActivity.this));
                    timePickerDialog.show();
                }
            });

            // Set OnClickListener for the Book Service button
            buttonBookService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Simulate booking
                    String serviceName = selectedService.getName();
                    String selectedDate = textViewSelectedDate.getText().toString();
                    String selectedTime = textViewSelectedTime.getText().toString();

                    String bookingDateTime = "";
                    if (!selectedDate.equals("Not selected") && !selectedTime.equals("Not selected")) {
                        bookingDateTime = selectedDate + " " + selectedTime;
                    } else {
                        Toast.makeText(ServiceDetailsActivity.this, "Please select a date and time", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // (Optional) Store basic booking info in SharedPreferences
                    SharedPreferences.Editor editor = bookingPreferences.edit();
                    editor.putString(serviceName, "Booked on: " + bookingDateTime);
                    editor.apply();

                    // Display confirmation message
                    Toast.makeText(ServiceDetailsActivity.this, serviceName + " booked for " + bookingDateTime + "!", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // Handle error if the service index is invalid
            finish(); // Go back to the previous activity
        }
    }
}