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

        bookingAdapter = new BookingAdapter(myBookingList);
        recyclerViewMyBookings.setAdapter(bookingAdapter);
    }
}