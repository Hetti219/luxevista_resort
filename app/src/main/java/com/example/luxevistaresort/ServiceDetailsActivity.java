package com.example.luxevistaresort;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
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

            // Set OnClickListener for the Book Service button
            buttonBookService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Simulate booking
                    String serviceName = selectedService.getName();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String bookingTime = sdf.format(new Date());

                    // (Optional) Store basic booking info in SharedPreferences
                    SharedPreferences.Editor editor = bookingPreferences.edit();
                    editor.putString(serviceName, "Booked on: " + bookingTime);
                    editor.apply();

                    // Display confirmation message
                    Toast.makeText(ServiceDetailsActivity.this, serviceName + " booked successfully!", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // Handle error if the service index is invalid
            finish(); // Go back to the previous activity
        }
    }
}