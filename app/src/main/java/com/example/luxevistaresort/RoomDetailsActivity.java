package com.example.luxevistaresort;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class RoomDetailsActivity extends AppCompatActivity {

    private ImageView imageViewRoomDetails;
    private TextView textViewRoomTypeDetails;
    private TextView textViewRoomPriceDetails;
    private TextView textViewRoomDescriptionDetails;
    private Button buttonBookNow;
    private SharedPreferences roomPreferences;
    private TextView textViewCheckInDate;
    private Button buttonPickCheckInDate;
    private TextView textViewCheckOutDate;
    private Button buttonPickCheckOutDate;
    private int checkInYear, checkInMonth, checkInDay;
    private int checkOutYear, checkOutMonth, checkOutDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // Initialize views
        imageViewRoomDetails = findViewById(R.id.imageViewRoomDetails);
        textViewRoomTypeDetails = findViewById(R.id.textViewRoomTypeDetails);
        textViewRoomPriceDetails = findViewById(R.id.textViewPriceDetails);
        textViewRoomDescriptionDetails = findViewById(R.id.textViewDescriptionDetails);
        buttonBookNow = findViewById(R.id.buttonBookNow);
        textViewCheckInDate = findViewById(R.id.textViewCheckInDate);
        buttonPickCheckInDate = findViewById(R.id.buttonPickCheckInDate);
        textViewCheckOutDate = findViewById(R.id.textViewCheckOutDate);
        buttonPickCheckOutDate = findViewById(R.id.buttonPickCheckOutDate);


        // Initialize SharedPreferences for room bookings
        roomPreferences = getSharedPreferences("room_bookings", MODE_PRIVATE);

        // Get the selected room index passed from RoomListActivity
        int roomIndex = getIntent().getIntExtra(Constants.EXTRA_ROOM_INDEX, -1);

        // Fetch the selected room from the repository
        DataRepository dataRepository = DataRepository.getInstance();
        Room selectedRoom = dataRepository.getRoomAt(roomIndex);

        if (selectedRoom != null) {

            // Populate the views with the room details
            imageViewRoomDetails.setImageResource(selectedRoom.getImageResourceId());
            textViewRoomTypeDetails.setText(selectedRoom.getRoomType());
            textViewRoomPriceDetails.setText("$" + String.format("%.2f", selectedRoom.getPricePerNight()));
            textViewRoomDescriptionDetails.setText(selectedRoom.getDescription());

            // Lambda expression for Pick Check-in Date button
            buttonPickCheckInDate.setOnClickListener(v -> {
                // Get current date
                final Calendar c = Calendar.getInstance();
                checkInYear = c.get(Calendar.YEAR);
                checkInMonth = c.get(Calendar.MONTH);
                checkInDay = c.get(Calendar.DAY_OF_MONTH);

                // Create and show the DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(RoomDetailsActivity.this,
                        (view, year, monthOfYear, dayOfMonth) -> {
                            // Update the selected date TextView
                            RoomDetailsActivity.this.checkInYear = year;
                            RoomDetailsActivity.this.checkInMonth = monthOfYear;
                            RoomDetailsActivity.this.checkInDay = dayOfMonth;
                            textViewCheckInDate.setText(String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                        }, checkInYear, checkInMonth, checkInDay);
                datePickerDialog.show();
            });

            // Lambda expression for Pick Check-out Date button
            buttonPickCheckOutDate.setOnClickListener(v -> {
                // Get current date
                final Calendar c = Calendar.getInstance();
                checkOutYear = c.get(Calendar.YEAR);
                checkOutMonth = c.get(Calendar.MONTH);
                checkOutDay = c.get(Calendar.DAY_OF_MONTH);

                // Create and show the DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(RoomDetailsActivity.this,
                        (view, year, monthOfYear, dayOfMonth) -> {
                            // Update the selected date TextView
                            RoomDetailsActivity.this.checkOutYear = year;
                            RoomDetailsActivity.this.checkOutMonth = monthOfYear;
                            RoomDetailsActivity.this.checkOutDay = dayOfMonth;
                            textViewCheckOutDate.setText(String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth));
                        }, checkOutYear, checkOutMonth, checkOutDay);
                datePickerDialog.show();
            });

            // Lambda expression for Book Now button
            buttonBookNow.setOnClickListener(v -> {
                String roomType = selectedRoom.getRoomType();
                String checkInDate = textViewCheckInDate.getText().toString();
                String checkOutDate = textViewCheckOutDate.getText().toString();

                if (checkInDate.equals("Not selected") || checkOutDate.equals("Not selected")) {
                    Toast.makeText(RoomDetailsActivity.this, "Please select check-in and check-out dates", Toast.LENGTH_SHORT).show();
                    return;
                }

                String bookingDateTime = "Check-in: " + checkInDate + ", Check-out: " + checkOutDate;
                String confirmationId = UUID.randomUUID().toString().substring(0, 8);

                // Create a Booking object
                Booking booking = new Booking("Room", roomType, bookingDateTime, confirmationId);

                // Store the booking information
                SharedPreferences.Editor editor = roomPreferences.edit();
                editor.putString("booking_" + confirmationId, booking.toString());
                editor.apply();

                // Display confirmation message
                Toast.makeText(RoomDetailsActivity.this, roomType + " booked! " + bookingDateTime + " Confirmation ID: " + confirmationId, Toast.LENGTH_LONG).show();
            });


        } else {
            // Handle error if the room index is invalid
            finish(); // Go back to the previous activity
        }
    }
}