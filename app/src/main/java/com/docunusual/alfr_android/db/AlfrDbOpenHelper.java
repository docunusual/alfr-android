package com.docunusual.alfr_android.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlfrDbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "alfrdb";
    public static final int DATABASE_VERSION = 2;

    public static final String EVENTS_TABLE_NAME = "events";
    public static final String EVENTS_TABLE_CREATE =
            "CREATE TABLE " + EVENTS_TABLE_NAME + " (" +
                    AlfrContract.Events._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AlfrContract.Events.COLUMN_UUID + " TEXT, " +
                    AlfrContract.Events.COLUMN_SYNCED + " INTEGER, " +
                    AlfrContract.Events.COLUMN_BY + " TEXT, " +
                    AlfrContract.Events.COLUMN_INLINE + " TEXT);";

    public AlfrDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EVENTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion) {
            case 2:
                db.execSQL("ALTER TABLE events ADD COLUMN uuid;");
                db.execSQL("ALTER TABLE events ADD COLUMN synced;");
                break;
        }
    }

    public int getCount() {
        final Cursor cursor = getReadableDatabase().query(
                AlfrDbOpenHelper.EVENTS_TABLE_NAME,
                new String[]{"count(*)"},
                null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

}
