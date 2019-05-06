package com.example.mudda.test1;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEffect {
    private static SoundPool soundPool;
    private static int bagSound;
    private static int hitShapeSound;
    private static int hitStarSound;
    private static int gameOverSound;

    public SoundEffect(Context context) {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        bagSound = soundPool.load(context, R.raw.bag, 1);
        hitShapeSound = soundPool.load(context, R.raw.shape, 1);
        hitStarSound = soundPool.load(context, R.raw.star, 1);
        gameOverSound = soundPool.load(context, R.raw.over, 1);
    }

    public void playBagSound() {
        soundPool.play(bagSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playHitShapeSound() {
        soundPool.play(hitShapeSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playHitStarSound() {
        soundPool.play(hitStarSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playGameOverSound() {
        soundPool.play(gameOverSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}

