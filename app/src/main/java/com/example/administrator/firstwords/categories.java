package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;


public class categories extends Activity {

    MediaPlayer mPlayer;
    SoundPool sound, click;
    int soundID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //object for the animals btn sound
        mPlayer = MediaPlayer.create(this, R.raw.click);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

    }

    //method to play the sound of the picture displayed on the screen
    public void back(View view) {
        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    public void play(View view) {

        mPlayer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                finish();
                Intent cow = new Intent(getBaseContext(), cow.class);
                startActivity(cow);
            }
        });
    }
}
