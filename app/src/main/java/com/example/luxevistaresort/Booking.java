package com.example.luxevistaresort;

public class Booking {
    private String bookingType; // "Room" or "Service"
    private String itemName;
    private String dateTime; // Can be null for immediate bookings
    private String confirmationId;

    public Booking(String bookingType, String itemName, String dateTime, String confirmationId) {
        this.bookingType = bookingType;
        this.itemName = itemName;
        this.dateTime = dateTime;
        this.confirmationId = confirmationId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    // Optional: Override toString() for easier debugging
    @Override
    public String toString() {
        return "Booking{" +
                "bookingType='" + bookingType + '\'' +
                ", itemName='" + itemName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", confirmationId='" + confirmationId + '\'' +
                '}';
    }
}