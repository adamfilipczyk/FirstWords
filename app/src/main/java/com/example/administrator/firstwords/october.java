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

public class october  extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_october);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.october);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.october, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.october_o).setOnTouchListener(this);
        findViewById(R.id.october_c).setOnTouchListener(this);
        findViewById(R.id.october_t).setOnTouchListener(this);
        findViewById(R.id.october_o2).setOnTouchListener(this);
        findViewById(R.id.october_b).setOnTouchListener(this);
        findViewById(R.id.october_e).setOnTouchListener(this);
        findViewById(R.id.october_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_c).setOnDragListener(this);
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_o2).setOnDragListener(this);
        findViewById(R.id.bottom_b).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_r).setOnDragListener(this);

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

        LinearLayout b_c = (LinearLayout) findViewById(R.id.bottom_c);
        ImageView c = (ImageView) b_c.findViewById(R.id.october_c);

        LinearLayout b_t = (LinearLayout) findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.october_t);

        LinearLayout b_b = (LinearLayout) findViewById(R.id.bottom_b);
        ImageView b = (ImageView) b_b.findViewById(R.id.october_b);

        LinearLayout b_e = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e = (ImageView) b_e.findViewById(R.id.october_e);

        LinearLayout b_r = (LinearLayout) findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.october_r);

        //o---------------------------------------------------------

        LinearLayout b_o1 = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o1 = (ImageView) b_o1.findViewById(R.id.october_o);

        LinearLayout b_o2 = (LinearLayout)findViewById(R.id.bottom_o2);
        ImageView o2 = (ImageView) b_o2.findViewById(R.id.october_o2);

        LinearLayout b_o3 = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o3 = (ImageView) b_o3.findViewById(R.id.october_o2);

        LinearLayout b_o4 = (LinearLayout)findViewById(R.id.bottom_o2);
        ImageView o4 = (ImageView) b_o4.findViewById(R.id.october_o);


        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        if ((c != null) && (t != null) && (b != null) && (e != null) && (r != null) && (((o1!=null) && (o2!=null)) || ((o3!=null) && (o4!=null)))) {

            Random generate = new Random();
            int eventNumber = generate.nextInt(3) + 1;

            switch (eventNumber) {
                case 1:
                    correct1.start();
                    correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent november = new Intent(getApplicationContext(), november.class);
                            startActivity(november);
                        }
                    });
                    break;
                case 2:
                    correct2.start();
                    correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent november = new Intent(getApplicationContext(), november.class);
                            startActivity(november);
                        }
                    });
                    break;
                case 3:
                    correct3.start();
                    correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                            finish();
                            Intent november = new Intent(getApplicationContext(), november.class);
                            startActivity(november);
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
