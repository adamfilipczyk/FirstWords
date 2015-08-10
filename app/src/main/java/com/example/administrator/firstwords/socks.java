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

public class socks extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socks);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.socks);
        mMediaPlayer.start();


        //speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.socks, 1);

        //check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.socks_s).setOnTouchListener(this);
        findViewById(R.id.socks_o).setOnTouchListener(this);
        findViewById(R.id.socks_c).setOnTouchListener(this);
        findViewById(R.id.socks_k).setOnTouchListener(this);
        findViewById(R.id.socks_s2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_k).setOnDragListener(this);
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



        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.socks_o);

        LinearLayout b_c = (LinearLayout)findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.socks_c);

        LinearLayout b_k = (LinearLayout)findViewById(R.id.bottom_k);
        ImageView k = (ImageView) b_k.findViewById(R.id.socks_k);


        //s----------------------------------------------------------

        LinearLayout b_s = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s1 = (ImageView) b_s.findViewById(R.id.socks_s);

        LinearLayout b_s2 = (LinearLayout)findViewById(R.id.bottom_s2);
        ImageView s2 = (ImageView) b_s2.findViewById(R.id.socks_s2);

        LinearLayout b_s3 = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s3 = (ImageView) b_s3.findViewById(R.id.socks_s2);

        LinearLayout b_s4 = (LinearLayout)findViewById(R.id.bottom_s2);
        ImageView s4 = (ImageView) b_s4.findViewById(R.id.socks_s);


        if ((o!= null) && (c!=null) && (k!= null) && (((s1!=null) && (s2!=null)) || ((s3!=null) && (s4!=null)))) {

            b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_s2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_s3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_s4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_k.setBackgroundColor(Color.parseColor("#8BC34A"));

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
                            Intent trousers = new Intent(getApplicationContext(), trousers.class);
                            startActivity(trousers);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent trousers = new Intent(getApplicationContext(), trousers.class);
                            startActivity(trousers);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent trousers = new Intent(getApplicationContext(), trousers.class);
                            startActivity(trousers);
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


            //s1
            if (s1!= null ) {
                b_s.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (s3!= null ) {
                b_s3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_s.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //s2
            if (s2!= null ) {
                b_s2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (s4!= null ) {
                b_s4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_s2.setBackgroundColor(Color.parseColor("#FF0000"));
            }


            //o
            if (o!= null ) {
                b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_o.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //c
            if (c!= null ) {
                b_c.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_c.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //k
            if (k!= null ) {
                b_k.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_k.setBackgroundColor(Color.parseColor("#FF0000"));
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

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
