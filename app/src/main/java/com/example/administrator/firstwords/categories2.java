package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * @author Adam Filipczyk
 */

public class categories2 extends Activity {

    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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

    public void btn1(View view) {

        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent cat1 = new Intent(this, cow.class);
        startActivity(cat1);
    }

    public void btn2(View view) {

        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent cat2 = new Intent(this, apple.class);
        startActivity(cat2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
