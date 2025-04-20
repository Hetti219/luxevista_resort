package com.example.luxevistaresort;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;
    private Context context;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room currentRoom = roomList.get(position);
        holder.textViewRoomType.setText(currentRoom.getRoomType());
        holder.textViewPrice.setText("$" + String.format("%.2f", currentRoom.getPricePerNight()));
        holder.textViewAvailability.setText(currentRoom.isAvailable() ? "Available" : "Not Available");
        holder.imageViewRoom.setImageResource(currentRoom.getImageResourceId());

        // Set OnClickListener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start RoomDetailsActivity
                Intent intent = new Intent(context, RoomDetailsActivity.class);
                // Pass the position of the clicked item as an extra
                intent.putExtra("ROOM_INDEX", position);
                // Start the activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewRoom;
        public TextView textViewRoomType;
        public TextView textViewPrice;
        public TextView textViewAvailability;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRoom = itemView.findViewById(R.id.imageViewRoom);
            textViewRoomType = itemView.findViewById(R.id.textViewRoomType);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewAvailability = itemView.findViewById(R.id.textViewAvailability);
        }
    }
}