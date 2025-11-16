package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerViewRecommendedRooms;
    private RecommendedRoomAdapter recommendedRoomAdapter;
    private RecyclerView recyclerViewRecommendedServices;
    private RecommendedServiceAdapter recommendedServiceAdapter;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize repository
        dataRepository = DataRepository.getInstance();

        // Initialize views
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPreferences = getSharedPreferences(Constants.PREFS_USER_DATA, MODE_PRIVATE);

        // Initialize Recommended Rooms RecyclerView
        setupRecommendedRooms();

        // Initialize Recommended Services RecyclerView
        setupRecommendedServices();

        // Setup navigation - MainActivity doesn't have a selected item in bottom nav
        setupBottomNavigationForHome();
    }

    private void setupRecommendedRooms() {
        recyclerViewRecommendedRooms = findViewById(R.id.recyclerViewRecommendedRooms);
        LinearLayoutManager layoutManagerRooms = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommendedRooms.setLayoutManager(layoutManagerRooms);
        List<Room> recommendedRooms = dataRepository.getRecommendedRooms(Constants.MAX_RECOMMENDED_ITEMS);
        recommendedRoomAdapter = new RecommendedRoomAdapter(this, recommendedRooms, dataRepository.getRoomList());
        recyclerViewRecommendedRooms.setAdapter(recommendedRoomAdapter);
    }

    private void setupRecommendedServices() {
        recyclerViewRecommendedServices = findViewById(R.id.recyclerViewRecommendedServices);
        LinearLayoutManager layoutManagerServices = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommendedServices.setLayoutManager(layoutManagerServices);
        List<Service> recommendedServices = dataRepository.getRecommendedServices(Constants.MAX_RECOMMENDED_ITEMS);
        recommendedServiceAdapter = new RecommendedServiceAdapter(this, recommendedServices, dataRepository.getServiceList());
        recyclerViewRecommendedServices.setAdapter(recommendedServiceAdapter);
    }

    private void setupBottomNavigationForHome() {
        if (bottomNavigationView == null) {
            return;
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_rooms) {
                intent = new Intent(MainActivity.this, RoomListActivity.class);
            } else if (itemId == R.id.navigation_services) {
                intent = new Intent(MainActivity.this, ServiceListActivity.class);
            } else if (itemId == R.id.navigation_bookings) {
                intent = new Intent(MainActivity.this, MyBookingsActivity.class);
            } else if (itemId == R.id.navigation_profile) {
                intent = new Intent(MainActivity.this, ProfileActivity.class);
            } else if (itemId == R.id.navigation_attractions) {
                intent = new Intent(MainActivity.this, AttractionsActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}