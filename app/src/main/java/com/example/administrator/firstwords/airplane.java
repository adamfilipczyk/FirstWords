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

public class airplane extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.airplane);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.airplane, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.airplane_a).setOnTouchListener(this);
        findViewById(R.id.airplane_i).setOnTouchListener(this);
        findViewById(R.id.airplane_r).setOnTouchListener(this);
        findViewById(R.id.airplane_p).setOnTouchListener(this);
        findViewById(R.id.airplane_l).setOnTouchListener(this);
        findViewById(R.id.airplane_a2).setOnTouchListener(this);
        findViewById(R.id.airplane_n).setOnTouchListener(this);
        findViewById(R.id.airplane_e).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);
        findViewById(R.id.bottom_a2).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);


        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //method to play the sound of the picture displayed on the screen
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //method to play the sound of the picture displayed on the screen
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

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.airplane_i);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.airplane_r);

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.airplane_p);

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.airplane_l);

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.airplane_n);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.airplane_e);

        //a---------------------------------------------------------

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a1 = (ImageView) b_a.findViewById(R.id.airplane_a);

        LinearLayout b_a2 = (LinearLayout)findViewById(R.id.bottom_a2);
        ImageView a2 = (ImageView) b_a2.findViewById(R.id.airplane_a2);

        LinearLayout b_a3 = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a3 = (ImageView) b_a3.findViewById(R.id.airplane_a2);

        LinearLayout b_a4 = (LinearLayout)findViewById(R.id.bottom_a2);
        ImageView a4 = (ImageView) b_a4.findViewById(R.id.airplane_a);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((i!= null) && (r!= null) && (p!= null) && (l!= null) && (n!= null) && (e!= null) && (((a1!=null) && (a2!=null)) || ((a3!=null) && (a4!=null)))) {

            //change background color of the containers to green
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a4.setBackgroundColor(Color.parseColor("#8BC34A"));

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent bicycle = new Intent(getApplicationContext(), bicycle.class);
                            startActivity(bicycle);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent bicycle = new Intent(getApplicationContext(), bicycle.class);
                            startActivity(bicycle);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent bicycle = new Intent(getApplicationContext(), bicycle.class);
                            startActivity(bicycle);
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

            //change color to green if the letter is in proper container;
            //change color to red if the etter is not in proper container;
            if (i!= null) {
                b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_i.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (r!= null){
                b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (p!= null){
                b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_p.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (l!= null){
                b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_l.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (n!= null){
                b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_n.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (e!= null){
                b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_e.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (a1!=null){
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (a3!=null) {
                b_a3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (a2!=null){
                b_a2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (a4!=null){
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a2.setBackgroundColor(Color.parseColor("#FF0000"));
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
