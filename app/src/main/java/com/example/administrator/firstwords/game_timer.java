package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.TextView;



public class game_timer extends Activity {

    SoundPool tictac;
    int soundID;

    MediaPlayer mPlayer, buzz;
    public TextView gTimer;
    private CountDownTimer countDownTimer;
    private final int startTime = (121 * 1000);
    private final int interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        buzz = MediaPlayer.create(this, R.raw.buzz);
        mPlayer = MediaPlayer.create(this, R.raw.takeabreak);
        mPlayer.start();

        //object for the sound
        tictac = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = tictac.load(this, R.raw.tic, 1);


        gTimer = (TextView) findViewById(R.id.g_timer);
        countDownTimer = new CountDownTimerActivity(startTime, interval);
        countDownTimer.start();

        gTimer.setText(gTimer.getText() + String.valueOf(startTime / interval));
    }

    public class CountDownTimerActivity extends CountDownTimer {

        public CountDownTimerActivity(int starTime, int interval) {


            super(starTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tictac.play(soundID, 1, 1, 1, 0, 1);
            gTimer.setText("Seconds left: " + " " + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            buzz.start();
            gTimer.setText("Time's Up !");

            //read the value from the Save.xml
            SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
            String cat = sharedPreferences.getString("category", "");
            //call savePref method
            loadCategory(cat);
        }
    }

    public void loadCategory (String cat){

        switch (cat){
            case "3":
                finish();
                Intent load3 = new Intent(this, categories3.class);
                startActivity(load3);
                break;
            case "5":
                finish();
                //Intent load5 = new Intent(this, categories5.class);
                //startActivity(load5);
                break;
            case "7":
                finish();
                //Intent load7 = new Intent(this, categories7.class);
                //startActivity(load7);
                break;
        }
    }
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        android.os.Process.myPid();
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.myPid();
        System.exit(0);
    }
}
