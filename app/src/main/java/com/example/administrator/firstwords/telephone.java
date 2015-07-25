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

public class telephone extends Activity implements View.OnTouchListener, View.OnDragListener {

    MediaPlayer mMediaPlayer, correct1, correct2, correct3, incorr1, incorr2, incorr3;
    SoundPool sound, click;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mMediaPlayer = MediaPlayer.create(this, R.raw.telephone);
        mMediaPlayer.start();

        //object for the sound
        sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = sound.load(this, R.raw.telephone, 1);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        //letters
        findViewById(R.id.telephone_t).setOnTouchListener(this);
        findViewById(R.id.telephone_e).setOnTouchListener(this);
        findViewById(R.id.telephone_l).setOnTouchListener(this);
        findViewById(R.id.telephone_e2).setOnTouchListener(this);
        findViewById(R.id.telephone_p).setOnTouchListener(this);
        findViewById(R.id.telephone_h).setOnTouchListener(this);
        findViewById(R.id.telephone_o).setOnTouchListener(this);
        findViewById(R.id.telephone_n).setOnTouchListener(this);
        findViewById(R.id.telephone_e3).setOnTouchListener(this);

        //bottom containers drag listener
        findViewById(R.id.bottom_t).setOnDragListener(this);
        findViewById(R.id.bottom_e).setOnDragListener(this);
        findViewById(R.id.bottom_l).setOnDragListener(this);
        findViewById(R.id.bottom_e2).setOnDragListener(this);
        findViewById(R.id.bottom_p).setOnDragListener(this);
        findViewById(R.id.bottom_h).setOnDragListener(this);
        findViewById(R.id.bottom_o).setOnDragListener(this);
        findViewById(R.id.bottom_n).setOnDragListener(this);
        findViewById(R.id.bottom_e3).setOnDragListener(this);

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

        LinearLayout b_t = (LinearLayout)findViewById(R.id.bottom_t);
        ImageView t = (ImageView) b_t.findViewById(R.id.telephone_t);

        LinearLayout b_l = (LinearLayout)findViewById(R.id.bottom_l);
        ImageView l = (ImageView) b_l.findViewById(R.id.telephone_l);

        LinearLayout b_p = (LinearLayout)findViewById(R.id.bottom_p);
        ImageView p = (ImageView) b_p.findViewById(R.id.telephone_p);

        LinearLayout b_h = (LinearLayout)findViewById(R.id.bottom_h);
        ImageView h = (ImageView) b_h.findViewById(R.id.telephone_h);

        LinearLayout b_o = (LinearLayout)findViewById(R.id.bottom_o);
        ImageView o = (ImageView) b_o.findViewById(R.id.telephone_o);

        LinearLayout b_n = (LinearLayout)findViewById(R.id.bottom_n);
        ImageView n = (ImageView) b_n.findViewById(R.id.telephone_n);

        //e1------------------------------------------------------------

        LinearLayout b_e1 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e1 = (ImageView) b_e1.findViewById(R.id.telephone_e);

        LinearLayout b_e2 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e2 = (ImageView) b_e2.findViewById(R.id.telephone_e2);

        LinearLayout b_e3 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e3 = (ImageView) b_e3.findViewById(R.id.telephone_e3);

        //e2-------------------------------------------------------------

        LinearLayout b_e4 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e4 = (ImageView) b_e4.findViewById(R.id.telephone_e);

        LinearLayout b_e5 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e5 = (ImageView) b_e5.findViewById(R.id.telephone_e2);

        LinearLayout b_e6 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e6 = (ImageView) b_e6.findViewById(R.id.telephone_e3);

        //e3-------------------------------------------------------------

        LinearLayout b_e7 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e7 = (ImageView) b_e7.findViewById(R.id.telephone_e);

        LinearLayout b_e8 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e8 = (ImageView) b_e8.findViewById(R.id.telephone_e2);

        LinearLayout b_e9 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e9 = (ImageView) b_e9.findViewById(R.id.telephone_e3);

        //e4-------------------------------------------------------------

        LinearLayout b_e10 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e10 = (ImageView) b_e10.findViewById(R.id.telephone_e);

