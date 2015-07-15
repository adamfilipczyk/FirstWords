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

public class dentist extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentist);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.dentist);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.dentist, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.dentist_d).setOnTouchListener(this);
        findViewById(R.id.dentist_e).setOnTouchListener(this);
        findViewById(R.id.dentist_n).setOnTouchListener(this);
        findViewById(R.id.dentist_t).setOnTouchListener(this);
        findViewById(R.id.dentist_i).setOnTouchListener(this);
        findViewById(R.id.dentist_s).setOnTouchListener(this);
        findViewById(R.id.dentist_t2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_d).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_t2).setOnDragListener(this);


        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }

    //method to play the sound of the picture displayed on the screen
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

        LinearLayout b_d = (LinearLayout)findViewById(R.id.bottom_d);
        ImageView d = (ImageView) b_d.findViewById(R.id.dentist_d);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.dentist_e);

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.dentist_n);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.dentist_i);

        LinearLayout b_s = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s = (ImageView) b_s.findViewById(R.id.dentist_s);


        //t---------------------------------------------------------

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t1 = (ImageView) b_t.findViewById(R.id.dentist_t);

        LinearLayout b_t2 = (LinearLayout)findViewById(R.id.bottom_t2);
        ImageView t2 = (ImageView) b_t2.findViewById(R.id.dentist_t2);

        LinearLayout b_t3 = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t3 = (ImageView) b_t3.findViewById(R.id.dentist_t2);

        LinearLayout b_t4 = (LinearLayout)findViewById(R.id.bottom_t2);
        ImageView t4 = (ImageView) b_t4.findViewById(R.id.dentist_t);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((d!= null) && (e!= null) && (n!= null) && (i!= null) && (s!= null) && ((t1!=null && t2!=null) || (t3!=null && t4!=null))) {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent doctor = new Intent(getApplicationContext(), doctor.class);
                            startActivity(doctor);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent doctor = new Intent(getApplicationContext(), doctor.class);
                            startActivity(doctor);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent doctor = new Intent(getApplicationContext(), doctor.class);
                            startActivity(doctor);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        else {

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
