package com.example.minddrop.presenter;

import android.graphics.Bitmap;

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
        void onProcessImageClicked(Bitmap imageBitmap); // Add this method
        void onDestroy(); //callback method used right beore closing to cleanup
    }
}
