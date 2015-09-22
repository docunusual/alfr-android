package com.docunusual.alfr_android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import junit.framework.Assert;


public class EventDbTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setContext(new IsolatedContext(null, getContext()));
    }

    public void testDbEmpty() throws Exception {
        final EventDbOpenHelper eventDbOpenHelper = new EventDbOpenHelper(getContext());
        final SQLiteDatabase database = eventDbOpenHelper.getReadableDatabase();
        final Cursor cursor = database.query(
                EventDbOpenHelper.EVENT_TABLE_NAME,
                new String[]{"count(*)"},
                null, null, null, null, null);
        cursor.moveToFirst();
        Assert.assertEquals(0, cursor.getInt(0));
    }

}