        LinearLayout b_e11 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e11 = (ImageView) b_e11.findViewById(R.id.telephone_e2);

        LinearLayout b_e12 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e12 = (ImageView) b_e12.findViewById(R.id.telephone_e3);

        //e5-------------------------------------------------------------

        LinearLayout b_e13 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e13 = (ImageView) b_e13.findViewById(R.id.telephone_e3);

        LinearLayout b_e14 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e14 = (ImageView) b_e14.findViewById(R.id.telephone_e2);

        LinearLayout b_e15 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e15 = (ImageView) b_e15.findViewById(R.id.telephone_e);

        //e6-------------------------------------------------------------

        LinearLayout b_e16 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e16 = (ImageView) b_e16.findViewById(R.id.telephone_e2);

        LinearLayout b_e17 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e17 = (ImageView) b_e17.findViewById(R.id.telephone_e);

        LinearLayout b_e18 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e18 = (ImageView) b_e18.findViewById(R.id.telephone_e3);

        //a7-------------------------------------------------------------

        LinearLayout b_e19 = (LinearLayout) findViewById(R.id.bottom_e);
        ImageView e19 = (ImageView) b_e19.findViewById(R.id.telephone_e);

        LinearLayout b_e20 = (LinearLayout) findViewById(R.id.bottom_e3);
        ImageView e20 = (ImageView) b_e20.findViewById(R.id.telephone_e2);

        LinearLayout b_e21 = (LinearLayout) findViewById(R.id.bottom_e2);
        ImageView e21 = (ImageView) b_e21.findViewById(R.id.telephone_e3);


//approval / disapproval sounds
        correct1 = MediaPlayer.create(this, R.raw.welldone);
        correct2 = MediaPlayer.create(this, R.raw.congrats);
        correct3 = MediaPlayer.create(this, R.raw.didit);

        incorr1 = MediaPlayer.create(this, R.raw.rusure);
        incorr2 = MediaPlayer.create(this, R.raw.incorrect);
        incorr3 = MediaPlayer.create(this, R.raw.tryagain);


