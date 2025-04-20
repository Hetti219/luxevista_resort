package com.example.luxevistaresort;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoomDetailsActivity extends AppCompatActivity {

    private ImageView imageViewRoomDetails;
    private TextView textViewRoomTypeDetails;
    private TextView textViewPriceDetails;
    private TextView textViewDescriptionDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // Initialize views
        imageViewRoomDetails = findViewById(R.id.imageViewRoomDetails);
        textViewRoomTypeDetails = findViewById(R.id.textViewRoomTypeDetails);
        textViewPriceDetails = findViewById(R.id.textViewPriceDetails);
        textViewDescriptionDetails = findViewById(R.id.textViewDescriptionDetails);

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
        } else {
            // Handle error if the room index is invalid
            finish(); // Go back to the previous activity
        }
    }
}