package com.example.luxevistaresort;

import java.util.UUID;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RoomDetailsActivity extends AppCompatActivity {

    private ImageView imageViewRoomDetails;
    private TextView textViewRoomTypeDetails;
    private TextView textViewPriceDetails;
    private TextView textViewDescriptionDetails;
    private Button buttonBookNow;
    private SharedPreferences bookingPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // Initialize views
        imageViewRoomDetails = findViewById(R.id.imageViewRoomDetails);
        textViewRoomTypeDetails = findViewById(R.id.textViewRoomTypeDetails);
        textViewPriceDetails = findViewById(R.id.textViewPriceDetails);
        textViewDescriptionDetails = findViewById(R.id.textViewDescriptionDetails);
        buttonBookNow = findViewById(R.id.buttonBookNow);

        // Initialize SharedPreferences for bookings (for this example)
        bookingPreferences = getSharedPreferences("room_bookings", MODE_PRIVATE);

        // Get the room index passed from RoomListActivity
        int roomIndex = getIntent().getIntExtra("ROOM_INDEX", -1);

        // Fetch the selected room from the list (for now, we'll use the same static list)
        if (roomIndex >= 0 && roomIndex < RoomListActivity.roomList.size()) {
            Room selectedRoom = RoomListActivity.roomList.get(roomIndex);

            // Populate the views with the room details
            imageViewRoomDetails.setImageResource(selectedRoom.getImageResourceId());
            textViewRoomTypeDetails.setText(selectedRoom.getRoomType());
            textViewPriceDetails.setText("$" + String.format("%.2f", selectedRoom.getPricePerNight()));
            textViewDescriptionDetails.setText(selectedRoom.getDescription());


            buttonBookNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String roomType = selectedRoom.getRoomType();
                    String confirmationId = UUID.randomUUID().toString().substring(0, 8); // Generate a short unique ID

                    // Create a Booking object
                    Booking booking = new Booking("Room", roomType, null, confirmationId);

                    // Store the booking information (using SharedPreferences for now)
                    SharedPreferences.Editor editor = bookingPreferences.edit();
                    editor.putString("booking_" + confirmationId, booking.toString()); // Store as a string for simplicity
                    editor.apply();

                    // Display confirmation message
                    Toast.makeText(RoomDetailsActivity.this, roomType + " booked! Confirmation ID: " + confirmationId, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle error if the room index is invalid
            finish(); // Go back to the previous activity
        }
    }
}