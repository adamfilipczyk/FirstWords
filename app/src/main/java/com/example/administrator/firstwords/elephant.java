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

public class elephant extends Activity implements OnTouchListener, OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elephant);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.elephant);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.elephant, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.elephant_e).setOnTouchListener(this);
        findViewById(R.id.elephant_l).setOnTouchListener(this);
        findViewById(R.id.elephant_e2).setOnTouchListener(this);
        findViewById(R.id.elephant_p).setOnTouchListener(this);
        findViewById(R.id.elephant_h).setOnTouchListener(this);
        findViewById(R.id.elephant_a).setOnTouchListener(this);
        findViewById(R.id.elephant_n).setOnTouchListener(this);
        findViewById(R.id.elephant_t).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
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

        //e----------------------------------------------------------

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.elephant_e);

        LinearLayout b_e2 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.elephant_e2);

        LinearLayout b_e3 = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.elephant_e2);

        LinearLayout b_e4 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.elephant_e);


        //-------------------------------------------------------------

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.elephant_l);

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.elephant_p);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.elephant_h);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.elephant_a);

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.elephant_n);

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.elephant_t);

        if ((((e!= null) && (e2!=null)) || ((e3!= null) && (e4!=null))) && (l!=null) && (p!= null) && (h!= null) && (a!=null) && (n!= null) && (t!=null)) {
            approved(e, e2, e3, e4, l, p, h, a, n, t, b_e, b_e2, b_e3, b_e4, b_l, b_p, b_h, b_a, b_n, b_t);
        }
        else {
            disapproved(e, e2, e3, e4, l, p, h, a, n, t, b_e, b_e2, b_e3, b_e4, b_l, b_p, b_h, b_a, b_n, b_t);
        }

    }

    //approvals
    public void approved(ImageView e, ImageView e2, ImageView e3, ImageView e4, ImageView l, ImageView p, ImageView h, ImageView a, ImageView n, ImageView t, LinearLayout b_e, LinearLayout b_e2, LinearLayout b_e3, LinearLayout b_e4, LinearLayout b_l, LinearLayout b_p, LinearLayout b_h, LinearLayout b_a, LinearLayout b_n, LinearLayout b_t){

        b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
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
                        Intent girr = new Intent(getApplicationContext(), giraffe.class);
                        startActivity(girr);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent girr = new Intent(getApplicationContext(), giraffe.class);
                        startActivity(girr);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent girr = new Intent(getApplicationContext(), giraffe.class);
                        startActivity(girr);
                    }
                });
                break;
            default:
                //do nothing
                break;
        }
    }

    //disapproval
    public void disapproved(ImageView e, ImageView e2, ImageView e3, ImageView e4, ImageView l, ImageView p, ImageView h, ImageView a, ImageView n, ImageView t, LinearLayout b_e, LinearLayout b_e2, LinearLayout b_e3, LinearLayout b_e4, LinearLayout b_l, LinearLayout b_p, LinearLayout b_h, LinearLayout b_a, LinearLayout b_n, LinearLayout b_t){
        //objects for disapproval sounds
        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;


        //d
        if (l!= null ) {
            b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_l.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e1
        if (e!= null ) {
            b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e3!= null ) {
            b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e2
        if (e2!= null ) {
            b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e4!= null ) {
            b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p
        if (p!= null ) {
            b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_p.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //h
        if (h!= null ) {
            b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_h.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //a
        if (a!= null ) {
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_a.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //h
        if (n!= null ) {
            b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_n.setBackgroundColor(Color.parseColor("#FF0000"));
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
}
