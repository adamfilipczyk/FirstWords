package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class truck extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.truck);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.truck, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.truck_t).setOnTouchListener(this);
        findViewById(R.id.truck_r).setOnTouchListener(this);
        findViewById(R.id.truck_u).setOnTouchListener(this);
        findViewById(R.id.truck_c).setOnTouchListener(this);
        findViewById(R.id.truck_k).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_u).setOnDragListener(this);
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_k).setOnDragListener(this);

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


    public void check(View view) {

        String value = "11";

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_t = (LinearLayout) findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.truck_t);

        LinearLayout b_r = (LinearLayout) findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.truck_r);

        LinearLayout b_u = (LinearLayout) findViewById(R.id.bottom_u);
        ImageView u = (ImageView) b_u.findViewById(R.id.truck_u);

        LinearLayout b_c = (LinearLayout) findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.truck_c);

        LinearLayout b_k = (LinearLayout) findViewById(R.id.bottom_k);
        ImageView k = (ImageView) b_k.findViewById(R.id.truck_k);

        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);



        if ((t != null) && (r != null) && (u != null) && (c != null) && (k != null)) {

            SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
            String cat = sharedPreferences.getString("category", "");
            int toInt = Integer.parseInt(cat);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    if (toInt < 11){
                        SharedPreferences sharedPreferences1 = getSharedPreferences("Save", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("category", value);
                        editor1.commit();
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent compl = new Intent(getApplicationContext(), one_completed.class);
                                startActivity(compl);
                            }
                        });
                    }
                    else if (toInt == 11){
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent fin = new Intent(getApplicationContext(), final_screen.class);
                                startActivity(fin);
                            }
                        });
                    }
                    else {
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent compl = new Intent(getApplicationContext(), one_completed.class);
                                startActivity(compl);
                            }
                        });
                    }
                    break;
                case 2:
                    if (toInt < 11){
                        SharedPreferences sharedPreferences1 = getSharedPreferences("Save", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("category", value);
                        editor1.commit();
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent compl = new Intent(getApplicationContext(), one_completed.class);
                                startActivity(compl);
                            }
                        });
                    }
                    else if (toInt == 11){
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent fin = new Intent(getApplicationContext(), final_screen.class);
                                startActivity(fin);
                            }
                        });
                    }
                    else {
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent compl = new Intent(getApplicationContext(), one_completed.class);
                                startActivity(compl);
                            }
                        });
                    }
                    break;
                case 3:
                    if (toInt < 11){
                        SharedPreferences sharedPreferences1 = getSharedPreferences("Save", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("category", value);
                        editor1.commit();
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent compl = new Intent(getApplicationContext(), one_completed.class);
                                startActivity(compl);
                            }
                        });
                    }
                    else if (toInt == 11){
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent fin = new Intent(getApplicationContext(), final_screen.class);
                                startActivity(fin);
                            }
                        });
                    }
                    else {
                        correct1.start();
                        correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();
                                finish();
                                Intent compl = new Intent(getApplicationContext(), one_completed.class);
                                startActivity(compl);
                            }
                        });
                    }
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        else {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

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
