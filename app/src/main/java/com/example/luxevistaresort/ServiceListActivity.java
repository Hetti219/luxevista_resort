package com.example.luxevistaresort;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ServiceListActivity extends BaseActivity {

    private RecyclerView recyclerViewServices;
    private List<Service> serviceList;
    private ServiceAdapter serviceAdapter;
    private BottomNavigationView bottomNavigationView;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        // Initialize repository
        dataRepository = DataRepository.getInstance();

        // Initialize views
        recyclerViewServices = findViewById(R.id.recyclerViewServices);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Get services from repository
        serviceList = dataRepository.getServiceList();

        // Setup adapter
        serviceAdapter = new ServiceAdapter(this, serviceList);
        recyclerViewServices.setAdapter(serviceAdapter);

        // Setup bottom navigation
        setupBottomNavigation(bottomNavigationView, R.id.navigation_services);
    }
}