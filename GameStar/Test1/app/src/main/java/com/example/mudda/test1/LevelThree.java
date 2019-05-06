package com.example.mudda.test1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class LevelThree extends AppCompatActivity {
    private FrameLayout gameFrame;
    private int frameHeight, frameWidth;
    private LinearLayout startLayout;
    private ImageView bag, home, pause, square, circle, triangle, star;
    private Drawable imageBagRight, imageBagLeft;
    private int bagSize, bagSpeed;
    private float bagX, bagY;
    private float squareX, squareY;
    private float circleX, circleY;
    private float triangleX, triangleY;
    private float starX, starY;
    private TextView scoreLabel, livesLabel, levelLabel, tapLabel;
    private int score2, lives, timeCount;
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
        setContentView(R.layout.activity_level_three);
        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        bag = findViewById(R.id.bag);
        home = findViewById(R.id.home);
        pause = findViewById(R.id.pause);
        tapLabel = findViewById(R.id.tapLabel);
        levelLabel = findViewById(R.id.levelLabel);
        square = findViewById(R.id.square);
        circle = findViewById(R.id.circle);
        triangle = findViewById(R.id.triangle);
        star = findViewById(R.id.star);
        scoreLabel = findViewById(R.id.scoreLabel);
        livesLabel = findViewById(R.id.livesLabel);
        imageBagLeft = getResources().getDrawable(R.drawable.bag1);
        imageBagRight = getResources().getDrawable(R.drawable.bag2);
        sound = new SoundEffect(this);
    }

    public void changePos() {
        timeCount += 20;
        squareY += 10;
        float squareCenterX = squareX + square.getWidth() / 2;
        float squareCenterY = squareY + square.getHeight() / 2;
        if (hitCheck(squareCenterX, squareCenterY)) {
            squareY = frameHeight + 100;
            lives -= 1;
            sound.playHitShapeSound();
            if (lives == 0) {
                gameOver();
                sound.playGameOverSound();
            }
        }
        if (squareY > frameHeight) {
            squareY = -100;
            squareX = (float)Math.floor(Math.random() * (frameWidth - square.getWidth()));
        }
        square.setX(squareX);
        square.setY(squareY);
        circleY += 11;
        float circleCenterX = circleX + circle.getWidth() / 2;
        float circleCenterY = circleY + circle.getHeight() / 2;
        if (hitCheck(circleCenterX, circleCenterY)) {
            circleY = frameHeight + 100;
            lives -= 1;
            sound.playHitShapeSound();
            if (lives == 0) {
                gameOver();
                sound.playGameOverSound();
            }
        }
        if (circleY > frameHeight) {
            circleY = -100;
            circleX = (float)Math.floor(Math.random() * (frameWidth - circle.getWidth()));
        }
        circle.setX(circleX);
        circle.setY(circleY);
        // Triangle
        triangleY += 12;
        float triangleCenterX = triangleX + triangle.getWidth() / 2;
        float triangleCenterY = triangleY + triangle.getHeight() / 2;
        if (hitCheck(triangleCenterX, triangleCenterY)) {
            triangleY = frameHeight + 100;
            lives -= 1;
            sound.playHitShapeSound();
            if (lives == 0) {
                gameOver();
                sound.playGameOverSound();
            }
        }
        if (triangleY > frameHeight) {
            triangleY = -100;
            triangleX = (float)Math.floor(Math.random() * (frameWidth - triangle.getWidth()));
        }
        triangle.setX(triangleX);
        triangle.setY(triangleY);
        // Star
        if (!star_flg && timeCount % 7000 == 0) {
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
                score2 += 10;
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
        scoreLabel.setText("Score : " + score2);
        livesLabel.setText("Lives : " + lives);
    }

    public boolean hitCheck(float x, float y) {
        if (bagX <= x && x <= bagX + bagSize && bagY <= y && y <= frameHeight) {
            return true;
        }
        return false;
    }

    public void gameOver() {
        timer.cancel();
        timer = null;
        start_flg = false;
        Intent intent = new Intent(getApplicationContext(), GameResult.class);
        intent.putExtra("SCORE", score2);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            action_flg = true;
            bagSpeed = -24;
            sound.playBagSound();
        }
        return true;
    }

    public void startGame(View view) {
        start_flg = true;
        startLayout.setVisibility(View.INVISIBLE);
        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            bagSize = bag.getHeight();
            bagX = bag.getX();
            bagY = bag.getY();
        }
        bag.setX(0.0f);
        square.setY(3000.0f);
        circle.setY(3000.0f);
        triangle.setY(3000.0f);
        star.setY(3000.0f);
        squareY = square.getY();
        circleY = circle.getY();
        triangleY = triangle.getY();
        starY = star.getY();
        bag.setVisibility(View.VISIBLE);
        home.setVisibility(View.VISIBLE);
        pause.setVisibility(View.VISIBLE);
        tapLabel.setVisibility(View.VISIBLE);
        scoreLabel.setVisibility(View.VISIBLE);
        livesLabel.setVisibility(View.VISIBLE);
        levelLabel.setVisibility(View.VISIBLE);
        square.setVisibility(View.VISIBLE);
        circle.setVisibility(View.VISIBLE);
        triangle.setVisibility(View.VISIBLE);
        star.setVisibility(View.VISIBLE);
        timeCount = 0;
        score2 = 0;
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
        if (pause_flg == false) {
            pause_flg = true;
            timer.cancel();
            timer = null;
        } else {
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