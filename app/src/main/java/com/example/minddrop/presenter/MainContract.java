package com.example.minddrop.presenter;

public interface MainContract {

    interface View {
        void showWelcomeMessage(String message);
    }

    interface Presenter {
        void onViewCreated();
        void onDestroy(); //callback method used right beore closing to cleanup
    }
}