        //redirection to the methods if the letters are properly ordered
        if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (n != null) && ((e1!= null) && (e2!= null) && (e3!= null))){
            approved(t,l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (n != null) && ((e4!= null) && (e5!= null) && (e6!= null))){
            approved(t, l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (n != null) && ((e7!= null) && (e8!= null) && (e9!= null))) {
            approved(t, l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (n != null) && ((e10!= null) && (e11!= null) && (e12!= null))){
            approved(t, l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && (n != null) && ((e13!= null) && (e14!= null) && (e15!= null))){
            approved(t, l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && ((e16!= null) && (e17!= null) && (e18!= null))){
            approved(t, l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else if ((t != null) && (l != null) && (p != null)&& (h != null) && (o != null) && ((e19!= null) && (e20!= null) && (e21!= null))){
            approved(t, l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
        else {
            disapproved(t,l, p, h, o, n, e1, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e2, e20, e21, e3, e4, e5, e6, e7, e8, e9, b_t, b_l, b_p, b_h, b_o, b_n, b_e1, b_e10, b_e11, b_e12, b_e13, b_e14, b_e15, b_e16, b_e17, b_e18, b_e19, b_e2, b_e20, b_e21, b_e3, b_e4, b_e5, b_e6, b_e7, b_e8, b_e9);
        }
    }

    //approvals
    public void approved(ImageView t, ImageView l, ImageView p, ImageView h, ImageView o, ImageView n, ImageView e1, ImageView e10, ImageView e11, ImageView e12, ImageView e13, ImageView e14, ImageView e15, ImageView e16, ImageView e17, ImageView e18, ImageView e19, ImageView e2, ImageView e20, ImageView e21, ImageView e3, ImageView e4, ImageView e5, ImageView e6, ImageView e7, ImageView e8, ImageView e9, LinearLayout b_t, LinearLayout b_l, LinearLayout b_p, LinearLayout b_h, LinearLayout b_o, LinearLayout b_n, LinearLayout b_e1, LinearLayout b_e10, LinearLayout b_e11, LinearLayout b_e12, LinearLayout b_e13, LinearLayout b_e14, LinearLayout b_e15, LinearLayout b_e16, LinearLayout b_e17, LinearLayout b_e18, LinearLayout b_e19, LinearLayout b_e2, LinearLayout b_e20, LinearLayout b_e21, LinearLayout b_e3, LinearLayout b_e4, LinearLayout b_e5, LinearLayout b_e6, LinearLayout b_e7, LinearLayout b_e8, LinearLayout b_e9){


        //change background color of the containers to green
        b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_n.setBackgroundColor(Color.parseColor("#8BC34A"));

        b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e2.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e4.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e5.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e6.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e7.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e8.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e9.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e10.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e11.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e12.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e13.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e14.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e15.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e16.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e17.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e18.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e19.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e20.setBackgroundColor(Color.parseColor("#8BC34A"));
        b_e21.setBackgroundColor(Color.parseColor("#8BC34A"));


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
                        Intent toaster = new Intent(getApplicationContext(), toaster.class);
                        startActivity(toaster);
                    }
                });
                break;
            case 2:
                correct2.start();
                correct2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent toaster = new Intent(getApplicationContext(), toaster.class);
                        startActivity(toaster);
                    }
                });
                break;
            case 3:
                correct3.start();
                correct3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        finish();
                        Intent toaster = new Intent(getApplicationContext(), toaster.class);
                        startActivity(toaster);
                    }
                });
                break;
            default:
                //do nothing
                break;
        }
    }

    //disapprovals
    public void disapproved(ImageView t, ImageView l, ImageView p, ImageView h, ImageView o, ImageView n, ImageView e1, ImageView e10, ImageView e11, ImageView e12, ImageView e13, ImageView e14, ImageView e15, ImageView e16, ImageView e17, ImageView e18, ImageView e19, ImageView e2, ImageView e20, ImageView e21, ImageView e3, ImageView e4, ImageView e5, ImageView e6, ImageView e7, ImageView e8, ImageView e9, LinearLayout b_t, LinearLayout b_l, LinearLayout b_p, LinearLayout b_h, LinearLayout b_o, LinearLayout b_n, LinearLayout b_e1, LinearLayout b_e10, LinearLayout b_e11, LinearLayout b_e12, LinearLayout b_e13, LinearLayout b_e14, LinearLayout b_e15, LinearLayout b_e16, LinearLayout b_e17, LinearLayout b_e18, LinearLayout b_e19, LinearLayout b_e2, LinearLayout b_e20, LinearLayout b_e21, LinearLayout b_e3, LinearLayout b_e4, LinearLayout b_e5, LinearLayout b_e6, LinearLayout b_e7, LinearLayout b_e8, LinearLayout b_e9){

        //sounds random generator
        Random generate = new Random();
        int eventNumber = generate.nextInt(3) + 1;


        //t
        if (t!= null) {
            b_t.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_t.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //l
        if (l!= null){
            b_l.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_l.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //p
        if (p!= null){
            b_p.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_p.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //h
        if (h!= null){
            b_h.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_h.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //o
        if (o!= null){
            b_o.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_o.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //n
        if (n!= null){
            b_n.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_n.setBackgroundColor(Color.parseColor("#FF0000"));
        }


        //e1
        if (e1!= null){
            b_e1.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e6!= null){
            b_e6.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e8!= null){
            b_e8.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e10!= null){
            b_e10.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e13!= null){
            b_e13.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e16!= null){
            b_e16.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e19!= null){
            b_e19.setBackgroundColor(Color.parseColor("#8BC34A"));
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
        else if (e9!= null){
            b_e9.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e11!= null){
            b_e11.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e14!= null){
            b_e14.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e17!= null){
            b_e17.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e21!= null){
            b_e21.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        //e3
        if (e3!= null){
            b_e3.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e5!= null){
            b_e5.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e7!= null){
            b_e7.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e12!= null){
            b_e12.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e15!= null){
            b_e15.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e18!= null){
            b_e18.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else if (e20!= null){
            b_e20.setBackgroundColor(Color.parseColor("#8BC34A"));
        }
        else {
            b_e3.setBackgroundColor(Color.parseColor("#FF0000"));
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
