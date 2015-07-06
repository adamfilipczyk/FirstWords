package com.example.administrator.firstwords;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;



public class one_completed extends Activity {

    private String cat_num = "2";
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_completed);

        mPlayer = MediaPlayer.create(this, R.raw.applause);
        mPlayer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion (MediaPlayer mp){
                mp.stop();
                mp.release();
                savePref(cat_num);
                finish();
                Intent cat2 = new Intent(getApplicationContext(), categories2.class);
                startActivity(cat2);
            }
        });
    }

    public void savePref (String value){
        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("category", value);
        editor.commit();
    }
}
