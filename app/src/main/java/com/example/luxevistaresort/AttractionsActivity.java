package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AttractionsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAttractions;
    private AttractionAdapter attractionAdapter;
    private List<Attraction> attractionList;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        recyclerViewAttractions = findViewById(R.id.recyclerViewAttractions);
        recyclerViewAttractions.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.navigation_attractions); // We'll add this to the menu

        // Sample data for attractions and offers
        attractionList = new ArrayList<>();
        attractionList.add(new Attraction("Sunset Beach Tour", "Enjoy a relaxing evening tour along the coast.", R.drawable.beach_tour));
        attractionList.add(new Attraction("Aqua Sports Adventure", "Experience thrilling water sports activities.", R.drawable.water_sports));
        attractionList.add(new Attraction("LuxeVista Fine Dining", "Special discount for in-house guests.", R.drawable.fine_dining));
        attractionList.add(new Attraction("Nearby Historical Site", "Explore the rich history of the region.", 0)); // No image for this one

        attractionAdapter = new AttractionAdapter(attractionList);
        recyclerViewAttractions.setAdapter(attractionAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.navigation_rooms) {
                intent = new Intent(AttractionsActivity.this, RoomListActivity.class);
            } else if (item.getItemId() == R.id.navigation_services) {
                intent = new Intent(AttractionsActivity.this, ServiceListActivity.class);
            } else if (item.getItemId() == R.id.navigation_bookings) {
                intent = new Intent(AttractionsActivity.this, MyBookingsActivity.class);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(AttractionsActivity.this, ProfileActivity.class);
            } else if (item.getItemId() == R.id.navigation_attractions) {
                return true; // Already on this screen
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