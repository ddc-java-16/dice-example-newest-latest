package edu.cnm.deepdive.diceexample.controller;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.diceexample.R;
import edu.cnm.deepdive.diceexample.adapter.RollsAdapter;
import edu.cnm.deepdive.diceexample.databinding.ActivityMainBinding;
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
    binding.roll.setOnClickListener((v) -> viewmodel.rollDice(50, 6));
  }

  private void setupViewmodel () {
    viewmodel = new ViewModelProvider(this).get(DiceRollViewModel.class);
    viewmodel.getDiceRoll().observe(this, (rolls) -> {
      RollsAdapter adapter = new RollsAdapter(this, rolls);
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