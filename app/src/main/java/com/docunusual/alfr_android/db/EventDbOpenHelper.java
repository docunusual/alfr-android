package com.docunusual.alfr_android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "events";
    public static final int DATABASE_VERSION = 1;

    public static final String EVENT_TABLE_NAME = "events";
    public static final String KEY_BY = "by";
    public static final String KEY_MIME = "mime";
    public static final String KEY_INLINE = "inline";
    public static final String EVENT_TABLE_CREATE =
            "CREATE TABLE " + EVENT_TABLE_NAME + " (" +
                    KEY_BY + " TEXT, " +
                    KEY_MIME + " TEXT, " +
                    KEY_INLINE + " TEXT);";

    public EventDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EVENT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
