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

public class giraffe extends Activity implements OnTouchListener, OnDragListener {

    SoundPool sound, click, back;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giraffe);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.giraffe);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.giraffe, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //object for the check sound
        back = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = back.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.giraffe_g).setOnTouchListener(this);
        findViewById(R.id.giraffe_i).setOnTouchListener(this);
        findViewById(R.id.giraffe_r).setOnTouchListener(this);
        findViewById(R.id.giraffe_a).setOnTouchListener(this);
        findViewById(R.id.giraffe_f).setOnTouchListener(this);
        findViewById(R.id.giraffe_f2).setOnTouchListener(this);
        findViewById(R.id.giraffe_e).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_g).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_f).setOnDragListener(this);
        findViewById(R.id.bottom_f2).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //method to play the sound
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //method to play the sound of the picture displayed on the screen
    public void back(View view) {
        back.play(soundID, 1, 1, 1, 0, 1);
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

        LinearLayout b_g = (LinearLayout)findViewById(R.id.bottom_g);
        ImageView g = (ImageView) b_g.findViewById(R.id.giraffe_g);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.giraffe_i);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.giraffe_r);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.giraffe_a);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.giraffe_e);
        //f--------------------------------------------------------

        LinearLayout b_f = (LinearLayout)findViewById(R.id.bottom_f);
        ImageView f = (ImageView) b_f.findViewById(R.id.giraffe_f);

        LinearLayout b_f2 = (LinearLayout)findViewById(R.id.bottom_f2);
        ImageView f2 = (ImageView) b_f2.findViewById(R.id.giraffe_f2);

        LinearLayout b_f3 = (LinearLayout)findViewById(R.id.bottom_f);
        ImageView f3 = (ImageView) b_f3.findViewById(R.id.giraffe_f2);

        LinearLayout b_f4 = (LinearLayout)findViewById(R.id.bottom_f2);
        ImageView f4 = (ImageView) b_f4.findViewById(R.id.giraffe_f);


        if ((g!= null) && (i!= null) && (r!=null) && (a!= null) && (((f!= null) && (f2!=null)) || ((f3!=null) && (f4!=null))) && (e!= null)) {
            approved(g, i, r, a, f, f2, f3, f4, e, b_g, b_i, b_r, b_a, b_f, b_f2, b_f3, b_f4, b_e);
        }
        else {
            disapproved(g, i, r, a, f, f2, f3, f4, e, b_g, b_i, b_r, b_a, b_f, b_f2, b_f3, b_f4, b_e);
        }
    }

    //approvals
    public void approved(ImageView g, ImageView i, ImageView r, ImageView a, ImageView f, ImageView f2, ImageView f3, ImageView f4, ImageView e, LinearLayout b_g, LinearLayout b_i, LinearLayout b_r, LinearLayout b_a, LinearLayout b_f, LinearLayout b_f2, LinearLayout b_f3, LinearLayout b_f4, LinearLayout b_e){

        b_g.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_f.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_f2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_f3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_f4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e.setBackgroundColor(Color.parseColor("#8BC34A"));


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
                        Intent he = new Intent(getApplicationContext(), hen.class);
                        startActivity(he);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent he = new Intent(getApplicationContext(), hen.class);
                        startActivity(he);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent he = new Intent(getApplicationContext(), hen.class);
                        startActivity(he);
                    }
                });
                break;
            default:
                //do nothing
                break;
        }
    }

    //disapproval
    public void disapproved(ImageView g, ImageView i, ImageView r, ImageView a, ImageView f, ImageView f2, ImageView f3, ImageView f4, ImageView e, LinearLayout b_g, LinearLayout b_i, LinearLayout b_r, LinearLayout b_a, LinearLayout b_f, LinearLayout b_f2, LinearLayout b_f3, LinearLayout b_f4, LinearLayout b_e){
        //objects for disapproval sounds
        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;


        //g
        if (g!= null ) {
            b_g.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_g.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //f1
        if (f!= null ) {
            b_f.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (f3!= null ) {
            b_f3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_f.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //f2
        if (f2!= null ) {
            b_f2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (f4!= null ) {
            b_f4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_f2.setBackgroundColor(Color.parseColor("#FF0000"));
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

        //a
        if (a!= null ) {
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_a.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e
        if (e!= null ) {
            b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e.setBackgroundColor(Color.parseColor("#FF0000"));
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
