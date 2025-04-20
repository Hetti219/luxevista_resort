package com.example.luxevistaresort;

public class Room {
    private String roomType;
    private String description;
    private double pricePerNight;
    private boolean isAvailable;
    private int imageResourceId; // To hold the reference to the room's image in our resources

    public Room(String roomType, String description, double pricePerNight, boolean isAvailable, int imageResourceId) {
        this.roomType = roomType;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.imageResourceId = imageResourceId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // You can add setter methods if you plan to modify room details later
}