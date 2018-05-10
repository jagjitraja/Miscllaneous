package com.example.t00533766.classtest;

import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.t00533766.classtest.MainActivity.TAG;

/**
 * Created by T00533766 on 2/14/2018.
 */

public class BookFileObserver extends FileObserver {


    BookViewModel viewModel;

    public BookFileObserver(String path) {
        super(path);
    }

    @Override
    public void onEvent(int i, @Nullable String s) {
        if(i==MODIFY) {
            Log.d(TAG, "onEvent: " + i);
            viewModel.setStringLiveData();
        }
    }

    public void setViewModel(BookViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
