package com.example.luxevistaresort;

public class Service {
    private String name;
    private String description;
    private double price;
    private String category; // e.g., "Spa", "Dining", "Activity", "Cabana"
    private String availability; // For now, a simple string like "Daily 9 AM - 6 PM"
    private String duration; // e.g., "60 minutes", "2 hours", "Varies"
    private int imageResourceId;

    public Service(String name, String description, double price, String category, String availability, String duration, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.availability = availability;
        this.duration = duration;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getAvailability() {
        return availability;
    }

    public String getDuration() {
        return duration;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // You can add setter methods if needed later
}