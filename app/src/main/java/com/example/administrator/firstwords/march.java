package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
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


public class march  extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_march);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.march);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.march, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.march_m).setOnTouchListener(this);
        findViewById(R.id.march_a).setOnTouchListener(this);
        findViewById(R.id.march_r).setOnTouchListener(this);
        findViewById(R.id.march_c).setOnTouchListener(this);
        findViewById(R.id.march_h).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_m).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);

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


    //method for dragging object
    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (action){
            case DragEvent.ACTION_DROP:
                View view = (View) event.getLocalState();
                ViewGroup group = (ViewGroup) view.getParent();
                group.removeView(view);
                LinearLayout target = (LinearLayout) v;
                target.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

    //method to set object moving
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            v.invalidate();
            return true;
        }
        else {
            return false;
        }
    }

    //final check
    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_m = (LinearLayout) findViewById(R.id.bottom_m);
        ImageView m = (ImageView) b_m.findViewById(R.id.march_m);

        LinearLayout b_a = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.march_a);

        LinearLayout b_r = (LinearLayout) findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.march_r);

        LinearLayout b_c = (LinearLayout) findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.march_c);

        LinearLayout b_h = (LinearLayout) findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.march_h);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((m != null) && (a != null) && (r != null) && (c != null) && (h != null)) {

            b_m.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_h.setBackgroundColor(Color.parseColor("#8BC34A"));


            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent april = new Intent(getApplicationContext(), april.class);
                            startActivity(april);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent april = new Intent(getApplicationContext(), april.class);
                            startActivity(april);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent april = new Intent(getApplicationContext(), april.class);
                            startActivity(april);
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

            //m
            if (m!= null ) {
                b_m.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_m.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //a
            if (a!= null ) {
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r
            if (r!= null ) {
                b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //c
            if (c!= null ) {
                b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_c.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r
            if (h!= null ) {
                b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_h.setBackgroundColor(Color.parseColor("#FF0000"));
            }

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

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
