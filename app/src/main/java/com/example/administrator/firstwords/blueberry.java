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


public class blueberry extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueberry);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //initial sound
        mMediaPlayer = MediaPlayer.create(this, R.raw.blueberry);
        mMediaPlayer.start();


        //speaker btn sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.blueberry, 1);

        //check btn sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.blueberry_b).setOnTouchListener(this);
        findViewById(R.id.blueberry_l).setOnTouchListener(this);
        findViewById(R.id.blueberry_u).setOnTouchListener(this);
        findViewById(R.id.blueberry_e).setOnTouchListener(this);
        findViewById(R.id.blueberry_b2).setOnTouchListener(this);
        findViewById(R.id.blueberry_e2).setOnTouchListener(this);
        findViewById(R.id.blueberry_r).setOnTouchListener(this);
        findViewById(R.id.blueberry_r2).setOnTouchListener(this);
        findViewById(R.id.blueberry_y).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);
        findViewById(R.id.bottom_u).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_b2).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_r2).setOnDragListener(this);
        findViewById(R.id.bottom_y).setOnDragListener(this);

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

        LinearLayout b_y = (LinearLayout)findViewById(R.id.bottom_y);
        ImageView y = (ImageView) b_y.findViewById(R.id.blueberry_y);

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.blueberry_l);

        LinearLayout b_u = (LinearLayout)findViewById(R.id.bottom_u);
        ImageView u = (ImageView) b_u.findViewById(R.id.blueberry_u);


        //b-------------------------------------------------------------

        LinearLayout b_b1 = (LinearLayout)findViewById(R.id.bottom_b);
        ImageView b1 = (ImageView) b_b1.findViewById(R.id.blueberry_b);

        LinearLayout b_b2 = (LinearLayout)findViewById(R.id.bottom_b2);
        ImageView b2 = (ImageView) b_b2.findViewById(R.id.blueberry_b2);

        LinearLayout b_b3 = (LinearLayout)findViewById(R.id.bottom_b);
        ImageView b3 = (ImageView) b_b3.findViewById(R.id.blueberry_b2);

        LinearLayout b_b4 = (LinearLayout)findViewById(R.id.bottom_b2);
        ImageView b4 = (ImageView) b_b4.findViewById(R.id.blueberry_b);

        //e--------------------------------------------------------------

        LinearLayout b_e1 = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e1.findViewById(R.id.blueberry_e);

        LinearLayout b_e2 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.blueberry_e2);

        LinearLayout b_e3 = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.blueberry_e2);

        LinearLayout b_e4 = (LinearLayout)findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.blueberry_e);

        //r--------------------------------------------------------------

        LinearLayout b_r1 = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r1 = (ImageView) b_r1.findViewById(R.id.blueberry_r);

        LinearLayout b_r2 = (LinearLayout)findViewById(R.id.bottom_r2);
        ImageView r2 = (ImageView) b_r2.findViewById(R.id.blueberry_r2);

        LinearLayout b_r3 = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r3 = (ImageView) b_r3.findViewById(R.id.blueberry_r2);

        LinearLayout b_r4 = (LinearLayout)findViewById(R.id.bottom_r2);
        ImageView r4 = (ImageView) b_r4.findViewById(R.id.blueberry_r);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((l!= null) && (u!= null) && (y!=null) && (((b1!=null) && (b2!=null)) || ((b3!=null) && (b4!=null))) && (((e1!=null) && (e2!=null)) || ((e3!=null) && (e4!=null))) && (((r1!=null) && (r2!=null)) || ((r3!=null) && (r4!=null)))) {

            //change background color of the containers to green
            b_b1.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_b2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_b3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_b4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_u.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r1.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_y.setBackgroundColor(Color.parseColor("#8BC34A"));


            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent cab = new Intent(getApplicationContext(), cabbage.class);
                            startActivity(cab);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent cab = new Intent(getApplicationContext(), cabbage.class);
                            startActivity(cab);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent cab = new Intent(getApplicationContext(), cabbage.class);
                            startActivity(cab);
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


            //b1
            if (b1!= null ) {
                b_b1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (b3!= null) {
                b_b3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_b1.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //b2
            if (b2!= null ){
                b_b2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (b4!= null ){
                b_b4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_b2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //l
            if (l!= null){
                b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_l.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //u
            if (u!= null){
                b_u.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_u.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //e1
            if (e1!=null ){
                b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (e3!=null ){
                b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_e1.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //e2
            if (e2!=null ){
                b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (e4!=null ){
                b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_e2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r1
            if (r1!=null){
                b_r1.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (r3!=null){
                b_r3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r1.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r2
            if (r2!=null){
                b_r2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (r4!=null){
                b_r4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //y
            if (y!=null ){
                b_y.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_y.setBackgroundColor(Color.parseColor("#FF0000"));
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
