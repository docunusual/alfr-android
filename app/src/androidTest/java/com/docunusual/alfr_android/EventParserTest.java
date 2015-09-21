package com.docunusual.alfr_android;

import com.docunusual.alfr_android.api.Content;
import com.docunusual.alfr_android.api.Event;
import com.google.gson.Gson;

import junit.framework.Assert;
import junit.framework.TestCase;


public class EventParserTest extends TestCase {

    public static final String EVENT_1 = "{'by':'Tommy','content':[{'mime':'text/plain','inline':'Hey Jim!'}]}";

    public void testParserEmptyEvent() throws Exception {
        final Event event = new Gson().fromJson("{}", Event.class);
        Assert.assertNotNull(event);
    }

    public void testParserSimpleEvent() {
        final Event event = new Gson().fromJson(EVENT_1, Event.class);
        Assert.assertEquals("Tommy", event.getBy());
        final Content content = event.getContent().get(0);
        Assert.assertEquals("text/plain", content.getMime());
        Assert.assertEquals("Hey Jim!", content.getInline());
    }
}
