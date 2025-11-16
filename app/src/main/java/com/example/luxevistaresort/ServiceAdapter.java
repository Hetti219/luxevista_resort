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

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;
    private Context context;

    public ServiceAdapter(Context context, List<Service> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service currentService = serviceList.get(position);
        holder.textViewServiceName.setText(currentService.getName());
        holder.textViewServicePrice.setText("$" + String.format("%.2f", currentService.getPrice()));
        holder.textViewServiceCategory.setText("Category: " + currentService.getCategory());
        holder.imageViewService.setImageResource(currentService.getImageResourceId());

        // Lambda expression for click listener
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ServiceDetailsActivity.class);
            intent.putExtra(Constants.EXTRA_SERVICE_INDEX, position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewService;
        public TextView textViewServiceName;
        public TextView textViewServicePrice;
        public TextView textViewServiceCategory;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewService = itemView.findViewById(R.id.imageViewService);
            textViewServiceName = itemView.findViewById(R.id.textViewServiceName);
            textViewServicePrice = itemView.findViewById(R.id.textViewServicePrice);
            textViewServiceCategory = itemView.findViewById(R.id.textViewServiceCategory);
        }
    }
}