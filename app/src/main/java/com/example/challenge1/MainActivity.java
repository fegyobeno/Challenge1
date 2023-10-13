package com.example.challenge1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.challenge1.model.Animal;
import com.example.challenge1.model.AnimalsViewModel;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    //protected AnimalsViewModel mViewModel = new ViewModelProvider(this).get(AnimalsViewModel.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimalsViewModel model = new ViewModelProvider(this).get(AnimalsViewModel.class);
        populateAnimals(model);
        model.getUiState().observe(this, uiState -> {
            // update UI
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, Fragment1.class, null)
                .commit(); // from activity - container

    }


    private void populateAnimals(AnimalsViewModel model){

        Objects.requireNonNull(model.getUiState().getValue()).add(new Animal(1, "frog", 1, "Ben", "frog"));
        Objects.requireNonNull(model.getUiState().getValue()).add(new Animal(2, "rhino", 2, "Adri", "rhino"));
        Objects.requireNonNull(model.getUiState().getValue()).add(new Animal(3, "snail", 3, "Smilja", "snail"));

    }

    public void switchToFr2() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_view, Fragment2.class, null)
                .commit(); // from activity - container
    }
    public void switchToFr1() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_view, Fragment1.class, null)
                .commit(); // from activity - container
    }
}