package com.example.administrator.firstwords;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View.DragShadowBuilder;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;


import java.util.Random;

/**
 * @author Adam Filipczyk
 */

public class zebra extends Activity implements OnTouchListener, OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zebra);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.zebra);
        mMediaPlayer.start();


        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.zebra, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.zebra_z).setOnTouchListener(this);
        findViewById(R.id.zebra_e).setOnTouchListener(this);
        findViewById(R.id.zebra_b).setOnTouchListener(this);
        findViewById(R.id.zebra_r).setOnTouchListener(this);
        findViewById(R.id.zebra_a).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_z).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);

        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //method to play the sound
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

        String value = "2";

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_z = (LinearLayout)findViewById(R.id.bottom_z);
        ImageView z = (ImageView) b_z.findViewById(R.id.zebra_z);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.zebra_e);

        LinearLayout b_b = (LinearLayout)findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.zebra_b);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.zebra_r);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.zebra_a);

        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);


        if ((z!= null) && (e!= null) && (b!=null) && (r!= null) && (a!= null)) {


            SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
            String cat = sharedPreferences.getString("category", "");
            int toInt = Integer.parseInt(cat);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    if (toInt < 2){
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
                    if (toInt < 2){
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
                    if (toInt < 2){
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
