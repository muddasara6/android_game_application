package com.example.mudda.test1;

import android.content.Intent;
// Provides classes to manage a variety of visual elements that are intended for display only
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
// Allows you to send and process Message and Runnable objects associated with a thread's MessageQueue
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
// Object used to report movement events
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
// A facility for threads to schedule tasks for future execution in a background thread
import java.util.Timer;
// A task that can be scheduled for one-time or repeated execution by a Timer
import java.util.TimerTask;

public class LevelOne extends AppCompatActivity {
    // Assigning variable names
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth;
    private LinearLayout startLayout;
    private ImageView bag, home, pause, square, star;
    private Drawable imageBagRight, imageBagLeft;
    private int bagSize, bagSpeed;
    private float bagX, bagY;
    private float squareX, squareY;
    private float starX, starY;
    private TextView scoreLabel, livesLabel, levelLabel, tapLabel;
    private int score, lives, timeCount;
    private Timer timer;
    private Handler handler = new Handler();
    private boolean start_flg = false;
    private boolean action_flg = false;
    private boolean star_flg = false;
    private boolean pause_flg = false;
    private SoundEffect sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        // Select names with ID
        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        bag = findViewById(R.id.bag);
        home = findViewById(R.id.home);
        pause = findViewById(R.id.pause);
        tapLabel = findViewById(R.id.tapLabel);
        levelLabel = findViewById(R.id.levelLabel);
        square = findViewById(R.id.square);
        star = findViewById(R.id.star);
        scoreLabel = findViewById(R.id.scoreLabel);
        livesLabel = findViewById(R.id.livesLabel);
        // Fetch drawable objects
        imageBagLeft = getResources().getDrawable(R.drawable.bag1);
        imageBagRight = getResources().getDrawable(R.drawable.bag2);
        // Get sound from Activity
        sound = new SoundEffect(this);
    }

    public void changePos() {
        // Increment time by 20
        timeCount += 20;
        // Square objects falling
        // The speed of the object to fall
        squareY += 9;
        // Get the height and width of the screen
        float squareCenterX = squareX + square.getWidth() / 2;
        float squareCenterY = squareY + square.getHeight() / 2;
        // if statement for method
        if (hitCheck(squareCenterX, squareCenterY)) {
            // Object drops from the top
            squareY = frameHeight + 100;
            // Lose a life
            lives -= 1;
            // Play sound
            sound.playHitShapeSound();
            // If all three lives are gone
            if (lives == 0) {
                // Open activity and play sound
                gameOver();
                sound.playGameOverSound();
            }
        }
        // Position of the square object
        if (squareY > frameHeight) {
            squareY = -100;
            squareX = (float)Math.floor(Math.random() * (frameWidth - square.getWidth()));
        }
        // Assigning variable
        square.setX(squareX);
        square.setY(squareY);
        // Star objects falling
        if (!star_flg && timeCount % 5000 == 0) {
            star_flg = true;
            starY = -24;
            starX = (float)Math.floor(Math.random() * (frameWidth - star.getWidth()));
        }
        if (star_flg) {
            starY += 11;
            float starCenterX = starX + star.getWidth() / 2;
            float starCenterY = starY + star.getHeight() / 2;
            if (hitCheck(starCenterX, starCenterY)) {
                starY = frameHeight + 30;
                score += 10;
                sound.playHitStarSound();
            }
            if (starY > frameHeight) star_flg = false;
            star.setX(starX);
            star.setY(starY);
        }
        bagX += bagSpeed;
        if (action_flg) {
            bagSpeed += 2;
            bag.setImageDrawable(imageBagRight);
        } else {
            bagSpeed += 2;
            bag.setImageDrawable(imageBagLeft);
        }
        if (bagX < 0) {
            bagX = 0;
            bag.setImageDrawable(imageBagLeft);
        }
        if (frameWidth - bagSize < bagX) {
            bagX = frameWidth - bagSize;
            bag.setImageDrawable(imageBagLeft);
        }
        bag.setX(bagX);
        // Sets the TextView to display the specified slice of the specified char array
        scoreLabel.setText("Score : " + score);
        livesLabel.setText("Lives : " + lives);
    }

    // Wraps value of the primitive type boolean in an object
    public boolean hitCheck(float x, float y) {
        // Move the bag left and right after tapping the device
        if (bagX <= x && x <= bagX + bagSize && bagY <= y && y <= frameHeight) {
            return true;
        }
        return false;
    }

    public void gameOver() {
        // Stop the objects from falling
        timer.cancel();
        timer = null;
        start_flg = false;
        Intent intent = new Intent(getApplicationContext(), GameResult.class);
        // Put the value data into score
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }

    @Override
    // Touch gesture for tapping
    public boolean onTouchEvent(MotionEvent event) {
        // GestureDetector to see if the user has tapped on the screen
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Move the bag to the left
            action_flg = true;
            // The bag speed
            bagSpeed = -24;
            sound.playBagSound();
        }
        return true;
    }

    public void startGame(View view) {
        start_flg = true;
        // Make layout visible after clicking the Start Button
        startLayout.setVisibility(View.INVISIBLE);
        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            bagSize = bag.getHeight();
            bagX = bag.getX();
            bagY = bag.getY();
        }
        // Setting positions and names
        bag.setX(0.0f);
        square.setY(3000.0f);
        star.setY(3000.0f);
        squareY = square.getY();
        starY = star.getY();
        // Make objects visible
        bag.setVisibility(View.VISIBLE);
        home.setVisibility(View.VISIBLE);
        pause.setVisibility(View.VISIBLE);
        tapLabel.setVisibility(View.VISIBLE);
        scoreLabel.setVisibility(View.VISIBLE);
        livesLabel.setVisibility(View.VISIBLE);
        levelLabel.setVisibility(View.VISIBLE);
        square.setVisibility(View.VISIBLE);
        star.setVisibility(View.VISIBLE);
        timeCount = 0;
        score = 0;
        lives = 3;
        scoreLabel.setText("Score : 0");
        livesLabel.setText("Lives : 3");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start_flg) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }
        }, 0, 20);
    }

    public void pause(View view) {
        // If the user clicks on the pause button
        if (pause_flg == false) {
            // Stop the bag from moving
            pause_flg = true;
            timer.cancel();
            timer = null;
        } else {
            // The bag should continue to move
            pause_flg = false;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (start_flg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                changePos();
                            }
                        });
                    }
                }
            }, 0, 20);
        }
    }

    public void home(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }

    public void scoreTable(View view) {
        startActivity(new Intent(getApplicationContext(), ScoreTable.class));
    }

    public void quitGame(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }
}