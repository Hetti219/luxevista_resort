package com.example.luxevistaresort;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMyBookings;
    private BookingAdapter bookingAdapter;
    private List<Booking> myBookingList;
    private SharedPreferences bookingPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        recyclerViewMyBookings = findViewById(R.id.recyclerViewMyBookings);
        recyclerViewMyBookings.setLayoutManager(new LinearLayoutManager(this));

        myBookingList = new ArrayList<>();
        bookingPreferences = getSharedPreferences("service_bookings", MODE_PRIVATE); // Using the same preferences for both room and service bookings for simplicity

        // Retrieve booking data from SharedPreferences
        Map<String, ?> allBookings = bookingPreferences.getAll();
        for (Map.Entry<String, ?> entry : allBookings.entrySet()) {
            if (entry.getKey().startsWith("booking_")) {
                String bookingString = (String) entry.getValue();
                // Simple parsing of the toString() output - consider a better approach (like Gson) for real apps
                String[] parts = bookingString.split("[{}, '=]");
                String bookingType = null;
                String itemName = null;
                String dateTime = null;
                String confirmationId = null;

                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].trim().equals("bookingType")) {
                        bookingType = parts[i + 1].trim().replace("'", "");
                    } else if (parts[i].trim().equals("itemName")) {
                        itemName = parts[i + 1].trim().replace("'", "");
                    } else if (parts[i].trim().equals("dateTime")) {
                        dateTime = parts[i + 1].trim().replace("'", "");
                        if (dateTime.equals("null")) dateTime = null;
                    } else if (parts[i].trim().equals("confirmationId")) {
                        confirmationId = parts[i + 1].trim().replace("'", "");
                    }
                }

                if (bookingType != null && itemName != null && confirmationId != null) {
                    myBookingList.add(new Booking(bookingType, itemName, dateTime, confirmationId));
                }
            }
        }

        bookingAdapter = new BookingAdapter(myBookingList);
        recyclerViewMyBookings.setAdapter(bookingAdapter);
    }
}