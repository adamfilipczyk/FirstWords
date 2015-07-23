package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.ClipData;
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

public class teacher  extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.teacher);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.teacher, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.teacher_t).setOnTouchListener(this);
        findViewById(R.id.teacher_e).setOnTouchListener(this);
        findViewById(R.id.teacher_a).setOnTouchListener(this);
        findViewById(R.id.teacher_c).setOnTouchListener(this);
        findViewById(R.id.teacher_h).setOnTouchListener(this);
        findViewById(R.id.teacher_e2).setOnTouchListener(this);
        findViewById(R.id.teacher_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);


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

        String value = "10";

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.teacher_t);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.teacher_a);

        LinearLayout b_c = (LinearLayout)findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.teacher_c);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.teacher_h);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.teacher_r);


        //e---------------------------------------------------------

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e.findViewById(R.id.teacher_e);

        LinearLayout b_e2 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.teacher_e2);

        LinearLayout b_e3 = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.teacher_e2);

        LinearLayout b_e4 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.teacher_e);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((t!= null) && (a!= null) && (c!= null) && (h!= null) && (r!= null) && (((e1!=null) && (e2!=null)) || ((e3!=null) && (e4!=null)))) {

            SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
            String cat = sharedPreferences.getString("category", "");
            int toInt = Integer.parseInt(cat);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    if (toInt < 10){
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
                    if (toInt < 10){
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
                    if (toInt < 10){
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
