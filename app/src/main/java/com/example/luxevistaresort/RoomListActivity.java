package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRooms;
    private RoomAdapter roomAdapter;
    public static List<Room> roomList;
    private Spinner spinnerRoomTypeFilter;
    private EditText editTextMaxPrice;
    private Button buttonApplyFilters;
    private List<Room> originalRoomList;
    private Spinner spinnerSortBy;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        // Initialize views
        recyclerViewRooms = findViewById(R.id.recyclerViewRooms);
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        spinnerRoomTypeFilter = findViewById(R.id.spinnerRoomTypeFilter);
        editTextMaxPrice = findViewById(R.id.editTextMaxPrice);
        buttonApplyFilters = findViewById(R.id.buttonApplyFilters);
        spinnerSortBy = findViewById(R.id.spinnerSortBy);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set the currently active item in the BottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.navigation_rooms);

        // Create a list of sample rooms
        roomList = new ArrayList<>();
        roomList.add(new Room("Ocean View Suite", "Luxurious suite with stunning ocean views.", 250.00, true, R.drawable.ocean_suite));
        roomList.add(new Room("Deluxe Room", "Comfortable room with a king-size bed.", 150.00, true, R.drawable.deluxe_room));
        roomList.add(new Room("Standard Double", "Standard room with two double beds.", 120.00, false, R.drawable.standard_double));
        roomList.add(new Room("Ocean View Suite", "Another ocean view suite.", 280.00, true, R.drawable.ocean_suite));
        roomList.add(new Room("Deluxe Room", "A cozy deluxe room.", 160.00, true, R.drawable.deluxe_room));

        originalRoomList = new ArrayList<>(roomList);

        // Populate the room type filter spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"All", "Ocean View Suite", "Deluxe Room", "Standard Double"});
        spinnerRoomTypeFilter.setAdapter(typeAdapter);

        // Populate the sort by spinner
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Default", "Price: Low to High", "Price: High to Low", "Availability"});
        spinnerSortBy.setAdapter(sortAdapter);

        // Set up the initial adapter
        roomAdapter = new RoomAdapter(this, roomList);
        recyclerViewRooms.setAdapter(roomAdapter);

        // Set click listener for the apply filters button
        buttonApplyFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilters();
            }
        });

        // Set item selected listener for the sort by spinner
        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortRooms(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.navigation_rooms) {
                // Do nothing, already on this screen
                return true;
            } else if (item.getItemId() == R.id.navigation_services) {
                intent = new Intent(RoomListActivity.this, ServiceListActivity.class);
            } else if (item.getItemId() == R.id.navigation_bookings) {
                intent = new Intent(RoomListActivity.this, MyBookingsActivity.class);
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(RoomListActivity.this, ProfileActivity.class);
            } else if (item.getItemId() == R.id.navigation_attractions) {
                intent = new Intent(RoomListActivity.this, AttractionsActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                finish(); // Optional: finish the current activity to prevent going back to it
                return true;
            }
            return false;
        });
    }

    private void applyFilters() {
        String selectedRoomType = spinnerRoomTypeFilter.getSelectedItem().toString();
        String maxPriceStr = editTextMaxPrice.getText().toString().trim();
        double maxPrice = Double.MAX_VALUE;

        if (!TextUtils.isEmpty(maxPriceStr)) {
            try {
                maxPrice = Double.parseDouble(maxPriceStr);
            } catch (NumberFormatException e) {
                editTextMaxPrice.setError("Invalid price");
                return;
            }
        }

        double finalMaxPrice = maxPrice;
        List<Room> filteredList = originalRoomList.stream()
                .filter(room -> (selectedRoomType.equals("All") || room.getRoomType().equals(selectedRoomType)))
                .filter(room -> room.getPricePerNight() <= finalMaxPrice)
                .collect(Collectors.toList());

        roomList.clear();
        roomList.addAll(filteredList);
        sortRooms(spinnerSortBy.getSelectedItem().toString()); // Apply current sort after filtering
    }

    private void sortRooms(String sortBy) {
        switch (sortBy) {
            case "Price: Low to High":
                roomList.sort(Comparator.comparingDouble(Room::getPricePerNight));
                break;
            case "Price: High to Low":
                roomList.sort(Comparator.comparingDouble(Room::getPricePerNight).reversed());
                break;
            case "Availability":
                roomList.sort(Comparator.comparing(Room::isAvailable).reversed()); // Available rooms first
                break;
            default: // "Default" or any other case, revert to the original order after filtering
                break;
        }
        roomAdapter.notifyDataSetChanged();
    }
}