package com.example.t00533766.midterm_t00533766;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String SHARED_KEY = "sharedPrefs";
    public static final String COUNT_KEY = "count";
    private int count = 0;
    private SharedPreferences sharedPreferences;
    private TextView textView;
    private Button addButton;
    private Button reduceButton;

    private View.OnClickListener goToDetails = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,DetailEggActivity.class);
            intent.putExtra(COUNT_KEY,count);
            startActivity(intent);
        }
    };

    private View.OnClickListener addCount = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count++;
            textView.setText(count + "");
        }
    };

    private View.OnClickListener reduceCount = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (count>0) {
                count--;
                textView.setText(count + "");
            }
            else{
                Toast.makeText(getApplicationContext(), R.string.egg_warning,Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_KEY,MODE_PRIVATE);
        count = sharedPreferences.getInt(COUNT_KEY,0);

        textView = (TextView) findViewById(R.id.egg_image);
        addButton = (Button) findViewById(R.id.add);
        reduceButton = (Button) findViewById(R.id.reduce);

        textView.setText(count+"");
        textView.setOnClickListener(goToDetails);
        addButton.setOnClickListener(addCount);
        reduceButton.setOnClickListener(reduceCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COUNT_KEY,count);
        editor.apply();
        editor.commit();
    }
}
