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
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class game_timer extends Activity {

    SoundPool press;
    int soundID, soundID2;

    MediaPlayer mPlayer, buzz;
    public TextView gTimer;
    private CountDownTimer countDownTimer;
    private final int startTime = (121 * 1000);
    private final int interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_timer);
        //keep the screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //final buzz sound
        buzz = MediaPlayer.create(this, R.raw.buzz);

        //take a break sound
        mPlayer = MediaPlayer.create(this, R.raw.takeabreak);
        mPlayer.start();


        //object for the bee pressed sound
        press = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID2 = press.load(this, R.raw.fart, 1);


        //timer
        gTimer = (TextView) findViewById(R.id.g_timer);
        countDownTimer = new CountDownTimerActivity(startTime, interval);
        countDownTimer.start();
        gTimer.setText(gTimer.getText() + String.valueOf(startTime / interval));

        //toast message
        Context cont = getApplicationContext();
        CharSequence message = "Press the belly and skip the break";
        int duration = Toast.LENGTH_LONG;
        Toast display = Toast.makeText(cont, message, duration);
        LinearLayout toastLayout = (LinearLayout) display.getView();
        TextView toastSize = (TextView) toastLayout.getChildAt(0);
        toastSize.setTextSize(18);
        display.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        display.show();
    }

    public class CountDownTimerActivity extends CountDownTimer {

        public CountDownTimerActivity(int starTime, int interval) {


            super(starTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
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


    public void pressed (View view){
        press.play(soundID2, 1, 1, 1, 0, 1);

        //read the value from the Save.xml
        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        String cat = sharedPreferences.getString("category", "");
        //call savePref method
        loadCategory(cat);
    }

    public void loadCategory (String cat){

        //load category based on variable saved to Save.xml file
        switch (cat){
            case "3":
                countDownTimer.cancel();
                finish();
                Intent load3 = new Intent(this, categories3.class);
                startActivity(load3);
                break;
            case "5":
                countDownTimer.cancel();
                finish();
                //Intent load5 = new Intent(this, categories5.class);
                //startActivity(load5);
                break;
            case "7":
                countDownTimer.cancel();
                finish();
                //Intent load7 = new Intent(this, categories7.class);
                //startActivity(load7);
                break;
            case "9":
                countDownTimer.cancel();
                finish();
                //Intent load9 = new Intent(this, categories9.class);
                //startActivity(load9);
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
