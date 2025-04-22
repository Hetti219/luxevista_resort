package com.example.luxevistaresort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMyBookings;
    private BookingAdapter bookingAdapter;
    private List<Booking> myBookingList;
    private SharedPreferences serviceBookingPreferences;
    private SharedPreferences roomBookingPreferences;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        recyclerViewMyBookings = findViewById(R.id.recyclerViewMyBookings);
        recyclerViewMyBookings.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.navigation_bookings);

        myBookingList = new ArrayList<>();
        serviceBookingPreferences = getSharedPreferences("service_bookings", MODE_PRIVATE);
        roomBookingPreferences = getSharedPreferences("room_bookings", MODE_PRIVATE);

        // Retrieve service booking data from SharedPreferences
        Map<String, ?> allServiceBookings = serviceBookingPreferences.getAll();
        for (Map.Entry<String, ?> entry : allServiceBookings.entrySet()) {
            if (entry.getKey().startsWith("booking_")) {
                addBookingToList(entry.getValue());
            }
        }

        // Retrieve room booking data from SharedPreferences
        Map<String, ?> allRoomBookings = roomBookingPreferences.getAll();
        for (Map.Entry<String, ?> entry : allRoomBookings.entrySet()) {
            if (entry.getKey().startsWith("booking_")) {
                addBookingToList(entry.getValue());
            }
        }

        bookingAdapter = new BookingAdapter(myBookingList);
        recyclerViewMyBookings.setAdapter(bookingAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;
            if (item.getItemId() == R.id.navigation_rooms) {
                intent = new Intent(MyBookingsActivity.this, RoomListActivity.class);
            } else if (item.getItemId() == R.id.navigation_services) {
                intent = new Intent(MyBookingsActivity.this, ServiceListActivity.class);
            } else if (item.getItemId() == R.id.navigation_bookings) {
                return true; // Do nothing, already on this screen
            } else if (item.getItemId() == R.id.navigation_profile) {
                intent = new Intent(MyBookingsActivity.this, ProfileActivity.class);
            } else if (item.getItemId() == R.id.navigation_attractions) {
                intent = new Intent(MyBookingsActivity.this, AttractionsActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }

    private void addBookingToList(Object bookingValue) {
        if (bookingValue instanceof String) {
            String bookingString = (String) bookingValue;
            String bookingType = null;
            String itemName = null;
            String dateTime = null;
            String confirmationId = null;

            int bookingTypeIndex = bookingString.indexOf("bookingType='");
            if (bookingTypeIndex != -1) {
                int startIndex = bookingTypeIndex + "bookingType='".length();
                int endIndex = bookingString.indexOf("'", startIndex);
                if (endIndex != -1) {
                    bookingType = bookingString.substring(startIndex, endIndex);
                }
            }

            int itemNameIndex = bookingString.indexOf("itemName='");
            if (itemNameIndex != -1) {
                int startIndex = itemNameIndex + "itemName='".length();
                int endIndex = bookingString.indexOf("'", startIndex);
                if (endIndex != -1) {
                    itemName = bookingString.substring(startIndex, endIndex);
                }
            }

            int dateTimeIndex = bookingString.indexOf("dateTime='");
            if (dateTimeIndex != -1) {
                int startIndex = dateTimeIndex + "dateTime='".length();
                int endIndex = bookingString.indexOf("'", startIndex);
                if (endIndex != -1) {
                    dateTime = bookingString.substring(startIndex, endIndex);
                    if (dateTime.equals("null")) dateTime = null;
                }
            }

            int confirmationIdIndex = bookingString.indexOf("confirmationId='");
            if (confirmationIdIndex != -1) {
                int startIndex = confirmationIdIndex + "confirmationId='".length();
                int endIndex = bookingString.indexOf("'", startIndex);
                if (endIndex != -1) {
                    confirmationId = bookingString.substring(startIndex, endIndex);
                }
            }

            if (bookingType != null && itemName != null && confirmationId != null) {
                myBookingList.add(new Booking(bookingType, itemName, dateTime, confirmationId));
            }
        }
    }
}