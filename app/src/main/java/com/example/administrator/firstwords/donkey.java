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


public class donkey extends Activity implements OnTouchListener, OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donkey);

        MediaPlayer mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.donkey);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.donkey, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.donkey_d).setOnTouchListener(this);
        findViewById(R.id.donkey_o).setOnTouchListener(this);
        findViewById(R.id.donkey_n).setOnTouchListener(this);
        findViewById(R.id.donkey_k).setOnTouchListener(this);
        findViewById(R.id.donkey_e).setOnTouchListener(this);
        findViewById(R.id.donkey_y).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_d).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_k).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_y).setOnDragListener(this);

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

        LinearLayout b_d = (LinearLayout)findViewById(R.id.bottom_d);
        ImageView d = (ImageView) b_d.findViewById(R.id.donkey_d);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.donkey_o);

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.donkey_n);

        LinearLayout b_k = (LinearLayout)findViewById(R.id.bottom_k);
        ImageView k = (ImageView) b_k.findViewById(R.id.donkey_k);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.donkey_e);

        LinearLayout b_y = (LinearLayout)findViewById(R.id.bottom_y);
        ImageView y = (ImageView) b_y.findViewById(R.id.donkey_y);

        if ((d!= null) && (o!= null) && (n!=null) && (k!= null) && (e!= null) && (y!=null)) {
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
                            Intent eleph = new Intent(getApplicationContext(), elephant.class);
                            startActivity(eleph);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent eleph = new Intent(getApplicationContext(), elephant.class);
                            startActivity(eleph);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent eleph = new Intent(getApplicationContext(), elephant.class);
                            startActivity(eleph);
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
