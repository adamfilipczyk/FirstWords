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

public class tie extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tie);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.tie);
        mMediaPlayer.start();


        //speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.tie, 1);

        //check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.tie_t).setOnTouchListener(this);
        findViewById(R.id.tie_i).setOnTouchListener(this);
        findViewById(R.id.tie_e).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);


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



        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.tie_t);

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.tie_i);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.tie_e);




        if ((t!= null) && (i!=null) && (e!= null)) {

            b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e.setBackgroundColor(Color.parseColor("#8BC34A"));

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
                            Intent tshirt = new Intent(getApplicationContext(), tshirt.class);
                            startActivity(tshirt);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent tshirt = new Intent(getApplicationContext(), tshirt.class);
                            startActivity(tshirt);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent tshirt = new Intent(getApplicationContext(), tshirt.class);
                            startActivity(tshirt);
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


            //t
            if (t!= null ) {
                b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_t.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //i
            if (i!= null ) {
                b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_i.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //e
            if (e!= null ) {
                b_e.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_e.setBackgroundColor(Color.parseColor("#FF0000"));
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
