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

public class cabbage extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabbage);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.cabbage);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.cabbage, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.cabbage_c).setOnTouchListener(this);
        findViewById(R.id.cabbage_a).setOnTouchListener(this);
        findViewById(R.id.cabbage_b).setOnTouchListener(this);
        findViewById(R.id.cabbage_b2).setOnTouchListener(this);
        findViewById(R.id.cabbage_a2).setOnTouchListener(this);
        findViewById(R.id.cabbage_g).setOnTouchListener(this);
        findViewById(R.id.cabbage_e).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_b2).setOnDragListener(this);
        findViewById(R.id.bottom_a2).setOnDragListener(this);
        findViewById(R.id.bottom_g).setOnDragListener(this);
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
        if (event.getAction() == DragEvent.ACTION_DROP){
            View view = (View) event.getLocalState();
            ViewGroup group = (ViewGroup) view.getParent();
            group.removeView(view);
            view.invalidate();
            LinearLayout target = (LinearLayout) v;
            target.addView(view);
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }

    //method to set object moving
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            v.invalidate();
            return true;
        }
        else {
            return false;
        }
    }


    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_c = (LinearLayout) findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.cabbage_c);

        LinearLayout b_g = (LinearLayout) findViewById(R.id.bottom_g);
        ImageView g = (ImageView) b_g.findViewById(R.id.cabbage_g);

        LinearLayout b_e = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.cabbage_e);


        //b-------------------------------------------------------------

        LinearLayout b_b1 = (LinearLayout) findViewById(R.id.bottom_b);
        ImageView b1 = (ImageView) b_b1.findViewById(R.id.cabbage_b);

        LinearLayout b_b2 = (LinearLayout) findViewById(R.id.bottom_b2);
        ImageView b2 = (ImageView) b_b2.findViewById(R.id.cabbage_b2);

        LinearLayout b_b3 = (LinearLayout) findViewById(R.id.bottom_b);
        ImageView b3 = (ImageView) b_b3.findViewById(R.id.cabbage_b2);

        LinearLayout b_b4 = (LinearLayout) findViewById(R.id.bottom_b2);
        ImageView b4 = (ImageView) b_b4.findViewById(R.id.cabbage_b);

        //a--------------------------------------------------------------

        LinearLayout b_a1 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a1 = (ImageView) b_a1.findViewById(R.id.cabbage_a);

        LinearLayout b_a2 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a2 = (ImageView) b_a2.findViewById(R.id.cabbage_a2);

        LinearLayout b_a3 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a3 = (ImageView) b_a3.findViewById(R.id.cabbage_a2);

        LinearLayout b_a4 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a4 = (ImageView) b_a4.findViewById(R.id.cabbage_a);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((c != null) && (g != null) && (e != null) && (((b1 != null) && (b2 != null)) || ((b3 != null) && (b4 != null))) && (((a1 != null) && (a2 != null)) || ((a3 != null) && (a4 != null)))) {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent car = new Intent(getApplicationContext(), carrot.class);
                            startActivity(car);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent car = new Intent(getApplicationContext(), carrot.class);
                            startActivity(car);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent car = new Intent(getApplicationContext(), carrot.class);
                            startActivity(car);
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