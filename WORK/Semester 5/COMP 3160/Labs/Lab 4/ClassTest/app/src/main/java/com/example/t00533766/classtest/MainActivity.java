package com.example.t00533766.classtest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "books.txt";
    private BookFileObserver fileObserver;
    public static final String TAG = "*******************************";
    private BookViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!fileExists()){
            try {
                FileOutputStream fileOutputStream = openFileOutput(FILE_NAME,MODE_PRIVATE);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getNetworkInfo(connectivityManager.getActiveNetwork());

        Button button = findViewById(R.id.search_button);
        final EditText editText = findViewById(R.id.title_entry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(networkInfo !=null && networkInfo.isConnected()){
                    getData(editText.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        viewModel.init(getApplicationContext());
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String stringLiveData) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(stringLiveData);
            }

        };
        viewModel.getStringLiveData().observe(this,observer);

        fileObserver = new BookFileObserver(getFilesDir().getPath());
        fileObserver.setViewModel(viewModel);
        fileObserver.startWatching();
    }


    public static String readFile(Context context){

        StringBuilder file = new StringBuilder();
        try {
            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line = "";
            while (line!=null){
                line = reader.readLine();
                file.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString();
    }
    private boolean fileExists() {
        File file = getFileStreamPath(FILE_NAME);
        return file.exists();
    }

    private void getData(String s){
        try {
            new BookAsyncTask(getApplicationContext()).execute(s).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileObserver.stopWatching();
    }

}














