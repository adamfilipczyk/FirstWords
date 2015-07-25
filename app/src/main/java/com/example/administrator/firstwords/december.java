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


public class december  extends Activity implements View.OnTouchListener, View.OnDragListener {

    String value = "7";
    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_december);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.december);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.december, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.december_d).setOnTouchListener(this);
        findViewById(R.id.december_e).setOnTouchListener(this);
        findViewById(R.id.december_c).setOnTouchListener(this);
        findViewById(R.id.december_e2).setOnTouchListener(this);
        findViewById(R.id.december_m).setOnTouchListener(this);
        findViewById(R.id.december_b).setOnTouchListener(this);
        findViewById(R.id.december_e3).setOnTouchListener(this);
        findViewById(R.id.december_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_d).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_m).setOnDragListener(this);
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_e3).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);

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

        LinearLayout b_d = (LinearLayout) findViewById(R.id.bottom_d);
        ImageView d = (ImageView) b_d.findViewById(R.id.december_d);

        LinearLayout b_c = (LinearLayout) findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.december_c);

        LinearLayout b_m = (LinearLayout) findViewById(R.id.bottom_m);
        ImageView m = (ImageView) b_m.findViewById(R.id.december_m);

        LinearLayout b_b = (LinearLayout) findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.december_b);

        LinearLayout b_r = (LinearLayout) findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.december_r);

        //e1------------------------------------------------------------

        LinearLayout b_e1 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e1.findViewById(R.id.december_e);

        LinearLayout b_e2 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.december_e2);

        LinearLayout b_e3 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.december_e3);

        //e2-------------------------------------------------------------

        LinearLayout b_e4 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.december_e);

        LinearLayout b_e5 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e5 = (ImageView) b_e5.findViewById(R.id.december_e2);

        LinearLayout b_e6 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e6 = (ImageView) b_e6.findViewById(R.id.december_e3);

        //e3-------------------------------------------------------------

        LinearLayout b_e7 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e7 = (ImageView) b_e7.findViewById(R.id.december_e);

        LinearLayout b_e8 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e8 = (ImageView) b_e8.findViewById(R.id.december_e2);

        LinearLayout b_e9 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e9 = (ImageView) b_e9.findViewById(R.id.december_e3);

        //e4-------------------------------------------------------------

        LinearLayout b_e10 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e10 = (ImageView) b_e10.findViewById(R.id.december_e);

        LinearLayout b_e11 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e11 = (ImageView) b_e11.findViewById(R.id.december_e2);

        LinearLayout b_e12 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e12 = (ImageView) b_e12.findViewById(R.id.december_e3);

        //e5-------------------------------------------------------------

        LinearLayout b_e13 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e13 = (ImageView) b_e13.findViewById(R.id.december_e3);

        LinearLayout b_e14 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e14 = (ImageView) b_e14.findViewById(R.id.december_e2);

        LinearLayout b_e15 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e15 = (ImageView) b_e15.findViewById(R.id.december_e);

        //e6-------------------------------------------------------------

        LinearLayout b_e16 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e16 = (ImageView) b_e16.findViewById(R.id.december_e2);

        LinearLayout b_e17 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e17 = (ImageView) b_e17.findViewById(R.id.december_e);

        LinearLayout b_e18 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e18 = (ImageView) b_e18.findViewById(R.id.december_e3);

        //e7-------------------------------------------------------------

        LinearLayout b_e19 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e19 = (ImageView) b_e19.findViewById(R.id.december_e);

        LinearLayout b_e20 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e20 = (ImageView) b_e20.findViewById(R.id.december_e2);

        LinearLayout b_e21 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e21 = (ImageView) b_e21.findViewById(R.id.december_e3);

        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //redirection to the methods if the letters are properly ordered
        if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e1!= null) && (e2!= null) && (e3!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_c, b_m, b_b, b_r);
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e4!= null) && (e5!= null) && (e6!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e7!= null) && (e8!= null) && (e9!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e10!= null) && (e11!= null) && (e12!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e13!= null) && (e14!= null) && (e15!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e16!= null) && (e17!= null) && (e18!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e19!= null) && (e20!= null) && (e21!= null))){
            approved(c, d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }
        else {
            disapproved(d, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, c, m, b, r, b_c, b_d, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3,  b_e4,  b_e5,  b_e6,  b_e7,  b_e8,  b_e9, b_m, b_b, b_r);
        }

    }


    //approvals
    public void approved(ImageView c, ImageView d, ImageView e1, ImageView e10, ImageView e11, ImageView e12, ImageView e13, ImageView e14, ImageView e15, ImageView e16, ImageView e17, ImageView e18, ImageView e19, ImageView e2, ImageView e20, ImageView e21, ImageView e3, ImageView e4, ImageView e5, ImageView e6, ImageView e7, ImageView e8, ImageView e9, ImageView imageView, ImageView m, ImageView b, ImageView r, LinearLayout b_d, LinearLayout b_e1, LinearLayout b_e10, LinearLayout b_e11, LinearLayout b_e12, LinearLayout b_e13, LinearLayout b_e14, LinearLayout b_e15, LinearLayout b_e16, LinearLayout b_e17, LinearLayout b_e18, LinearLayout b_e19, LinearLayout b_e2, LinearLayout b_e20, LinearLayout b_e21, LinearLayout b_e3, LinearLayout b_e4, LinearLayout b_e5, LinearLayout b_e6, LinearLayout b_e7, LinearLayout b_e8, LinearLayout b_e9, LinearLayout b_c, LinearLayout b_m, LinearLayout b_b, LinearLayout b_r){
        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        String cat = sharedPreferences.getString("category", "");
        int toInt = Integer.parseInt(cat);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

        b_d.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e5.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e6.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e7.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e8.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e9.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e10.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e11.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e12.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e13.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e14.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e15.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e17.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e18.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e19.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e20.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e21.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_b.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_m.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_r.setBackgroundColor(Color.parseColor("#8BC34A"));

        //load sound based on generated value and redirect to the next screen
        switch (eventNumber) {
            case 1:
                if (toInt < 7){
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
                if (toInt < 7){
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
                if (toInt < 7){
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

    //disapprovals
    public void disapproved(ImageView d, ImageView e1, ImageView e10, ImageView e11, ImageView e12, ImageView e13, ImageView e14, ImageView e15, ImageView e16, ImageView e17, ImageView e18, ImageView e19, ImageView e2, ImageView e20, ImageView e21, ImageView e3, ImageView e4, ImageView e5, ImageView e6, ImageView e7, ImageView e8, ImageView e9, ImageView c, ImageView m, ImageView b, ImageView r, LinearLayout b_c, LinearLayout b_d, LinearLayout b_e1, LinearLayout b_e10, LinearLayout b_e11, LinearLayout b_e12, LinearLayout b_e13, LinearLayout b_e14, LinearLayout b_e15, LinearLayout b_e16, LinearLayout b_e17, LinearLayout b_e18, LinearLayout b_e19, LinearLayout b_e2, LinearLayout b_e20, LinearLayout b_e21, LinearLayout b_e3, LinearLayout b_e4, LinearLayout b_e5, LinearLayout b_e6, LinearLayout b_e7, LinearLayout b_e8, LinearLayout b_e9, LinearLayout b_m, LinearLayout b_b, LinearLayout b_r){

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

        //b
        if (d!= null) {
            b_d.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_d.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //c
        if (c!= null){
            b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_c.setBackgroundColor(Color.parseColor("#FF0000"));
        }


        //e1
        if (e1!= null){
            b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e6!= null){
            b_e6.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e8!= null){
            b_e8.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e10!= null){
            b_e10.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e13!= null){
            b_e13.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e16!= null){
            b_e16.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e19!= null){
            b_e19.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e1.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e2
        if (e2!= null){
            b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e4!= null){
            b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e9!= null){
            b_e9.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e11!= null){
            b_e11.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e14!= null){
            b_e14.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e17!= null){
            b_e17.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e21!= null){
            b_e21.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e3
        if (e3!= null){
            b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e5!= null){
            b_e5.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e7!= null){
            b_e7.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e12!= null){
            b_e12.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e15!= null){
            b_e15.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e18!= null){
            b_e18.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e20!= null){
            b_e20.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e3.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //load sound based on generated value
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
