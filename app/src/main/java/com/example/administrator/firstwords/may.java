package com.example.administrator.firstwords;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * @author Adam Filipczyk
 */


public class may  extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.may);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.may, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.may_m).setOnTouchListener(this);
        findViewById(R.id.may_a).setOnTouchListener(this);
        findViewById(R.id.may_y).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_m).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_y).setOnDragListener(this);

        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }
    //play the sound of the picture displayed on the screen after "Speaker" button is clicked
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //play the "Home" button click sound and redirection to "Home" screen
    public void back(View view) {
        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }


    //dragging object
    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            View view = (View) event.getLocalState();
            ViewGroup group = (ViewGroup) view.getParent();
            group.removeView(view);
            LinearLayout target = (LinearLayout) v;
            target.addView(view);
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }

    //set object movement
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        } else {
            return false;
        }
    }

    //final check
    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_m = (LinearLayout) findViewById(R.id.bottom_m);
        ImageView m = (ImageView) b_m.findViewById(R.id.may_m);

        LinearLayout b_a = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.may_a);

        LinearLayout b_y = (LinearLayout) findViewById(R.id.bottom_y);
        ImageView y = (ImageView) b_y.findViewById(R.id.may_y);

        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((m != null) && (a != null) && (y != null)) {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent june = new Intent(getApplicationContext(), june.class);
                            startActivity(june);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent june = new Intent(getApplicationContext(), june.class);
                            startActivity(june);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent june = new Intent(getApplicationContext(), june.class);
                            startActivity(june);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        } else {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    incorr1.start();
                    break;
                case 2:
                    incorr2.start();
                    break;
                case 3:
                    incorr3.start();
                    break;
                default:
                    //do nothing
                    break;
            }
        }
    }
}
