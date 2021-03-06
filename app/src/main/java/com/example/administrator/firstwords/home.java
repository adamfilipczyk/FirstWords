package com.example.administrator.firstwords;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * @author Adam Filipczyk
 */

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
                        .setMessage("\"Yes\" - exit game\n\"No\" - back to game")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close the app
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

                // create alert dialog box
                AlertDialog alertDialog = alertDialogBuilder.create();

                // display dialog box
                alertDialog.show();
            }
        });


    }

    public void info(View view) {

        click.play(soundID, 1, 1, 1, 0, 1);
        finish();
        Intent home = new Intent(this, info.class);
        startActivity(home);
    }


    public void youtube (View view) {

        click.play(soundID, 1, 1, 1, 0, 1);
        Uri uri = Uri.parse("http://youtu.be/BH4b-k8fePE");
        finish();
        Intent utube = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(utube);
    }


    public void play(View view) {

        click.play(soundID, 1, 1, 1, 0, 1);

        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        String cat = sharedPreferences.getString("category", "");

        switch (cat){
            case "11":
                finish();
                Intent cat11 = new Intent(this, categories11.class);
                startActivity(cat11);
                break;
            case "10":
                finish();
                Intent cat10 = new Intent(this, categories10.class);
                startActivity(cat10);
                break;
            case "9":
                finish();
                Intent cat9 = new Intent(this, categories9.class);
                startActivity(cat9);
                break;
            case "8":
                finish();
                Intent cat8 = new Intent(this, categories8.class);
                startActivity(cat8);
                break;
            case "7":
                finish();
                Intent cat7 = new Intent(this, categories7.class);
                startActivity(cat7);
                break;
            case "6":
                finish();
                Intent cat6 = new Intent(this, categories6.class);
                startActivity(cat6);
                break;
            case "5":
                finish();
                Intent cat5 = new Intent(this, categories5.class);
                startActivity(cat5);
                break;
            case "4":
                finish();
                Intent cat4 = new Intent(this, categories4.class);
                startActivity(cat4);
                break;
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
                //sharedfPreferences file initial check
                SharedPreferences initSharedPref = getSharedPreferences("Save", Context.MODE_PRIVATE);
                String var = initSharedPref.getString("category", "");
                //if empty save "1"
                if (var.isEmpty()){
                    SharedPreferences sharedPreferences1 = getSharedPreferences("Save", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.putString("category", "1");
                    editor1.commit();
                    finish();
                    Intent cat1 = new Intent(this, categories.class);
                    startActivity(cat1);
                }
                else {
                    finish();
                    Intent cat1 = new Intent(this, categories.class);
                    startActivity(cat1);
                }
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }
}
