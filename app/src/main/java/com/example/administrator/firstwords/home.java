package com.example.administrator.firstwords;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


public class home extends Activity{

    SoundPool sound, click;
    int soundID;
    final Context context = this;
    ImageButton quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //object for the check sound
        click = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = click.load(this, R.raw.click, 1);

        quit = (ImageButton) findViewById(R.id.btn_quit);
        quit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                click.play(soundID, 1, 1, 1, 0, 1);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Quit the game ?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click \"Yes\" to exit\nClick \"No\" to get back to game")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close the app
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    public void play(View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        String cat = sharedPreferences.getString("category", "");

        switch (cat){
            case "3":
                finish();
                Intent cat3 = new Intent(this, categories3.class);
                startActivity(cat3);
                break;
            case "2":
                finish();
                Intent cat2 = new Intent(this, categories2.class);
                startActivity(cat2);
                break;
            default:
                finish();
                Intent cat1 = new Intent(this, categories.class);
                startActivity(cat1);
                break;
        }

    }
    public void info(View view) {

        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent home = new Intent(this, info.class);
        startActivity(home);
    }
}
