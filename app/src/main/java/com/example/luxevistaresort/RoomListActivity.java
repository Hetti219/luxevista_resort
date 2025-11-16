package com.example.luxevistaresort;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomListActivity extends BaseActivity {

    private RecyclerView recyclerViewRooms;
    private RoomAdapter roomAdapter;
    private List<Room> displayedRoomList;
    private Spinner spinnerRoomTypeFilter;
    private EditText editTextMaxPrice;
    private Button buttonApplyFilters;
    private List<Room> originalRoomList;
    private Spinner spinnerSortBy;
    private BottomNavigationView bottomNavigationView;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        // Initialize repository
        dataRepository = DataRepository.getInstance();

        // Initialize views
        initializeViews();

        // Get rooms from repository
        originalRoomList = new ArrayList<>(dataRepository.getRoomList());
        displayedRoomList = new ArrayList<>(originalRoomList);

        // Setup spinners
        setupSpinners();

        // Set up the adapter
        roomAdapter = new RoomAdapter(this, displayedRoomList);
        recyclerViewRooms.setAdapter(roomAdapter);

        // Set click listeners
        setupListeners();

        // Setup bottom navigation
        setupBottomNavigation(bottomNavigationView, R.id.navigation_rooms);
    }

    private void initializeViews() {
        recyclerViewRooms = findViewById(R.id.recyclerViewRooms);
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        spinnerRoomTypeFilter = findViewById(R.id.spinnerRoomTypeFilter);
        editTextMaxPrice = findViewById(R.id.editTextMaxPrice);
        buttonApplyFilters = findViewById(R.id.buttonApplyFilters);
        spinnerSortBy = findViewById(R.id.spinnerSortBy);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void setupSpinners() {
        // Populate the room type filter spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"All", "Ocean View Suite", "Deluxe Room", "Standard Double"});
        spinnerRoomTypeFilter.setAdapter(typeAdapter);

        // Populate the sort by spinner
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Default", "Price: Low to High", "Price: High to Low", "Availability"});
        spinnerSortBy.setAdapter(sortAdapter);
    }

    private void setupListeners() {
        // Lambda expression for apply filters button
        buttonApplyFilters.setOnClickListener(v -> applyFilters());

        // Lambda expression for sort spinner
        spinnerSortBy.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                sortRooms(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                // Do nothing
            }
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

        displayedRoomList.clear();
        displayedRoomList.addAll(filteredList);
        sortRooms(spinnerSortBy.getSelectedItem().toString());
    }

    private void sortRooms(String sortBy) {
        switch (sortBy) {
            case "Price: Low to High":
                displayedRoomList.sort(Comparator.comparingDouble(Room::getPricePerNight));
                break;
            case "Price: High to Low":
                displayedRoomList.sort(Comparator.comparingDouble(Room::getPricePerNight).reversed());
                break;
            case "Availability":
                displayedRoomList.sort(Comparator.comparing(Room::isAvailable).reversed());
                break;
            default:
                break;
        }
        roomAdapter.notifyDataSetChanged();
    }
}