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

public class chair extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.chair);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.chair, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.chair_c).setOnTouchListener(this);
        findViewById(R.id.chair_h).setOnTouchListener(this);
        findViewById(R.id.chair_a).setOnTouchListener(this);
        findViewById(R.id.chair_i).setOnTouchListener(this);
        findViewById(R.id.chair_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);

        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);

    }


    //method to play the sound
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

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


    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_c = (LinearLayout)findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.chair_c);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.chair_h);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a= (ImageView) b_a.findViewById(R.id.chair_a);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.chair_i);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r= (ImageView) b_r.findViewById(R.id.chair_r);



        if ((c!= null) && (h!= null) && (a!=null) && (i!= null) && (r!=null)) {

            b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));

            correct1 = MediaPlayer.create(this, R.raw.welldone);
            correct2 = MediaPlayer.create(this, R.raw.congrats);
            correct3 = MediaPlayer.create(this, R.raw.didit);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent door = new Intent(getApplicationContext(), door.class);
                            startActivity(door);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent door = new Intent(getApplicationContext(), door.class);
                            startActivity(door);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent door = new Intent(getApplicationContext(), door.class);
                            startActivity(door);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        else {

            incorr1 = MediaPlayer.create(this, R.raw.rusure);
            incorr2 = MediaPlayer.create(this, R.raw.incorrect);
            incorr3 = MediaPlayer.create(this, R.raw.tryagain);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            //c
            if (c!= null ) {
                b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_c.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //h
            if (h!= null ) {
                b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_h.setBackgroundColor(Color.parseColor("#FF0000"));
            }


            //e
            if (a!= null ) {
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a.setBackgroundColor(Color.parseColor("#FF0000"));
            }


            //i
            if (i!= null ) {
                b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_i.setBackgroundColor(Color.parseColor("#FF0000"));
            }


            //r
            if (r!= null ) {
                b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r.setBackgroundColor(Color.parseColor("#FF0000"));
            }


            switch (eventNumber){
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
