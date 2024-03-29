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

public class august  extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_august);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.august);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.august, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.august_a).setOnTouchListener(this);
        findViewById(R.id.august_u).setOnTouchListener(this);
        findViewById(R.id.august_g).setOnTouchListener(this);
        findViewById(R.id.august_u2).setOnTouchListener(this);
        findViewById(R.id.august_s).setOnTouchListener(this);
        findViewById(R.id.august_t).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_u).setOnDragListener(this);
        findViewById(R.id.bottom_g).setOnDragListener(this);
        findViewById(R.id.bottom_u2).setOnDragListener(this);
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_t).setOnDragListener(this);

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

        LinearLayout b_a = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.august_a);

        LinearLayout b_g = (LinearLayout) findViewById(R.id.bottom_g);
        ImageView g = (ImageView) b_g.findViewById(R.id.august_g);

        LinearLayout b_s = (LinearLayout) findViewById(R.id.bottom_s);
        ImageView s = (ImageView) b_s.findViewById(R.id.august_s);

        LinearLayout b_t = (LinearLayout) findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.august_t);

        //u---------------------------------------------------------

        LinearLayout b_u1 = (LinearLayout)findViewById(R.id.bottom_u);
        ImageView u1 = (ImageView) b_u1.findViewById(R.id.august_u);

        LinearLayout b_u2 = (LinearLayout)findViewById(R.id.bottom_u2);
        ImageView u2 = (ImageView) b_u2.findViewById(R.id.august_u2);

        LinearLayout b_u3 = (LinearLayout)findViewById(R.id.bottom_u);
        ImageView u3 = (ImageView) b_u3.findViewById(R.id.august_u2);

        LinearLayout b_u4 = (LinearLayout)findViewById(R.id.bottom_u2);
        ImageView u4 = (ImageView) b_u4.findViewById(R.id.august_u);

        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((a != null) && (g != null) && (s != null) && (t != null) && (((u1!=null) && (u2!=null)) || ((u3!=null) && (u4!=null)))) {

            //change background color of the containers to green
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_u1.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_u2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_u3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_u4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_g.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_t.setBackgroundColor(Color.parseColor("#8BC34A"));


            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent september = new Intent(getApplicationContext(), september.class);
                            startActivity(september);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent september = new Intent(getApplicationContext(), september.class);
                            startActivity(september);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent september = new Intent(getApplicationContext(), september.class);
                            startActivity(september);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        } else {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            //change color to green if the letter is in proper container;
            //change color to red if the etter is not in proper container;

            //a
            if (a!= null) {
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //u1
            if (u1!= null){
                b_u1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (u3!= null){
                b_u3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_u1.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //u2
            if (u2!= null){
                b_u2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (u4!= null){
                b_u4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_u2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //g
            if (g!= null){
                b_g.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_g.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //s
            if (s!= null){
                b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_s.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //t
            if (t!= null){
                b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_t.setBackgroundColor(Color.parseColor("#FF0000"));
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
