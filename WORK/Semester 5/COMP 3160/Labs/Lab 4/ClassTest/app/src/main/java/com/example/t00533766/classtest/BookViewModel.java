package com.example.t00533766.classtest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import static com.example.t00533766.classtest.MainActivity.TAG;

/**
 * Created by T00533766 on 2/5/2018.
 */

public class BookViewModel extends ViewModel{

    MutableLiveData<String> stringLiveData;
    private Context context;

    public BookViewModel(){

    }

    public void init(Context context){
        this.context = context;
    }
    public LiveData<String> getStringLiveData(){
        if(stringLiveData==null){
            stringLiveData = new MutableLiveData<>();
        }
        stringLiveData.postValue(MainActivity.readFile(context));

        return stringLiveData;
    }

    public void setStringLiveData(){
        stringLiveData.postValue(MainActivity.readFile(context));
    }

}
