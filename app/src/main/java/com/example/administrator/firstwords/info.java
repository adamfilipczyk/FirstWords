package com.example.administrator.firstwords;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;


public class info extends ActionBarActivity {

    TextView link;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        link = (TextView) findViewById(R.id.information);
        Linkify.addLinks(link, Linkify.EMAIL_ADDRESSES);

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
}
