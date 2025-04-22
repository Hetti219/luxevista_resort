package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewServices;
    public static List<Service> serviceList;
    private ServiceAdapter serviceAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        recyclerViewServices = findViewById(R.id.recyclerViewServices);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.navigation_services);

        // Create a list of sample services (replace with your actual data later)
        serviceList = new ArrayList<>();
        serviceList.add(new Service("Aromatherapy Massage", "Relaxing full-body massage with essential oils.", 75.00, "Spa", "Daily 10 AM - 7 PM", "60 minutes", R.drawable.spa_treatment));
        serviceList.add(new Service("Fine Dining Experience", "Exquisite multi-course meal at our signature restaurant.", 120.00, "Dining", "Daily 7 PM - 10 PM", "2 hours", R.drawable.fine_dining));
        serviceList.add(new Service("Guided Beach Tour", "Explore the beautiful coastline with our expert guide.", 40.00, "Activity", "Daily 9 AM & 2 PM", "3 hours", R.drawable.beach_tour));
        serviceList.add(new Service("Poolside Cabana Rental", "Enjoy a private cabana by the pool.", 60.00, "Cabana", "Daily 9 AM - 6 PM", "Full day", R.drawable.poolside_cabana));
        // Add more services here

        serviceAdapter = new ServiceAdapter(this, serviceList);
        recyclerViewServices.setAdapter(serviceAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.navigation_rooms) {
                intent = new Intent(ServiceListActivity.this, RoomListActivity.class);
            } else if (item.getItemId() == R.id.navigation_services) {
                // Do nothing, already on this screen
                return true;
            } else if (item.getItemId() == R.id.navigation_bookings) {
                intent = new Intent(ServiceListActivity.this, MyBookingsActivity.class);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(ServiceListActivity.this, ProfileActivity.class);
            } else if (item.getItemId() == R.id.navigation_attractions) {
                intent = new Intent(ServiceListActivity.this, AttractionsActivity.class);
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