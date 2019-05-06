// Root of the project directory
package com.example.mudda.test1;

// Abstraction description of an operation to be performed
import android.content.Intent;
// Base class for activities that use the support library action bar features
import android.support.v7.app.AppCompatActivity;
// A mapping from String keys to various Parcelable values.
import android.os.Bundle;
// Refers to the methods used to view classes with the parameter
import android.view.View;
// Users can tap or click to perform an action
import android.widget.Button;

// Activity that supports backward compatibility of the action bar
public class MainMenu extends AppCompatActivity {
    // Set up variable button
    private Button playButton;

    // Override method
    @Override
    // Save the state of the application
    protected void onCreate(Bundle savedInstanceState) {
        // Reference to a Bundle object that is passed into the onCreate method
        super.onCreate(savedInstanceState);
        // Associated to a XML file
        setContentView(R.layout.activity_main_menu);
        // Select Button with ID
        playButton = findViewById(R.id.playButton);
        // onClick method to make Button clickable
        // setOnClickListener takes View.OnClickListener as its parameter
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // Open method below
                openActivity();
            }
        });
    }

    public void openActivity() {
        // Start another activity
        Intent intent = new Intent(this, Levels.class);
        // Starts an instance of the activity specified by the Intent
        startActivity(intent);
    }

    // Method to view other classes
    public void openAchievements(View view) {
        // Start a new Activity using Intent
        startActivity(new Intent(getApplicationContext(), Achievements.class));
    }

    public void openHelp(View view) {
        startActivity(new Intent(getApplicationContext(), Help.class));
    }

    public void openOptions(View view) {
        startActivity(new Intent(getApplicationContext(), Options.class));
    }
}