package com.example.mudda.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Levels extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
    }

    // Open the main menu Activity
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }

    // Open the first level Activity
    public void level1(View view) {
        startActivity(new Intent(getApplicationContext(), LevelOne.class));
    }

    // Open the second level Activity
    public void level2(View view) {
        startActivity(new Intent(getApplicationContext(), LevelTwo.class));
    }

    // Open the third level Activity
    public void level3(View view) {
        startActivity(new Intent(getApplicationContext(), LevelThree.class));
    }

    // Open the fourth level Activity
    public void level4(View view) {
        startActivity(new Intent(getApplicationContext(), LevelFour.class));
    }

    // Open the fifth level Activity
    public void level5(View view) {
        startActivity(new Intent(getApplicationContext(), LevelFive.class));
    }

    // Open the sixth level Activity
    public void level6(View view) {
        startActivity(new Intent(getApplicationContext(), LevelSix.class));
    }
}