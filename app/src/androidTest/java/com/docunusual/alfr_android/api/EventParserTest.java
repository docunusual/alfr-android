package com.docunusual.alfr_android.api;

import com.docunusual.alfr_android.TestData;
import com.google.gson.Gson;

import junit.framework.Assert;
import junit.framework.TestCase;


public class EventParserTest extends TestCase {

    public void testParserEmptyEvent() throws Exception {
        final Event event = new Gson().fromJson("{}", Event.class);
        Assert.assertNotNull(event);
    }

    public void testParserSimpleEvent() {
        final Event event = new Gson().fromJson(TestData.event("Tommy", "Hey Jim!"), Event.class);
        Assert.assertEquals("Tommy", event.getBy());
        Assert.assertEquals("Hey Jim!", event.getInline());
    }
}
