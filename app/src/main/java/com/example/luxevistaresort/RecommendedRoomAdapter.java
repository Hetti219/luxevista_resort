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

public class RecommendedRoomAdapter extends RecyclerView.Adapter<RecommendedRoomAdapter.ViewHolder> {

    private Context context;
    private List<Room> recommendedRooms;

    public RecommendedRoomAdapter(Context context, List<Room> recommendedRooms) {
        this.context = context;
        this.recommendedRooms = recommendedRooms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommended, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = recommendedRooms.get(position);
        holder.textViewRecommendedItemName.setText(room.getRoomType());
        holder.imageViewRecommendedItem.setImageResource(room.getImageResourceId());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RoomDetailsActivity.class);
            intent.putExtra("ROOM_INDEX", RoomListActivity.roomList.indexOf(room)); // Pass the index to fetch details
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendedRooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewRecommendedItem;
        TextView textViewRecommendedItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRecommendedItem = itemView.findViewById(R.id.imageViewRecommendedItem);
            textViewRecommendedItemName = itemView.findViewById(R.id.textViewRecommendedItemName);
        }
    }
}