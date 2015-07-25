package com.example.administrator.firstwords;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
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

public class hippo extends Activity implements OnTouchListener, OnDragListener  {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hippo);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.hippo);
        mMediaPlayer.start();


        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.hippo, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.hippo_h).setOnTouchListener(this);
        findViewById(R.id.hippo_i).setOnTouchListener(this);
        findViewById(R.id.hippo_p).setOnTouchListener(this);
        findViewById(R.id.hippo_p2).setOnTouchListener(this);
        findViewById(R.id.hippo_o).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_p2).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);

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

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.hippo_h);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.hippo_i);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.hippo_o);

        //p-----------------------------------------------------------

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.hippo_p);

        LinearLayout b_p2 = (LinearLayout)findViewById(R.id.bottom_p2);
        ImageView p2 = (ImageView) b_p2.findViewById(R.id.hippo_p2);

        LinearLayout b_p3 = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p3 = (ImageView) b_p3.findViewById(R.id.hippo_p2);

        LinearLayout b_p4 = (LinearLayout)findViewById(R.id.bottom_p2);
        ImageView p4 = (ImageView) b_p4.findViewById(R.id.hippo_p);



        if ((h!= null) && (i!= null) && (((p!=null) && (p2!=null)) || ((p3!=null) && (p4!=null))) && (o!=null)) {
            approved(h, i, p, p2, p3, p4, o, b_h, b_i, b_p, b_p2, b_p3, b_p4, b_o);
        }
        else {
            disapproved(h, i, p, p2, p3, p4, o, b_h, b_i, b_p, b_p2, b_p3, b_p4, b_o);
        }
    }

    //approvals
    public void approved(ImageView h, ImageView i, ImageView p, ImageView p2, ImageView p3, ImageView p4, ImageView o, LinearLayout b_h, LinearLayout b_i, LinearLayout b_p, LinearLayout b_p2, LinearLayout b_p3, LinearLayout b_p4, LinearLayout b_o){


        b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_p2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_p3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_p4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_o.setBackgroundColor(Color.parseColor("#8BC34A"));

        //objects for approval sounds
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
                        Intent li = new Intent(getApplicationContext(), lion.class);
                        startActivity(li);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent li = new Intent(getApplicationContext(), lion.class);
                        startActivity(li);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent li = new Intent(getApplicationContext(), lion.class);
                        startActivity(li);
                    }
                });
                break;
            default:
                //do nothing
                break;
        }
    }

    //disapproval
    public void disapproved(ImageView h, ImageView i, ImageView p, ImageView p2, ImageView p3, ImageView p4, ImageView o, LinearLayout b_h, LinearLayout b_i, LinearLayout b_p, LinearLayout b_p2, LinearLayout b_p3, LinearLayout b_p4, LinearLayout b_o){
        //objects for disapproval sounds
        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;


        //h
        if (h!= null ) {
            b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_h.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p1
        if (p!= null ) {
            b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (p3!= null ) {
            b_p3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_p.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p2
        if (p2!= null ) {
            b_p2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (p4!= null ) {
            b_p4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_p2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //i
        if (i!= null ) {
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_i.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //o
        if (o!= null ) {
            b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_o.setBackgroundColor(Color.parseColor("#FF0000"));
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
