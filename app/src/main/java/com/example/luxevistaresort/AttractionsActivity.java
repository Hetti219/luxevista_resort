package com.example.luxevistaresort;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AttractionsActivity extends BaseActivity {

    private RecyclerView recyclerViewAttractions;
    private AttractionAdapter attractionAdapter;
    private List<Attraction> attractionList;
    private BottomNavigationView bottomNavigationView;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        // Initialize repository
        dataRepository = DataRepository.getInstance();

        // Initialize views
        recyclerViewAttractions = findViewById(R.id.recyclerViewAttractions);
        recyclerViewAttractions.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Get attractions from repository
        attractionList = dataRepository.getAttractionList();

        // Setup adapter
        attractionAdapter = new AttractionAdapter(attractionList);
        recyclerViewAttractions.setAdapter(attractionAdapter);

        // Setup bottom navigation
        setupBottomNavigation(bottomNavigationView, R.id.navigation_attractions);
    }
}