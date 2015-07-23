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


public class apple extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //initial sound
       mMediaPlayer = MediaPlayer.create(this, R.raw.apple);
       mMediaPlayer.start();


        //speaker btn sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.apple, 1);

        //check btn sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.apple_a).setOnTouchListener(this);
        findViewById(R.id.apple_p).setOnTouchListener(this);
        findViewById(R.id.apple_p2).setOnTouchListener(this);
        findViewById(R.id.apple_l).setOnTouchListener(this);
        findViewById(R.id.apple_e).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_p2).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);

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

        //letters to containers
        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.apple_a);

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.apple_l);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.apple_e);

        //p---------------------------------------------------------

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.apple_p);

        LinearLayout b_p2 = (LinearLayout)findViewById(R.id.bottom_p2);
        ImageView p2 = (ImageView) b_p2.findViewById(R.id.apple_p2);

        LinearLayout b_p3 = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p3 = (ImageView) b_p3.findViewById(R.id.apple_p2);

        LinearLayout b_p4 = (LinearLayout)findViewById(R.id.bottom_p2);
        ImageView p4 = (ImageView) b_p4.findViewById(R.id.apple_p);
    
        //approval / disapproval sounds
        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //redirection to the next screen if the letters are properly ordered
        if ((a!= null) && (l!= null) && (e!=null) && (((p!=null) && (p2!=null)) || ((p3!=null) && (p4!=null)))) {

            //sounds random generator
            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent banana = new Intent(getApplicationContext(), banana.class);
                            startActivity(banana);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent banana = new Intent(getApplicationContext(), banana.class);
                            startActivity(banana);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent banana = new Intent(getApplicationContext(), banana.class);
                            startActivity(banana);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        else {

            //disapproval sounds random generator
            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

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
