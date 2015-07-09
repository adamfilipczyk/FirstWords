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
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;


public class shoes extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);
        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.shoes);
        mMediaPlayer.start();


        //speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.shoes, 1);

        //check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.shoes_s).setOnTouchListener(this);
        findViewById(R.id.shoes_o).setOnTouchListener(this);
        findViewById(R.id.shoes_e).setOnTouchListener(this);
        findViewById(R.id.shoes_h).setOnTouchListener(this);
        findViewById(R.id.shoes_s2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_s2).setOnDragListener(this);

        //top container listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //sound of the picture displayed on the screen
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //sound when the button clicked; redirect to home screen
    public void back(View view) {
        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    //dragging object
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

    //shadow object
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

    //final check
    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.shoes_o);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.shoes_e);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.shoes_h);

        //s----------------------------------------------------------

        LinearLayout b_s = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s1 = (ImageView) b_s.findViewById(R.id.shoes_s);

        LinearLayout b_s2 = (LinearLayout)findViewById(R.id.bottom_s2);
        ImageView s2 = (ImageView) b_s2.findViewById(R.id.shoes_s2);

        LinearLayout b_s3 = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s3 = (ImageView) b_s3.findViewById(R.id.shoes_s2);

        LinearLayout b_s4 = (LinearLayout)findViewById(R.id.bottom_s2);
        ImageView s4 = (ImageView) b_s4.findViewById(R.id.shoes_s);

        if ((h!= null) && (o!= null) && (e!=null) && ((s1!=null && s2!=null) || (s3!=null && s4!=null))) {

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
                            Intent shorts = new Intent(getApplicationContext(), shorts.class);
                            startActivity(shorts);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent shorts = new Intent(getApplicationContext(), shorts.class);
                            startActivity(shorts);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent shorts = new Intent(getApplicationContext(), shorts.class);
                            startActivity(shorts);
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
