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

public class telephone extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.telephone);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.telephone, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.telephone_t).setOnTouchListener(this);
        findViewById(R.id.telephone_e).setOnTouchListener(this);
        findViewById(R.id.telephone_l).setOnTouchListener(this);
        findViewById(R.id.telephone_e2).setOnTouchListener(this);
        findViewById(R.id.telephone_p).setOnTouchListener(this);
        findViewById(R.id.telephone_h).setOnTouchListener(this);
        findViewById(R.id.telephone_o).setOnTouchListener(this);
        findViewById(R.id.telephone_n).setOnTouchListener(this);
        findViewById(R.id.telephone_e3).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_e3).setOnDragListener(this);

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
            return true;
        }
        else {
            return false;
        }
    }


    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.telephone_t);

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.telephone_l);

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.telephone_p);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.telephone_h);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.telephone_o);

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.telephone_n);

        //e1------------------------------------------------------------

        LinearLayout b_a1 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a1 = (ImageView) b_a1.findViewById(R.id.telephone_e);

        LinearLayout b_a2 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a2 = (ImageView) b_a2.findViewById(R.id.telephone_e2);

        LinearLayout b_a3 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a3 = (ImageView) b_a3.findViewById(R.id.telephone_e3);

        //e2-------------------------------------------------------------

        LinearLayout b_a4 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a4 = (ImageView) b_a4.findViewById(R.id.telephone_e);

        LinearLayout b_a5 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a5 = (ImageView) b_a5.findViewById(R.id.telephone_e2);

        LinearLayout b_a6 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a6 = (ImageView) b_a6.findViewById(R.id.telephone_e3);

        //e3-------------------------------------------------------------

        LinearLayout b_a7 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a7 = (ImageView) b_a7.findViewById(R.id.telephone_e);

        LinearLayout b_a8 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a8 = (ImageView) b_a8.findViewById(R.id.telephone_e2);

        LinearLayout b_a9 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a9 = (ImageView) b_a9.findViewById(R.id.telephone_e3);

        //e4-------------------------------------------------------------

        LinearLayout b_a10 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a10 = (ImageView) b_a10.findViewById(R.id.telephone_e);

        LinearLayout b_a11 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a11 = (ImageView) b_a11.findViewById(R.id.telephone_e2);

        LinearLayout b_a12 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a12 = (ImageView) b_a12.findViewById(R.id.telephone_e3);

        //e5-------------------------------------------------------------

        LinearLayout b_a13 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a13 = (ImageView) b_a13.findViewById(R.id.telephone_e3);

        LinearLayout b_a14 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a14 = (ImageView) b_a14.findViewById(R.id.telephone_e2);

        LinearLayout b_a15 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a15 = (ImageView) b_a15.findViewById(R.id.telephone_e);

        //e6-------------------------------------------------------------

        LinearLayout b_a16 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a16 = (ImageView) b_a16.findViewById(R.id.telephone_e2);

        LinearLayout b_a17 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a17 = (ImageView) b_a17.findViewById(R.id.telephone_e);

        LinearLayout b_a18 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a18 = (ImageView) b_a18.findViewById(R.id.telephone_e3);

        //a7-------------------------------------------------------------

        LinearLayout b_a19 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView a19 = (ImageView) b_a19.findViewById(R.id.telephone_e);

        LinearLayout b_a20 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView a20 = (ImageView) b_a20.findViewById(R.id.telephone_e2);

        LinearLayout b_a21 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView a21 = (ImageView) b_a21.findViewById(R.id.telephone_e3);


//approval / disapproval sounds
        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);


        //redirection to the methods if the letters are properly ordered
        if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a1!= null && a2!= null && a3!= null)){
            approved();
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a4!= null && a5!= null && a6!= null)){
            approved();
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a7!= null && a8!= null && a9!= null)){
            approved();
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a10!= null && a11!= null && a12!= null)){
            approved();
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a13!= null && a14!= null && a15!= null)){
            approved();
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a16!= null && a17!= null && a18!= null)){
            approved();
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (a19!= null && a20!= null && a21!= null)){
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
                        Intent toaster = new Intent(getApplicationContext(), toaster.class);
                        startActivity(toaster);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent toaster = new Intent(getApplicationContext(), toaster.class);
                        startActivity(toaster);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent toaster = new Intent(getApplicationContext(), toaster.class);
                        startActivity(toaster);
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
