package com.example.administrator.firstwords;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class splash extends ActionBarActivity {

    MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPlayer = MediaPlayer.create(this, R.raw.splash_sound);
        mPlayer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                finish();
                Intent home = new Intent(getApplicationContext(), home.class);
                startActivity(home);

            }
        });
    }

}
