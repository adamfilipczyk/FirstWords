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

public class wardrobe extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.wardrobe);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.wardrobe, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.wardrobe_w).setOnTouchListener(this);
        findViewById(R.id.wardrobe_a).setOnTouchListener(this);
        findViewById(R.id.wardrobe_r).setOnTouchListener(this);
        findViewById(R.id.wardrobe_d).setOnTouchListener(this);
        findViewById(R.id.wardrobe_r2).setOnTouchListener(this);
        findViewById(R.id.wardrobe_o).setOnTouchListener(this);
        findViewById(R.id.wardrobe_b).setOnTouchListener(this);
        findViewById(R.id.wardrobe_e).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_w).setOnDragListener(this);
        findViewById(R.id.bottom_a).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_d).setOnDragListener(this);
        findViewById(R.id.bottom_r2).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);

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

        LinearLayout b_w = (LinearLayout)findViewById(R.id.bottom_w);
        ImageView w = (ImageView) b_w.findViewById(R.id.wardrobe_w);

        LinearLayout b_a = (LinearLayout)findViewById(R.id.bottom_a);
        ImageView a = (ImageView) b_a.findViewById(R.id.wardrobe_a);

        LinearLayout b_d = (LinearLayout)findViewById(R.id.bottom_d);
        ImageView d = (ImageView) b_d.findViewById(R.id.wardrobe_d);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.wardrobe_o);

        LinearLayout b_b = (LinearLayout)findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.wardrobe_b);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.wardrobe_e);

        //o---------------------------------------------------------

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r1 = (ImageView) b_r.findViewById(R.id.wardrobe_r);

        LinearLayout b_r2 = (LinearLayout)findViewById(R.id.bottom_r2);
        ImageView r2 = (ImageView) b_r2.findViewById(R.id.wardrobe_r2);

        LinearLayout b_r3 = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r3 = (ImageView) b_r3.findViewById(R.id.wardrobe_r2);

        LinearLayout b_r4 = (LinearLayout)findViewById(R.id.bottom_r2);
        ImageView r4 = (ImageView) b_r4.findViewById(R.id.wardrobe_r);




        if ((w!= null) && (a!= null) && (d!=null ) && (o!= null) && (b!= null) && (e!=null ) && (((r1!=null) && (r2!=null)) || ((r3!=null) && (r4!=null)))) {


            b_w.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r2.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r3.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_r4.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_d.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            b_b.setBackgroundColor(Color.parseColor("#8BC34A"));
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
                            Intent window = new Intent(getApplicationContext(), window.class);
                            startActivity(window);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent window = new Intent(getApplicationContext(), window.class);
                            startActivity(window);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent window = new Intent(getApplicationContext(), window.class);
                            startActivity(window);
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

            //w
            if (w!= null ) {
                b_w.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_w.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //a
            if (a!= null ) {
                b_a.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_a.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r1
            if (r1!= null ) {
                b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (r3!= null ) {
                b_r3.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //r2
            if (r2!= null ) {
                b_r2.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else if (r4!= null ) {
                b_r4.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_r2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //d
            if (d!= null ) {
                b_d.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_d.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //o
            if (o!= null ) {
                b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_o.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            //b
            if (b!= null ) {
                b_b.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
            else {
                b_b.setBackgroundColor(Color.parseColor("#FF0000"));
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

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
