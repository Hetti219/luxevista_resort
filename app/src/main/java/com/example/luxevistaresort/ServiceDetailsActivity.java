package com.example.luxevistaresort;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceDetailsActivity extends AppCompatActivity {

    private ImageView imageViewServiceDetails;
    private TextView textViewServiceNameDetails;
    private TextView textViewServicePriceDetails;
    private TextView textViewServiceAvailabilityDetails;
    private TextView textViewServiceDurationDetails;
    private TextView textViewServiceDescriptionDetails;

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
        } else {
            // Handle error if the service index is invalid
            finish(); // Go back to the previous activity
        }
    }
}