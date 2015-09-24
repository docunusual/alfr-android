package com.docunusual.alfr_android.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.docunusual.alfr_android.api.Event;

import junit.framework.Assert;


public class EventsDbTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setContext(new RenamingDelegatingContext(getContext(), "test_"));
    }

    public void testDbEmpty() throws Exception {
        final AlfrDbOpenHelper alfrDbOpenHelper = new AlfrDbOpenHelper(getContext());
        Assert.assertEquals(0, alfrDbOpenHelper.getCount());
    }

    public void testInsertEvent() throws Exception {
        final Event event = new Event();
        event.setBy("Jim");
        event.setInline("Hello World");

        final AlfrDbOpenHelper alfrDbOpenHelper = new AlfrDbOpenHelper(getContext());
        final SQLiteDatabase database = alfrDbOpenHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(AlfrContract.Events.BY, event.getBy());
        values.put(AlfrContract.Events.INLINE, event.getInline());
        database.insert(AlfrDbOpenHelper.EVENTS_TABLE_NAME, null, values);

        Assert.assertEquals(1, alfrDbOpenHelper.getCount());
    }
}
