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

public class onion extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onion);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.onion);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.onion, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.onion_o).setOnTouchListener(this);
        findViewById(R.id.onion_n).setOnTouchListener(this);
        findViewById(R.id.onion_i).setOnTouchListener(this);
        findViewById(R.id.onion_o2).setOnTouchListener(this);
        findViewById(R.id.onion_n2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_i).setOnDragListener(this);
        findViewById(R.id.bottom_o2).setOnDragListener(this);
        findViewById(R.id.bottom_n2).setOnDragListener(this);

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

        LinearLayout b_i = (LinearLayout)findViewById(R.id.bottom_i);
        ImageView i = (ImageView) b_i.findViewById(R.id.onion_i);

        //n----------------------------------------------------------

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n1 = (ImageView) b_n.findViewById(R.id.onion_n);

        LinearLayout b_n2 = (LinearLayout)findViewById(R.id.bottom_n2);
        ImageView n2 = (ImageView) b_n2.findViewById(R.id.onion_n2);

        LinearLayout b_n3 = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n3 = (ImageView) b_n3.findViewById(R.id.onion_n2);

        LinearLayout b_n4 = (LinearLayout)findViewById(R.id.bottom_n2);
        ImageView n4 = (ImageView) b_n4.findViewById(R.id.onion_n);

        //o----------------------------------------------------------

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o1 = (ImageView) b_o.findViewById(R.id.onion_o);

        LinearLayout b_o2 = (LinearLayout)findViewById(R.id.bottom_o2);
        ImageView o2 = (ImageView) b_o2.findViewById(R.id.onion_o2);

        LinearLayout b_o3 = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o3 = (ImageView) b_o3.findViewById(R.id.onion_o2);

        LinearLayout b_o4 = (LinearLayout)findViewById(R.id.bottom_o2);
        ImageView o4 = (ImageView) b_o4.findViewById(R.id.onion_o);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((i!= null) && (((n1!=null) && (n2!=null)) || ((n3!=null) && (n4!=null))) && (((o1!=null) && (o2!=null)) || ((o3!=null) && (o4!=null)))) {

            b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_o2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_o3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_o4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_n4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_i.setBackgroundColor(Color.parseColor("#8BC34A"));

            Random generate = new Random();
            int eventNumber = generate.nextInt(3)+1;

            switch (eventNumber){
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent pea = new Intent(getApplicationContext(), pear.class);
                            startActivity(pea);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent pea = new Intent(getApplicationContext(), pear.class);
                            startActivity(pea);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent pea = new Intent(getApplicationContext(), pear.class);
                            startActivity(pea);
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

            //i
            if (i!= null ) {
                b_i.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_i.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //o1
            if (o1!= null ) {
                b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (o3!= null ) {
                b_o3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_o.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //o2
            if (o2!= null ) {
                b_o2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (o4!= null ) {
                b_o4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_o2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //n1
            if (n1!= null ) {
                b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (n3!= null ) {
                b_n3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_n.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //n2
            if (n2!= null ) {
                b_n2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (n4!= null ) {
                b_n4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_n2.setBackgroundColor(Color.parseColor("#FF0000"));
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
