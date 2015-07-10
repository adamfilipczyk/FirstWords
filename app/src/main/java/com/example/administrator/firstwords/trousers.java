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

public class trousers extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trousers);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.trousers);
        mMediaPlayer.start();


        //speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.trousers, 1);

        //check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.trousers_t).setOnTouchListener(this);
        findViewById(R.id.trousers_r).setOnTouchListener(this);
        findViewById(R.id.trousers_o).setOnTouchListener(this);
        findViewById(R.id.trousers_u).setOnTouchListener(this);
        findViewById(R.id.trousers_s).setOnTouchListener(this);
        findViewById(R.id.trousers_e).setOnTouchListener(this);
        findViewById(R.id.trousers_r2).setOnTouchListener(this);
        findViewById(R.id.trousers_s2).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_u).setOnDragListener(this);
        findViewById(R.id.bottom_s).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_r2).setOnDragListener(this);
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

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.trousers_t);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.trousers_o);

        LinearLayout b_u = (LinearLayout)findViewById(R.id.bottom_u);
        ImageView u = (ImageView) b_u.findViewById(R.id.trousers_u);

        LinearLayout b_e = (LinearLayout)findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.trousers_e);

        //r----------------------------------------------------------

        LinearLayout b_r = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r1 = (ImageView) b_r.findViewById(R.id.trousers_r);

        LinearLayout b_r2 = (LinearLayout)findViewById(R.id.bottom_r2);
        ImageView r2 = (ImageView) b_r2.findViewById(R.id.trousers_r2);

        LinearLayout b_r3 = (LinearLayout)findViewById(R.id.bottom_r);
        ImageView r3 = (ImageView) b_r3.findViewById(R.id.trousers_r2);

        LinearLayout b_r4 = (LinearLayout)findViewById(R.id.bottom_r2);
        ImageView r4 = (ImageView) b_r4.findViewById(R.id.trousers_r);


        //s----------------------------------------------------------

        LinearLayout b_s = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s1 = (ImageView) b_s.findViewById(R.id.trousers_s);

        LinearLayout b_s2 = (LinearLayout)findViewById(R.id.bottom_s2);
        ImageView s2 = (ImageView) b_s2.findViewById(R.id.trousers_s2);

        LinearLayout b_s3 = (LinearLayout)findViewById(R.id.bottom_s);
        ImageView s3 = (ImageView) b_s3.findViewById(R.id.trousers_s2);

        LinearLayout b_s4 = (LinearLayout)findViewById(R.id.bottom_s2);
        ImageView s4 = (ImageView) b_s4.findViewById(R.id.trousers_s);

        if ((t!= null) && (o!= null) && (u!=null) && (e!= null) && ((s1!=null && s2!=null) || (s3!=null && s4!=null)) && ((r1!=null && r2!=null) || (r3!=null && r4!=null))) {

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
                            Intent tie = new Intent(getApplicationContext(), tie.class);
                            startActivity(tie);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent tie = new Intent(getApplicationContext(), tie.class);
                            startActivity(tie);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent tie = new Intent(getApplicationContext(), tie.class);
                            startActivity(tie);
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
