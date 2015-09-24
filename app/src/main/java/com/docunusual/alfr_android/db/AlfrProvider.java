package com.docunusual.alfr_android.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class AlfrProvider extends ContentProvider {

    private static final UriMatcher uriMatcher;

    private static final int EVENTS_ALLROWS = 1;
    private static final int EVENTS_SINGLE_ROW = 2;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AlfrContract.AUTHORITY, "events", EVENTS_ALLROWS);
        uriMatcher.addURI(AlfrContract.AUTHORITY, "events/#", EVENTS_SINGLE_ROW);
    }

    private AlfrDbOpenHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new AlfrDbOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (uriMatcher.match(uri)) {
            case EVENTS_ALLROWS:
                qb.setTables(AlfrDbOpenHelper.EVENTS_TABLE_NAME);
                break;

            case EVENTS_SINGLE_ROW:
                qb.setTables(AlfrDbOpenHelper.EVENTS_TABLE_NAME);
                qb.appendWhere(AlfrContract.Events._ID + " = " + uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        final Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id;
        switch (uriMatcher.match(uri)) {
            case EVENTS_ALLROWS:
                id = db.insertOrThrow(AlfrDbOpenHelper.EVENTS_TABLE_NAME, null, values);
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        final Uri insertUri = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(insertUri, null);
        return insertUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
