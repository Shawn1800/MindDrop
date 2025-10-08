package com.example.minddrop.presenter;

public class MainPresenter  implements MainContract.Presenter {

    // A reference to the View, nullable to avoid memory leaks.
    private MainContract.View view; //  this is a blueprint for the view package (mainactivity) ,rules are written here and the logic is written in the view package

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void  onViewCreated() {
        if (view != null) {
            view.showWelcomeMessage("welcome to MindDrop");
        }
    }

    @Override
    public void onDestroy() {
        // Nullify the view reference to prevent memory leaks
        view = null;
    }
}

