package com.example.musfiqrahman.samplecontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.musfiqrahman.samplecontentprovider.data.DictionaryContract;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryDao;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryDatabase;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryEntry;

import java.util.ArrayList;

/**
 * Created by musfiqrahman on 2018-01-21.
 */

/**
 * A {@link ContentProvider} based on a Room database.
 *
 * <p>Note that you don't need to implement a ContentProvider unless you want to expose the data
 * outside your process or your application already uses a ContentProvider.</p>
 */

public class SampleDictionaryProvider extends ContentProvider {



    private DictionaryDatabase dictionaryDatabase;

    /** The match code for some items in the Dictionary table. */
    private static final int CODE_DICTIONARY_DIR = 1;

    /** The match code for an item in the Dictionary table. */
    private static final int CODE_DICTIONARY_ITEM = 2;

    /** The URI matcher. */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        MATCHER.addURI(DictionaryContract.AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME, CODE_DICTIONARY_DIR);
        MATCHER.addURI(DictionaryContract.AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME + "/*", CODE_DICTIONARY_ITEM);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context==null){
            return false;
        }
        dictionaryDatabase = DictionaryDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Cursor cursor = null;

        if(MATCHER.match(uri) == CODE_DICTIONARY_ITEM){

            cursor = dictionaryDatabase.dictionaryDao().selectById(Long.parseLong(uri.getLastPathSegment()));
            //cursor = dictionaryDatabase.dictionaryDao().selectById(ContentUris.parseId(uri));

        }else if(MATCHER.match(uri) == CODE_DICTIONARY_DIR){

            cursor = dictionaryDatabase.dictionaryDao().selectAll();
        }

        //sends a noification back to the content resolver calling this provider
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if(MATCHER.match(uri)==CODE_DICTIONARY_DIR){
            return "dictionary_word";
        }else{
            return "N/A";
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        DictionaryEntry entry = DictionaryEntry.fromContentValues(contentValues);
        long id = 0;
        if(MATCHER.match(uri) == CODE_DICTIONARY_DIR) {
            id = dictionaryDatabase.dictionaryDao().insert(entry);
        }
        //Append the id of the newly inserted row to the URI
        return ContentUris.withAppendedId(uri,id);
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        if(MATCHER.match(uri) == CODE_DICTIONARY_ITEM){

            dictionaryDatabase.dictionaryDao().deleteById(Long.parseLong(uri.getLastPathSegment()));
            //dictionaryDatabase.dictionaryDao().deleteById(ContentUris.parseId(uri));
        }else if(MATCHER.match(uri) == CODE_DICTIONARY_DIR){

           return -1;
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        if(MATCHER.match(uri) == CODE_DICTIONARY_ITEM){

            dictionaryDatabase.dictionaryDao().update(DictionaryEntry.fromContentValues(contentValues));

        }else if(MATCHER.match(uri) == CODE_DICTIONARY_DIR){
            return -1;
        }
        return 0;
    }


    // Need to override six methods.
}
