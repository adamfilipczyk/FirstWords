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

public class pumpkin extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumpkin);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.pumpkin);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.pumpkin, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.pumpkin_p).setOnTouchListener(this);
        findViewById(R.id.pumpkin_u).setOnTouchListener(this);
        findViewById(R.id.pumpkin_m).setOnTouchListener(this);
        findViewById(R.id.pumpkin_p2).setOnTouchListener(this);
        findViewById(R.id.pumpkin_k).setOnTouchListener(this);
        findViewById(R.id.pumpkin_i).setOnTouchListener(this);
        findViewById(R.id.pumpkin_n).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_u).setOnDragListener(this);
        findViewById(R.id.bottom_m).setOnDragListener(this);
        findViewById(R.id.bottom_p2).setOnDragListener(this);
        findViewById(R.id.bottom_k).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);

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

        LinearLayout b_u = (LinearLayout) findViewById(R.id.bottom_u);
        ImageView u = (ImageView) b_u.findViewById(R.id.pumpkin_u);

        LinearLayout b_m = (LinearLayout) findViewById(R.id.bottom_m);
        ImageView m = (ImageView) b_m.findViewById(R.id.pumpkin_m);

        LinearLayout b_k = (LinearLayout) findViewById(R.id.bottom_k);
        ImageView k = (ImageView) b_k.findViewById(R.id.pumpkin_k);

        LinearLayout b_i = (LinearLayout) findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.pumpkin_i);

        LinearLayout b_n = (LinearLayout) findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.pumpkin_n);


        //p-------------------------------------------------------------

        LinearLayout b_p1 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView p1 = (ImageView) b_p1.findViewById(R.id.pumpkin_p);

        LinearLayout b_p2 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView p2 = (ImageView) b_p2.findViewById(R.id.pumpkin_p2);

        LinearLayout b_p3 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView p3 = (ImageView) b_p3.findViewById(R.id.pumpkin_p2);

        LinearLayout b_p4 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView p4 = (ImageView) b_p4.findViewById(R.id.pumpkin_p);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((u != null) && (m != null) && (k != null) && (i != null) && (n != null) && (((p1 != null) && (p2 != null)) || ((p3 != null) && (p4 != null)))) {

            b_u.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_p1.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_p2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_p3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_p4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_k.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n.setBackgroundColor(Color.parseColor("#8BC34A"));


            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent rad = new Intent(getApplicationContext(), radish.class);
                            startActivity(rad);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent rad = new Intent(getApplicationContext(), radish.class);
                            startActivity(rad);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent rad = new Intent(getApplicationContext(), radish.class);
                            startActivity(rad);
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

            //u
            if (u!= null ) {
                b_u.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_u.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //p1
            if (p1!= null ) {
                b_p1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (p3!= null ) {
                b_p3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_p1.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //p2
            if (p2!= null ) {
                b_p2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (p4!= null ) {
                b_p4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_p2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //k
            if (k!= null ) {
                b_k.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_k.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //i
            if (i!= null ) {
                b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_i.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //n
            if (n!= null ) {
                b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_n.setBackgroundColor(Color.parseColor("#FF0000"));
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
}
