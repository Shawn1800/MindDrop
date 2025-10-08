package com.example.minddrop.view;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.minddrop.R;
import com.example.minddrop.presenter.MainContract;
import com.example.minddrop.databinding.ActivityMainBinding;
import com.example.minddrop.presenter.MainContract;
import com.example.minddrop.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityMainBinding binding;
    private MainContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());// infalte creates a view heirarchy
        setContentView(binding.getRoot());

        // Initialize the presenter
        presenter = new MainPresenter(this);//create a new presenter instance
        presenter.onViewCreated(); // notifies presenter that the view is ready
    }
    @Override
    protected void onDestroy(){ //lifecycle callback called before the activity is being destroyed
        super.onDestroy(); //call parent ondestroy for proper cleanup
        presenter.onDestroy(); // notifies the presenter to clean up resources

    }
    @Override
    public void showWelcomeMessage(String message ){//implements the method maincontract.view
        //use binding object to access the textview
        binding.welcomeTextView.setText(message);//overides xml layout
    }
}