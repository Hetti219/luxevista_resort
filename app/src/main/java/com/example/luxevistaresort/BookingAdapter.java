package com.example.luxevistaresort;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking currentBooking = bookingList.get(position);
        holder.textViewBookingType.setText(currentBooking.getBookingType() + " Booking");
        holder.textViewItemName.setText(currentBooking.getItemName());
        String dateTime = currentBooking.getDateTime();
        if (dateTime != null && !dateTime.isEmpty()) {
            holder.textViewBookingDateTime.setText("Date & Time: " + dateTime);
        } else {
            holder.textViewBookingDateTime.setText("Date & Time: Not Applicable");
        }
        holder.textViewConfirmationId.setText("Confirmation ID: " + currentBooking.getConfirmationId());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBookingType;
        public TextView textViewItemName;
        public TextView textViewBookingDateTime;
        public TextView textViewConfirmationId;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBookingType = itemView.findViewById(R.id.textViewBookingType);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewBookingDateTime = itemView.findViewById(R.id.textViewBookingDateTime);
            textViewConfirmationId = itemView.findViewById(R.id.textViewConfirmationId);
        }
    }
}