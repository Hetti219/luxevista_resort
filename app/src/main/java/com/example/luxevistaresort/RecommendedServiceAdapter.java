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

public class RecommendedServiceAdapter extends RecyclerView.Adapter<RecommendedServiceAdapter.ViewHolder> {

    private Context context;
    private List<Service> recommendedServices;

    public RecommendedServiceAdapter(Context context, List<Service> recommendedServices) {
        this.context = context;
        this.recommendedServices = recommendedServices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommended, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Service service = recommendedServices.get(position);
        holder.textViewRecommendedItemName.setText(service.getName());
        holder.imageViewRecommendedItem.setImageResource(service.getImageResourceId());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ServiceDetailsActivity.class);
            intent.putExtra("SERVICE_INDEX", ServiceListActivity.serviceList.indexOf(service)); // Pass the index
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendedServices.size();
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