package com.example.mudda.test1;

import android.content.Intent;
// Control playback of audio/video files and streams
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Options extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        // Try and read the Raw folder holding the music files
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
    }

    public void playSong(View view) {
        CheckBox musicCheck = findViewById(R.id.musicCheck);
        // Check if CheckBox is checked
        if (musicCheck.isChecked()) {
            // Start playing music
            mediaPlayer.start();
        }
        else {
            // Pause the music
            mediaPlayer.pause();
        }
    }

    public void openMenu(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }
}