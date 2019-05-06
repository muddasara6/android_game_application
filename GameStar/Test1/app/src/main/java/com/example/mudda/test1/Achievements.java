package com.example.mudda.test1;

// Allows access to application-specific resources and classes
import android.content.Context;
import android.content.Intent;
// Interface for accessing and modifying preference data returned by Context#getSharedPreferences
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Achievements extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        CheckBox firstCheck = findViewById(R.id.firstCheck);
        CheckBox secondCheck = findViewById(R.id.secondCheck);
        CheckBox thirdCheck = findViewById(R.id.thirdCheck);
        CheckBox fourthCheck = findViewById(R.id.fourthCheck);
        CheckBox fifthCheck = findViewById(R.id.fifthCheck);
        CheckBox sixthCheck = findViewById(R.id.sixthCheck);
        CheckBox seventhCheck = findViewById(R.id.seventhCheck);
        CheckBox eighthCheck = findViewById(R.id.eighthCheck);
        // Store data to the SharedPreferences
        SharedPreferences settings = getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        // Access data in the SharedPreferences
        int highScore = settings.getInt("HIGH_SCORE", 0);
        // If high score is greater than 50
        if (highScore >= 50) {
            // Check the CheckBox
            firstCheck.setChecked(true);
        }
        if (highScore >= 100) {
            secondCheck.setChecked(true);
        }
        if (highScore >= 150) {
            thirdCheck.setChecked(true);
        }
        if (highScore >= 200) {
            fourthCheck.setChecked(true);
        }
        if (highScore >= 250) {
            fifthCheck.setChecked(true);
        }
        if (highScore >= 300) {
            sixthCheck.setChecked(true);
        }
        if (highScore >= 350) {
            seventhCheck.setChecked(true);
        }
        if (highScore >= 400) {
            eighthCheck.setChecked(true);
        }
    }

    public void openMenu(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }
}