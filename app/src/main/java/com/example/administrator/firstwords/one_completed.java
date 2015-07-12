package com.example.administrator.firstwords;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;

/**
 * @author Adam Filipczyk
 */

public class one_completed extends Activity {

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_completed);

        //applause sound
        mPlayer = MediaPlayer.create(this, R.raw.applause);
        mPlayer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

                //read the value from the Save.xml
                SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
                String cat = sharedPreferences.getString("category", "");
                int toInt = Integer.parseInt(cat);

                //pass variable and call savePref method
                savePref(toInt);
            }
        });
    }

    public void savePref(int cat) {

        //load the screen or timer based on Save.xml variable
        switch (cat) {
            case 2:
                finish();
                Intent load2 = new Intent(this, categories2.class);
                startActivity(load2);
                break;
            case 3:
                finish();
                Intent timer = new Intent(this, game_timer.class);
                startActivity(timer);
                break;
            case 4:
                finish();
                Intent load4 = new Intent(this, categories4.class);
                startActivity(load4);
                break;
            case 5:
                finish();
                Intent timer2 = new Intent(this, game_timer.class);
                startActivity(timer2);

                break;
            case 6:
                /*
                finish();
                Intent load6 = new Intent(this, categories6.class);
                startActivity(load6);
                break;*/
            case 7:
                finish();
                Intent timer3 = new Intent(this, game_timer.class);
                startActivity(timer3);
                break;
            case 8:
                /*
                finish();
                Intent load8 = new Intent(this, categories8.class);
                startActivity(load8);
                break;*/
            case 9:
                finish();
                Intent timer4 = new Intent(this, game_timer.class);
                startActivity(timer4);
                break;
            case 10:
                /*
                finish();
                Intent timer = new Intent(this, categories10.class);
                startActivity(timer);
                break;*/
            default:
                //do nothing
                break;
        }
    }
}
