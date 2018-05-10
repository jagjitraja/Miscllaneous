package com.example.musfiqrahman.samplecontentprovider.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by musfiqrahman on 2018-01-21.
 */

public class DictionaryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    /** The authority of this content provider. */
    public static final String AUTHORITY = "com.example.musfiqrahman.samplecontentprovider.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    public static final Uri DICTIONARY_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(DictionaryContract.DictionaryEntity.TABLE_NAME).build();

    private DictionaryContract() {}

    /* Inner class that defines the table contents */
    public static class DictionaryEntity implements BaseColumns {
        /** The name of the DictionaryEntry table. */
        public static final String TABLE_NAME = "dictionary";
        /** The name of the ID column. */
        public static final String COLUMN_ID = BaseColumns._ID;
        /** The name of the word column. */
        public static final String COLUMN_WORD = "word";
        /** The name of the meaning column. */
        public static final String COLUMN_MEANING = "meaning";
    }
}
