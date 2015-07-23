package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.ClipData;
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

public class potato extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potato);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.potato);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.potato, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.potato_p).setOnTouchListener(this);
        findViewById(R.id.potato_o).setOnTouchListener(this);
        findViewById(R.id.potato_t).setOnTouchListener(this);
        findViewById(R.id.potato_a).setOnTouchListener(this);
        findViewById(R.id.potato_t2).setOnTouchListener(this);
        findViewById(R.id.potato_o2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_t2).setOnDragListener(this);
        findViewById(R.id.bottom_o2).setOnDragListener(this);

        //top container drag listener
        findViewById(R.id.top_c).setOnDragListener(this);
    }
    //play the sound of the picture displayed on the screen after "Speaker" button is clicked
    public void play(View view) {
        sound.play(soundID, 1, 1, 1, 0, 1);
    }

    //play the "Home" button click sound and redirection to "Home" screen
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

    //final check
    public void check (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        LinearLayout b_p = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.potato_p);

        LinearLayout b_a = (LinearLayout) findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.potato_a);


        //o-------------------------------------------------------------

        LinearLayout b_o1 = (LinearLayout) findViewById(R.id.bottom_o);
        ImageView o1 = (ImageView) b_o1.findViewById(R.id.potato_o);

        LinearLayout b_o2 = (LinearLayout) findViewById(R.id.bottom_o2);
        ImageView o2 = (ImageView) b_o2.findViewById(R.id.potato_o2);

        LinearLayout b_b3 = (LinearLayout) findViewById(R.id.bottom_o);
        ImageView o3 = (ImageView) b_b3.findViewById(R.id.potato_o2);

        LinearLayout b_o4 = (LinearLayout) findViewById(R.id.bottom_o2);
        ImageView o4 = (ImageView) b_o4.findViewById(R.id.potato_o);

        //t--------------------------------------------------------------

        LinearLayout b_t1 = (LinearLayout) findViewById(R.id.bottom_t);
        ImageView t1 = (ImageView) b_t1.findViewById(R.id.potato_t);

        LinearLayout b_t2 = (LinearLayout) findViewById(R.id.bottom_t2);
        ImageView t2 = (ImageView) b_t2.findViewById(R.id.potato_t2);

        LinearLayout b_t3 = (LinearLayout) findViewById(R.id.bottom_t);
        ImageView t3 = (ImageView) b_t3.findViewById(R.id.potato_t2);

        LinearLayout b_t4 = (LinearLayout) findViewById(R.id.bottom_t2);
        ImageView t4 = (ImageView) b_t4.findViewById(R.id.potato_t);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((p != null) && (a != null)&& (((o1 != null) && (o2 != null)) || ((o3 != null) && (o4 != null))) && (((t1 != null) && (t2 != null)) || ((t3 != null) && (t4 != null)))) {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent pum = new Intent(getApplicationContext(), pumpkin.class);
                            startActivity(pum);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent pum = new Intent(getApplicationContext(), pumpkin.class);
                            startActivity(pum);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent pum = new Intent(getApplicationContext(), pumpkin.class);
                            startActivity(pum);
                        }
                    });
                    break;
                default:
                    //do nothing
                    break;
            }
        } else {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

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
}
