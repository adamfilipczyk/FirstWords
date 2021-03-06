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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;


import java.util.Random;

/**
 * @author Adam Filipczyk
 */

public class rabbit extends Activity implements OnTouchListener, OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.rabbit);
        mMediaPlayer.start();


        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.rabbit, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.rabbit_r).setOnTouchListener(this);
        findViewById(R.id.rabbit_a).setOnTouchListener(this);
        findViewById(R.id.rabbit_b).setOnTouchListener(this);
        findViewById(R.id.rabbit_b2).setOnTouchListener(this);
        findViewById(R.id.rabbit_i).setOnTouchListener(this);
        findViewById(R.id.rabbit_t).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_b2).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_t).setOnDragListener(this);

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

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.rabbit_r);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.rabbit_a);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.rabbit_i);

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.rabbit_t);

        //b-----------------------------------------------------------

        LinearLayout b_b = (LinearLayout)findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.rabbit_b);

        LinearLayout b_b2 = (LinearLayout)findViewById(R.id.bottom_b2);
        ImageView b2 = (ImageView) b_b2.findViewById(R.id.rabbit_b2);

        LinearLayout b_b3 = (LinearLayout)findViewById(R.id.bottom_b);
        ImageView b3 = (ImageView) b_b3.findViewById(R.id.rabbit_b2);

        LinearLayout b_b4 = (LinearLayout)findViewById(R.id.bottom_b2);
        ImageView b4 = (ImageView) b_b4.findViewById(R.id.rabbit_b);


        if ((r!= null) && (a!= null) && (i!=null) && (t!= null) && (((b!= null) && (b2!=null)) || ((b3!=null) && (b4!=null)))) {
            approved(r, a, b, b2, b3, b4, i, t, b_r, b_a, b_b, b_b2, b_b3, b_b4, b_i, b_t);
        }
        else {
            disapproved(r, a, b, b2, b3, b4, i, t, b_r, b_a, b_b, b_b2, b_b3, b_b4, b_i, b_t);
        }
    }
    //approvals
    public void approved(ImageView r, ImageView a, ImageView b, ImageView b2, ImageView b3, ImageView b4, ImageView i, ImageView t, LinearLayout b_r, LinearLayout b_a, LinearLayout b_b, LinearLayout b_b2, LinearLayout b_b3, LinearLayout b_b4, LinearLayout b_i, LinearLayout b_t){

        b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_b.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_b2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_b3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_b4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_t.setBackgroundColor(Color.parseColor("#8BC34A"));


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
                        Intent ra = new Intent(getApplicationContext(), rat.class);
                        startActivity(ra);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent ra = new Intent(getApplicationContext(), rat.class);
                        startActivity(ra);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent ra = new Intent(getApplicationContext(), rat.class);
                        startActivity(ra);
                    }
                });
                break;
            default:
                //do nothing
                break;
        }
    }

    //disapproval
    public void disapproved(ImageView r, ImageView a, ImageView b, ImageView b2, ImageView b3, ImageView b4, ImageView i, ImageView t, LinearLayout b_r, LinearLayout b_a, LinearLayout b_b, LinearLayout b_b2, LinearLayout b_b3, LinearLayout b_b4, LinearLayout b_i, LinearLayout b_t){
        //objects for disapproval sounds
        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

        //r
        if (r!= null ) {
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_r.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //b1
        if (b!= null ) {
            b_b.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (b3!= null ) {
            b_b3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_b.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //b2
        if (b2!= null ) {
            b_b2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (b4!= null ) {
            b_b4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_b2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //a
        if (a!= null ) {
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_a.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //i
        if (i!= null ) {
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_i.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //t
        if (t!= null ) {
            b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_t.setBackgroundColor(Color.parseColor("#FF0000"));
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

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
