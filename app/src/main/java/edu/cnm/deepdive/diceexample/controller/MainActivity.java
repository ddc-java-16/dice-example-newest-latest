package edu.cnm.deepdive.diceexample.controller;

import android.util.Log;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.diceexample.databinding.ActivityMainBinding;
import edu.cnm.deepdive.diceexample.model.Roll;
import edu.cnm.deepdive.diceexample.viewmodel.DiceRollViewModel;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private DiceRollViewModel viewmodel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    setupViewmodel();
    binding.roll.setOnClickListener((v) -> viewmodel.rollDice(20, 60));
  }

  private void setupViewmodel () {
    viewmodel = new ViewModelProvider(this).get(DiceRollViewModel.class);
    viewmodel.getDiceRoll().observe(this, (rolls) -> {
      ArrayAdapter<Roll> adapter = new ArrayAdapter<Roll>(this, android.R.layout.simple_list_item_1, rolls);
      binding.rollValues.setAdapter(adapter);
    });
    viewmodel.getThrowable()
        .observe(this, (throwable -> {
          if (throwable != null) {
            Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
          }
        }));
  }
}