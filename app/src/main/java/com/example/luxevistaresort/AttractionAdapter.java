package com.example.luxevistaresort;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    private List<Attraction> attractionList;

    public AttractionAdapter(List<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attraction, parent, false);
        return new AttractionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        Attraction currentAttraction = attractionList.get(position);
        holder.textViewAttractionName.setText(currentAttraction.getName());
        holder.textViewAttractionDescription.setText(currentAttraction.getDescription());

        if (currentAttraction.getImageResourceId() != 0) {
            holder.imageViewAttraction.setImageResource(currentAttraction.getImageResourceId());
            holder.imageViewAttraction.setVisibility(View.VISIBLE);
        } else {
            holder.imageViewAttraction.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    public static class AttractionViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewAttractionName;
        public TextView textViewAttractionDescription;
        public ImageView imageViewAttraction;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAttractionName = itemView.findViewById(R.id.textViewAttractionName);
            textViewAttractionDescription = itemView.findViewById(R.id.textViewAttractionDescription);
            imageViewAttraction = itemView.findViewById(R.id.imageViewAttraction);
        }
    }
}