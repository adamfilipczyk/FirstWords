package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
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

public class two extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.two);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.two, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.two_t).setOnTouchListener(this);
        findViewById(R.id.two_w).setOnTouchListener(this);
        findViewById(R.id.two_o).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_w).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);

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

        String value = "8";

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.two_t);

        LinearLayout b_w = (LinearLayout)findViewById(R.id.bottom_w);
        ImageView w = (ImageView) b_w.findViewById(R.id.two_w);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.two_o);



        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);



        if ((t!= null) && (w!= null) && (o!= null)) {


            b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_w.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_o.setBackgroundColor(Color.parseColor("#8BC34A"));

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent three = new Intent(getApplicationContext(), three.class);
                            startActivity(three);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent three = new Intent(getApplicationContext(), three.class);
                            startActivity(three);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent three = new Intent(getApplicationContext(), three.class);
                            startActivity(three);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        else {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;


            //t
            if (t!= null ) {
                b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_t.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //w
            if (w!= null ) {
                b_w.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_w.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //o
            if (o!= null ) {
                b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_o.setBackgroundColor(Color.parseColor("#FF0000"));
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
}
