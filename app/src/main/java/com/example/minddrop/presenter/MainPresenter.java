package com.example.minddrop.presenter;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Looper;
import android.os.Handler;

import com.example.minddrop.model.MindDropItem;
import com.example.minddrop.model.databse.MindDropDao;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MainPresenter  implements MainContract.Presenter {

    // A reference to the View, nullable to avoid memory leaks.
    private MainContract.View view; //this is a blueprint for the view package (mainactivity) ,rules are written here and the logic is written in the view package


    private final MindDropDao mindDropDao;

    // Executor for background tasks
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    // Handler to post results back to the main thread
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    public MainPresenter (MainContract.View view, MindDropDao mindDropDao) {
        this.view = view;
        this.mindDropDao = mindDropDao;
    }

    @Override
    public void  onViewCreated() { //this creates view

        loadItems();
    }
    private void loadItems() {
        if (view == null) return;

        executor.execute(() -> {
            // Background thread
            List<MindDropItem> items = mindDropDao.getAllItems();

            handler.post(() -> {
                // Main thread
                if (view != null) {
                    if (items.isEmpty()) {
                        view.showEmptyView();
                    } else {
                        view.showItems(items);
                    }
                }
            });
        });
    }
    @Override
    public void onFabClicked() {
        if (view == null) return;

        executor.execute(() -> {
            // Background thread
            String content = " (fab)New item added at " + new Date().toString();
            mindDropDao.insert(new MindDropItem(content, new Date()));

            // Reload items and update the UI
            loadItems();
        });
        view.showMessage("New Item Added from fab !");
    }
    @Override
    public void onProcessImageClicked(Bitmap imageBitmap) {
        if (view == null || imageBitmap == null) return;
        view.showMessage("Processing image...");

        InputImage image = InputImage.fromBitmap(imageBitmap, 0);
        recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    // Task completed successfully, save the text
                    String extractedText = visionText.getText();
                    if (extractedText.isEmpty()) {
                        extractedText = "No text found in image.";
                    }
                    saveNewItem(extractedText);
                })
                .addOnFailureListener(e -> {
                    // Task failed with an exception
                    if (view != null) {
                        view.showMessage("Failed to process image: " + e.getMessage());
                    }
                });
    }

    private void saveNewItem(String content) {
        executor.execute(() -> {
            mindDropDao.insert(new MindDropItem(content, new Date()));
            loadItems(); // Refresh the list
        });
    }


    @Override
    public void onDestroy() {
        // Nullify the view reference to prevent memory leaks
        view = null;
    }
}

