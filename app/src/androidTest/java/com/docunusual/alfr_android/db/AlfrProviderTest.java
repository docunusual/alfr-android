package com.docunusual.alfr_android.db;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

public class AlfrProviderTest extends ProviderTestCase2<AlfrProvider> {

    private MockContentResolver mockResolver;

    public AlfrProviderTest() {
        super(AlfrProvider.class, AlfrContract.AUTHORITY);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockResolver = getMockContentResolver();
    }

    public void testEventsInsert__inserts_a_valid_record() {
        Uri uri = mockResolver.insert(AlfrContract.Events.CONTENT_URI, getEventsContentValues());
        assertEquals(1L, ContentUris.parseId(uri));
    }

    public void testActiveUserInsert__cursor_contains_valid_data() {
        mockResolver.insert(AlfrContract.Events.CONTENT_URI, getEventsContentValues());
        Cursor cursor = mockResolver.query(AlfrContract.Events.CONTENT_URI, null, null, new String[]{}, null);
        assertNotNull(cursor);
        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        assertEquals("Jimmy", cursor.getString(cursor.getColumnIndex(AlfrContract.Events.BY)));
        assertEquals("Hi!", cursor.getString(cursor.getColumnIndex(AlfrContract.Events.INLINE)));
    }

    private ContentValues getEventsContentValues() {
        final ContentValues values = new ContentValues();
        values.put(AlfrContract.Events.BY, "Jimmy");
        values.put(AlfrContract.Events.INLINE, "Hi!");
        return values;
    }
}
