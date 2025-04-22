package com.example.luxevistaresort;

public class Attraction {
    private String name;
    private String description;
    private int imageResourceId; // Optional: Image for the attraction/offer

    public Attraction(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}