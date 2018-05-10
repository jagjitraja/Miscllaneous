package com.example.musfiqrahman.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * This is the Repository class for the Words. Words are stored in an internal SQLite database,
 * this class act as a mediator between the ViewModel class and Room database.
 *
 * Created by musfiqrahman on 2018-01-17.
 */


public class WordRepository {
    // Reference to the the Word Data Access Object
    private WordDao wordDao;

    // Reference to the LiveData wordList coming from Room database
    private LiveData<List<Word>> mAllwords;

    /**
     * Constructor.
     * @param app the application for the context
     */
    WordRepository(Application app){
        // T-ODO (WR1) get a WordRoomDatabase instance and initialize the wordDao from the database
        wordDao = WordRoomDatabase.getDatabase(app.getApplicationContext()).wordDao();
        mAllwords = wordDao.getAll();
    }

    public LiveData<List<Word>> getAllWords(){

        return wordDao.getAll();
    }

    public void insertWord(Word word){
        // T-ODO (WR3) insert Word using AsyncTask class
        InsertAsyncTast asyncTast = new InsertAsyncTast(wordDao);
        asyncTast.execute(word);
    }


    private static class InsertAsyncTast extends AsyncTask<Word, Void, Void>{
        private WordDao wordDao;
        InsertAsyncTast(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            // T-ODO (WR4) call the insert method from the word dao and pass the word
            wordDao.insert(words[0]);
            return null;
        }
    }
}
