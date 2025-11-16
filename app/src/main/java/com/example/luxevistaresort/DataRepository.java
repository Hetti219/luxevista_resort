package com.example.luxevistaresort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton repository for managing application data.
 * Replaces static lists in activities for better memory management and data encapsulation.
 */
public class DataRepository {
    private static DataRepository instance;
    private List<Room> roomList;
    private List<Service> serviceList;
    private List<Attraction> attractionList;

    private DataRepository() {
        initializeRooms();
        initializeServices();
        initializeAttractions();
    }

    public static synchronized DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    private void initializeRooms() {
        roomList = new ArrayList<>();
        roomList.add(new Room("Ocean View Suite", "Luxurious suite with stunning ocean views.", 250.00, true, R.drawable.ocean_suite));
        roomList.add(new Room("Deluxe Room", "Comfortable room with a king-size bed.", 150.00, true, R.drawable.deluxe_room));
        roomList.add(new Room("Standard Double", "Standard room with two double beds.", 120.00, false, R.drawable.standard_double));
        roomList.add(new Room("Ocean View Suite", "Another ocean view suite.", 280.00, true, R.drawable.ocean_suite));
        roomList.add(new Room("Deluxe Room", "A cozy deluxe room.", 160.00, true, R.drawable.deluxe_room));
    }

    private void initializeServices() {
        serviceList = new ArrayList<>();
        serviceList.add(new Service("Aromatherapy Massage", "Relaxing full-body massage with essential oils.", 75.00, "Spa", "Daily 10 AM - 7 PM", "60 minutes", R.drawable.spa_treatment));
        serviceList.add(new Service("Fine Dining Experience", "Exquisite multi-course meal at our signature restaurant.", 120.00, "Dining", "Daily 7 PM - 10 PM", "2 hours", R.drawable.fine_dining));
        serviceList.add(new Service("Guided Beach Tour", "Explore the beautiful coastline with our expert guide.", 40.00, "Activity", "Daily 9 AM & 2 PM", "3 hours", R.drawable.beach_tour));
        serviceList.add(new Service("Poolside Cabana Rental", "Enjoy a private cabana by the pool.", 60.00, "Cabana", "Daily 9 AM - 6 PM", "Full day", R.drawable.poolside_cabana));
    }

    private void initializeAttractions() {
        attractionList = new ArrayList<>();
        attractionList.add(new Attraction("Sunset Beach Tour", "Enjoy a relaxing evening tour along the coast.", R.drawable.beach_tour));
        attractionList.add(new Attraction("Aqua Sports Adventure", "Experience thrilling water sports activities.", R.drawable.water_sports));
        attractionList.add(new Attraction("LuxeVista Fine Dining", "Special discount for in-house guests.", R.drawable.fine_dining));
        attractionList.add(new Attraction("Nearby Historical Site", "Explore the rich history of the region.", 0));
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public List<Attraction> getAttractionList() {
        return attractionList;
    }

    /**
     * Get recommended rooms (first N items)
     * @param count Number of rooms to recommend
     * @return List of recommended rooms
     */
    public List<Room> getRecommendedRooms(int count) {
        if (roomList == null || roomList.isEmpty()) {
            return Collections.emptyList();
        }
        return roomList.subList(0, Math.min(count, roomList.size()));
    }

    /**
     * Get recommended services (first N items)
     * @param count Number of services to recommend
     * @return List of recommended services
     */
    public List<Service> getRecommendedServices(int count) {
        if (serviceList == null || serviceList.isEmpty()) {
            return Collections.emptyList();
        }
        return serviceList.subList(0, Math.min(count, serviceList.size()));
    }

    /**
     * Get room by index with null safety
     * @param index Index of the room
     * @return Room at index or null if invalid
     */
    public Room getRoomAt(int index) {
        if (roomList != null && index >= 0 && index < roomList.size()) {
            return roomList.get(index);
        }
        return null;
    }

    /**
     * Get service by index with null safety
     * @param index Index of the service
     * @return Service at index or null if invalid
     */
    public Service getServiceAt(int index) {
        if (serviceList != null && index >= 0 && index < serviceList.size()) {
            return serviceList.get(index);
        }
        return null;
    }
}
