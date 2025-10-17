package com.example.minddrop.view;

import android.os.Bundle;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.minddrop.R;
import com.example.minddrop.model.MindDropItem;
import com.example.minddrop.presenter.MainContract;
import com.example.minddrop.databinding.ActivityMainBinding;
import com.example.minddrop.presenter.MainContract;
import com.example.minddrop.presenter.MainPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding binding;
    private MainContract.Presenter presenter;
    private MindDropAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());// infalte creates a view heirarchy
        setContentView(binding.getRoot());


         setupRecyclerView();

        // Initialize the presenter
        presenter = new MainPresenter(this);//create a new presenter instance
        presenter.onViewCreated(); // notifies presenter that the view is ready

        binding.fab.setOnClickListener(v -> presenter.onFabClicked());  // when the button is clicked from the view in main presenter public void onFabClicked()
        //fab is the id from activitymai.xml
    }

    private void setupRecyclerView() {
        adapter = new MindDropAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy(){ //lifecycle callback called before the activity is being destroyed
        super.onDestroy(); //call parent ondestroy for proper cleanup
        presenter.onDestroy(); // notifies the presenter to clean up resources

    }

    @Override
    public void showItems(List<MindDropItem> items) {
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.emptyView.setVisibility(View.GONE);
        adapter.setItems(items);
    }

    @Override
    public void showEmptyView() {
        binding.recyclerView.setVisibility(View.GONE);
        binding.emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
    }
}