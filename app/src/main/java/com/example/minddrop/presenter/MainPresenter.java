package com.example.minddrop.presenter;

import com.example.minddrop.model.MindDropItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPresenter  implements MainContract.Presenter {

    // A reference to the View, nullable to avoid memory leaks.
    private MainContract.View view; //this is a blueprint for the view package (mainactivity) ,rules are written here and the logic is written in the view package
    // This will hold our data. Later, it will come from a database.
    private List<MindDropItem> items = new ArrayList<>();
    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void  onViewCreated() { //this creates view
        // Load initial data (dummy data for now)
        loadItems();
    }
    public void loadItems(){  // this data will be stored in the view
        if (view == null) return;

        // Create some dummy data
        if (items.isEmpty()) {
            items.add(new MindDropItem("My first brilliant idea!", new Date()));
            items.add(new MindDropItem("A screenshot of a recipe I want to try.", new Date(System.currentTimeMillis() - 86400000))); // Yesterday
        }

        if (items.isEmpty()) {
            view.showEmptyView();
        } else {
            view.showItems(items);
        }

    }
    @Override
    public void onFabClicked() { // this is the floating actionbutton i added
        if (view != null) {
            // In the future, this will open a capture screen.
            // For now, just show a message.
            view.showMessage("Capture Action Triggered!");
        }


    }

    @Override
    public void onDestroy() {
        // Nullify the view reference to prevent memory leaks
        view = null;
    }
}

