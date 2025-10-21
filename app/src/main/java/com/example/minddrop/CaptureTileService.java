package com.example.minddrop;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

import com.example.minddrop.model.MindDropItem;
import com.example.minddrop.model.databse.AppDatabase;
import com.example.minddrop.model.databse.MindDropDao;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RequiresApi(api= Build.VERSION_CODES.N)
public class CaptureTileService extends TileService {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    public CaptureTileService() {
    }

    // Called when the user adds the tile to their Quick Settings.
    @Override
    public void onTileAdded() {
        super.onTileAdded();
        updateTileState(Tile.STATE_INACTIVE);
    }

    // Called when the tile becomes visible.
    @Override
    public void onStartListening() {
        super.onStartListening();
        updateTileState(Tile.STATE_INACTIVE);
    }

    // This is the most important method. It's called when the user taps the tile.
    @Override
    public void onClick() {
        super.onClick();

        // Switch tile to "active" to give user feedback
        updateTileState(Tile.STATE_ACTIVE);

        // Perform the capture action in the background
        captureNewItem();
    }

    private void captureNewItem() {
        executor.execute(() -> {
            // Get a reference to our database DAO
            MindDropDao dao = AppDatabase.getDatabase(getApplicationContext()).mindDropDao();

            // Create and insert a new item
            String content = "Captured via Quick Tile at " + new Date().toString();
            MindDropItem newItem = new MindDropItem(content, new Date());
            dao.insert(newItem);

            // You can't show a Toast from a background thread, so we'd need a Handler
            // but for simplicity, the main UI will just show the new item when it opens.
            // In a real app, you might show a Heads-up Notification here.
        });

        // After a short delay, set the tile back to inactive
        // We use a Handler to post a delayed action
        new android.os.Handler().postDelayed(() -> updateTileState(Tile.STATE_INACTIVE), 1000);
    }

    /**
     * Updates the visual state of the tile.
     * @param state The new state (e.g., Tile.STATE_ACTIVE, Tile.STATE_INACTIVE)
     */
    private void updateTileState(int state) {
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(state);
            tile.updateTile();
        }
    }
}




