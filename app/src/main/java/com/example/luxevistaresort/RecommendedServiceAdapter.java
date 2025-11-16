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
    private List<Service> allServices;

    public RecommendedServiceAdapter(Context context, List<Service> recommendedServices, List<Service> allServices) {
        this.context = context;
        this.recommendedServices = recommendedServices;
        this.allServices = allServices;
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

        // Lambda expression for click listener - pass index directly instead of using indexOf
        holder.itemView.setOnClickListener(v -> {
            // Since recommended services are the first N services, position is the same as index in allServices
            Intent intent = new Intent(context, ServiceDetailsActivity.class);
            intent.putExtra(Constants.EXTRA_SERVICE_INDEX, position);
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