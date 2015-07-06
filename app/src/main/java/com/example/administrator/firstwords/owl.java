package com.example.administrator.firstwords;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;

import java.util.Random;


public class owl extends Activity implements OnTouchListener, OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owl);

        mMediaPlayer = MediaPlayer.create(this, R.raw.owl);
        mMediaPlayer.start();


        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.owl, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.owl_o).setOnTouchListener(this);
        findViewById(R.id.owl_w).setOnTouchListener(this);
        findViewById(R.id.owl_l).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_w).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);

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
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        }
        else {
            return false;
        }
    }


    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.owl_o);

        LinearLayout b_w = (LinearLayout)findViewById(R.id.bottom_w);
        ImageView w = (ImageView) b_w.findViewById(R.id.owl_w);

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.owl_l);

        if ((o!= null) && (w!= null) && (l!=null)) {
            correct1 = MediaPlayer.create(this, R.raw.welldone);
            correct2 = MediaPlayer.create(this, R.raw.congrats);
            correct3 = MediaPlayer.create(this, R.raw.didit);

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent ra = new Intent(getApplicationContext(), rabbit.class);
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
                            Intent ra = new Intent(getApplicationContext(), rabbit.class);
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
                            Intent ra = new Intent(getApplicationContext(), rabbit.class);
                            startActivity(ra);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        else {

            incorr1 = MediaPlayer.create(this, R.raw.rusure);
            incorr2 = MediaPlayer.create(this, R.raw.incorrect);
            incorr3 = MediaPlayer.create(this, R.raw.tryagain);

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
