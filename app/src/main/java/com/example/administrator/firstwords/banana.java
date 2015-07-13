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


public class banana extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //initial sound
        mMediaPlayer = MediaPlayer.create(this, R.raw.banana);
        mMediaPlayer.start();

        //speaker btn sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.banana, 1);

        //check btn sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.ban_b).setOnTouchListener(this);
        findViewById(R.id.ban_a).setOnTouchListener(this);
        findViewById(R.id.ban_n).setOnTouchListener(this);
        findViewById(R.id.ban_a2).setOnTouchListener(this);
        findViewById(R.id.ban_n2).setOnTouchListener(this);
        findViewById(R.id.ban_a3).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_a2).setOnDragListener(this);
        findViewById(R.id.bottom_n2).setOnDragListener(this);
        findViewById(R.id.bottom_a3).setOnDragListener(this);

        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //sound of the picture displayed
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //click btn sound; redirection to home screen
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

    //moving object shadow
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
    public void check(View view) {

        //click sound for "Check" button
        click.play(soundID, 1, 1, 1, 0, 1);

        //letters to containers

        LinearLayout b_b = (LinearLayout) findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.ban_b);

        //n1------------------------------------------------------------

        LinearLayout b_n1 = (LinearLayout) findViewById(R.id.bottom_n);
        ImageView n1 = (ImageView) b_n1.findViewById(R.id.ban_n);

        LinearLayout b_n2 = (LinearLayout) findViewById(R.id.bottom_n2);
        ImageView n2 = (ImageView) b_n2.findViewById(R.id.ban_n2);

        //n2-------------------------------------------------------------

        LinearLayout b_n3 = (LinearLayout) findViewById(R.id.bottom_n);
        ImageView n3 = (ImageView) b_n3.findViewById(R.id.ban_n2);

        LinearLayout b_n4 = (LinearLayout) findViewById(R.id.bottom_n2);
        ImageView n4 = (ImageView) b_n4.findViewById(R.id.ban_n);

        //a1------------------------------------------------------------

        LinearLayout b_a1 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a1 = (ImageView) b_a1.findViewById(R.id.ban_a);

        LinearLayout b_a2 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a2 = (ImageView) b_a2.findViewById(R.id.ban_a2);

        LinearLayout b_a3 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a3 = (ImageView) b_a3.findViewById(R.id.ban_a3);

        //a2-------------------------------------------------------------

        LinearLayout b_a4 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a4 = (ImageView) b_a4.findViewById(R.id.ban_a);

        LinearLayout b_a5 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a5 = (ImageView) b_a5.findViewById(R.id.ban_a2);

        LinearLayout b_a6 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a6 = (ImageView) b_a6.findViewById(R.id.ban_a3);

        //a3-------------------------------------------------------------

        LinearLayout b_a7 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a7 = (ImageView) b_a7.findViewById(R.id.ban_a);

        LinearLayout b_a8 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a8 = (ImageView) b_a8.findViewById(R.id.ban_a2);

        LinearLayout b_a9 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a9 = (ImageView) b_a9.findViewById(R.id.ban_a3);

        //a4-------------------------------------------------------------

        LinearLayout b_a10 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a10 = (ImageView) b_a10.findViewById(R.id.ban_a);

        LinearLayout b_a11 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a11 = (ImageView) b_a11.findViewById(R.id.ban_a2);

        LinearLayout b_a12 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a12 = (ImageView) b_a12.findViewById(R.id.ban_a3);

        //a5-------------------------------------------------------------

        LinearLayout b_a13 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a13 = (ImageView) b_a13.findViewById(R.id.ban_a3);

        LinearLayout b_a14 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a14 = (ImageView) b_a14.findViewById(R.id.ban_a2);

        LinearLayout b_a15 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a15 = (ImageView) b_a15.findViewById(R.id.ban_a);

        //a5-------------------------------------------------------------

        LinearLayout b_a16 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a16 = (ImageView) b_a16.findViewById(R.id.ban_a2);

        LinearLayout b_a17 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a17 = (ImageView) b_a17.findViewById(R.id.ban_a);

        LinearLayout b_a18 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a18 = (ImageView) b_a18.findViewById(R.id.ban_a3);

        //a6-------------------------------------------------------------

        LinearLayout b_a19 = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a19 = (ImageView) b_a19.findViewById(R.id.ban_a);

        LinearLayout b_a20 = (LinearLayout) findViewById(R.id.bottom_a3);
        ImageView a20 = (ImageView) b_a20.findViewById(R.id.ban_a2);

        LinearLayout b_a21 = (LinearLayout) findViewById(R.id.bottom_a2);
        ImageView a21 = (ImageView) b_a21.findViewById(R.id.ban_a3);



        //redirection to the methods if the letters are properly ordered
        if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a1!= null && a2!= null && a3!= null)){
            approved();
        }
        else if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a4!= null && a5!= null && a6!= null)){
            approved();
        }
        else if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a7!= null && a8!= null && a9!= null)){
            approved();
        }
        else if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a10!= null && a11!= null && a12!= null)){
            approved();
        }
        else if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a13!= null && a14!= null && a15!= null)){
            approved();
        }
        else if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a16!= null && a17!= null && a18!= null)){
            approved();
        }
        else if ((b != null) && ((n1 != null && n2 != null) || (n3 != null && n4 != null)) && (a19!= null && a20!= null && a21!= null)){
            approved();
        }
        else {
            disapproved();
        }
    }

    //approvals
    public void approved (){

        //approval sounds
        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

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
                        Intent bberry = new Intent(getApplicationContext(), blueberry.class);
                        startActivity(bberry);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent bberry = new Intent(getApplicationContext(), blueberry.class);
                        startActivity(bberry);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent bberry = new Intent(getApplicationContext(), blueberry.class);
                        startActivity(bberry);
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

        //disapproval sounds

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

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


