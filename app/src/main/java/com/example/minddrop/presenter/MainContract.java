package com.example.minddrop.presenter;

import com.example.minddrop.model.MindDropItem;

import java.util.List;

public interface MainContract {

    interface View {
        void showItems(List<MindDropItem> items);
        void showEmptyView();
        void showMessage(String message);

    }

    interface Presenter {
        void onViewCreated();

        void onFabClicked();
        void onDestroy(); //callback method used right beore closing to cleanup
    }
}
