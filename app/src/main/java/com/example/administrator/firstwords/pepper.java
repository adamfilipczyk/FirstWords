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

public class pepper extends Activity implements View.OnTouchListener, View.OnDragListener {

    SoundPool sound, click;
    int soundID;
    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pepper);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //player
        mMediaPlayer = MediaPlayer.create(this, R.raw.pepper);
        mMediaPlayer.start();


        //object for the speaker sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.pepper, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);


        //letters
        findViewById(R.id.pepper_p).setOnTouchListener(this);
        findViewById(R.id.pepper_e).setOnTouchListener(this);
        findViewById(R.id.pepper_p2).setOnTouchListener(this);
        findViewById(R.id.pepper_p3).setOnTouchListener(this);
        findViewById(R.id.pepper_e2).setOnTouchListener(this);
        findViewById(R.id.pepper_r).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_p2).setOnDragListener(this);
        findViewById(R.id.bottom_p3).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
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
    public void check(View view) {

        //click sound for "Check" button
        click.play(soundID, 1, 1, 1, 0, 1);

        //all possible combinations of letters and containers
        //r------------------------------------------------------------

        LinearLayout b_r = (LinearLayout) findViewById(R.id.bottom_r);
        ImageView r = (ImageView) b_r.findViewById(R.id.pepper_r);

        //e1------------------------------------------------------------

        LinearLayout b_e1 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e1.findViewById(R.id.pepper_e);

        LinearLayout b_e2 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.pepper_e2);

        //e2-------------------------------------------------------------

        LinearLayout b_e3 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.pepper_e2);

        LinearLayout b_e4 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.pepper_e);

        //p1------------------------------------------------------------

        LinearLayout b_a1 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a1 = (ImageView) b_a1.findViewById(R.id.pepper_p);

        LinearLayout b_a2 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a2 = (ImageView) b_a2.findViewById(R.id.pepper_p2);

        LinearLayout b_a3 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a3 = (ImageView) b_a3.findViewById(R.id.pepper_p3);

        //p2-------------------------------------------------------------

        LinearLayout b_a4 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a4 = (ImageView) b_a4.findViewById(R.id.pepper_p);

        LinearLayout b_a5 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a5 = (ImageView) b_a5.findViewById(R.id.pepper_p2);

        LinearLayout b_a6 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a6 = (ImageView) b_a6.findViewById(R.id.pepper_p3);

        //p3-------------------------------------------------------------

        LinearLayout b_a7 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a7 = (ImageView) b_a7.findViewById(R.id.pepper_p);

        LinearLayout b_a8 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a8 = (ImageView) b_a8.findViewById(R.id.pepper_p2);

        LinearLayout b_a9 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a9 = (ImageView) b_a9.findViewById(R.id.pepper_p3);

        //p4-------------------------------------------------------------

        LinearLayout b_a10 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a10 = (ImageView) b_a10.findViewById(R.id.pepper_p);

        LinearLayout b_a11 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a11 = (ImageView) b_a11.findViewById(R.id.pepper_p2);

        LinearLayout b_a12 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a12 = (ImageView) b_a12.findViewById(R.id.pepper_p3);

        //p5-------------------------------------------------------------

        LinearLayout b_a13 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a13 = (ImageView) b_a13.findViewById(R.id.pepper_p3);

        LinearLayout b_a14 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a14 = (ImageView) b_a14.findViewById(R.id.pepper_p2);

        LinearLayout b_a15 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a15 = (ImageView) b_a15.findViewById(R.id.pepper_p);

        //p5-------------------------------------------------------------

        LinearLayout b_a16 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a16 = (ImageView) b_a16.findViewById(R.id.pepper_p2);

        LinearLayout b_a17 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a17 = (ImageView) b_a17.findViewById(R.id.pepper_p);

        LinearLayout b_a18 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a18 = (ImageView) b_a18.findViewById(R.id.pepper_p3);

        //p6-------------------------------------------------------------

        LinearLayout b_a19 = (LinearLayout) findViewById(R.id.bottom_p);
        ImageView a19 = (ImageView) b_a19.findViewById(R.id.pepper_p);

        LinearLayout b_a20 = (LinearLayout) findViewById(R.id.bottom_p3);
        ImageView a20 = (ImageView) b_a20.findViewById(R.id.pepper_p2);

        LinearLayout b_a21 = (LinearLayout) findViewById(R.id.bottom_p2);
        ImageView a21 = (ImageView) b_a21.findViewById(R.id.pepper_p3);



        //check if containers are not empty and all letters are in proper place
        if ((r != null) && (((e1 != null) && (e2 != null)) || ((e3 != null) && (e4 != null))) && ((a1!= null) && (a2!= null) && (a3!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else if ((r != null) && (((e1 != null) && (e2 != null)) || ((e3 != null) && (e4 != null))) && ((a4!= null) && (a5!= null) && (a6!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else if ((r != null) && (((e1 != null) && (e2 != null)) || ((e3 != null) && (e4 != null))) && ((a7!= null) && (a8!= null) && (a9!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else if ((r != null) && (((e1 != null) && (e2 != null) || (e3 != null) && (e4 != null))) && ((a10!= null) && (a11!= null) && (a12!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else if ((r != null) && (((e1 != null) && (e2 != null)) || (e3 != null) && (e4 != null)) && ((a13!= null) && (a14!= null) && (a15!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else if ((r != null) && (((e1 != null) && (e2 != null)) || ((e3 != null) && (e4 != null))) && ((a16!= null) && (a17!= null) && (a18!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else if ((r != null) && (((e1 != null) && (e2 != null)) || ((e3 != null) && (e4 != null))) && ((a19!= null) && (a20!= null) && (a21!= null))){
            approved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
        else {
            disapproved(a1, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a2, a20, a21, a3, a4, a5, a6, a7, a8, a9, e1, e2, e3, e4, b_a1, b_a10, b_a11, b_a12, b_a13, b_a14, b_a15, b_a16, b_a17, b_a18, b_a19, b_a2, b_a20, b_a21, b_a3, b_a4, b_a5, b_a6, b_a7, b_a8, b_a9, r, b_r, b_e1, b_e2, b_e3, b_e4);
        }
    }

    //approvals
    public void approved(ImageView a1, ImageView a10, ImageView a11, ImageView a12, ImageView a13, ImageView a14, ImageView a15, ImageView a16, ImageView a17, ImageView a18, ImageView a19, ImageView a2, ImageView a20, ImageView a21, ImageView a3, ImageView a4, ImageView a5, ImageView a6, ImageView a7, ImageView a8, ImageView a9, ImageView e1, ImageView e2, ImageView e3, ImageView e4, LinearLayout b_a1, LinearLayout b_a10, LinearLayout b_a11, LinearLayout b_a12, LinearLayout b_a13, LinearLayout b_a14, LinearLayout b_a15, LinearLayout b_a16, LinearLayout b_a17, LinearLayout b_a18, LinearLayout b_a19, LinearLayout b_a2, LinearLayout b_a20, LinearLayout b_a21, LinearLayout b_a3, LinearLayout b_a4, LinearLayout b_a5, LinearLayout b_a6, LinearLayout b_a7, LinearLayout b_a8, LinearLayout b_a9, ImageView r, LinearLayout b_r, LinearLayout b_e1, LinearLayout b_e2, LinearLayout b_e3, LinearLayout b_e4){

        //change background color of the containers to green
        b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a1.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a5.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a6.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a7.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a8.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a9.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a10.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a11.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a12.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a13.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a14.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a15.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a16.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a17.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a18.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a19.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a20.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_a21.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));

        //objects for approval sounds
        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;

        //load sound based on generated value and redirect to the next screen
        switch (eventNumber) {
            case 1:
                correct1.start();
                correct1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent pot = new Intent(getApplicationContext(), potato.class);
                        startActivity(pot);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent pot = new Intent(getApplicationContext(), potato.class);
                        startActivity(pot);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent pot = new Intent(getApplicationContext(), potato.class);
                        startActivity(pot);
                    }
                });
                break;
            default:
                //do nothing
                break;
        }
    }

    //disapprovals
    public void disapproved(ImageView a1, ImageView a10, ImageView a11, ImageView a12, ImageView a13, ImageView a14, ImageView a15, ImageView a16, ImageView a17, ImageView a18, ImageView a19, ImageView a2, ImageView a20, ImageView a21, ImageView a3, ImageView a4, ImageView a5, ImageView a6, ImageView a7, ImageView a8, ImageView a9, ImageView e1, ImageView e2, ImageView e3, ImageView e4, LinearLayout b_a1, LinearLayout b_a10, LinearLayout b_a11, LinearLayout b_a12, LinearLayout b_a13, LinearLayout b_a14, LinearLayout b_a15, LinearLayout b_a16, LinearLayout b_a17, LinearLayout b_a18, LinearLayout b_a19, LinearLayout b_a2, LinearLayout b_a20, LinearLayout b_a21, LinearLayout b_a3, LinearLayout b_a4, LinearLayout b_a5, LinearLayout b_a6, LinearLayout b_a7, LinearLayout b_a8, LinearLayout b_a9, ImageView r, LinearLayout b_r, LinearLayout b_e1, LinearLayout b_e2, LinearLayout b_e3, LinearLayout b_e4){
        //objects for disapproval sounds
        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;


        //r
        if (r!= null) {
            b_r.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_r.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e1
        if (e1!= null){
            b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e3!= null){
            b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e1.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e2
        if (e2!= null){
            b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e4!= null){
            b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e4.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p1
        if (a1!= null){
            b_a1.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a6!= null){
            b_a6.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a8!= null){
            b_a8.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a10!= null){
            b_a10.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a13!= null){
            b_a13.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a16!= null){
            b_a16.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a19!= null){
            b_a19.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_a1.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p2
        if (a2!= null){
            b_a2.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a4!= null){
            b_a4.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a9!= null){
            b_a9.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a11!= null){
            b_a11.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a14!= null){
            b_a14.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a17!= null){
            b_a17.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a21!= null){
            b_a21.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_a2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p3
        if (a3!= null){
            b_a3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a5!= null){
            b_a5.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a7!= null){
            b_a7.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a12!= null){
            b_a12.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a15!= null){
            b_a15.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a18!= null){
            b_a18.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (a20!= null){
            b_a20.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_a3.setBackgroundColor(Color.parseColor("#FF0000"));
        }


        //load sound based on generated value
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
