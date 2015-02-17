package com.rickgoldman.speechmaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AboutScreen extends Activity {

    // About screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.aboutlayout);
        final Context context = this;

        rlayout.setOnClickListener(new View.OnClickListener() {



            @Override
           public void onClick(View v) {

               Intent intent = new Intent(context, VoiceRecognitionActivity.class);
               startActivity(intent);

           }

       });
    }

}