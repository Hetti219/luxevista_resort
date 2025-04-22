package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerViewRecommendedRooms;
    private RecommendedRoomAdapter recommendedRoomAdapter;
    private RecyclerView recyclerViewRecommendedServices;
    private RecommendedServiceAdapter recommendedServiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);


        // Initialize Recommended Rooms RecyclerView
        recyclerViewRecommendedRooms = findViewById(R.id.recyclerViewRecommendedRooms);
        LinearLayoutManager layoutManagerRooms = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommendedRooms.setLayoutManager(layoutManagerRooms);
        List<Room> recommendedRooms = getRecommendedRooms(); // Implement this method
        recommendedRoomAdapter = new RecommendedRoomAdapter(this, recommendedRooms);
        recyclerViewRecommendedRooms.setAdapter(recommendedRoomAdapter);

        // Initialize Recommended Services RecyclerView
        recyclerViewRecommendedServices = findViewById(R.id.recyclerViewRecommendedServices);
        LinearLayoutManager layoutManagerServices = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommendedServices.setLayoutManager(layoutManagerServices);
        List<Service> recommendedServices = getRecommendedServices(); // Implement this method
        recommendedServiceAdapter = new RecommendedServiceAdapter(this, recommendedServices);
        recyclerViewRecommendedServices.setAdapter(recommendedServiceAdapter);

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
            } else if (item.getItemId() == R.id.navigation_attractions) {
                intent = new Intent(MainActivity.this, AttractionsActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    // Dummy method to get recommended rooms
    private List<Room> getRecommendedRooms() {
        List<Room> allRooms = RoomListActivity.roomList;
        if (allRooms != null) {
            Log.d("MainActivity", "Number of rooms available: " + allRooms.size());
            if (allRooms.size() >= 2) {
                List<Room> recommendations = allRooms.subList(0, Math.min(2, allRooms.size()));
                Log.d("MainActivity", "Recommended rooms count: " + recommendations.size());
                return recommendations;
            }
        } else {
            Log.d("MainActivity", "Room list is null");
        }
        return new ArrayList<>();
    }

    private List<Service> getRecommendedServices() {
        List<Service> allServices = ServiceListActivity.serviceList;
        if (allServices != null) {
            Log.d("MainActivity", "Number of services available: " + allServices.size());
            if (allServices.size() >= 2) {
                List<Service> recommendations = allServices.subList(0, Math.min(2, allServices.size()));
                Log.d("MainActivity", "Recommended services count: " + recommendations.size());
                return recommendations;
            }
        } else {
            Log.d("MainActivity", "Service list is null");
        }
        return new ArrayList<>();
    }
}