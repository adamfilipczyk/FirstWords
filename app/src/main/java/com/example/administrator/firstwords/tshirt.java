package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class tshirt extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tshirt);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.tshirt);
        mMediaPlayer.start();


        //speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.tshirt, 1);

        //check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.tshirt_t).setOnTouchListener(this);
        findViewById(R.id.tshirt_hyphen).setOnTouchListener(this);
        findViewById(R.id.tshirt_s).setOnTouchListener(this);
        findViewById(R.id.tshirt_h).setOnTouchListener(this);
        findViewById(R.id.tshirt_i).setOnTouchListener(this);
        findViewById(R.id.tshirt_r).setOnTouchListener(this);
        findViewById(R.id.tshirt_t2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_hyphen).setOnDragListener(this);
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_t2).setOnDragListener(this);


        //top container listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //sound of the picture displayed on the screen
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //sound when the button clicked; redirect to home screen
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

        String value = "4";

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_s = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s = (ImageView) b_s.findViewById(R.id.tshirt_s);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.tshirt_h);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.tshirt_i);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.tshirt_r);

        LinearLayout b_hy = (LinearLayout)findViewById(R.id.bottom_hyphen);
        ImageView hy = (ImageView) b_hy.findViewById(R.id.tshirt_hyphen);


        //t----------------------------------------------------------

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t1 = (ImageView) b_t.findViewById(R.id.tshirt_t);

        LinearLayout b_t2 = (LinearLayout)findViewById(R.id.bottom_t2);
        ImageView t2 = (ImageView) b_t2.findViewById(R.id.tshirt_t2);

        LinearLayout b_t3 = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t3 = (ImageView) b_t3.findViewById(R.id.tshirt_t2);

        LinearLayout b_t4 = (LinearLayout)findViewById(R.id.bottom_t2);
        ImageView t4 = (ImageView) b_t4.findViewById(R.id.tshirt_t);

        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((s!= null) && (h!=null) && (i!= null) && (r!=null) && (hy!= null) && (((t1!=null) && (t2!=null)) || ((t3!=null) && (t4!=null)))) {


            b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_t2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_t3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_t4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_hy.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));

            SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
            String cat = sharedPreferences.getString("category", "");
            int toInt = Integer.parseInt(cat);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    if (toInt < 4){
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
                    if (toInt < 4){
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
                    if (toInt < 4){
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


            //t1
            if (t1!= null ) {
                b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (t3!= null ) {
                b_t3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_t.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //t2
            if (t2!= null ) {
                b_t2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (t4!= null ) {
                b_t4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_t2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //hyphen
            if (hy!= null ) {
                b_hy.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_hy.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //s
            if (s!= null ) {
                b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_s.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //h
            if (h!= null ) {
                b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_h.setBackgroundColor(Color.parseColor("#FF0000"));
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
}
