package com.example.administrator.firstwords;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;



public class one_completed extends Activity {

    MediaPlayer mPlayer;
    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_completed);

        mPlayer = MediaPlayer.create(this, R.raw.applause);
        mPlayer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion (MediaPlayer mp){
                mp.release();
                //read the value from the Save.xml
                SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
                String cat = sharedPreferences.getString("category", "");

                savePref(cat);
            }
        });
    }

    public void savePref (String ca){

        switch (ca){
            case "3":
                finish();
                Intent timer = new Intent(this, game_timer.class);
                startActivity(timer);
                break;
            default:
                finish();
                Intent cat2 = new Intent(this, categories2.class);
                startActivity(cat2);
                break;
        }

    }
}


/*

//redirect to
                Intent cat2 = new Intent(getApplicationContext(), categories2.class);
                startActivity(cat2);

SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("category", value);
        editor.commit();
 */