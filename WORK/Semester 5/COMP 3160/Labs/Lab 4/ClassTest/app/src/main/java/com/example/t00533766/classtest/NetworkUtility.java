package com.example.t00533766.classtest;

import android.net.Uri;
import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

/**
 * Created by T00533766 on 2/5/2018.
 */

public class NetworkUtility {

    private static final String TAG = "NETWORK UTILITY";

    private static final String BASE_URL = " https://www.googleapis.com/books/v1/volumes";
    private static final String QUERY_LABEL = "q";
    private static final String MAX_RESULTS_LABEL = "maxResults";
    private static Uri BASE_URI = Uri.parse(BASE_URL);

    private static final String JSON_ITEMS = "items";
    private static final String JSON_VOLUME_INFO = "volumeInfo";
    private static final String JSON_AUTHORS = "authors";
    private static final String JSON_TITLE = "title";

    public static String getBookInfo(String param) {

        Uri searchUri = BASE_URI.buildUpon()
                .appendQueryParameter(QUERY_LABEL, param)
                .appendQueryParameter(MAX_RESULTS_LABEL, "5").build();


        HttpURLConnection urlConnection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer JSONBuffer = null;

        try {
            URL url = new URL(searchUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d(TAG, "getBookInfo: "+ urlConnection.toString());
            int response = urlConnection.getResponseCode();
            inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            JSONBuffer = new StringBuffer();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                JSONBuffer.append(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return parseJSON(JSONBuffer.toString());
    }



    public static String parseJSON(String json) {

        String title = null;
        String auths = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ITEMS);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject book = jsonArray.getJSONObject(i);
                JSONObject volume_info = book.getJSONObject(JSON_VOLUME_INFO);
                title = volume_info.getString(JSON_TITLE);
                JSONArray authors = volume_info.getJSONArray(JSON_AUTHORS);
                auths = "";
                for (int j = 0; j < authors.length(); j++) {
                    auths += j+") "+ authors.getString(j) +" \n";
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return title + " \n " + auths;
    }
}
