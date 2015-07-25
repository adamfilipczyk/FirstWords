package com.example.administrator.firstwords;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
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

public class speaker extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.speaker);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.speaker, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.speaker_s).setOnTouchListener(this);
        findViewById(R.id.speaker_p).setOnTouchListener(this);
        findViewById(R.id.speaker_e).setOnTouchListener(this);
        findViewById(R.id.speaker_a).setOnTouchListener(this);
        findViewById(R.id.speaker_k).setOnTouchListener(this);
        findViewById(R.id.speaker_e2).setOnTouchListener(this);
        findViewById(R.id.speaker_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_k).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);

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

        LinearLayout b_s = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s = (ImageView) b_s.findViewById(R.id.speaker_s);

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.speaker_p);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.speaker_a);

        LinearLayout b_k = (LinearLayout)findViewById(R.id.bottom_k);
        ImageView k = (ImageView) b_k.findViewById(R.id.speaker_k);

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.speaker_r);

        //e---------------------------------------------------------

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e.findViewById(R.id.speaker_e);

        LinearLayout b_e2 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.speaker_e2);

        LinearLayout b_e3 = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.speaker_e2);

        LinearLayout b_e4 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.speaker_e);



        if ((s!= null) && (p!= null) && (a!=null) && (k!=null) &&  (r!= null) && (((e1!=null) && (e2!=null)) || ((e3!=null) && (e4!=null)))) {

            b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_k.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));

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
                            Intent telephone = new Intent(getApplicationContext(), telephone.class);
                            startActivity(telephone);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent telephone = new Intent(getApplicationContext(), telephone.class);
                            startActivity(telephone);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent telephone = new Intent(getApplicationContext(), telephone.class);
                            startActivity(telephone);
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


            //e1
            if (e1!= null ) {
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

            //s
            if (s!= null ) {
                b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_s.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //p
            if (p!= null ) {
                b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_p.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //a
            if (a!= null ) {
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //k
            if (k!= null ) {
                b_k.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_k.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r
            if (r!= null ) {
                b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r.setBackgroundColor(Color.parseColor("#FF0000"));
            }

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
