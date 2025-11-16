package com.example.luxevistaresort;

public class Constants {
    // Intent extras
    public static final String EXTRA_ROOM_INDEX = "ROOM_INDEX";
    public static final String EXTRA_SERVICE_INDEX = "SERVICE_INDEX";

    // SharedPreferences
    public static final String PREFS_USER_DATA = "user_data";

    // Recommendation limits
    public static final int MAX_RECOMMENDED_ITEMS = 2;

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }
}
