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
            approved();
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e4!= null) && (e5!= null) && (e6!= null))){
            approved();
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e7!= null) && (e8!= null) && (e9!= null))){
            approved();
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e10!= null) && (e11!= null) && (e12!= null))){
            approved();
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e13!= null) && (e14!= null) && (e15!= null))){
            approved();
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e16!= null) && (e17!= null) && (e18!= null))){
            approved();
        }
        else if ((d != null) && (c != null) && (m != null) && (b != null) && (r != null) && ((e19!= null) && (e20!= null) && (e21!= null))){
            approved();
        }
        else {
            disapproved();
        }

    }


    //approvals
    public void approved (){

        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        String cat = sharedPreferences.getString("category", "");
        int toInt = Integer.parseInt(cat);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

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
    public void disapproved (){

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

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
