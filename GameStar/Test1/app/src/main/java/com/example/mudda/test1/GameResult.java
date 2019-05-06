package com.example.mudda.test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameResult extends AppCompatActivity {
    private Button nextLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);
        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");
        SharedPreferences settings = getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        if (score > highScore) {
            highScoreLabel.setText("High Score : " + score);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        } else {
            highScoreLabel.setText("High Score : " + highScore);
        }
        nextLevel = findViewById(R.id.nextLevel);
        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLevelActivity();
            }
        });
    }

    public void openData(View view) {
        startActivity(new Intent(getApplicationContext(), SaveData.class));
    }

    public void openLevelActivity() {
        Intent intent = new Intent(this, Levels.class);
        startActivity(intent);
    }

    public void quitGame(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }
}

