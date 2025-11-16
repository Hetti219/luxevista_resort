package com.example.luxevistaresort;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Base activity with common navigation logic.
 * Reduces code duplication across activities.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Set up bottom navigation with common logic.
     * @param bottomNavigationView The bottom navigation view to configure
     * @param currentMenuItemId The currently active menu item ID
     */
    protected void setupBottomNavigation(BottomNavigationView bottomNavigationView, int currentMenuItemId) {
        if (bottomNavigationView == null) {
            return;
        }

        bottomNavigationView.setSelectedItemId(currentMenuItemId);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // If already on this screen, do nothing
            if (itemId == currentMenuItemId) {
                return true;
            }

            Intent intent = createNavigationIntent(itemId);
            if (intent != null) {
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }

    /**
     * Create intent based on navigation item ID.
     * @param itemId Menu item ID
     * @return Intent for the selected screen or null
     */
    private Intent createNavigationIntent(int itemId) {
        if (itemId == R.id.navigation_rooms) {
            return new Intent(this, RoomListActivity.class);
        } else if (itemId == R.id.navigation_services) {
            return new Intent(this, ServiceListActivity.class);
        } else if (itemId == R.id.navigation_bookings) {
            return new Intent(this, MyBookingsActivity.class);
        } else if (itemId == R.id.navigation_profile) {
            return new Intent(this, ProfileActivity.class);
        } else if (itemId == R.id.navigation_attractions) {
            return new Intent(this, AttractionsActivity.class);
        }
        return null;
    }
}
