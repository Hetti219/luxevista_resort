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
    private List<Room> allRooms;

    public RecommendedRoomAdapter(Context context, List<Room> recommendedRooms, List<Room> allRooms) {
        this.context = context;
        this.recommendedRooms = recommendedRooms;
        this.allRooms = allRooms;
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

        // Lambda expression for click listener - pass index directly instead of using indexOf
        holder.itemView.setOnClickListener(v -> {
            // Since recommended rooms are the first N rooms, position is the same as index in allRooms
            Intent intent = new Intent(context, RoomDetailsActivity.class);
            intent.putExtra(Constants.EXTRA_ROOM_INDEX, position);
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