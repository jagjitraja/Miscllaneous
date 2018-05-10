package com.example.t00533766.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.ButtonBarLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {


    private Runnable activityRun = new Runnable() {
        @Override
        public void run() {
            Intent goToMain  = new Intent(getApplicationContext(),FullscreenActivity.class);
            startActivity(goToMain);
            finish();
            System.gc();
        }
    };

    private Handler activityHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Button goButton = (Button) findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityRun.run();
            }
        });
        activityHandler.postDelayed(activityRun,10000);
    }


}
