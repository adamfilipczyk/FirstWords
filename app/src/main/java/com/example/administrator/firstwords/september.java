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


public class september  extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_september);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.septemberr);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.septemberr, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.september_s).setOnTouchListener(this);
        findViewById(R.id.september_e).setOnTouchListener(this);
        findViewById(R.id.september_p).setOnTouchListener(this);
        findViewById(R.id.september_t).setOnTouchListener(this);
        findViewById(R.id.september_e2).setOnTouchListener(this);
        findViewById(R.id.september_m).setOnTouchListener(this);
        findViewById(R.id.september_b).setOnTouchListener(this);
        findViewById(R.id.september_e3).setOnTouchListener(this);
        findViewById(R.id.september_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_t).setOnDragListener(this);
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


    //dragging object
    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            View view = (View) event.getLocalState();
            ViewGroup group = (ViewGroup) view.getParent();
            group.removeView(view);
            LinearLayout target = (LinearLayout) v;
            target.addView(view);
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }

    //set object movement
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        } else {
            return false;
        }
    }

    //final check
    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_s = (LinearLayout) findViewById(R.id.bottom_s);
        ImageView s = (ImageView) b_s.findViewById(R.id.september_s);

        LinearLayout b_p = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.september_p);

        LinearLayout b_t = (LinearLayout) findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.september_t);

        LinearLayout b_b = (LinearLayout) findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.september_b);

        LinearLayout b_r = (LinearLayout) findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.september_r);

        //e1------------------------------------------------------------

        LinearLayout b_e1 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e1.findViewById(R.id.september_e);

        LinearLayout b_e2 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.september_e2);

        LinearLayout b_e3 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.september_e3);

        //e2-------------------------------------------------------------

        LinearLayout b_e4 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.september_e);

        LinearLayout b_e5 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e5 = (ImageView) b_e5.findViewById(R.id.september_e2);

        LinearLayout b_e6 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e6 = (ImageView) b_e6.findViewById(R.id.september_e3);

        //e3-------------------------------------------------------------

        LinearLayout b_e7 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e7 = (ImageView) b_e7.findViewById(R.id.september_e);

        LinearLayout b_e8 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e8 = (ImageView) b_e8.findViewById(R.id.september_e2);

        LinearLayout b_e9 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e9 = (ImageView) b_e9.findViewById(R.id.september_e3);

        //e4-------------------------------------------------------------

        LinearLayout b_e10 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e10 = (ImageView) b_e10.findViewById(R.id.september_e);

        LinearLayout b_e11 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e11 = (ImageView) b_e11.findViewById(R.id.september_e2);

        LinearLayout b_e12 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e12 = (ImageView) b_e12.findViewById(R.id.september_e3);

        //e5-------------------------------------------------------------

        LinearLayout b_e13 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e13 = (ImageView) b_e13.findViewById(R.id.september_e3);

        LinearLayout b_e14 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e14 = (ImageView) b_e14.findViewById(R.id.september_e2);

        LinearLayout b_e15 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e15 = (ImageView) b_e15.findViewById(R.id.september_e);

        //e6-------------------------------------------------------------

        LinearLayout b_e16 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e16 = (ImageView) b_e16.findViewById(R.id.september_e2);

        LinearLayout b_e17 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e17 = (ImageView) b_e17.findViewById(R.id.september_e);

        LinearLayout b_e18 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e18 = (ImageView) b_e18.findViewById(R.id.september_e3);

        //e7-------------------------------------------------------------

        LinearLayout b_e19 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e19 = (ImageView) b_e19.findViewById(R.id.september_e);

        LinearLayout b_e20 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e20 = (ImageView) b_e20.findViewById(R.id.september_e2);

        LinearLayout b_e21 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e21 = (ImageView) b_e21.findViewById(R.id.september_e3);



        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //redirection to the methods if the letters are properly ordered
        if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e1!= null && e2!= null && e3!= null)){
            approved();
        }
        else if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e4!= null && e5!= null && e6!= null)){
            approved();
        }
        else if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e7!= null && e8!= null && e9!= null)){
            approved();
        }
        else if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e10!= null && e11!= null && e12!= null)){
            approved();
        }
        else if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e13!= null && e14!= null && e15!= null)){
            approved();
        }
        else if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e16!= null && e17!= null && e18!= null)){
            approved();
        }
        else if ((s != null) && (p != null) && (t != null) && (b != null) && (r != null) && (e19!= null && e20!= null && e21!= null)){
            approved();
        }
        else {
            disapproved();
        }
    }


    //approvals
    public void approved (){

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

        //load sound based on generated value and redirect to the next screen
        switch (eventNumber) {
            case 1:
                correct1.start();
                correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent october = new Intent(getApplicationContext(), october.class);
                        startActivity(october);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent october = new Intent(getApplicationContext(), october.class);
                        startActivity(october);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent october = new Intent(getApplicationContext(), october.class);
                        startActivity(october);
                    }
                });
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
