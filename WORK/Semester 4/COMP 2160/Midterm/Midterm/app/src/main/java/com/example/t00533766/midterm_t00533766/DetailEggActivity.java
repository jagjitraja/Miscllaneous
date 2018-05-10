package com.example.t00533766.midterm_t00533766;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailEggActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_egg);

        Intent intent = getIntent();
        int count = intent.getIntExtra(MainActivity.COUNT_KEY,0);

        TextView textView = (TextView) findViewById(R.id.egg_details);

        double dozens = count/12;
        if(dozens==1) {
            textView.setText("You have about " + (int)dozens + " dozen of eggs");
        }else{

            textView.setText("You have about " + (int)dozens + " dozens of eggs");
        }
    }
}
