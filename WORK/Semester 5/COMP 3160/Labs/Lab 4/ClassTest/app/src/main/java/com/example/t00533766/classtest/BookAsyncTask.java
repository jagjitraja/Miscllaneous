package com.example.t00533766.classtest;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

import static com.example.t00533766.classtest.MainActivity.FILE_NAME;
import static com.example.t00533766.classtest.MainActivity.TAG;

/**
 * Created by T00533766 on 2/5/2018.
 */

public class BookAsyncTask extends AsyncTask<String,Void,String> {

    private Context context;

    public BookAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String response = NetworkUtility.getBookInfo(strings[0]);
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            context.deleteFile(FILE_NAME);
            //DELETE THE FILE IF IT EXISTS

            FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            fileOutputStream.write(s.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
