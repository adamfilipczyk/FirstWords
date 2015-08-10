package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;


public class final_screen extends Activity {

    MediaPlayer mPlayer;
    SoundPool click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);

        //applause sound
        mPlayer = MediaPlayer.create(this, R.raw.tada);
        mPlayer.start();

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);
    }

    public void back (View view){
        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    public void reset (View view){
        click.play(soundID, 1, 1, 1, 0, 1);
        SharedPreferences sharedPreferences1 = getSharedPreferences("Save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putString("category", "1");
        editor1.commit();
        finish();
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
